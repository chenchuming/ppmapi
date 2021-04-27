package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.InputParams;
import io.swagger.model.Report;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.proteininformationresource.ppm.service.MatchManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-25T21:01:43.979Z[GMT]")
@RestController
@CrossOrigin
@PropertySource({ "classpath:ppm.properties", "classpath:prosite.properties" })
public class PeptideApiController implements PeptideApi {

	@Autowired
	public Environment env;
	
    private static final Logger log = LoggerFactory.getLogger(PeptideApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PeptideApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Report> matchByPeptide(@NotNull @Parameter(in = ParameterIn.QUERY, description = "A protein sequence pattern, it can be a peptide, a user defined pattern, a prosite accesion or identifier and combination of above in logical expressions." ,required=true,schema=@Schema()) @Valid @RequestParam(value = "query", required = true) String query,@NotNull @Parameter(in = ParameterIn.QUERY, description = "Search UniProtKB - SwissProt" ,required=true,schema=@Schema( defaultValue="true")) @Valid @RequestParam(value = "searchSwissProt", required = true, defaultValue="true") Boolean searchSwissProt,@NotNull @Parameter(in = ParameterIn.QUERY, description = "Include Protein Isoforms" ,required=true,schema=@Schema( defaultValue="true")) @Valid @RequestParam(value = "includeIsoforms", required = true, defaultValue="true") Boolean includeIsoforms,@NotNull @Parameter(in = ParameterIn.QUERY, description = "Search UniProtKB - TrEMBL" ,required=true,schema=@Schema( defaultValue="false")) @Valid @RequestParam(value = "searchTrEMBL", required = true, defaultValue="false") Boolean searchTrEMBL,@NotNull @Parameter(in = ParameterIn.QUERY, description = "Search UniProtKB - UniRef100" ,required=true,schema=@Schema( defaultValue="false")) @Valid @RequestParam(value = "searchUniRef100", required = true, defaultValue="false") Boolean searchUniRef100,@Parameter(in = ParameterIn.QUERY, description = "A list of comma-separated NCBI taxonomy IDs" ,schema=@Schema( defaultValue="9606")) @Valid @RequestParam(value = "org", required = false, defaultValue="9606") String org,@Min(0)@Parameter(in = ParameterIn.QUERY, description = "The number of records to skip." ,schema=@Schema(allowableValues={  }
)) @Valid @RequestParam(value = "offset", required = false) Integer offset,@Min(1) @Max(1000) @Parameter(in = ParameterIn.QUERY, description = "The numbers of records to return (max. 100)." ,schema=@Schema(allowableValues={  }, minimum="1", maximum="1000"
)) @Valid @RequestParam(value = "limit", required = false) Integer limit) {
    	InputParams inputParams = new InputParams();
    	inputParams.setQuery(query);
    	inputParams.setSp(searchSwissProt);
    	inputParams.setTr(searchTrEMBL);
    	inputParams.setIsoform(includeIsoforms);
    	inputParams.setUniref100(searchUniRef100);
    	inputParams.setOffset(offset);
    	inputParams.setLimit(limit);
    	
    	MatchManager matchManager = new MatchManager(env, inputParams);

    	Report report = matchManager.solrSearch();
    	report.setInputParams(inputParams);
    	    	
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	
            	report.setInputParams(inputParams);
            	if(report.getMessage() != null) {
            		//System.out.println("I am here has message");
            		return new ResponseEntity<Report>(HttpStatus.INTERNAL_SERVER_ERROR);
            	}
            	else {
            		//System.out.println("I am here has no message");
            		return new ResponseEntity<Report>(report, HttpStatus.OK);
            	}
            } catch (Exception e) {
            	e.printStackTrace();
            	StringWriter sw = new StringWriter();
            	PrintWriter pw = new PrintWriter(sw);
            	e.printStackTrace(pw);
            	report.setMessage(pw.toString());
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Report>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
        if (accept != null && accept.contains("application/xml")) {
            try {
            	report.setInputParams(inputParams);
            	if(report.getMessage() != null) {
            		return new ResponseEntity<Report>(HttpStatus.INTERNAL_SERVER_ERROR);
            	}
            	else {
            		return new ResponseEntity<Report>(report, HttpStatus.OK);
            	}
            } catch (Exception e) {
            	e.printStackTrace();
            	StringWriter sw = new StringWriter();
            	PrintWriter pw = new PrintWriter(sw);
            	e.printStackTrace(pw);
            	report.setMessage(pw.toString());
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Report>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Report>(HttpStatus.NOT_IMPLEMENTED);
    }

}
