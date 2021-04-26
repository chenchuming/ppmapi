package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Protein;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Response from SolrCloud search.
 */
@Schema(description = "Response from SolrCloud search.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-26T13:57:51.947Z[GMT]")


public class SolrResponse   {
  @JsonProperty("totalMatchedProteins")
  private Integer totalMatchedProteins = null;

  @JsonProperty("currentMatchedProteinList")
  @Valid
  private List<Protein> currentMatchedProteinList = null;

  @JsonProperty("qtime")
  private BigDecimal qtime = null;

  public SolrResponse totalMatchedProteins(Integer totalMatchedProteins) {
    this.totalMatchedProteins = totalMatchedProteins;
    return this;
  }

  /**
   * Number of matched SolrDocuments.
   * @return totalMatchedProteins
   **/
  @Schema(description = "Number of matched SolrDocuments.")
  
    public Integer getTotalMatchedProteins() {
    return totalMatchedProteins;
  }

  public void setTotalMatchedProteins(Integer totalMatchedProteins) {
    this.totalMatchedProteins = totalMatchedProteins;
  }

  public SolrResponse currentMatchedProteinList(List<Protein> currentMatchedProteinList) {
    this.currentMatchedProteinList = currentMatchedProteinList;
    return this;
  }

  public SolrResponse addCurrentMatchedProteinListItem(Protein currentMatchedProteinListItem) {
    if (this.currentMatchedProteinList == null) {
      this.currentMatchedProteinList = new ArrayList<Protein>();
    }
    this.currentMatchedProteinList.add(currentMatchedProteinListItem);
    return this;
  }

  /**
   * Get currentMatchedProteinList
   * @return currentMatchedProteinList
   **/
  @Schema(description = "")
      @Valid
    public List<Protein> getCurrentMatchedProteinList() {
    return currentMatchedProteinList;
  }

  public void setCurrentMatchedProteinList(List<Protein> currentMatchedProteinList) {
    this.currentMatchedProteinList = currentMatchedProteinList;
  }

  public SolrResponse qtime(BigDecimal qtime) {
    this.qtime = qtime;
    return this;
  }

  /**
   * The number of milliseconds it took Solr to execute a search.
   * @return qtime
   **/
  @Schema(description = "The number of milliseconds it took Solr to execute a search.")
  
    @Valid
    public BigDecimal getQtime() {
    return qtime;
  }

  public void setQtime(BigDecimal qtime) {
    this.qtime = qtime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SolrResponse solrResponse = (SolrResponse) o;
    return Objects.equals(this.totalMatchedProteins, solrResponse.totalMatchedProteins) &&
        Objects.equals(this.currentMatchedProteinList, solrResponse.currentMatchedProteinList) &&
        Objects.equals(this.qtime, solrResponse.qtime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalMatchedProteins, currentMatchedProteinList, qtime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SolrResponse {\n");
    
    sb.append("    totalMatchedProteins: ").append(toIndentedString(totalMatchedProteins)).append("\n");
    sb.append("    currentMatchedProteinList: ").append(toIndentedString(currentMatchedProteinList)).append("\n");
    sb.append("    qtime: ").append(toIndentedString(qtime)).append("\n");
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
