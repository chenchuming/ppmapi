package org.proteininformationresource.ppm.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.NamedList;
import org.proteininformationresource.ppm.pattern.Expression;
import org.proteininformationresource.ppm.pattern.ExpressionParser;
import org.proteininformationresource.ppm.pattern.PatternParserResult;
import org.proteininformationresource.ppm.support.web.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.swagger.model.Report;
import io.swagger.model.SolrResponse;
import io.swagger.model.DBStats;
import io.swagger.model.OrganismStats;
import io.swagger.model.OrganismStatsInner;
import io.swagger.model.Protein;
import io.swagger.model.TaxonGroupStats;
import io.swagger.model.TaxonGroupStatsInner;
import io.swagger.model.Highlights;
import io.swagger.model.HighlightsInner;
import io.swagger.model.InputParams;

@Configuration
@Service("matchManager")
@Component
@PropertySource({ "classpath:ppm.properties", "classpath:prosite.properties" })
public class MatchManager {

	@Autowired
	private HttpServletRequest request;

	private Environment env;

	private InputParams queryParam;

	/**
	 * 
	 */
	public MatchManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param env
	 */
	public MatchManager(Environment env, InputParams queryParam) {
		super();
		this.env = env;

		this.queryParam = queryParam;
	}

	public Report solrSearch() {

		Report searchResult = new Report();
		final SolrClient client = getSolrClient();
		PatternParserResult result = null;
		result = checkQueryPattern(searchResult, result);

		// PatternParserResult result =
		// PatternParser.parsePattern(queryParam.getPattern());
		if (result.getLog() == null) {
			SolrQuery solrQuery = setSolrQueryParameters(result.getParsedPattern());
			QueryResponse queryResponse;
			try {
				System.out.println("SolrQuery 2: " + solrQuery);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				System.out.println("Before: " + timestamp);
				if (this.getQueryParam().isTr()) {
					queryResponse = client.query(env.getProperty("solrCollection"), solrQuery, METHOD.POST);
					System.out.println("solrCollection: " + env.getProperty("solrCollection"));
				} else {
					queryResponse = client.query(env.getProperty("solrCollectionSP"), solrQuery, METHOD.POST);
					System.out.println("solrCollection: " + env.getProperty("solrCollectionSP"));
				}

				timestamp = new Timestamp(System.currentTimeMillis());
				System.out.println("After: " + timestamp);
				// get query response header
				SolrResponseHeader respHeader = getSolrResponseHeader(queryResponse);
				//searchResult.setResponseHeader(respHeader);

				// get query response
				SolrResponse response = new SolrResponse();
				response.setTotalMatchedProteins((int)queryResponse.getResults().getNumFound());
				
				// response.setMaxScore(queryResponse.getResults().getMaxScore());
				//response.setStart(queryResponse.getResults().getStart());
				response.setCurrentMatchedProteinList(getMatchedProteins(queryResponse.getResults()));
			
				response.setQtime(BigDecimal.valueOf(respHeader.getqTime()));
				searchResult.setResponse(response);
				
				// get facet info
			//	 System.out.println(queryResponse.getFacetFields());
				List<FacetField> facetFields = queryResponse.getFacetFields();
				DBStats dbStats = getDBStats(facetFields);
				TaxonGroupStats taxongroupStats = getTaxonGroupStats(facetFields);
				OrganismStats organismStats = getOrganismStats(facetFields);
				searchResult.setDbStats(dbStats);
				searchResult.setOrganismStats(organismStats);
				searchResult.setTaxongroupStats(taxongroupStats);

//				System.out.println("DBStats: " + dbStats);
//				System.out.println("TaxonGroupStats: " + taxongroupStats);
//				System.out.println("OrganismStats: " + organismStats);

				Highlights highlights = getHighLightingMap(queryResponse);
				
				searchResult.setHighlights(highlights);
				client.close();
				
			} catch (Exception e) {
				System.out.println(e);
				Message message = new Message(e.getMessage(), Message.Type.DANGER);
				searchResult.setMessage(message.getMessage());
			}
		} else {
			searchResult.setMessage(result.getLog().getMessage());
		}

		//System.out.println("get results");
		return searchResult;
	}

	/**
	 * @return the queryParam
	 */
	public InputParams getQueryParam() {
		return queryParam;
	}

