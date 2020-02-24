
package org.telegram.cane.soccerdata;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Table {

    @JsonProperty("position")
    private Integer position;
    @JsonProperty("team")
    private Team team;
    @JsonProperty("playedGames")
    private Integer playedGames;
    @JsonProperty("won")
    private Integer won;
    @JsonProperty("draw")
    private Integer draw;
    @JsonProperty("lost")
    private Integer lost;
    @JsonProperty("points")
    private Integer points;
    @JsonProperty("goalsFor")
    private Integer goalsFor;
    @JsonProperty("goalsAgainst")
    private Integer goalsAgainst;
    @JsonProperty("goalDifference")
    private Integer goalDifference;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("position")
    public Integer getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(Integer position) {
        this.position = position;
    }

    @JsonProperty("team")
    public Team getTeam() {
        return team;
    }

    @JsonProperty("team")
    public void setTeam(Team team) {
        this.team = team;
    }

    @JsonProperty("playedGames")
    public Integer getPlayedGames() {
        return playedGames;
    }

    @JsonProperty("playedGames")
    public void setPlayedGames(Integer playedGames) {
        this.playedGames = playedGames;
    }

    @JsonProperty("won")
    public Integer getWon() {
        return won;
    }

    @JsonProperty("won")
    public void setWon(Integer won) {
        this.won = won;
    }

    @JsonProperty("draw")
    public Integer getDraw() {
        return draw;
    }

    @JsonProperty("draw")
    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    @JsonProperty("lost")
    public Integer getLost() {
        return lost;
    }

    @JsonProperty("lost")
    public void setLost(Integer lost) {
        this.lost = lost;
    }

    @JsonProperty("points")
    public Integer getPoints() {
        return points;
    }

    @JsonProperty("points")
    public void setPoints(Integer points) {
        this.points = points;
    }

    @JsonProperty("goalsFor")
    public Integer getGoalsFor() {
        return goalsFor;
    }

    @JsonProperty("goalsFor")
    public void setGoalsFor(Integer goalsFor) {
        this.goalsFor = goalsFor;
    }

    @JsonProperty("goalsAgainst")
    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    @JsonProperty("goalsAgainst")
    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    @JsonProperty("goalDifference")
    public Integer getGoalDifference() {
        return goalDifference;
    }

    @JsonProperty("goalDifference")
    public void setGoalDifference(Integer goalDifference) {
        this.goalDifference = goalDifference;
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
