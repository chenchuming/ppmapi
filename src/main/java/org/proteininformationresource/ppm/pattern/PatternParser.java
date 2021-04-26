package org.proteininformationresource.ppm.pattern;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Properties;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.NamedList;
import org.proteininformationresource.ppm.service.Node;
import org.proteininformationresource.ppm.service.Params;
import org.proteininformationresource.ppm.service.SolrResponseHeader;
import org.proteininformationresource.ppm.support.web.Message;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
public class PatternParser {
	private static List<String> allPaths = new ArrayList<String>();
	private static boolean debug = false;

	public static void main(String[] args) {
		// String pattern = "<[AC](2)-x-V-G(1,2)-x(1,2)-{ED}-{AE}(0,2)";
		// String pattern = "P-A-P-D-x(0,4)-G>";
		// String pattern = "PAP(2,4)DCAD{ASDA}{AAS}(2,3)Dx(0,100)G(2)";
		// String pattern =
		// "<[LIVM]{VIC}(0,2)x(2)G[DENQTA]X(1)[GAC]X(2)[LIVMFY](4)X(2)G";
		// String pattern
		// ="[LIVM]-[VIC]-X(2)-G-[DENQTA]-X-[GAC]-X(2)-[LIVMFY](4)-X(2)-G";
		// String pattern ="[LIVM][VIC]X(2)G[DENQTA]X[GAC]X(2)[LIVMFY](4)X(2)G";
		// String pattern = "<MTERS";
		// String pattern = "MYDTL";
		// String pattern = "<{C|D}*>";
		String pattern = "<A-x-[ST](2)-x(0,1)-V";
		// String pattern = "[AC]-x-V-x(4)-{ED}";
		// String pattern = "IIRIFHLRNI";
		// String pattern = "M L L M";
		// String pattern =
		// "[LIVM] [VIC] x (2) G [DENQTA] x [GAC] x (2) [LIVMFY] (4) x (2) G";
		// String pattern =
		// "[LIVM]-[VIC]-x(2) -G-[DENQTA]-x-[GAC]-x(2) -[LIVMFY](4)-x(2)-G";
		// String pattern ="MAS";
		// String pattern = "N-{P}-[ST]-{P}";
		PatternParserResult result = parsePattern(pattern);
		if (result.getLog().getMessage() == null) {
			//searchSolr(result.getParsedPattern());
		}
	}

