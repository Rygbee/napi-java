package org.maluuba.napi.util;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

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
