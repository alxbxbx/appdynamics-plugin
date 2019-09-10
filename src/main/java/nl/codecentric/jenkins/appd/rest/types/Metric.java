package nl.codecentric.jenkins.appd.rest.types;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"metric",
"granularity",
"aggregation"
})
public class Metric {

@JsonProperty("metric")
private String metric;
@JsonProperty("granularity")
private Integer granularity;
@JsonProperty("aggregation")
private String aggregation;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("metric")
public String getMetric() {
return metric;
}

@JsonProperty("metric")
public void setMetric(String metric) {
this.metric = metric;
}

@JsonProperty("granularity")
public Integer getGranularity() {
return granularity;
}

@JsonProperty("granularity")
public void setGranularity(Integer granularity) {
this.granularity = granularity;
}

@JsonProperty("aggregation")
public String getAggregation() {
return aggregation;
}

@JsonProperty("aggregation")
public void setAggregation(String aggregation) {
this.aggregation = aggregation;
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