	public static PatternParserResult parsePattern(String pattern) {
		//System.out.println("input to my pattern parser: " + pattern);
		if (pattern.contains(" <--> ")) {
			String[] ps = pattern.split(" <--> ");
			pattern = ps[1];
		}
		PatternParserResult parserResult = new PatternParserResult();
		allPaths = new ArrayList<String>();
		String[] elements = null;
		String query = "";
		Node<String> root = new Node<>("");
		List<Node<String>> parents = new ArrayList<Node<String>>();
		parents.add(root);
		Pattern p;
		Matcher m;
		boolean match;
		boolean hasInvalidPattern = false;
		String invalidPattern = "";
		if (debug) {
			System.out.println("Input: " + pattern);
		}
		pattern = pattern.replaceAll(" ", "");
		if (pattern.contains("*")) {
			p = Pattern.compile("(<)(\\{.*\\})(\\*)(>)");
			m = p.matcher(pattern);
			if (m.matches()) {
				String excluded = pattern.replaceAll("^\\<\\{", "").replaceAll("\\}\\*\\>$", "");
				// query = "{!complexphrase inOrder=true} -seq:(\"/" + excluded
				// + "/\")";
				query = " -(\"/" + excluded + "/\")";
				if (debug) {
					System.out.println(query);
				}
				// searchSolr(query);
			}

		} else {
			if (pattern.contains("-")) {
				elements = pattern.split("-");
			} else {
				elements = processCompactPattern(pattern);
			}
			if (debug) {

				for (int i = 0; i < elements.length; i++) {
					if (i == 0) {

						System.out.print(elements[i]);
					} else {
						System.out.print("-" + elements[i]);
					}
				}
				System.out.println();
			}

			for (int i = 0; i < elements.length; i++) {
				match = false;
				// System.out.println(elements[i] + " | " + parents);
				if (elements[i].contains("X") || elements[i].contains("x")) {
					// Match x or X repeat range
					p = Pattern.compile("(<)?(x|X)\\((\\d+)\\,(\\d+)\\)(>)?");
					m = p.matcher(elements[i]);
					if (m.matches()) {
						// System.out.println(elements[i]);
						processRepeatXRange(parents, m);
						match = true;
					}
					// Match x or X repeat
					p = Pattern.compile("(<)?\\s*(x|X)\\((\\d+)\\)\\s*(>)?");
					m = p.matcher(elements[i]);
					if (m.matches()) {
						processRepeatX(parents, m);
						match = true;
					}
					// Match x or X
					p = Pattern.compile("(<)?\\s*(x|X)\\s*(>)?");
					m = p.matcher(elements[i]);
					if (m.matches()) {
						processSingleX(parents, m);
						match = true;
					}
				} else {
					if (elements[i].contains("[") && elements[i].contains("]")) {
						// Match ambiguities accept repeat range
						p = Pattern.compile("(<)?\\s*(\\[\\w+\\])\\((\\d)\\)\\s*(>)?");
						m = p.matcher(elements[i]);
						if (m.matches()) {
							processAmbiguityAcceptRepeat(parents, m);
							match = true;
						}
						// Match ambiguities accept repeat
						p = Pattern.compile("(<)?\\s*(\\[\\w+\\])\\((\\d)\\,(\\d)\\)\\s*(>)?");
						m = p.matcher(elements[i]);
						if (m.matches()) {
							processAmbiguityAcceptRepeatRange(parents, m);
							match = true;
						}

						// Match ambiguities accept
						p = Pattern.compile("(<)?\\s*(\\[\\w+\\])\\s*(>)?");
						m = p.matcher(elements[i]);
						if (m.matches()) {
							processAmbiguityAccept(parents, m);
							match = true;
						}
					} else if (elements[i].contains("{") && elements[i].contains("}")) {
						// Match ambiguities except repeat range
						p = Pattern.compile("(<)?\\s*(\\{\\w+\\})\\((\\d+)\\,(\\d+)\\)\\s*(>)?");
						m = p.matcher(elements[i]);
						if (m.matches()) {
							processAmbiguityExceptRepeatRange(parents, m);
							match = true;
						}
						// Match ambiguities except repeat
						p = Pattern.compile("(<)?\\s*(\\{\\w+\\})\\((\\d+)\\)\\s*(>)?");
						m = p.matcher(elements[i]);
						if (m.matches()) {
							processAmbiguityExceptRepeat(parents, m);
							match = true;
						}

						// Match ambiguities except
						p = Pattern.compile("(<)?\\s*(\\{\\w+\\})\\s*(>)?");
						m = p.matcher(elements[i]);
						if (m.matches()) {
							processAmbiguityExcept(parents, m);
							match = true;
						}
					} else {
						// Match single amino acid repeat
						p = Pattern.compile("(<)?\\s*(\\w)\\((\\d+)\\,(\\d+)\\)\\s*(>)?");
						m = p.matcher(elements[i]);
						if (m.matches()) {
							processSingleAminoAcidRepeatRange(parents, m);
							match = true;
						}
						// Match single amino acid repeat
						p = Pattern.compile("(<)?\\s*(\\w)\\((\\d+)\\)\\s*(>)?");
						m = p.matcher(elements[i]);
						if (m.matches()) {
							processSingleAminoAcidRepeat(parents, m);
							match = true;
						}
						// Match single amino acid
						p = Pattern.compile("(<)?\\s*(\\w)\\s*(>)?");
						m = p.matcher(elements[i]);
						if (m.matches()) {
							// System.out.println("??? "+ elements[i]);
							processSingleAminoAcid(parents, m);
							match = true;
						}
					}
				}

				if (match == false) {
					//System.out.println("Invalid pattern: " + elements[i]);
					hasInvalidPattern = true;
					invalidPattern = "Invalid pattern for \""+pattern+"\" at position " + (i+1) + ": '"+ elements[i]+"'.";
				}
			}

			if (!hasInvalidPattern) {
				showLeafPaths(root, new StringBuilder());
				// query = "{!complexphrase inOrder=true} seq:(";
				query = "(";
				for (int i = 0; i < allPaths.size(); i++) {
					String path = allPaths.get(i).replaceAll("\\s+", " ").replace("<", "NT").replace(">", "CT");
					if (debug) {
						System.out.println("Path " + (i + 1) + ": " + path);
					}
					if (i == 0) {
						query += "\"" + path + "\"";
					} else {
						query += " OR \"" + path + "\"";
					}
				}
				query += ")";
				// System.out.println(query);
				// searchSolr(query);
				parserResult.setParsedPattern("seq: "+query);
			} else {
				Message message = new Message(invalidPattern, Message.Type.DANGER);
				parserResult.setLog(message);
			}
		}
		if(debug) {
			System.out.println(pattern + " <--> " + parserResult);
		}
		return parserResult;

	}

