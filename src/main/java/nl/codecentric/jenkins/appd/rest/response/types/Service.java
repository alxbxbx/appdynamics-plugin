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
"id",
"label",
"types",
"technologies",
"entityType"
})
public class Service {

@JsonProperty("id")
private String id;
@JsonProperty("label")
private String label;
@JsonProperty("types")
private List<String> types = null;
@JsonProperty("technologies")
private List<String> technologies = null;
@JsonProperty("entityType")
private String entityType;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("id")
public String getId() {
return id;
}

@JsonProperty("id")
public void setId(String id) {
this.id = id;
}

@JsonProperty("label")
public String getLabel() {
return label;
}

@JsonProperty("label")
public void setLabel(String label) {
this.label = label;
}

@JsonProperty("types")
public List<String> getTypes() {
return types;
}

@JsonProperty("types")
public void setTypes(List<String> types) {
this.types = types;
}

@JsonProperty("technologies")
public List<String> getTechnologies() {
return technologies;
}

@JsonProperty("technologies")
public void setTechnologies(List<String> technologies) {
this.technologies = technologies;
}

@JsonProperty("entityType")
public String getEntityType() {
return entityType;
}

@JsonProperty("entityType")
public void setEntityType(String entityType) {
this.entityType = entityType;
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
