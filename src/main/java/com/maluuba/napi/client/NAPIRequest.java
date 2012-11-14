package com.maluuba.napi.client;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * An internal utility class representing a request to
 * the Maluuba nAPI.
 * 
 * This class will probably never need to be directly
 * interacted with. Use specific subclasses for the endpoint
 * you're trying to access.
 */
public abstract class NAPIRequest {
  protected String apiKey;
  
  /**
   * The API key each request is signed with.
   * 
   * @see <a href="http://developer.maluuba.com">http://developer.maluuba.com</a>
   * @return the API key provided to you in the developer profile
   */
  @JsonProperty(value="apikey")
  protected String getApiKey() {
    return apiKey;
  }
  
  /**
   * Sets the API key to authenticate this request.
   * 
   * This never needs to be called directly. The client will take care
   * of authenticating each request.
   * 
   * @param apiKey the API key provided to you in the developer profile
   */
  protected void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }
  
  protected abstract String toQueryString();
}
