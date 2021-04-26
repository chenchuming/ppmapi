package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.DBStats;
import io.swagger.model.Highlights;
import io.swagger.model.InputParams;
import io.swagger.model.OrganismStats;
import io.swagger.model.SolrResponse;
import io.swagger.model.TaxonGroupStats;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Protein pattern match report.
 */
@Schema(description = "Protein pattern match report.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-26T13:57:51.947Z[GMT]")


public class Report   {
  @JsonProperty("inputParams")
  private InputParams inputParams = null;

  @JsonProperty("response")
  private SolrResponse response = null;

  @JsonProperty("dbStats")
  private DBStats dbStats = null;

  @JsonProperty("organismStats")
  private OrganismStats organismStats = null;

  @JsonProperty("taxongroupStats")
  private TaxonGroupStats taxongroupStats = null;

  @JsonProperty("highlights")
  private Highlights highlights = null;

  @JsonProperty("message")
  private String message = null;

  public Report inputParams(InputParams inputParams) {
    this.inputParams = inputParams;
    return this;
  }

  /**
   * Get inputParams
   * @return inputParams
   **/
  @Schema(description = "")
  
    @Valid
    public InputParams getInputParams() {
    return inputParams;
  }

  public void setInputParams(InputParams inputParams) {
    this.inputParams = inputParams;
  }

  public Report response(SolrResponse response) {
    this.response = response;
    return this;
  }

  /**
   * Get response
   * @return response
   **/
  @Schema(description = "")
  
    @Valid
    public SolrResponse getResponse() {
    return response;
  }

  public void setResponse(SolrResponse response) {
    this.response = response;
  }

  public Report dbStats(DBStats dbStats) {
    this.dbStats = dbStats;
    return this;
  }

  /**
   * Get dbStats
   * @return dbStats
   **/
  @Schema(description = "")
  
    @Valid
    public DBStats getDbStats() {
    return dbStats;
  }

  public void setDbStats(DBStats dbStats) {
    this.dbStats = dbStats;
  }

  public Report organismStats(OrganismStats organismStats) {
    this.organismStats = organismStats;
    return this;
  }

  /**
   * Get organismStats
   * @return organismStats
   **/
  @Schema(description = "")
  
    @Valid
    public OrganismStats getOrganismStats() {
    return organismStats;
  }

  public void setOrganismStats(OrganismStats organismStats) {
    this.organismStats = organismStats;
  }

  public Report taxongroupStats(TaxonGroupStats taxongroupStats) {
    this.taxongroupStats = taxongroupStats;
    return this;
  }

  /**
   * Get taxongroupStats
   * @return taxongroupStats
   **/
  @Schema(description = "")
  
    @Valid
    public TaxonGroupStats getTaxongroupStats() {
    return taxongroupStats;
  }

  public void setTaxongroupStats(TaxonGroupStats taxongroupStats) {
    this.taxongroupStats = taxongroupStats;
  }

  public Report highlights(Highlights highlights) {
    this.highlights = highlights;
    return this;
  }

  /**
   * Get highlights
   * @return highlights
   **/
  @Schema(description = "")
  
    @Valid
    public Highlights getHighlights() {
    return highlights;
  }

  public void setHighlights(Highlights highlights) {
    this.highlights = highlights;
  }

  public Report message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   **/
  @Schema(description = "")
  
    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Report report = (Report) o;
    return Objects.equals(this.inputParams, report.inputParams) &&
        Objects.equals(this.response, report.response) &&
        Objects.equals(this.dbStats, report.dbStats) &&
        Objects.equals(this.organismStats, report.organismStats) &&
        Objects.equals(this.taxongroupStats, report.taxongroupStats) &&
        Objects.equals(this.highlights, report.highlights) &&
        Objects.equals(this.message, report.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inputParams, response, dbStats, organismStats, taxongroupStats, highlights, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Report {\n");
    
    sb.append("    inputParams: ").append(toIndentedString(inputParams)).append("\n");
    sb.append("    response: ").append(toIndentedString(response)).append("\n");
    sb.append("    dbStats: ").append(toIndentedString(dbStats)).append("\n");
    sb.append("    organismStats: ").append(toIndentedString(organismStats)).append("\n");
    sb.append("    taxongroupStats: ").append(toIndentedString(taxongroupStats)).append("\n");
    sb.append("    highlights: ").append(toIndentedString(highlights)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