	/**
	 * @param queryParam the queryParam to set
	 */
	public void setQueryParam(InputParams queryParam) {
		this.queryParam = queryParam;
	}

	public PatternParserResult checkQueryPattern(Report searchResult, PatternParserResult result) {
		try {
			String query = queryParam.getQuery();
			System.out.println("input query: " + query);
			result = new PatternParserResult();
			ExpressionParser parser = new ExpressionParser(query.replaceAll("-", ""));
			Expression e = parser.parseExpression();
			System.out.println("solr query: " + e.toSolrQuery());
			if (e.toSolrQuery().contains("/") || e.toSolrQuery().contains("?")) {
				result.setParsedPattern("{!complexphrase inOrder=true} " + e.toSolrQuery());
			} else {
				result.setParsedPattern("" + e.toSolrQuery());
			}
			result.setInputPattern(e.toHTML());
		} catch (Exception ex) {
			System.out.println("NOK.");
			System.out.println(ex.getMessage());
			Message message = new Message(ex.getMessage(), Message.Type.DANGER);
			result.setLog(message);
		} catch (Error er) {
			System.out.println("Oops.");
			System.out.println(er.getMessage());
			Message message = new Message(er.getMessage(), Message.Type.DANGER);
			result.setLog(message);
		}
		return result;
	}

	

	private Highlights getHighLightingMap(QueryResponse queryResponse) {
		Highlights hls = new Highlights();
		// get highlighting
		for (String name : queryResponse.getHighlighting().keySet()) {
			HighlightsInner hl = new HighlightsInner();
			if (queryResponse.getHighlighting().get(name).get("seq") != null) {
				hl.setId(name);
				hl.setSeq(queryResponse.getHighlighting().get(name).get("seq").get(0).replaceAll("^NT", "").replaceAll("^<em>NT</em>", "")
						.replaceAll("CT$", "").replaceAll("<em>CT</em>$", "").replaceAll(" ", ""));
				hls.add(hl);
			}
//			if (queryResponse.getHighlighting().get(name).get("seq") != null) {
//				hl.setSeq(queryResponse.getHighlighting().get(name).get("seq").get(0).replaceAll("^NT", "").replaceAll("^<em>NT</em>", "")
//						.replaceAll("CT$", "").replaceAll("<em>CT</em>$", "").replaceAll(" ", ""));
//			}
//			highLightMap.put(hl.getId(), hl.getSeq());
		}
		return hls;
	}

	private SolrResponseHeader getSolrResponseHeader(QueryResponse queryResponse) {
		SolrResponseHeader respHeader = new SolrResponseHeader();
		NamedList responseHeader = queryResponse.getResponseHeader();
		for (int i = 0; i < responseHeader.size(); i++) {
			if (responseHeader.getName(i).equals("params")) {
				Params params = getResponseParams(responseHeader);
				respHeader.setParams(params);
			} else {
				if (responseHeader.getName(i).equals("zkconnected")) {
					respHeader.setZkConnected((boolean) responseHeader.getVal(i));
				}
				if (responseHeader.getName(i).equals("status")) {
					respHeader.setStatus((int) responseHeader.getVal(i));
				}
				if (responseHeader.getName(i).equals("QTime")) {
					respHeader.setQTime((int) responseHeader.getVal(i));
				}
			}
		}
		return respHeader;
	}

	private DBStats getDBStats(List<FacetField> facetFields) {
		DBStats dbStats = new DBStats();
		long isoformCount = 0;
		long uniref100Count = 0;
		for (FacetField facet : facetFields) {

			if (facet.getName().equals("isSP")) {
				long tr = 0;
				long sp = 0;
				List<Count> isSPCount = facet.getValues();
				for (Count count : isSPCount) {
					if (count.getName().equals("true")) {
						sp = count.getCount();
					}
					if (count.getName().equals("false")) {
						tr = count.getCount();
					}
				}
				dbStats.setNumSwissProt((int)sp);
				dbStats.setNumTrEMBL((int)tr);
			}
			if (facet.getName().equals("isIsoform")) {
				List<Count> isIsoformCount = facet.getValues();
				for (Count count : isIsoformCount) {
					if (count.getName().equals("true")) {
						isoformCount = count.getCount();
					}

				}
				dbStats.setNumIsoform((int)isoformCount);
			}
			if (facet.getName().equals("uniref100")) {
				List<Count> uniref100 = facet.getValues();
				for (Count c : uniref100) {
					uniref100Count += c.getCount();
				}
				dbStats.setNumUniRef100((int)uniref100Count);
			}
		}
		return dbStats;
	}

