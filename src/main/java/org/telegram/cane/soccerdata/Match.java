
package org.telegram.cane.soccerdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "season",
    "utcDate",
    "status",
    "matchday",
    "stage",
    "group",
    "lastUpdated",
    "odds",
    "score",
    "homeTeam",
    "awayTeam",
    "referees"
})
public class Match {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("season")
    private Season season;
    @JsonProperty("utcDate")
    private String utcDate;
    @JsonProperty("status")
    private String status;
    @JsonProperty("matchday")
    private Integer matchday;
    @JsonProperty("stage")
    private String stage;
    @JsonProperty("group")
    private String group;
    @JsonProperty("lastUpdated")
    private String lastUpdated;
    @JsonProperty("odds")
    private Odds odds;
    @JsonProperty("score")
    private Score score;
    @JsonProperty("homeTeam")
    private HomeTeam homeTeam;
    @JsonProperty("awayTeam")
    private AwayTeam awayTeam;
    @JsonProperty("referees")
    private List<Referee> referees = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("season")
    public Season getSeason() {
        return season;
    }

    @JsonProperty("season")
    public void setSeason(Season season) {
        this.season = season;
    }

    @JsonProperty("utcDate")
    public String getUtcDate() {
        return utcDate;
    }

    @JsonProperty("utcDate")
    public void setUtcDate(String utcDate) {
        this.utcDate = utcDate;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("matchday")
    public Integer getMatchday() {
        return matchday;
    }

    @JsonProperty("matchday")
    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }

    @JsonProperty("stage")
    public String getStage() {
        return stage;
    }

    @JsonProperty("stage")
    public void setStage(String stage) {
        this.stage = stage;
    }

    @JsonProperty("group")
    public String getGroup() {
        return group;
    }

    @JsonProperty("group")
    public void setGroup(String group) {
        this.group = group;
    }

    @JsonProperty("lastUpdated")
    public String getLastUpdated() {
        return lastUpdated;
    }

    @JsonProperty("lastUpdated")
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @JsonProperty("odds")
    public Odds getOdds() {
        return odds;
    }

    @JsonProperty("odds")
    public void setOdds(Odds odds) {
        this.odds = odds;
    }

    @JsonProperty("score")
    public Score getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Score score) {
        this.score = score;
    }

    @JsonProperty("homeTeam")
    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    @JsonProperty("homeTeam")
    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    @JsonProperty("awayTeam")
    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    @JsonProperty("awayTeam")
    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    @JsonProperty("referees")
    public List<Referee> getReferees() {
        return referees;
    }

    @JsonProperty("referees")
    public void setReferees(List<Referee> referees) {
        this.referees = referees;
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
