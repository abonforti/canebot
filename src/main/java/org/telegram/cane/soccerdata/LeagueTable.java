
package org.telegram.cane.soccerdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@JsonPropertyOrder({ "_links", "leagueCaption", "matchday", "standing" })
public class LeagueTable {

    @JsonProperty("_links")
    private org.telegram.cane.soccerdata.Links Links;
    @JsonProperty("leagueCaption")
    private String leagueCaption;
    @JsonProperty("matchday")
    private Integer matchday;
    @JsonProperty("standing")
    private List<Standing> standing = new ArrayList<Standing>();
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
     * @return The leagueCaption
     */
    @JsonProperty("leagueCaption")
    public String getLeagueCaption() {
        return leagueCaption;
    }

    /**
     * 
     * @param leagueCaption
     *            The leagueCaption
     */
    @JsonProperty("leagueCaption")
    public void setLeagueCaption(String leagueCaption) {
        this.leagueCaption = leagueCaption;
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
     * @return The standing
     */
    @JsonProperty("standing")
    public List<Standing> getStanding() {
        return standing;
    }

    /**
     * 
     * @param standing
     *            The standing
     */
    @JsonProperty("standing")
    public void setStanding(List<Standing> standing) {
        this.standing = standing;
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
