package org.maluuba.napi.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;
import org.maluuba.napi.util.EntityUtils;


public class InterpretResponse {
  @JsonProperty(value="entities")
  private Map<String, List<Object>> entities = new HashMap<String, List<Object>>();
  @JsonProperty(value="category")
  private NAPICategory category;
  @JsonProperty(value="action")
  private NAPIAction action;
  
  public InterpretResponse() { }
  
  protected InterpretResponse(NAPICategory category, NAPIAction action) {
    this.category = category;
    this.action = action;
  }
  
  public NAPICategory getCategory() {
    return category;
  }
  
  public NAPIAction getAction() {
    return action;
  }
  
  public void setEntities(Map<String, List<Object>> entities) {
    this.entities = EntityUtils.convertEntities(entities);
  }
  
  /*
  @JsonProperty(value="entities")
  public void setEntities(JsonNode node) {
    for (JsonNode entity : node) {
      String entityName = entity.getFieldNames().next();
      if (!entities.containsKey(entityName)) {
        entities.put(entityName, new LinkedList<Object>());
      }
      try {
        entities.get(entityName).add(
            ObjectMapperPool.INSTANCE.getMapper().readValue(
                entity.get(entityName),
                EntityUtils.getValueTypeForEntity(entityName)));
      } catch (IOException ioe) {
        // TODO(apetresc): Handle this
      }
    }
  }
  */
  
  public Map<String, List<Object>> getEntities() {
    return entities;
  }
}
