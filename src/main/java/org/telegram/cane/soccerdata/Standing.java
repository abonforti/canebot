
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
@JsonPropertyOrder({ "_links", "position", "teamName", "crestURI", "playedGames", "points", "goals", "goalsAgainst", "goalDifference", "wins", "draws", "losses", "home", "away" })
public class Standing {

    @JsonProperty("_links")
    private Links_ Links;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("teamName")
    private String teamName;
    @JsonProperty("crestURI")
    private String crestURI;
    @JsonProperty("playedGames")
    private Integer playedGames;
    @JsonProperty("points")
    private Integer points;
    @JsonProperty("goals")
    private Integer goals;
    @JsonProperty("goalsAgainst")
    private Integer goalsAgainst;
    @JsonProperty("goalDifference")
    private Integer goalDifference;
    @JsonProperty("wins")
    private Integer wins;
    @JsonProperty("draws")
    private Integer draws;
    @JsonProperty("losses")
    private Integer losses;
    @JsonProperty("home")
    private Home home;
    @JsonProperty("away")
    private Away away;
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
     * @return The position
     */
    @JsonProperty("position")
    public Integer getPosition() {
        return position;
    }

    /**
     * 
     * @param position
     *            The position
     */
    @JsonProperty("position")
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * 
     * @return The teamName
     */
    @JsonProperty("teamName")
    public String getTeamName() {
        return teamName;
    }

    /**
     * 
     * @param teamName
     *            The teamName
     */
    @JsonProperty("teamName")
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * 
     * @return The crestURI
     */
    @JsonProperty("crestURI")
    public String getCrestURI() {
        return crestURI;
    }

    /**
     * 
     * @param crestURI
     *            The crestURI
     */
    @JsonProperty("crestURI")
    public void setCrestURI(String crestURI) {
        this.crestURI = crestURI;
    }

    /**
     * 
     * @return The playedGames
     */
    @JsonProperty("playedGames")
    public Integer getPlayedGames() {
        return playedGames;
    }

    /**
     * 
     * @param playedGames
     *            The playedGames
     */
    @JsonProperty("playedGames")
    public void setPlayedGames(Integer playedGames) {
        this.playedGames = playedGames;
    }

    /**
     * 
     * @return The points
     */
    @JsonProperty("points")
    public Integer getPoints() {
        return points;
    }

    /**
     * 
     * @param points
     *            The points
     */
    @JsonProperty("points")
    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * 
     * @return The goals
     */
    @JsonProperty("goals")
    public Integer getGoals() {
        return goals;
    }

    /**
     * 
     * @param goals
     *            The goals
     */
    @JsonProperty("goals")
    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    /**
     * 
     * @return The goalsAgainst
     */
    @JsonProperty("goalsAgainst")
    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    /**
     * 
     * @param goalsAgainst
     *            The goalsAgainst
     */
    @JsonProperty("goalsAgainst")
    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    /**
     * 
     * @return The goalDifference
     */
    @JsonProperty("goalDifference")
    public Integer getGoalDifference() {
        return goalDifference;
    }

    /**
     * 
     * @param goalDifference
     *            The goalDifference
     */
    @JsonProperty("goalDifference")
    public void setGoalDifference(Integer goalDifference) {
        this.goalDifference = goalDifference;
    }

    /**
     * 
     * @return The wins
     */
    @JsonProperty("wins")
    public Integer getWins() {
        return wins;
    }

    /**
     * 
     * @param wins
     *            The wins
     */
    @JsonProperty("wins")
    public void setWins(Integer wins) {
        this.wins = wins;
    }

    /**
     * 
     * @return The draws
     */
    @JsonProperty("draws")
    public Integer getDraws() {
        return draws;
    }

    /**
     * 
     * @param draws
     *            The draws
     */
    @JsonProperty("draws")
    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    /**
     * 
     * @return The losses
     */
    @JsonProperty("losses")
    public Integer getLosses() {
        return losses;
    }

    /**
     * 
     * @param losses
     *            The losses
     */
    @JsonProperty("losses")
    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    /**
     * 
     * @return The home
     */
    @JsonProperty("home")
    public Home getHome() {
        return home;
    }

    /**
     * 
     * @param home
     *            The home
     */
    @JsonProperty("home")
    public void setHome(Home home) {
        this.home = home;
    }

    /**
     * 
     * @return The away
     */
    @JsonProperty("away")
    public Away getAway() {
        return away;
    }

    /**
     * 
     * @param away
     *            The away
     */
    @JsonProperty("away")
    public void setAway(Away away) {
        this.away = away;
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
