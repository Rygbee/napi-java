package com.maluuba.napi.util;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * A singleton instance of Jackson's ObjectMapper to use throughout the client
 * library.
 * 
 * This ObjectMapper is configured to ignore unknown properties.
 */
public enum ObjectMapperPool {
  INSTANCE;
  
  private final ObjectMapper mapper;
  
  private ObjectMapperPool() {
    mapper = new ObjectMapper();
    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }
  
  public ObjectMapper getMapper() {
    return mapper;
  }
}
