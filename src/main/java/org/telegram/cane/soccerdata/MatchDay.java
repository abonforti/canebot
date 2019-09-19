
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
@JsonPropertyOrder({ "_links", "count", "fixtures" })
public class MatchDay {

    @JsonProperty("_links")
    private org.telegram.cane.soccerdata.Links Links;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("fixtures")
    private List<Fixture> fixtures = new ArrayList<Fixture>();
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
     * @return The count
     */
    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *            The count
     */
    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 
     * @return The fixtures
     */
    @JsonProperty("fixtures")
    public List<Fixture> getFixtures() {
        return fixtures;
    }

    /**
     * 
     * @param fixtures
     *            The fixtures
     */
    @JsonProperty("fixtures")
    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
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
