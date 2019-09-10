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
"items",
"page",
"pageSize",
"totalHits"
})
public class MetricsResponse {

@JsonProperty("items")
private List<Item> items = null;
@JsonProperty("page")
private Integer page;
@JsonProperty("pageSize")
private Integer pageSize;
@JsonProperty("totalHits")
private Integer totalHits;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("items")
public List<Item> getItems() {
return items;
}

@JsonProperty("items")
public void setItems(List<Item> items) {
this.items = items;
}

@JsonProperty("page")
public Integer getPage() {
return page;
}

@JsonProperty("page")
public void setPage(Integer page) {
this.page = page;
}

@JsonProperty("pageSize")
public Integer getPageSize() {
return pageSize;
}

@JsonProperty("pageSize")
public void setPageSize(Integer pageSize) {
this.pageSize = pageSize;
}

@JsonProperty("totalHits")
public Integer getTotalHits() {
return totalHits;
}

@JsonProperty("totalHits")
public void setTotalHits(Integer totalHits) {
this.totalHits = totalHits;
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
