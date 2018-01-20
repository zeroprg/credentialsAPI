package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * This is password strength DataType
 */
@ApiModel(description = "This is password strength DataType")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-20T11:12:24.482-08:00")

public class PasswordStrength   {
  @JsonProperty("strength")
  private Integer strength = null;

  public PasswordStrength(int i) {
	  strength  = i;
}

public PasswordStrength strength(Integer strength) {
    this.strength = strength;
    return this;
  }

   /**
   * 
   * @return strength
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Integer getStrength() {
    return strength;
  }

  public void setStrength(Integer strength) {
    this.strength = strength;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PasswordStrength passwordStrength = (PasswordStrength) o;
    return Objects.equals(this.strength, passwordStrength.strength);
  }

  @Override
  public int hashCode() {
    return Objects.hash(strength);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PasswordStrength {\n");
    
    sb.append("    strength: ").append(toIndentedString(strength)).append("\n");
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

