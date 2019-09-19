
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
@JsonPropertyOrder({ "goalsHomeTeam", "goalsAwayTeam" })
public class Result {

    @JsonProperty("goalsHomeTeam")
    private Object goalsHomeTeam;
    @JsonProperty("goalsAwayTeam")
    private Object goalsAwayTeam;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return The goalsHomeTeam
     */
    @JsonProperty("goalsHomeTeam")
    public Object getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    /**
     * 
     * @param goalsHomeTeam
     *            The goalsHomeTeam
     */
    @JsonProperty("goalsHomeTeam")
    public void setGoalsHomeTeam(Object goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    /**
     * 
     * @return The goalsAwayTeam
     */
    @JsonProperty("goalsAwayTeam")
    public Object getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    /**
     * 
     * @param goalsAwayTeam
     *            The goalsAwayTeam
     */
    @JsonProperty("goalsAwayTeam")
    public void setGoalsAwayTeam(Object goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
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
