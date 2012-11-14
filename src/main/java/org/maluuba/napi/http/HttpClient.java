package org.maluuba.napi.http;

import java.io.IOException;

public interface HttpClient {

  public HttpResponse request(HttpRequest request) throws IOException;
}
