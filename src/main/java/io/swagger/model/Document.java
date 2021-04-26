package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A matched protein, returned as a SolrDocument.
 */
@Schema(description = "A matched protein, returned as a SolrDocument.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-25T23:55:24.901Z[GMT]")


public class Document   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("proteinID")
  private String proteinID = null;

  @JsonProperty("proteinName")
  private String proteinName = null;

  @JsonProperty("organismName")
  private String organismName = null;

  @JsonProperty("organismID")
  private Integer organismID = null;

  @JsonProperty("taxongroupName")
  private String taxongroupName = null;

  @JsonProperty("taxongroupID")
  private Integer taxongroupID = null;

  @JsonProperty("fullLineage")
  @Valid
  private List<Integer> fullLineage = null;

  @JsonProperty("shortLineage")
  @Valid
  private List<Integer> shortLineage = null;

  @JsonProperty("isSP")
  private Boolean isSP = null;

  @JsonProperty("isTR")
  private Boolean isTR = null;

  @JsonProperty("isIsoform")
  private Boolean isIsoform = null;

  @JsonProperty("uniref100")
  private String uniref100 = null;

  @JsonProperty("seq")
  private String seq = null;

  @JsonProperty("length")
  private Integer length = null;

  @JsonProperty("_version")
  private String _version = null;

  public Document id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The document ID.
   * @return id
   **/
  @Schema(description = "The document ID.")
  
    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Document proteinID(String proteinID) {
    this.proteinID = proteinID;
    return this;
  }

  /**
   * The protein ID.
   * @return proteinID
   **/
  @Schema(description = "The protein ID.")
  
    public String getProteinID() {
    return proteinID;
  }

  public void setProteinID(String proteinID) {
    this.proteinID = proteinID;
  }

  public Document proteinName(String proteinName) {
    this.proteinName = proteinName;
    return this;
  }

  /**
   * The protein name.
   * @return proteinName
   **/
  @Schema(description = "The protein name.")
  
    public String getProteinName() {
    return proteinName;
  }

  public void setProteinName(String proteinName) {
    this.proteinName = proteinName;
  }

  public Document organismName(String organismName) {
    this.organismName = organismName;
    return this;
  }

  /**
   * The organism name.
   * @return organismName
   **/
  @Schema(description = "The organism name.")
  
    public String getOrganismName() {
    return organismName;
  }

  public void setOrganismName(String organismName) {
    this.organismName = organismName;
  }

  public Document organismID(Integer organismID) {
    this.organismID = organismID;
    return this;
  }

  /**
   * The organism ID.
   * @return organismID
   **/
  @Schema(description = "The organism ID.")
  
    public Integer getOrganismID() {
    return organismID;
  }

  public void setOrganismID(Integer organismID) {
    this.organismID = organismID;
  }

  public Document taxongroupName(String taxongroupName) {
    this.taxongroupName = taxongroupName;
    return this;
  }

  /**
   * The taxongroup Name.
   * @return taxongroupName
   **/
  @Schema(description = "The taxongroup Name.")
  
    public String getTaxongroupName() {
    return taxongroupName;
  }

  public void setTaxongroupName(String taxongroupName) {
    this.taxongroupName = taxongroupName;
  }

  public Document taxongroupID(Integer taxongroupID) {
    this.taxongroupID = taxongroupID;
    return this;
  }

  /**
   * The taxongroup ID.
   * @return taxongroupID
   **/
  @Schema(description = "The taxongroup ID.")
  
    public Integer getTaxongroupID() {
    return taxongroupID;
  }

  public void setTaxongroupID(Integer taxongroupID) {
    this.taxongroupID = taxongroupID;
  }

  public Document fullLineage(List<Integer> fullLineage) {
    this.fullLineage = fullLineage;
    return this;
  }

  public Document addFullLineageItem(Integer fullLineageItem) {
    if (this.fullLineage == null) {
      this.fullLineage = new ArrayList<Integer>();
    }
    this.fullLineage.add(fullLineageItem);
    return this;
  }

  /**
   * Get fullLineage
   * @return fullLineage
   **/
  @Schema(description = "")
  
    public List<Integer> getFullLineage() {
    return fullLineage;
  }

  public void setFullLineage(List<Integer> fullLineage) {
    this.fullLineage = fullLineage;
  }

  public Document shortLineage(List<Integer> shortLineage) {
    this.shortLineage = shortLineage;
    return this;
  }

  public Document addShortLineageItem(Integer shortLineageItem) {
    if (this.shortLineage == null) {
      this.shortLineage = new ArrayList<Integer>();
    }
    this.shortLineage.add(shortLineageItem);
    return this;
  }

  /**
   * Get shortLineage
   * @return shortLineage
   **/
  @Schema(description = "")
  
    public List<Integer> getShortLineage() {
    return shortLineage;
  }

  public void setShortLineage(List<Integer> shortLineage) {
    this.shortLineage = shortLineage;
  }

  public Document isSP(Boolean isSP) {
    this.isSP = isSP;
    return this;
  }

  /**
   * Whether the protein is a SwissProt entry.
   * @return isSP
   **/
  @Schema(description = "Whether the protein is a SwissProt entry.")
  
    public Boolean isIsSP() {
    return isSP;
  }

  public void setIsSP(Boolean isSP) {
    this.isSP = isSP;
  }

  public Document isTR(Boolean isTR) {
    this.isTR = isTR;
    return this;
  }

  /**
   * Whether the protein is a TrEMBL entry.
   * @return isTR
   **/
  @Schema(description = "Whether the protein is a TrEMBL entry.")
  
    public Boolean isIsTR() {
    return isTR;
  }

  public void setIsTR(Boolean isTR) {
    this.isTR = isTR;
  }

  public Document isIsoform(Boolean isIsoform) {
    this.isIsoform = isIsoform;
    return this;
  }

  /**
   * Whether the protein is a protein Isoform.
   * @return isIsoform
   **/
  @Schema(description = "Whether the protein is a protein Isoform.")
  
    public Boolean isIsIsoform() {
    return isIsoform;
  }

  public void setIsIsoform(Boolean isIsoform) {
    this.isIsoform = isIsoform;
  }

  public Document uniref100(String uniref100) {
    this.uniref100 = uniref100;
    return this;
  }

  /**
   * The UniRef100 cluster this protein belongs to.
   * @return uniref100
   **/
  @Schema(description = "The UniRef100 cluster this protein belongs to.")
  
    public String getUniref100() {
    return uniref100;
  }

  public void setUniref100(String uniref100) {
    this.uniref100 = uniref100;
  }

  public Document seq(String seq) {
    this.seq = seq;
    return this;
  }

  /**
   * Protein sequence
   * @return seq
   **/
  @Schema(description = "Protein sequence")
  
    public String getSeq() {
    return seq;
  }

  public void setSeq(String seq) {
    this.seq = seq;
  }

  public Document length(Integer length) {
    this.length = length;
    return this;
  }

  /**
   * The length of protein sequence.
   * @return length
   **/
  @Schema(description = "The length of protein sequence.")
  
    public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public Document _version(String _version) {
    this._version = _version;
    return this;
  }

  /**
   * The version of the SolrDocument.
   * @return _version
   **/
  @Schema(description = "The version of the SolrDocument.")
  
    public String getVersion() {
    return _version;
  }

  public void setVersion(String _version) {
    this._version = _version;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Document document = (Document) o;
    return Objects.equals(this.id, document.id) &&
        Objects.equals(this.proteinID, document.proteinID) &&
        Objects.equals(this.proteinName, document.proteinName) &&
        Objects.equals(this.organismName, document.organismName) &&
        Objects.equals(this.organismID, document.organismID) &&
        Objects.equals(this.taxongroupName, document.taxongroupName) &&
        Objects.equals(this.taxongroupID, document.taxongroupID) &&
        Objects.equals(this.fullLineage, document.fullLineage) &&
        Objects.equals(this.shortLineage, document.shortLineage) &&
        Objects.equals(this.isSP, document.isSP) &&
        Objects.equals(this.isTR, document.isTR) &&
        Objects.equals(this.isIsoform, document.isIsoform) &&
        Objects.equals(this.uniref100, document.uniref100) &&
        Objects.equals(this.seq, document.seq) &&
        Objects.equals(this.length, document.length) &&
        Objects.equals(this._version, document._version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, proteinID, proteinName, organismName, organismID, taxongroupName, taxongroupID, fullLineage, shortLineage, isSP, isTR, isIsoform, uniref100, seq, length, _version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Document {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    proteinID: ").append(toIndentedString(proteinID)).append("\n");
    sb.append("    proteinName: ").append(toIndentedString(proteinName)).append("\n");
    sb.append("    organismName: ").append(toIndentedString(organismName)).append("\n");
    sb.append("    organismID: ").append(toIndentedString(organismID)).append("\n");
    sb.append("    taxongroupName: ").append(toIndentedString(taxongroupName)).append("\n");
    sb.append("    taxongroupID: ").append(toIndentedString(taxongroupID)).append("\n");
    sb.append("    fullLineage: ").append(toIndentedString(fullLineage)).append("\n");
    sb.append("    shortLineage: ").append(toIndentedString(shortLineage)).append("\n");
    sb.append("    isSP: ").append(toIndentedString(isSP)).append("\n");
    sb.append("    isTR: ").append(toIndentedString(isTR)).append("\n");
    sb.append("    isIsoform: ").append(toIndentedString(isIsoform)).append("\n");
    sb.append("    uniref100: ").append(toIndentedString(uniref100)).append("\n");
    sb.append("    seq: ").append(toIndentedString(seq)).append("\n");
    sb.append("    length: ").append(toIndentedString(length)).append("\n");
    sb.append("    _version: ").append(toIndentedString(_version)).append("\n");
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
