package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Input parameters.
 */
@Schema(description = "Input parameters.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-25T23:55:24.901Z[GMT]")


public class InputParams   {
  @JsonProperty("query")
  private String query = null;

  @JsonProperty("sp")
  private Boolean sp = null;

  @JsonProperty("isoform")
  private Boolean isoform = null;

  @JsonProperty("tr")
  private Boolean tr = null;

  @JsonProperty("uniref100")
  private Boolean uniref100 = null;

  @JsonProperty("org")
  private String org = null;

  @JsonProperty("offset")
  private Integer offset = null;

  @JsonProperty("limit")
  private Integer limit = null;

  public InputParams query(String query) {
    this.query = query;
    return this;
  }

  /**
   * queryParam
   * @return query
   **/
  @Schema(description = "queryParam")
  
    public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public InputParams sp(Boolean sp) {
    this.sp = sp;
    return this;
  }

  /**
   * spParam
   * @return sp
   **/
  @Schema(description = "spParam")
  
    public Boolean isSp() {
    return sp;
  }

  public void setSp(Boolean sp) {
    this.sp = sp;
  }

  public InputParams isoform(Boolean isoform) {
    this.isoform = isoform;
    return this;
  }

  /**
   * isoformParam
   * @return isoform
   **/
  @Schema(description = "isoformParam")
  
    public Boolean isIsoform() {
    return isoform;
  }

  public void setIsoform(Boolean isoform) {
    this.isoform = isoform;
  }

  public InputParams tr(Boolean tr) {
    this.tr = tr;
    return this;
  }

  /**
   * trParam
   * @return tr
   **/
  @Schema(description = "trParam")
  
    public Boolean isTr() {
    return tr;
  }

  public void setTr(Boolean tr) {
    this.tr = tr;
  }

  public InputParams uniref100(Boolean uniref100) {
    this.uniref100 = uniref100;
    return this;
  }

  /**
   * uniref100Param
   * @return uniref100
   **/
  @Schema(description = "uniref100Param")
  
    public Boolean isUniref100() {
    return uniref100;
  }

  public void setUniref100(Boolean uniref100) {
    this.uniref100 = uniref100;
  }

  public InputParams org(String org) {
    this.org = org;
    return this;
  }

  /**
   * orgParam
   * @return org
   **/
  @Schema(description = "orgParam")
  
    public String getOrg() {
    return org;
  }

  public void setOrg(String org) {
    this.org = org;
  }

  public InputParams offset(Integer offset) {
    this.offset = offset;
    return this;
  }

  /**
   * offsetParam
   * @return offset
   **/
  @Schema(description = "offsetParam")
  
    public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public InputParams limit(Integer limit) {
    this.limit = limit;
    return this;
  }

  /**
   * limitParam
   * @return limit
   **/
  @Schema(description = "limitParam")
  
    public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InputParams inputParams = (InputParams) o;
    return Objects.equals(this.query, inputParams.query) &&
        Objects.equals(this.sp, inputParams.sp) &&
        Objects.equals(this.isoform, inputParams.isoform) &&
        Objects.equals(this.tr, inputParams.tr) &&
        Objects.equals(this.uniref100, inputParams.uniref100) &&
        Objects.equals(this.org, inputParams.org) &&
        Objects.equals(this.offset, inputParams.offset) &&
        Objects.equals(this.limit, inputParams.limit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(query, sp, isoform, tr, uniref100, org, offset, limit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InputParams {\n");
    
    sb.append("    query: ").append(toIndentedString(query)).append("\n");
    sb.append("    sp: ").append(toIndentedString(sp)).append("\n");
    sb.append("    isoform: ").append(toIndentedString(isoform)).append("\n");
    sb.append("    tr: ").append(toIndentedString(tr)).append("\n");
    sb.append("    uniref100: ").append(toIndentedString(uniref100)).append("\n");
    sb.append("    org: ").append(toIndentedString(org)).append("\n");
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
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
