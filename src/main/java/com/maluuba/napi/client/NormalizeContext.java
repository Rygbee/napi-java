package com.maluuba.napi.client;

import java.util.TimeZone;

/**
 * Contextual information about a given query, provided by the
 * {@code /normalize} endpoint. Currently only contains a timezone.
 * 
 * @see <a href="http://developer.maluuba.com/normalize-api">http://developer.maluuba.com/normalize-api</a>
 *
 */
public class NormalizeContext {
  private TimeZone timezone;
  
  public NormalizeContext() {}
  
  public NormalizeContext(TimeZone timezone) {
    this.timezone = timezone;
  }
  
  /**
   * Returns the timezone used for relative time normalization in the
   * corresponding normalize request.
   * 
   * @return the time zone used for relative time normalization.
   */
  public TimeZone getTimezone() {
    return timezone;
  }
}
