package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Document;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-25T23:55:24.901Z[GMT]")


public class SolrResponse   {
  @JsonProperty("numFound")
  private Integer numFound = null;

  @JsonProperty("docs")
  @Valid
  private List<Document> docs = null;

  @JsonProperty("qtime")
  private BigDecimal qtime = null;

  public SolrResponse numFound(Integer numFound) {
    this.numFound = numFound;
    return this;
  }

  /**
   * Number of matched SolrDocuments.
   * @return numFound
   **/
  @Schema(description = "Number of matched SolrDocuments.")
  
    public Integer getNumFound() {
    return numFound;
  }

  public void setNumFound(Integer numFound) {
    this.numFound = numFound;
  }

  public SolrResponse docs(List<Document> docs) {
    this.docs = docs;
    return this;
  }

  public SolrResponse addDocsItem(Document docsItem) {
    if (this.docs == null) {
      this.docs = new ArrayList<Document>();
    }
    this.docs.add(docsItem);
    return this;
  }

  /**
   * Get docs
   * @return docs
   **/
  @Schema(description = "")
      @Valid
    public List<Document> getDocs() {
    return docs;
  }

  public void setDocs(List<Document> docs) {
    this.docs = docs;
  }

  public SolrResponse qtime(BigDecimal qtime) {
    this.qtime = qtime;
    return this;
  }

  /**
   * SolrCloud query time.
   * @return qtime
   **/
  @Schema(description = "SolrCloud query time.")
  
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
    return Objects.equals(this.numFound, solrResponse.numFound) &&
        Objects.equals(this.docs, solrResponse.docs) &&
        Objects.equals(this.qtime, solrResponse.qtime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numFound, docs, qtime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SolrResponse {\n");
    
    sb.append("    numFound: ").append(toIndentedString(numFound)).append("\n");
    sb.append("    docs: ").append(toIndentedString(docs)).append("\n");
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
