package nl.codecentric.jenkins.appd.rest.types;

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
"metrics",
"timeFrame",
"serviceId"
})
public class Metrics {

@JsonProperty("metrics")
private List<Metric> metrics = null;
@JsonProperty("timeFrame")
private TimeFrame timeFrame;
@JsonProperty("serviceId")
private String serviceId;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("metrics")
public List<Metric> getMetrics() {
return metrics;
}

@JsonProperty("metrics")
public void setMetrics(List<Metric> metrics) {
this.metrics = metrics;
}

@JsonProperty("timeFrame")
public TimeFrame getTimeFrame() {
return timeFrame;
}

@JsonProperty("timeFrame")
public void setTimeFrame(TimeFrame timeFrame) {
this.timeFrame = timeFrame;
}

@JsonProperty("serviceId")
public String getServiceId() {
return serviceId;
}

@JsonProperty("serviceId")
public void setServiceId(String serviceId) {
this.serviceId = serviceId;
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
