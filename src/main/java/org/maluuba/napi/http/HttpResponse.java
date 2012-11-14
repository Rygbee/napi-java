package org.maluuba.napi.http;

import java.util.List;
import java.util.Map;

public class HttpResponse {
  private final String content;
  private final Map<String, List<String>> headers;
  
  public HttpResponse(String content, Map<String, List<String>> headers) {
    this.content = content;
    this.headers = headers;
  }
  
  public String getContent() {
    return content;
  }
  
  public Map<String, List<String>> getHeaders() {
    return headers;
  }
}
