package org.maluuba.napi.client;

import java.net.URLEncoder;

public class InterpretRequest extends NAPIRequest {
  private final String phrase;
  
  public InterpretRequest(String phrase) {
    this.phrase = phrase;
  }
    
  public String getPhrase() {
    return phrase;
  }
    
  @SuppressWarnings("deprecation")
  @Override
  protected String toQueryString() {
    return String.format(
        "phrase=%s&apikey=%s",
        URLEncoder.encode(getPhrase()),
        getApiKey());
  }
}
