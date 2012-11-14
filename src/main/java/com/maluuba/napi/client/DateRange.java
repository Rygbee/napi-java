package com.maluuba.napi.client;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

import com.maluuba.napi.util.ObjectMapperPool;

/**
 * A class representing a span of time between two dates.
 *
 */
public class DateRange {
  @JsonProperty(value="start")
  private Date start;
  @JsonProperty(value="end")
  private Date end;
  
  /**
   * This constructor exists only for the serializer. It need never be called.
   */
  public DateRange() { }
  
  /**
   * Constructs a new DateRange from {@code start} to {@code end}.
   *
   * @param start the starting date of this range (inclusive)
   * @param end the end date of this range (exclusive)
   */
  public DateRange(Date start, Date end) {
    this.start = start;
    this.end = end;
  }
  
  /**
   * The starting date of this range. <b>Inclusive</b>. Only the year, month,
   * and day fields be filled in. The values of the other fields are undefined.
   * 
   * @return the starting date of this range.
   */
  public Date getStart() {
    return start;
  }
  
  /**
   * The end date of this range. <b>Exclusive</b>. Only the year, month,
   * and day fields will be filled in. The values of the other fields are undefined.
   * 
   * @return the end date of this range.
   */
  public Date getEnd() {
    return end;
  }
  
  @Override
  public String toString() {
    try {
      return ObjectMapperPool.INSTANCE.getMapper().writeValueAsString(this);
    } catch (IOException ioe) {
      return "";
    }
  }
}
