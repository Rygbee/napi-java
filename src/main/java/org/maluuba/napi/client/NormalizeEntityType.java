package org.maluuba.napi.client;

public enum NormalizeEntityType {
  TIME("time"),
  DATE_RANGE("daterange"),
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