	private static String[] processCompactPattern(String pattern) {
		List<String> elements = new ArrayList<String>();
		boolean hasNT = false;
		boolean hasCT = false;
		if (pattern.startsWith("<")) {
			hasNT = true;
			pattern = pattern.replaceAll("^<", "");
		}
		if (pattern.endsWith(">")) {
			hasCT = true;
			pattern = pattern.replaceAll(">$", "");
		}
		String[] chars = pattern.split("");
		String lookahead = "";
		String current = "";
		Stack<String> stack = new Stack<String>();

		for (int i = 0; i < chars.length; i++) {

			current = chars[i];
			if (i < chars.length - 1) {
				lookahead = chars[i + 1];
			} else {
				lookahead = null;
			}

			// System.out.println("current: "+ current + " | lookahead: " +
			// lookahead);
			if (lookahead != null) {

				if (lookahead.equals("(") /* || lookahead.equals("[") */) {
					stack.push(current);
					// System.out.println("pushing " + current);
				} else if (current.equals("{") || current.equals("[")) {
					stack.push(current);
					// System.out.println("pushing " + current);
				} else if (current.equals(")") || current.equals("}") || current.equals("]")) {
					stack.push(current);
					// System.out.println("pushing " + current);
					String temp = "";
					while (!stack.empty()) {
						String top = stack.pop();
						temp = top + temp;
						// System.out.println("poping " + top);
					}
					if (singleAA(lookahead) || lookahead.equals("[") || lookahead.equals("{")) {
						elements.add(temp);
					} else {
						stack.push(temp);
						// System.out.println("pushing " + temp);
					}
				} else {
					if (stack.empty()) {
						elements.add(current);
					} else {
						stack.push(current);
						// System.out.println("pushing " + current);
					}
				}
			} else {
				if (current.equals(")") || current.equals("}") || current.equals("]")) {
					stack.push(current);
					// System.out.println("pushing " + current);
					String temp = "";
					while (!stack.empty()) {
						String top = stack.pop();
						temp = top + temp;
						// System.out.println("poping " + top);
					}
					elements.add(temp);
				} else {
					if (stack.empty()) {
						elements.add(current);
					} else {
						stack.push(current);
						// System.out.println("pushing " + current);
					}
				}
			}
		}
		// System.out.println(elements);
		String[] arr = new String[elements.size()];
		if (hasNT) {
			elements.set(0, "<" + elements.get(0));
		}
		if (hasCT) {
			int size = elements.size();
			elements.set(size - 1, elements.get(size - 1) + ">");
		}
		for (int j = 0; j < elements.size(); j++) {
			arr[j] = elements.get(j);
		}
		return arr;
	}

	private static boolean singleAA(String current) {
		String regex = "^[a-zA-Z]$";
		// System.out.println("|"+current+"|");
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(current);

		return matcher.matches();
	}

