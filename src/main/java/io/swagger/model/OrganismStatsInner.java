package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Statistics about the taxononmy distribution of matched proteins.
 */
@Schema(description = "Statistics about the taxononmy distribution of matched proteins.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-25T23:55:24.901Z[GMT]")


public class OrganismStatsInner   {
  @JsonProperty("taxonId")
  private Integer taxonId = null;

  @JsonProperty("taxonName")
  private String taxonName = null;

  @JsonProperty("count")
  private Integer count = null;

  public OrganismStatsInner taxonId(Integer taxonId) {
    this.taxonId = taxonId;
    return this;
  }

  /**
   * The NCBI Taxonomic ID.
   * @return taxonId
   **/
  @Schema(description = "The NCBI Taxonomic ID.")
  
    public Integer getTaxonId() {
    return taxonId;
  }

  public void setTaxonId(Integer taxonId) {
    this.taxonId = taxonId;
  }

  public OrganismStatsInner taxonName(String taxonName) {
    this.taxonName = taxonName;
    return this;
  }

  /**
   * The NCBI Taxon name.
   * @return taxonName
   **/
  @Schema(description = "The NCBI Taxon name.")
  
    public String getTaxonName() {
    return taxonName;
  }

  public void setTaxonName(String taxonName) {
    this.taxonName = taxonName;
  }

  public OrganismStatsInner count(Integer count) {
    this.count = count;
    return this;
  }

  /**
   * The number of matched proteins in each taxon.
   * @return count
   **/
  @Schema(description = "The number of matched proteins in each taxon.")
  
    public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrganismStatsInner organismStatsInner = (OrganismStatsInner) o;
    return Objects.equals(this.taxonId, organismStatsInner.taxonId) &&
        Objects.equals(this.taxonName, organismStatsInner.taxonName) &&
        Objects.equals(this.count, organismStatsInner.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taxonId, taxonName, count);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrganismStatsInner {\n");
    
    sb.append("    taxonId: ").append(toIndentedString(taxonId)).append("\n");
    sb.append("    taxonName: ").append(toIndentedString(taxonName)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
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
