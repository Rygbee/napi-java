package org.maluuba.napi.client;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class ContactsList {
  @JsonProperty(value="start")
  private Date start;
  @JsonProperty(value="end")
  private Date end;
  
  public ContactsList() { }
  
  public ContactsList(Date start, Date end) {
    this.start = start;
    this.end = end;
  }
  
  public Date getStart() {
    return start;
  }
  
  public Date getEnd() {
    return end;
  }
}