	private OrganismStats getOrganismStats(List<FacetField> facetFields) {
		OrganismStats organismStats = new OrganismStats();

		for (FacetField facet : facetFields) {
			if (facet.getName().equals("organismNameID")) {
				List<Count> counts = facet.getValues();
				for (Count count : counts) {
					OrganismStatsInner stats = new OrganismStatsInner();
					String[] taxon = count.getName().split("\\^\\|\\^");
					stats.setTaxonId(Integer.parseInt(taxon[1]));
					stats.setTaxonName(taxon[0]);
					stats.setCount((int)count.getCount());
					organismStats.add(stats);
				}
			}
		}
		return organismStats;
	}

	private TaxonGroupStats getTaxonGroupStats(List<FacetField> facetFields) {
		TaxonGroupStats taxongroupStats = new TaxonGroupStats();
		for (FacetField facet : facetFields) {

			if (facet.getName().equals("taxongroupNameID")) {
				List<Count> counts = facet.getValues();
				for (Count count : counts) {
					TaxonGroupStatsInner stats = new TaxonGroupStatsInner();
					String[] taxon = count.getName().split("\\^\\|\\^");
					stats.setTaxonId(Integer.parseInt(taxon[1]));
					stats.setTaxonName(taxon[0]);
					stats.setCount((int)count.getCount());
					taxongroupStats.add(stats);
				}
			}
		}
		return taxongroupStats;
	}

	private SolrQuery setSolrQueryParameters(String query) {
		final SolrQuery solrQuery = new SolrQuery(query);
		solrQuery.setParam("hl", "on");
		solrQuery.setParam("hl.fl", "seq");
		solrQuery.setParam("hl.fragsize", "100000");
		solrQuery.setParam("start", Integer.toString(queryParam.getOffset()));
		solrQuery.setParam("rows", Integer.toString(queryParam.getLimit()));
		solrQuery.setParam("facet", "on");
		solrQuery.setParam("facet.limit", "-1");
		solrQuery.setParam("facet.mincount", "1");
		solrQuery.setParam("facet.sort", "count");
		solrQuery.add("facet.field", "taxongroupNameID");
		solrQuery.add("facet.field", "organismNameID");
		solrQuery.add("facet.field", "isSP");
		solrQuery.add("facet.field", "isTR");
		solrQuery.add("facet.field", "isIsoform");
		solrQuery.add("facet.field", "uniref100");

		if (queryParam.getOrg() != null && !queryParam.getOrg().equals("all")) {
			String[] organismIDs = queryParam.getOrg().split(";");
			String orgFilterQuery = "organismID: (";
			for (String orgId : organismIDs) {
				orgFilterQuery += orgId + " ";
			}
			orgFilterQuery.trim();
			orgFilterQuery += ")";
			System.out.println("Filter Query organimsID: " + orgFilterQuery);
			solrQuery.addFilterQuery(orgFilterQuery);
		}
//		if (queryParam.getTaxonGroupId() != null) {
//			String[] taxonGroupIDs = queryParam.getTaxonGroupId().split(";");
//			String taxonGroupFilterQuery = "taxongroupID: (";
//			for (String orgId : taxonGroupIDs) {
//				taxonGroupFilterQuery += orgId + " ";
//			}
//			taxonGroupFilterQuery.trim();
//			taxonGroupFilterQuery += ")";
//			System.out.println("Filter Query taxonGroupID: " + taxonGroupFilterQuery);
//			solrQuery.addFilterQuery(taxonGroupFilterQuery);
//		}

		String isoformFilterQuery;
		if (queryParam.isIsoform()) {
			isoformFilterQuery = "isIsoform:true";
		} else {
			isoformFilterQuery = "isIsoform:false";
		}

		System.out.println("Filter Query Isoform: " + isoformFilterQuery);

		String spFilterQuery = "";
		if (queryParam.isSp()) {
			spFilterQuery = "isSP:true";
		} else {
			spFilterQuery = "isSP:false";
		}
		System.out.println("Filter Query SP: " + spFilterQuery);

		String trFilterQuery;
		if (queryParam.isTr()) {
			trFilterQuery = "isTR:true";
		} else {
			trFilterQuery = "isTR:false";
		}
		System.out.println("Filter Query TR: " + trFilterQuery);

		String uniref100FilterQuery = null;
		if (queryParam.isUniref100()) {
			uniref100FilterQuery = "uniref100:[* TO *]";
		}
		System.out.println("Filter Query UniRef100: " + uniref100FilterQuery);

		String filterQuery = "";
		if (spFilterQuery != null) {
			filterQuery += spFilterQuery + " OR ";
		}
		if (trFilterQuery != null) {
			filterQuery += trFilterQuery + " OR ";
		}
		if (isoformFilterQuery != null) {
			filterQuery += isoformFilterQuery + "";
		}
		if (uniref100FilterQuery != null) {
			filterQuery = "(" + filterQuery + ") AND " + uniref100FilterQuery;
		}

		System.out.println("Filter Query: " + filterQuery);
		solrQuery.addFilterQuery(filterQuery);

//		System.out.println("SortBy: " + queryParam.getSortBy());
//		String[] orderBy = queryParam.getSortBy().split(":");
//		if (orderBy[1].equals("asc")) {
//			solrQuery.setSort(orderBy[0], ORDER.asc);
//		}
//		if (orderBy[1].equals("desc")) {
//			solrQuery.setSort(orderBy[0], ORDER.desc);
//		}
//		System.out.println("Ordered Solr Query: " + solrQuery);
		return solrQuery;
	}

	

