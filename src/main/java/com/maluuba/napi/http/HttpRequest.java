package com.maluuba.napi.http;

import java.net.URL;

/**
 * A basic container for a generic HTTP request.
 */
public class HttpRequest {
  public final URL url;
  
  /**
   * Constructs a new HTTP request.
   * @param url the URL to access.
   */
  public HttpRequest(URL url) {
    this.url = url;
  }
  
  /**
   * Returns the URL to access.
   * @return the URL to access.
   */
  public URL getUrl() {
    return url;
  }
}