	private static boolean nonSingleAA(String current) {
		String regex = "^[^a-zA-Z]$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(current);

		return matcher.matches();
	}
/*
	private static void searchSolr(String query) {
		// TODO Auto-generated method stub
		final SolrClient client = getSolrClient();

		final SolrQuery solrQuery = new SolrQuery(query);
		solrQuery.setParam("hl", "on");
		solrQuery.setParam("hl.fl", "seq");
		solrQuery.setParam("hl.fragsize", "100000");
		solrQuery.setParam("start", "0");
		solrQuery.setParam("rows", "50");
		solrQuery.setParam("facet", "on");
		solrQuery.setParam("facet.sort", "count");
		solrQuery.setParam("facet.field", "taxongroupNameID");
		solrQuery.add("facet.field", "organismNameID");
		solrQuery.add("facet.field", "isSP");
		solrQuery.add("facet.field", "isIsoform");
		solrQuery.add("facet.field", "uniref100");
		// solrQuery.addFilterQuery("fullLineage:1224");
		// solrQuery.addFilterQuery("taxongroupID:1239 or taxongroupID: 68525");
		QueryResponse queryResponse;
		try {
			System.out.println("SolrQuery: " + solrQuery);
			queryResponse = client.query("peptidematch", solrQuery, METHOD.POST);
			// response = client.query("peptidematch", queryParams);
			System.out.println(queryResponse);

			// get query response header
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
			System.out.println(respHeader);
			// get query response
			SolrResponse response = new SolrResponse();
			response.setNumFound(queryResponse.getResults().getNumFound());
			response.setMaxScore(queryResponse.getResults().getMaxScore());
			response.setStart(queryResponse.getResults().getStart());
			response.setDocs(getDocs(queryResponse.getResults()));
			// System.out.println("????"+queryResponse);

			// get facet info
			System.out.println(queryResponse.getFacetFields());
			DBStats dbStats = new DBStats();
			long isoformCount = 0;
			long uniref100Count = 0;
			List<TaxonStats> taxongroupStats = new ArrayList<TaxonStats>();
			List<TaxonStats> organismStats = new ArrayList<TaxonStats>();
			List<FacetField> facetFields = queryResponse.getFacetFields();
			for (FacetField facet : facetFields) {
				if (facet.getName().equals("isSP")) {
					long tr = facet.getValues().get(0).getCount();
					long sp = facet.getValues().get(1).getCount();
					dbStats.setSwissProt(sp);
					dbStats.setTrEMBL(tr);
				}
				if (facet.getName().equals("isIsoform")) {
					isoformCount = facet.getValues().get(1).getCount();
					dbStats.setIsoform(isoformCount);
				}
				if (facet.getName().equals("uniref100")) {
					uniref100Count = facet.getValues().get(1).getCount();
					dbStats.setUniref100(uniref100Count);
				}
				if (facet.getName().equals("taxongroupNameID")) {
					List<Count> counts = facet.getValues();
					for (Count count : counts) {
						TaxonStats stats = new TaxonStats();
						String[] taxon = count.getName().split("\\^\\|\\^");
						stats.setTaxonId(Integer.parseInt(taxon[0]));
						stats.setTaxonName(taxon[1]);
						stats.setCount(count.getCount());
						taxongroupStats.add(stats);
					}
				}
				if (facet.getName().equals("organismNameID")) {
					List<Count> counts = facet.getValues();
					for (Count count : counts) {
						TaxonStats stats = new TaxonStats();
						String[] taxon = count.getName().split("\\^\\|\\^");
						stats.setTaxonId(Integer.parseInt(taxon[1]));
						stats.setTaxonName(taxon[0]);
						stats.setCount(count.getCount());
						organismStats.add(stats);
					}
				}
			}
			System.out.println(dbStats);
			// System.out.println("isformCount: " + isoformCount);
			System.out.println("TaxonGroupStats: " + taxongroupStats);
			System.out.println("OrganismStats: " + organismStats);

			List<HighLight> highLightings = new ArrayList<HighLight>();
			// get highlighting
			for (String name : queryResponse.getHighlighting().keySet()) {
				HighLight hl = new HighLight();
				hl.setId(name);
				if (queryResponse.getHighlighting().get(name).get("seq") != null) {
					hl.setSeq(queryResponse.getHighlighting().get(name).get("seq").get(0).replaceAll("^<", "").replaceAll(">$", "").replaceAll(" ", ""));
				}
				highLightings.add(hl);
			}
			System.out.println(highLightings);
			client.close();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static List<Doc> getDocs(SolrDocumentList results) {
		List<Doc> docs = new ArrayList<Doc>();
		for (SolrDocument doc : results) {
			Doc entry = new Doc();
			entry.setId((String) doc.getFieldValue("id"));
			entry.setProteinID((String) doc.getFieldValue("proteinID"));
			entry.setProteinName((String) doc.getFieldValue("proteinName"));
			entry.setOrganismName((String) doc.getFieldValue("organismName"));
			entry.setOrganismID((int) doc.getFieldValue("organismID"));
			entry.setOrganismNameID((String) doc.getFieldValue("organismNameID"));
			entry.setTaxongroupName((String) doc.getFieldValue("taxongroupName"));
			entry.setTaxongroupID((int) doc.getFieldValue("taxongroupID"));
			entry.setTaxongroupNameID((String) doc.getFieldValue("taxongroupNameID"));
			entry.setFullLineage(getLineage(doc.getFieldValue("fullLineage")));
			entry.setShortLineage(getLineage(doc.getFieldValue("shortLineage")));
			entry.setUniref100((String) doc.getFieldValue("uniref100"));
			entry.setSP((boolean) doc.getFieldValue("isSP"));
			entry.setIsoform((boolean) doc.getFieldValue("isIsoform"));
			entry.set_version_((long) doc.getFieldValue("_version_"));
			@SuppressWarnings("unchecked")
			List<String> seq = (List<String>) doc.getFieldValue("seq");
			// System.out.println("|||"+seq.get(0)+"|||");
			entry.setSeq(seq.get(0).replaceAll("^NT", "").replaceAll("CT$", "").replaceAll(" ", ""));
			// System.out.println(entry.getSeq());
			docs.add(entry);
		}
		return docs;
	}
*/
	private static List<Integer> getLineage(Object fieldValue) {
		String[] lineages = ((String) fieldValue).split(", ");
		List<Integer> lineageList = new ArrayList<Integer>();
		for (int i = 0; i < lineages.length; i++) {
			Integer taxonId = Integer.parseInt(lineages[i]);
			lineageList.add(taxonId);
		}
		return lineageList;
	}

