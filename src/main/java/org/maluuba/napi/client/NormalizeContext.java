package org.maluuba.napi.client;

import java.util.TimeZone;

public class NormalizeContext {
  private final TimeZone timezone;
  
  public NormalizeContext(TimeZone timezone) {
    this.timezone = timezone;
  }
  
  public TimeZone getTimezone() {
    return timezone;
  }
}
