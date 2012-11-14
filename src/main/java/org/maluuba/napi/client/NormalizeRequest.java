package org.maluuba.napi.client;

import java.net.URLEncoder;
import java.util.TimeZone;


public class NormalizeRequest extends NAPIRequest {
  private final String phrase;
  private final NormalizeEntityType type;
  private final TimeZone timezone;
  
  public NormalizeRequest(String phrase, NormalizeEntityType type, TimeZone timezone) {
    this.phrase = phrase;
    this.type = type;
    this.timezone = timezone;
  }

  public String getPhrase() {
    return phrase;
  }
  
  public NormalizeEntityType getType() {
    return type;
  }
  
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
