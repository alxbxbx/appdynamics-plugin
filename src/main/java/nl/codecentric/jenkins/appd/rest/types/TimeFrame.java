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
"windowSize",
"to"
})
public class TimeFrame {

@JsonProperty("windowSize")
private Integer windowSize;
@JsonProperty("to")
private Long to;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("windowSize")
public Integer getWindowSize() {
return windowSize;
}

@JsonProperty("windowSize")
public void setWindowSize(Integer windowSize) {
this.windowSize = windowSize;
}

@JsonProperty("to")
public Long getTo() {
return to;
}

@JsonProperty("to")
public void setTo(Long to) {
this.to = to;
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
