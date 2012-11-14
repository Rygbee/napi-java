package com.maluuba.napi.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

import com.maluuba.napi.util.EntityUtils;

/**
 * The response for a successful query to the {@code /interpret} endpoint of the
 * Maluuba nAPI.
 * 
 * @see <a href="http://developer.maluuba.com/interpret-api">http://developer.maluuba.com/interpret-api</a>
 */
public class InterpretResponse {
  @JsonProperty(value="entities")
  private Map<String, List<Object>> entities = new HashMap<String, List<Object>>();
  @JsonProperty(value="category")
  private NAPICategory category;
  @JsonProperty(value="action")
  private NAPIAction action;
  
  /**
   * This constructor exists only for the serializer. It need never be called.
   */
  public InterpretResponse() { }
  
  /**
   * This constructor exists only for the serializer. It need never be called.
   */
  protected InterpretResponse(NAPICategory category, NAPIAction action) {
    this.category = category;
    this.action = action;
  }
  
  /**
   * Returns the category chosen for the provided phrase.
   * 
   * @see <a href="http://developer.maluuba.com/categories">http://developer.maluuba.com/categories</a>
   * @return the category chosen for the provided phrase.
   */
  public NAPICategory getCategory() {
    return category;
  }
  
  /**
   * Returns the action within the chosen category for the provided phrase.
   * 
   * @see <a href="http://developer.maluuba.com/categories">http://developer.maluuba.com/categories</a>
   * @return the action within the chosen category
   */
  public NAPIAction getAction() {
    return action;
  }
  
  /**
   * This method exists only for the serializer. It need never be called.
   */
  public void setEntities(Map<String, List<Object>> entities) {
    this.entities = EntityUtils.convertEntities(entities);
  }
  
  /**
   * Returns the entities extracted for the provided phrase.
   * 
   * Multiple entities of the same type will be collected into a List<>.
   * However, the vast majority of entities only appear once. 
   * 
   * @see <a href="http://developer.maluuba.com/interpret-api">http://developer.maluuba.com/interpret-api</a>
   * @return the entities extracted for the provided phrase.
   */
  public Map<String, List<Object>> getEntities() {
    return entities;
  }
}
