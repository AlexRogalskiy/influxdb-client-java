/*
 * Influx API Service
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * OpenAPI spec version: 0.1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.influxdata.client.domain;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.influxdata.client.domain.DashboardColor;
import org.influxdata.client.domain.DashboardQuery;

/**
 * ViewProperties
 */

public class ViewProperties {
  public static final String SERIALIZED_NAME_QUERIES = "queries";
  @SerializedName(SERIALIZED_NAME_QUERIES)
  private List<DashboardQuery> queries = new ArrayList<>();

  public static final String SERIALIZED_NAME_COLORS = "colors";
  @SerializedName(SERIALIZED_NAME_COLORS)
  private List<DashboardColor> colors = new ArrayList<>();

  public static final String SERIALIZED_NAME_NOTE = "note";
  @SerializedName(SERIALIZED_NAME_NOTE)
  private String note;

  public static final String SERIALIZED_NAME_SHOW_NOTE_WHEN_EMPTY = "showNoteWhenEmpty";
  @SerializedName(SERIALIZED_NAME_SHOW_NOTE_WHEN_EMPTY)
  private Boolean showNoteWhenEmpty;

  public ViewProperties queries(List<DashboardQuery> queries) {
    this.queries = queries;
    return this;
  }

  public ViewProperties addQueriesItem(DashboardQuery queriesItem) {
    if (this.queries == null) {
      this.queries = new ArrayList<>();
    }
    this.queries.add(queriesItem);
    return this;
  }

   /**
   * Get queries
   * @return queries
  **/
  @ApiModelProperty(value = "")
  public List<DashboardQuery> getQueries() {
    return queries;
  }

  public void setQueries(List<DashboardQuery> queries) {
    this.queries = queries;
  }

  public ViewProperties colors(List<DashboardColor> colors) {
    this.colors = colors;
    return this;
  }

  public ViewProperties addColorsItem(DashboardColor colorsItem) {
    if (this.colors == null) {
      this.colors = new ArrayList<>();
    }
    this.colors.add(colorsItem);
    return this;
  }

   /**
   * Colors define color encoding of data into a visualization
   * @return colors
  **/
  @ApiModelProperty(value = "Colors define color encoding of data into a visualization")
  public List<DashboardColor> getColors() {
    return colors;
  }

  public void setColors(List<DashboardColor> colors) {
    this.colors = colors;
  }

  public ViewProperties note(String note) {
    this.note = note;
    return this;
  }

   /**
   * Get note
   * @return note
  **/
  @ApiModelProperty(value = "")
  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public ViewProperties showNoteWhenEmpty(Boolean showNoteWhenEmpty) {
    this.showNoteWhenEmpty = showNoteWhenEmpty;
    return this;
  }

   /**
   * if true, will display note when empty
   * @return showNoteWhenEmpty
  **/
  @ApiModelProperty(value = "if true, will display note when empty")
  public Boolean getShowNoteWhenEmpty() {
    return showNoteWhenEmpty;
  }

  public void setShowNoteWhenEmpty(Boolean showNoteWhenEmpty) {
    this.showNoteWhenEmpty = showNoteWhenEmpty;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ViewProperties viewProperties = (ViewProperties) o;
    return Objects.equals(this.queries, viewProperties.queries) &&
        Objects.equals(this.colors, viewProperties.colors) &&
        Objects.equals(this.note, viewProperties.note) &&
        Objects.equals(this.showNoteWhenEmpty, viewProperties.showNoteWhenEmpty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(queries, colors, note, showNoteWhenEmpty);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ViewProperties {\n");
    sb.append("    queries: ").append(toIndentedString(queries)).append("\n");
    sb.append("    colors: ").append(toIndentedString(colors)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
    sb.append("    showNoteWhenEmpty: ").append(toIndentedString(showNoteWhenEmpty)).append("\n");
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