	private static Params getResponseParams(NamedList responseHeader) {
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

	private static SolrClient getSolrClient() {
		String solrUrl = null; //"http://localhost:8983/solr";
		String zkHostString = "localhost:9983/solr";
		int solrConnectionTimeout = 10000000;
		int solrSocketTimeout = 60000000;
		Resource resource = new ClassPathResource("/ppm.properties");
		try {
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			solrUrl = (String) props.get("solrUrl");
			solrConnectionTimeout = Integer.parseInt(props.getProperty("solrConnectionTimeout"));
			solrSocketTimeout = Integer.parseInt(props.getProperty("solrSocketTimeout"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//final String solrUrl = env.getProperty("solrUrl");
		// System.out.println(solrUrl);
		//return new CloudSolrClient.Builder().withSolrUrl(solrUrl).withConnectionTimeout(solrConnectionTimeout).withSocketTimeout(solrSocketTimeout).build();
		//return new CloudSolrClient.Builder().withZkHost(zkHostString).withConnectionTimeout(solrConnectionTimeout).withSocketTimeout(solrSocketTimeout).build();
		
		
		return new HttpSolrClient.Builder(solrUrl).withConnectionTimeout(10000000).withSocketTimeout(60000000).build();
	}

	private static void processAmbiguityExcept(List<Node<String>> parents, Matcher m) {
		String expr = "/[^" + m.group(2).replace("{", "").replace("}", "").replaceAll("", "|").replaceAll("^\\|", "").replaceAll("\\|$", "") + "]/";

		if (m.group(1) != null) {
			expr = "< " + expr;
		}
		if (m.group(3) != null) {
			expr = expr.trim() + " >";
		}
		addChildAndUpdateParents(parents, expr);
	}

	private static void processAmbiguityExceptRepeat(List<Node<String>> parents, Matcher m) {
		String expr = "";
		String ambiguity = "/[^" + m.group(2).replace("{", "").replace("}", "").replaceAll("", "|").replaceAll("^\\|", "").replaceAll("\\|$", "") + "]/";
		for (int j = 0; j < Integer.parseInt(m.group(3)); j++) {
			if (j == 0) {
				expr += ambiguity;
			} else {
				expr += " " + ambiguity;
			}
		}
		expr.trim();
		if (m.group(1) != null) {
			expr = "< " + expr;
		}
		if (m.group(4) != null) {
			expr = expr.trim() + " >";
		}
		addChildAndUpdateParents(parents, expr);
	}

	private static void processAmbiguityAccept(List<Node<String>> parents, Matcher m) {
		String expr = "/" + m.group(2).replace("[", "").replace("]", "").replaceAll("", "|").replaceAll("^\\|", "").replaceAll("\\|$", "") + "/";

		if (m.group(1) != null) {
			expr = "< " + expr;
		}
		if (m.group(3) != null) {
			expr = expr.trim() + " >";
		}
		addChildAndUpdateParents(parents, expr);
	}

	private static void processSingleAminoAcidRepeatRange(List<Node<String>> parents, Matcher m) {
		int least = Integer.parseInt(m.group(3));
		int most = Integer.parseInt(m.group(4));
		int originalParentSize = parents.size();
		for (int k = 0; k < originalParentSize; k++) {
			Node<String> parent = parents.get(k);
			for (int i = least; i <= most; i++) {
				String expr = "";
				for (int j = 0; j < i; j++) {
					if (j == 0) {
						expr += m.group(2);
					} else {
						expr += " " + m.group(2);
					}
				}
				expr = expr.trim();

				if (m.group(1) != null) {
					expr = "< " + expr;
				}
				if (m.group(5) != null) {
					expr = expr.trim() + " >";
				}
				Node<String> child = new Node<>(expr);
				if (expr.length() > 0) {
					parent.addChild(child);
					parents.add(child);
				}
			}
			if (least > 0) {
				parents.remove(parent);
			}
		}

	}

	private static void processAmbiguityExceptRepeatRange(List<Node<String>> parents, Matcher m) {
		int least = Integer.parseInt(m.group(3));
		int most = Integer.parseInt(m.group(4));
		int originalParentSize = parents.size();
		for (int k = 0; k < originalParentSize; k++) {
			Node<String> parent = parents.get(k);
			for (int i = least; i <= most; i++) {
				String expr = "";
				String ambiguity = "/[^" + m.group(2).replace("{", "").replace("}", "").replaceAll("", "|").replaceAll("^\\|", "").replaceAll("\\|$", "")
						+ "]/";
				for (int j = 0; j < i; j++) {
					if (j == 0) {
						expr += ambiguity;
					} else {
						expr += " " + ambiguity;
					}
				}
				expr.trim();
				if (m.group(1) != null) {
					expr = "< " + expr;
				}
				if (m.group(5) != null) {
					expr = expr.trim() + " >";
				}
				Node<String> child = new Node<>(expr);
				if (expr.length() > 0) {
					parent.addChild(child);
					parents.add(child);
				}
			}
			if (least > 0) {
				parents.remove(parent);
			}
		}

	}

	private static void processAmbiguityAcceptRepeatRange(List<Node<String>> parents, Matcher m) {
		int least = Integer.parseInt(m.group(3));
		int most = Integer.parseInt(m.group(4));
		int originalParentSize = parents.size();
		for (int k = 0; k < originalParentSize; k++) {
			Node<String> parent = parents.get(k);
			for (int i = least; i <= most; i++) {
				String expr = "";
				String ambiguity = "/" + m.group(2).replace("[", "").replace("]", "").replaceAll("", "|").replaceAll("^\\|", "").replaceAll("\\|$", "") + "/";
				for (int j = 0; j < i; j++) {
					if (j == 0) {
						expr += ambiguity;
					} else {
						expr += " " + ambiguity;
					}
				}
				expr.trim();
				if (m.group(1) != null) {
					expr = "< " + expr;
				}
				if (m.group(5) != null) {
					expr = expr.trim() + " >";
				}
				Node<String> child = new Node<>(expr);
				if (expr.length() > 0) {
					parent.addChild(child);
					parents.add(child);
				}
			}
			if (least > 0) {
				parents.remove(parent);
			}
		}

	}

	private static void processAmbiguityAcceptRepeat(List<Node<String>> parents, Matcher m) {
		String expr = "";
		String ambiguity = "/" + m.group(2).replace("[", "").replace("]", "").replaceAll("", "|").replaceAll("^\\|", "").replaceAll("\\|$", "") + "/";
		for (int j = 0; j < Integer.parseInt(m.group(3)); j++) {
			if (j == 0) {
				expr += ambiguity;
			} else {
				expr += " " + ambiguity;
			}
		}
		expr.trim();
		if (m.group(1) != null) {
			expr = "< " + expr;
		}
		if (m.group(4) != null) {
			expr = expr.trim() + " >";
		}
		addChildAndUpdateParents(parents, expr);
	}

	private static void processSingleAminoAcidRepeat(List<Node<String>> parents, Matcher m) {
		String expr = "";
		for (int j = 0; j < Integer.parseInt(m.group(3)); j++) {
			if (j == 0) {
				expr += m.group(2);
			} else {
				expr += " " + m.group(2);
			}
		}
		expr = expr.trim();

		if (m.group(1) != null) {
			expr = "< " + expr;
		}
		if (m.group(4) != null) {
			expr = expr.trim() + " >";
		}
		addChildAndUpdateParents(parents, expr);

	}

	private static void addChildAndUpdateParents(List<Node<String>> parents, String expr) {
		Node<String> child = new Node<>(expr);
		List<Node<String>> tempParents = new ArrayList<Node<String>>();

		int size = parents.size();
		for (int i = 0; i < size; i++) {
			Node<String> parent = parents.get(i);
			parent.addChild(child);
			// parents.remove(parent);
			tempParents.add(parent);
		}
		parents.removeAll(tempParents);
		parents.add(child);
	}

	private static void processSingleAminoAcid(List<Node<String>> parents, Matcher m) {
		String expr = m.group(2);
		if (m.group(1) != null) {
			expr = "< " + expr;
		}
		if (m.group(3) != null) {
			expr = expr.trim() + " >";
		}
		addChildAndUpdateParents(parents, expr);
	}

	private static void processRepeatXRange(List<Node<String>> parents, Matcher m) {
		int least = Integer.parseInt(m.group(3));
		int most = Integer.parseInt(m.group(4));
		int originalParentSize = parents.size();
		for (int k = 0; k < originalParentSize; k++) {
			Node<String> parent = parents.get(k);
			for (int i = least; i <= most; i++) {
				String expr = "";
				for (int j = 0; j < i; j++) {
					if (j == 0) {
						expr += "?";
					} else {
						expr += " ?";
					}
				}
				expr = expr.trim();

				if (m.group(1) != null) {
					expr = "< " + expr;
				}
				if (m.group(5) != null) {
					expr = expr.trim() + " >";
				}
				Node<String> child = new Node<>(expr);
				if (expr.length() > 0) {
					parent.addChild(child);
					parents.add(child);
				}
			}
			if (least > 0) {
				parents.remove(parent);
			}
		}
	}

	private static void processRepeatX(List<Node<String>> parents, Matcher m) {
		String expr = "";
		for (int j = 0; j < Integer.parseInt(m.group(3)); j++) {
			if (j == 0) {
				expr += "?";
			} else {
				expr += " ?";
			}
		}
		expr = expr.trim();

		if (m.group(1) != null) {
			expr = "< " + expr;
		}
		if (m.group(4) != null) {
			expr = expr.trim() + " >";
		}
		addChildAndUpdateParents(parents, expr);
	}

	private static void showLeafPaths(Node node, StringBuilder path) {
		final int pathLen = path.length();
		if (pathLen != 0) {
			path.append("");
		}
		String str = (String) node.getData();
		if (str.length() > 0) {
			path.append(" " + node.getData());
		} else {
			path.append(node.getData());
		}
		boolean hasChild = false;
		node.getChildren().forEach(each -> showLeafPaths((Node) each, path));
		if (node.getChildren().size() > 0) {
			hasChild = true;
		}
		if (!hasChild) {
			allPaths.add(path.toString().trim());
		}
		// System.out.println(path);
		path.setLength(pathLen);
	}

	private static void processSingleX(List<Node<String>> parents, Matcher m) {
		String expr = "?";

		if (m.group(1) != null) {
			expr = "< " + expr;
		}
		if (m.group(3) != null) {
			expr = expr.trim() + " >";
		}
		addChildAndUpdateParents(parents, expr);

	}
}
