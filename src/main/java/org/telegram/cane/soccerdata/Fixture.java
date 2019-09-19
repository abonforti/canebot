
package org.telegram.cane.soccerdata;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "_links", "date", "status", "matchday", "homeTeamName", "awayTeamName", "result" })
public class Fixture {

    @JsonProperty("_links")
    private Links_ Links;
    @JsonProperty("date")
    private String date;
    @JsonProperty("status")
    private String status;
    @JsonProperty("matchday")
    private Integer matchday;
    @JsonProperty("homeTeamName")
    private String homeTeamName;
    @JsonProperty("awayTeamName")
    private String awayTeamName;
    @JsonProperty("result")
    private Result result;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return The Links
     */
    @JsonProperty("_links")
    public Links_ getLinks() {
        return Links;
    }

    /**
     * 
     * @param Links
     *            The _links
     */
    @JsonProperty("_links")
    public void setLinks(Links_ Links) {
        this.Links = Links;
    }

    /**
     * 
     * @return The date
     */
    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *            The date
     */
    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *            The status
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return The matchday
     */
    @JsonProperty("matchday")
    public Integer getMatchday() {
        return matchday;
    }

    /**
     * 
     * @param matchday
     *            The matchday
     */
    @JsonProperty("matchday")
    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }

    /**
     * 
     * @return The homeTeamName
     */
    @JsonProperty("homeTeamName")
    public String getHomeTeamName() {
        return homeTeamName;
    }

    /**
     * 
     * @param homeTeamName
     *            The homeTeamName
     */
    @JsonProperty("homeTeamName")
    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    /**
     * 
     * @return The awayTeamName
     */
    @JsonProperty("awayTeamName")
    public String getAwayTeamName() {
        return awayTeamName;
    }

    /**
     * 
     * @param awayTeamName
     *            The awayTeamName
     */
    @JsonProperty("awayTeamName")
    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    /**
     * 
     * @return The result
     */
    @JsonProperty("result")
    public Result getResult() {
        return result;
    }

    /**
     * 
     * @param result
     *            The result
     */
    @JsonProperty("result")
    public void setResult(Result result) {
        this.result = result;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
