package nl.codecentric.jenkins.appd.rest.response.types;

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
"latency.mean"
})
public class Metrics_ {

@JsonProperty("latency.mean")
private List<List<Integer>> latencyMean = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("latency.mean")
public List<List<Integer>> getLatencyMean() {
return latencyMean;
}

@JsonProperty("latency.mean")
public void setLatencyMean(List<List<Integer>> latencyMean) {
this.latencyMean = latencyMean;
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
