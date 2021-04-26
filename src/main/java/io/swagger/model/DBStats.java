package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Statistics about the matched proteins in different seqeunce databases.
 */
@Schema(description = "Statistics about the matched proteins in different seqeunce databases.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-25T23:55:24.901Z[GMT]")


public class DBStats   {
  @JsonProperty("numSwissProt")
  private Integer numSwissProt = null;

  @JsonProperty("numTrEMBL")
  private Integer numTrEMBL = null;

  @JsonProperty("numIsoform")
  private Integer numIsoform = null;

  @JsonProperty("numUniRef100")
  private Integer numUniRef100 = null;

  public DBStats numSwissProt(Integer numSwissProt) {
    this.numSwissProt = numSwissProt;
    return this;
  }

  /**
   * Number of matched proteins that are SwissProt entries.
   * @return numSwissProt
   **/
  @Schema(description = "Number of matched proteins that are SwissProt entries.")
  
    public Integer getNumSwissProt() {
    return numSwissProt;
  }

  public void setNumSwissProt(Integer numSwissProt) {
    this.numSwissProt = numSwissProt;
  }

  public DBStats numTrEMBL(Integer numTrEMBL) {
    this.numTrEMBL = numTrEMBL;
    return this;
  }

  /**
   * Number of matched proteins that are TrEMBL entries.
   * @return numTrEMBL
   **/
  @Schema(description = "Number of matched proteins that are TrEMBL entries.")
  
    public Integer getNumTrEMBL() {
    return numTrEMBL;
  }

  public void setNumTrEMBL(Integer numTrEMBL) {
    this.numTrEMBL = numTrEMBL;
  }

  public DBStats numIsoform(Integer numIsoform) {
    this.numIsoform = numIsoform;
    return this;
  }

  /**
   * Number of matched proteins that are protein Isoforms.
   * @return numIsoform
   **/
  @Schema(description = "Number of matched proteins that are protein Isoforms.")
  
    public Integer getNumIsoform() {
    return numIsoform;
  }

  public void setNumIsoform(Integer numIsoform) {
    this.numIsoform = numIsoform;
  }

  public DBStats numUniRef100(Integer numUniRef100) {
    this.numUniRef100 = numUniRef100;
    return this;
  }

  /**
   * Number of matched proteins that are in UniRef100 clusters.
   * @return numUniRef100
   **/
  @Schema(description = "Number of matched proteins that are in UniRef100 clusters.")
  
    public Integer getNumUniRef100() {
    return numUniRef100;
  }

  public void setNumUniRef100(Integer numUniRef100) {
    this.numUniRef100 = numUniRef100;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DBStats dbStats = (DBStats) o;
    return Objects.equals(this.numSwissProt, dbStats.numSwissProt) &&
        Objects.equals(this.numTrEMBL, dbStats.numTrEMBL) &&
        Objects.equals(this.numIsoform, dbStats.numIsoform) &&
        Objects.equals(this.numUniRef100, dbStats.numUniRef100);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numSwissProt, numTrEMBL, numIsoform, numUniRef100);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DBStats {\n");
    
    sb.append("    numSwissProt: ").append(toIndentedString(numSwissProt)).append("\n");
    sb.append("    numTrEMBL: ").append(toIndentedString(numTrEMBL)).append("\n");
    sb.append("    numIsoform: ").append(toIndentedString(numIsoform)).append("\n");
    sb.append("    numUniRef100: ").append(toIndentedString(numUniRef100)).append("\n");
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
