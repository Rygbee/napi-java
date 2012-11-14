package org.maluuba.napi.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;
import org.maluuba.napi.util.EntityUtils;

public class NormalizeResponse {
  @JsonProperty(value="entities")
  private Map<String, List<Object>> entities = new HashMap<String, List<Object>>();
  @JsonProperty(value="context")
  private NormalizeContext context;
  
  public NormalizeResponse() { }
  
  protected NormalizeResponse(NormalizeContext context) {
    this.context = context;
    this.entities = new HashMap<String, List<Object>>();
  }
  
  public NormalizeContext getContext() {
    return context;
  }
  
  public Map<String, List<Object>> getEntities() {
    return entities;
  }
  
  public void setEntities(Map<String, List<Object>> entities) {
    this.entities = EntityUtils.convertEntities(entities);
  }
  
}
