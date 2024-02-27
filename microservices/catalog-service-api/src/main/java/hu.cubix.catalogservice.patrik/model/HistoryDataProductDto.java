package hu.cubix.catalogservice.patrik.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * HistoryDataProductDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-26T21:26:59.725292900+01:00[Europe/Budapest]")
public class HistoryDataProductDto {

  @JsonProperty("data")
  private ProductDto data;

  /**
   * Gets or Sets revType
   */
  public enum RevTypeEnum {
    ADD("ADD"),
    
    MOD("MOD"),
    
    DEL("DEL");

    private String value;

    RevTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RevTypeEnum fromValue(String value) {
      for (RevTypeEnum b : RevTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("revType")
  private RevTypeEnum revType;

  @JsonProperty("revision")
  private Integer revision;

  @JsonProperty("date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime date;

  public HistoryDataProductDto data(ProductDto data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
  */
  @Valid 
  @Schema(name = "data", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public ProductDto getData() {
    return data;
  }

  public void setData(ProductDto data) {
    this.data = data;
  }

  public HistoryDataProductDto revType(RevTypeEnum revType) {
    this.revType = revType;
    return this;
  }

  /**
   * Get revType
   * @return revType
  */
  
  @Schema(name = "revType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public RevTypeEnum getRevType() {
    return revType;
  }

  public void setRevType(RevTypeEnum revType) {
    this.revType = revType;
  }

  public HistoryDataProductDto revision(Integer revision) {
    this.revision = revision;
    return this;
  }

  /**
   * Get revision
   * @return revision
  */
  
  @Schema(name = "revision", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Integer getRevision() {
    return revision;
  }

  public void setRevision(Integer revision) {
    this.revision = revision;
  }

  public HistoryDataProductDto date(OffsetDateTime date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  @Valid 
  @Schema(name = "date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public OffsetDateTime getDate() {
    return date;
  }

  public void setDate(OffsetDateTime date) {
    this.date = date;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HistoryDataProductDto historyDataProductDto = (HistoryDataProductDto) o;
    return Objects.equals(this.data, historyDataProductDto.data) &&
        Objects.equals(this.revType, historyDataProductDto.revType) &&
        Objects.equals(this.revision, historyDataProductDto.revision) &&
        Objects.equals(this.date, historyDataProductDto.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, revType, revision, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HistoryDataProductDto {\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    revType: ").append(toIndentedString(revType)).append("\n");
    sb.append("    revision: ").append(toIndentedString(revision)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

