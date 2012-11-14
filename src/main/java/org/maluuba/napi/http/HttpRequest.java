package org.maluuba.napi.http;

import java.net.URL;

public class HttpRequest {
  public final URL url;
  
  public HttpRequest(URL url) {
    this.url = url;
  }
  
  public URL getUrl() {
    return url;
  }
}
