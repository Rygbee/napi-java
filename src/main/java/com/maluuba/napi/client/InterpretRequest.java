package com.maluuba.napi.client;

import java.net.URLEncoder;

/**
 * A request to the Maluuba nAPI {@code /interpret} endpoint.
 * 
 * @see <a href="http://developer.maluuba.com/interpret-api">http://developer.maluuba.com/interpret-api</a>
 */
public class InterpretRequest extends NAPIRequest {
  private final String phrase;
  
  /**
   * Constructs a new request for the {@code /interpret} endpoint.
   * 
   * @param phrase the phrase to categorize and extract entities for.
   */
  public InterpretRequest(String phrase) {
    this.phrase = phrase;
  }

  /**
   * Returns the phrase to categorize and extract entities for.
   * @return the phrase to categorize and extract entities for.
   */
  public String getPhrase() {
    return phrase;
  }

  /**
   * Constructs a query string for this request object. For private
   * use by the {@code HttpClient}.
   * 
   * @return a query string for the {@code /interpret} endpoint.
   */
  @SuppressWarnings("deprecation")
  @Override
  protected String toQueryString() {
    return String.format(
        "phrase=%s&apikey=%s",
        URLEncoder.encode(getPhrase()),
        getApiKey());
  }
}
