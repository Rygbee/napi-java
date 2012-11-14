package com.maluuba.napi.client;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

import com.maluuba.napi.util.ObjectMapperPool;

/**
 * A class representing a span of time between two times.
 *
 */
public class TimeRange {
  @JsonProperty(value="start")
  private Date start;
  @JsonProperty(value="end")
  private Date end;
  
  /**
   * This constructor exists only for the serializer. It need never be called.
   */
  public TimeRange() { }
  
  /**
   * Constructs a new TimeRange from {@code start} to {@code end}.
   * 
   * @param start the starting time of this range (inclusive)
   * @param end the ending time of this range (inclusive)
   */
  public TimeRange(Date start, Date end) {
    this.start = start;
    this.end = end;
  }
  
  /**
   * The starting time of this range. <b>Inclusive</b>. Only the hour, minute,
   * and second fields will be filled in. The values of the other fields are
   * undefined.
   * 
   * @return the starting time of this range.
   */
  public Date getStart() {
    return start;
  }
  
  /**
   * The ending time of this range. <b>Inclusive</b>. Only the hour, minute,
   * and second fields will be filled in. The values of the other fields are
   * undefined.
   * 
   * @return the ending time of this range.
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
