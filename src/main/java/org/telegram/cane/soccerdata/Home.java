
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
@JsonPropertyOrder({ "goals", "goalsAgainst", "wins", "draws", "losses" })
public class Home {

    @JsonProperty("goals")
    private Integer goals;
    @JsonProperty("goalsAgainst")
    private Integer goalsAgainst;
    @JsonProperty("wins")
    private Integer wins;
    @JsonProperty("draws")
    private Integer draws;
    @JsonProperty("losses")
    private Integer losses;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
