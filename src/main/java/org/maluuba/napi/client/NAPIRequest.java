package org.maluuba.napi.client;

import org.codehaus.jackson.annotate.JsonProperty;

public abstract class NAPIRequest {
  protected String apiKey;
  
  @JsonProperty(value="apikey")
  protected String getApiKey() {
    return apiKey;
  }
  
  protected void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }
  
  protected abstract String toQueryString();
}