	private List<Protein> getMatchedProteins(SolrDocumentList results) {
		List<Protein> docs = new ArrayList<Protein>();
		for (SolrDocument doc : results) {
			Protein entry = new Protein();
			
			entry.setProteinAC((String) doc.getFieldValue("id"));
			entry.setProteinID((String) doc.getFieldValue("proteinID"));
			entry.setProteinName((String) doc.getFieldValue("proteinName"));
			entry.setOrganismName((String) doc.getFieldValue("organismName"));
			entry.setOrganismID((int) doc.getFieldValue("organismID"));
			entry.setTaxongroupName((String) doc.getFieldValue("taxongroupName"));
			entry.setTaxongroupID((int) doc.getFieldValue("taxongroupID"));
			entry.setFullLineage(getLineage(doc.getFieldValue("fullLineage")));
			entry.setShortLineage(getLineage(doc.getFieldValue("shortLineage")));
			entry.setUniref100((String) doc.getFieldValue("uniref100"));
		
			entry.setIsSP((boolean) doc.getFieldValue("isSP"));
			entry.setIsTR((boolean) doc.getFieldValue("isTR"));
			entry.setIsIsoform((boolean) doc.getFieldValue("isIsoform"));
			entry.setVersion(String.valueOf(doc.getFieldValue("_version_")));
			//System.out.println("I am here");
			entry.setLength((Integer) (doc.getFieldValue("length")));
			@SuppressWarnings("unchecked")
			List<String> seq = (List<String>) doc.getFieldValue("seq");
			// System.out.println("|||"+seq.get(0)+"|||");
			entry.setSeq(seq.get(0).replaceAll("^<", "").replaceAll(">$", "").replaceAll(" ", ""));
			// System.out.println(entry.getSeq());
			docs.add(entry);
		}
		
		return docs;
	}

	private List<Integer> getLineage(Object fieldValue) {
		String[] lineages = ((String) fieldValue).split(", ");
		List<Integer> lineageList = new ArrayList<Integer>();
		for (int i = 0; i < lineages.length; i++) {
			Integer taxonId = Integer.parseInt(lineages[i]);
			lineageList.add(taxonId);
		}
		return lineageList;
	}

