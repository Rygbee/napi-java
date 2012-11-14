package com.maluuba.napi.http;

import java.io.IOException;

/**
 * A simple interface representing an HTTP client that can communicate
 * with the Maluuba nAPI server.
 * 
 * The developer only needs to implement this interface if he wishes to
 * use a different HTTP backend for the Maluuba nAPI client. This would
 * be necessary if, for instance, {@code java.net} was unavailable in
 * your Java environment, or if you wanted to use a client backed by
 * a common thread or connection pool.
 */
public interface HttpClient {

  /**
   * Executes the given HTTP request.
   * 
   * @param request the HTTP request to execute.
   * @return the response returned by the HTTP server.
   * @throws IOException if there was a problem communicating with the server.
   */
  public HttpResponse request(HttpRequest request) throws IOException;
}
