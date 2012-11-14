package com.maluuba.napi.http;

import java.util.List;
import java.util.Map;

/**
 * A basic container for a generic HTTP response.
 *
 */
public class HttpResponse {
  private final String content;
  private final Map<String, List<String>> headers;
  
  /**
   * Constructs a new HTTP response.
   * 
   * @param content the content in the payload of the HTTP response entity.
   * @param headers the headers returned by the HTTP server.
   */
  public HttpResponse(String content, Map<String, List<String>> headers) {
    this.content = content;
    this.headers = headers;
  }
  
  /**
   * Returns the content in the payload of the HTTP response entity.
   * 
   * @return the content in the payload of the HTTP response entity.
   */
  public String getContent() {
    return content;
  }
  
  /**
   * Returns the headers returned by the HTTP server.
   * 
   * @return the headers returned by the HTTP server.
   */
  public Map<String, List<String>> getHeaders() {
    return headers;
  }
}
