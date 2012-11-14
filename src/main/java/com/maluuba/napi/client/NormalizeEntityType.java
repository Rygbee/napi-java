package com.maluuba.napi.client;

/**
 * The possible entity types that can be normalized by the
 * {@code /normalize} endpoint.
 * 
 * @see <a href="http://developer.maluuba.com/normalize-api">http://developer.maluuba.com/normalize-api</a>
 *
 */
public enum NormalizeEntityType {
  /**
   * A single instant in time (such as "five o'clock")
   */
  TIME("time"),
  /**
   * A range of dates.
   * 
   * @see DateRange
   */
  DATE_RANGE("daterange"),
  /**
   * A range of times.
   * 
   * @see TimeRange
   */
  TIME_RANGE("timerange");
  
  private final String name;
  
  private NormalizeEntityType(String name) {
    this.name = name;
  }
  
  @Override
  public String toString() {
    return name;
  }
}