	private Params getResponseParams(NamedList responseHeader) {
		Params params = new Params();
		NamedList paramsList = (NamedList) responseHeader.get("params");
		for (int j = 0; j < paramsList.size(); j++) {
			if (paramsList.getName(j).equals("q")) {
				params.setQ((String) paramsList.getVal(j));
			}
			if (paramsList.getName(j).equals("hl")) {
				params.setHl((String) paramsList.getVal(j));
			}
			if (paramsList.getName(j).equals("hl.fragsize")) {
				// System.out.println("hlFragSize: " +paramsList.getVal(j));
				params.setHlFragSize(Integer.parseInt((String) paramsList.getVal(j)));
			}
			if (paramsList.getName(j).equals("hl.fl")) {
				params.setHlFl((String) paramsList.getVal(j));
			}
			if (paramsList.getName(j).equals("start")) {
				params.setStart(Integer.parseInt((String) paramsList.getVal(j)));
			}
			if (paramsList.getName(j).equals("rows")) {
				params.setRows(Integer.parseInt((String) paramsList.getVal(j)));
			}
			if (paramsList.getName(j).equals("wt")) {
				params.setWt((String) paramsList.getVal(j));
			}
			if (paramsList.getName(j).equals("version")) {
				params.setVersion((String) paramsList.getVal(j));
			}
		}
		return params;
	}

	
	
	private SolrClient getSolrClient() {
		// CloudSolrClient.Builder().withSolrUrl("http://localhost:8983/solr").build();
		// System.out.println(env);
		String zkHostString = "localhost:9983/solr";
		final String solrUrl = env.getProperty("solrUrl");
		// System.out.println(solrUrl);
		final int solrConnectionTimeout = Integer.parseInt(env.getProperty("solrConnectionTimeout"));
		final int solrSocketTimeout = Integer.parseInt(env.getProperty("solrSocketTimeout"));
		return new HttpSolrClient.Builder(solrUrl).withConnectionTimeout(solrConnectionTimeout).withSocketTimeout(solrSocketTimeout).build();
		// return new
		// CloudSolrClient.Builder().withSolrUrl(solrUrl).withConnectionTimeout(solrConnectionTimeout).withSocketTimeout(solrSocketTimeout).build();
		// return new
		// CloudSolrClient.Builder().withZkHost(zkHostString).withConnectionTimeout(solrConnectionTimeout).withSocketTimeout(solrSocketTimeout).build();

	}

	/**
	 * @return the env
	 */
	public Environment getEnv() {
		return env;
	}

	/**
	 * @param env
	 *            the env to set
	 */
	public void setEnv(Environment env) {
		this.env = env;
	}


	/**
	 * @param queryParam
	 *            the queryParam to set
	 */
	
	private String wrapSeq(String seq, int length) {
		String fseq = "";
		String[] seqs = seq.split("");
		for (int i = 0; i < seqs.length; i++) {
			fseq += seqs[i];
			if ((i + 1) % length == 0) {
				fseq += "\n";
			}
		}
		return fseq;
	}

	private String wordwrap(final String input, final int length) {
		if (input == null || length < 1) {
			throw new IllegalArgumentException("Invalid input args");
		}

		final String text = input.trim();

		if (text.length() > length && text.contains(" ")) {
			final String line = text.substring(0, length);
			final int lineBreakIndex = line.indexOf("\n");
			final int lineLastSpaceIndex = line.lastIndexOf(" ");
			final int inputFirstSpaceIndex = text.indexOf(" ");

			final int breakIndex = lineBreakIndex > -1 ? lineBreakIndex : (lineLastSpaceIndex > -1 ? lineLastSpaceIndex : inputFirstSpaceIndex);

			return text.substring(0, breakIndex) + "\n" + wordwrap(text.substring(breakIndex + 1), length);
		} else {
			return text;
		}
	}
	


	private Map<String, Long> sortByValueAsc(Map<String, Long> map) {
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				if (((Map.Entry) (o2)).getValue() == ((Map.Entry) (o1)).getValue()) {
					return ((Comparable) ((Map.Entry) (o2)).getKey()).compareTo(((Map.Entry) (o1)).getKey());
				}
				return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		Map result = new LinkedHashMap();
		int count = 0;
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	private Map<String, Long> sortByValueDesc(Map<String, Long> map) {
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			// @Override
			@Override
			public int compare(Object o1, Object o2) {
				if (((Map.Entry) (o2)).getValue() == ((Map.Entry) (o1)).getValue()) {
					return ((Comparable) ((Map.Entry) (o2)).getKey()).compareTo(((Map.Entry) (o1)).getKey());
				}
				return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		Map result = new LinkedHashMap();
		int count = 0;
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

}
