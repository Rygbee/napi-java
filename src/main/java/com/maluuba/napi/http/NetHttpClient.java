package com.maluuba.napi.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A basic implementation of {@code HttpClient} backed by {@link HttpURLConnection}
 *
 */
public class NetHttpClient implements HttpClient {

  /**
   * The maximum amount of times to attempt a failed connection.
   */
  private static final int MAX_RETRIES = 3;
  /**
   * The amount of time (in milliseconds) to wait for a connection to be established.
   */
  private static final int CONNECT_TIMEOUT = 5 * 1000;
  /**
   * The amount of time (in milliseconds) to wait for a connection's input stream to
   * read the server response.
   */
  private static final int READ_TIMEOUT = 10 * 1000;
  
  private final Logger log = Logger.getLogger(NetHttpClient.class.getName());
  
  /**
   * @see HttpClient#request(HttpRequest)
   */
  public HttpResponse request(HttpRequest request) throws IOException{
    for (int retryCount = 0; retryCount < MAX_RETRIES; retryCount++) {
      HttpURLConnection con = null;
      try {
        con = (HttpURLConnection)request.getUrl().openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(CONNECT_TIMEOUT);
        con.setReadTimeout(READ_TIMEOUT);
          
        con.connect();
          
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        for (String line = in.readLine(); line != null; line = in.readLine()) {
          sb.append(line + '\n');
        }
        
        return new HttpResponse(sb.toString(), con.getHeaderFields());
      } catch (IOException ioe) {
        log.log(
            Level.WARNING,
            String.format("[%d/%d] Failed to access URL: %s", retryCount + 1, MAX_RETRIES, request.getUrl()), ioe);
      } finally {
        con.disconnect();
      }
    }
    
    throw new IOException(String.format("Connection failed after %d retries", MAX_RETRIES));
  }

}
