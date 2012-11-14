package com.maluuba.napi.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

import com.maluuba.napi.util.EntityUtils;

/**
 * The response for a successful query to the {@code /normalize} endpoint of the
 * Maluuba nAPI.
 * 
   * @see <a href="http://developer.maluuba.com/normalize-api">http://developer.maluuba.com/normalize-api</a>
 */
public class NormalizeResponse {
  @JsonProperty(value="entities")
  private Map<String, List<Object>> entities = new HashMap<String, List<Object>>();
  @JsonProperty(value="context")
  private NormalizeContext context;
  
  /**
   * This constructor exists only for the serializer. It need never be called.
   */
  public NormalizeResponse() { }
  
  /**
   * This constructor exists only for the serializer. It need never be called.
   */
  protected NormalizeResponse(NormalizeContext context) {
    this.context = context;
    this.entities = new HashMap<String, List<Object>>();
  }
  
  /**
   * Returns contextual information about the provided query.
   * @return contextual information about the provided query.
   */
  public NormalizeContext getContext() {
    return context;
  }
  
  /**
   * Returns a list of normalized entities.
   * @return a list of normalized entities.
   */
  public Map<String, List<Object>> getEntities() {
    return entities;
  }
  
  /**
   * This exists only for the serializer. It need never be called.
   */
  public void setEntities(Map<String, List<Object>> entities) {
    this.entities = EntityUtils.convertEntities(entities);
  }
  
}
