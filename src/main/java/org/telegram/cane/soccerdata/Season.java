
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
@JsonPropertyOrder({ "_links", "id", "caption", "league", "year", "currentMatchday", "numberOfMatchdays", "numberOfTeams", "numberOfGames", "lastUpdated" })
public class Season {

    @JsonProperty("_links")
    private org.telegram.cane.soccerdata.Links Links;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("caption")
    private String caption;
    @JsonProperty("league")
    private String league;
    @JsonProperty("year")
    private String year;
    @JsonProperty("currentMatchday")
    private Integer currentMatchday;
    @JsonProperty("numberOfMatchdays")
    private Integer numberOfMatchdays;
    @JsonProperty("numberOfTeams")
    private Integer numberOfTeams;
    @JsonProperty("numberOfGames")
    private Integer numberOfGames;
    @JsonProperty("lastUpdated")
    private String lastUpdated;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return The Links
     */
    @JsonProperty("_links")
    public org.telegram.cane.soccerdata.Links getLinks() {
        return Links;
    }

    /**
     * 
     * @param Links
     *            The _links
     */
    @JsonProperty("_links")
    public void setLinks(org.telegram.cane.soccerdata.Links Links) {
        this.Links = Links;
    }

    /**
     * 
     * @return The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *            The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return The caption
     */
    @JsonProperty("caption")
    public String getCaption() {
        return caption;
    }

    /**
     * 
     * @param caption
     *            The caption
     */
    @JsonProperty("caption")
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * 
     * @return The league
     */
    @JsonProperty("league")
    public String getLeague() {
        return league;
    }

    /**
     * 
     * @param league
     *            The league
     */
    @JsonProperty("league")
    public void setLeague(String league) {
        this.league = league;
    }

    /**
     * 
     * @return The year
     */
    @JsonProperty("year")
    public String getYear() {
        return year;
    }

    /**
     * 
     * @param year
     *            The year
     */
    @JsonProperty("year")
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * 
     * @return The currentMatchday
     */
    @JsonProperty("currentMatchday")
    public Integer getCurrentMatchday() {
        return currentMatchday;
    }

    /**
     * 
     * @param currentMatchday
     *            The currentMatchday
     */
    @JsonProperty("currentMatchday")
    public void setCurrentMatchday(Integer currentMatchday) {
        this.currentMatchday = currentMatchday;
    }

    /**
     * 
     * @return The numberOfMatchdays
     */
    @JsonProperty("numberOfMatchdays")
    public Integer getNumberOfMatchdays() {
        return numberOfMatchdays;
    }

    /**
     * 
     * @param numberOfMatchdays
     *            The numberOfMatchdays
     */
    @JsonProperty("numberOfMatchdays")
    public void setNumberOfMatchdays(Integer numberOfMatchdays) {
        this.numberOfMatchdays = numberOfMatchdays;
    }

    /**
     * 
     * @return The numberOfTeams
     */
    @JsonProperty("numberOfTeams")
    public Integer getNumberOfTeams() {
        return numberOfTeams;
    }

    /**
     * 
     * @param numberOfTeams
     *            The numberOfTeams
     */
    @JsonProperty("numberOfTeams")
    public void setNumberOfTeams(Integer numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    /**
     * 
     * @return The numberOfGames
     */
    @JsonProperty("numberOfGames")
    public Integer getNumberOfGames() {
        return numberOfGames;
    }

    /**
     * 
     * @param numberOfGames
     *            The numberOfGames
     */
    @JsonProperty("numberOfGames")
    public void setNumberOfGames(Integer numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    /**
     * 
     * @return The lastUpdated
     */
    @JsonProperty("lastUpdated")
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     * 
     * @param lastUpdated
     *            The lastUpdated
     */
    @JsonProperty("lastUpdated")
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
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
