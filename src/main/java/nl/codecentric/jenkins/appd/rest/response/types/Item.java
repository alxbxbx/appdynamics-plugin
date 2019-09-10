package nl.codecentric.jenkins.appd.rest.response.types;

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
"service",
"metrics"
})
public class Item {

@JsonProperty("service")
private Service service;
@JsonProperty("metrics")
private Metrics_ metrics;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("service")
public Service getService() {
return service;
}

@JsonProperty("service")
public void setService(Service service) {
this.service = service;
}

@JsonProperty("metrics")
public Metrics_ getMetrics() {
return metrics;
}

@JsonProperty("metrics")
public void setMetrics(Metrics_ metrics) {
this.metrics = metrics;
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