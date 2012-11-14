package org.maluuba.napi.client;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.maluuba.napi.util.ObjectMapperPool;

public class TimeRange {
  @JsonProperty(value="start")
  private Date start;
  @JsonProperty(value="end")
  private Date end;
  
  public TimeRange() { }
  
  public TimeRange(Date start, Date end) {
    this.start = start;
    this.end = end;
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
