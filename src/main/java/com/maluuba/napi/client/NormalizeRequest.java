package com.maluuba.napi.client;

import java.net.URLEncoder;
import java.util.TimeZone;

/**
 * A request to the Maluuba nAPI {@code /normalize} endpoint.
 * 
   * @see <a href="http://developer.maluuba.com/normalize-api">http://developer.maluuba.com/normalize-api</a>
 */
public class NormalizeRequest extends NAPIRequest {
  private final String phrase;
  private final NormalizeEntityType type;
  private final TimeZone timezone;
  
  /**
   * Constructs a new request for the {@code /normalize} endpoint.
   * @param phrase the entity to normalize
   * @param type the type of entity
   * @param timezone the timezone in which {@code phrase} is expressed (optional)
   */
  public NormalizeRequest(String phrase, NormalizeEntityType type, TimeZone timezone) {
    this.phrase = phrase;
    this.type = type;
    this.timezone = timezone;
  }

  /**
   * Returns the entity to normalize.
   * @return the entity to normalize.
   */
  public String getPhrase() {
    return phrase;
  }
  
  /**
   * Returns the type of entity.
   * @return the type of entity .
   */
  public NormalizeEntityType getType() {
    return type;
  }
  
  /**
   * Returns the timezone in which {@code phrase} is expressed. (optional)
   * @return the timezone in which {@code phrase} is expressed, or {@code null}.
   */
  public TimeZone getTimezone() {
    return timezone;
  }
  
  @SuppressWarnings("deprecation")
  @Override
  protected String toQueryString() {
    return String.format(
        "phrase=%s&type=%s&apikey=%s&timezone=%s",
        URLEncoder.encode(getPhrase()),
        getType(),
        getApiKey(),
        getTimezone().getDisplayName(false, TimeZone.SHORT));
  }
}
