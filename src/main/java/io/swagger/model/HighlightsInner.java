package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * HighlightsInner
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-26T13:57:51.947Z[GMT]")


public class HighlightsInner   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("seq")
  private String seq = null;

  public HighlightsInner id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Protein AC
   * @return id
   **/
  @Schema(description = "Protein AC")
  
    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public HighlightsInner seq(String seq) {
    this.seq = seq;
    return this;
  }

  /**
   * The sequence with matched region highlighted.
   * @return seq
   **/
  @Schema(description = "The sequence with matched region highlighted.")
  
    public String getSeq() {
    return seq;
  }

  public void setSeq(String seq) {
    this.seq = seq;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HighlightsInner highlightsInner = (HighlightsInner) o;
    return Objects.equals(this.id, highlightsInner.id) &&
        Objects.equals(this.seq, highlightsInner.seq);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, seq);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HighlightsInner {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    seq: ").append(toIndentedString(seq)).append("\n");
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
