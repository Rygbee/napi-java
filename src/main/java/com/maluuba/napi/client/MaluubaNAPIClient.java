package com.maluuba.napi.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


import com.maluuba.napi.http.HttpClient;
import com.maluuba.napi.http.HttpRequest;
import com.maluuba.napi.http.HttpResponse;
import com.maluuba.napi.http.NetHttpClient;
import com.maluuba.napi.util.ObjectMapperPool;

/**
 * The primary access point for the Maluuba nAPI. This object can be used to
 * access the {@code /interpret} and {@code /normalize} endpoints, and to
 * manipulate its results.
 *
 */
public class MaluubaNAPIClient {
  /**
   * The Maluuba nAPI host name.
   */
  public static final String BASE_NAPI_URL = "http://napi.maluuba.com";
  /**
   * The API version this client communicates with. At the moment, Maluuba nAPI is in
   * early Alpha, and so the version is set to 0.
   */
  public static final String NAPI_VERSION = "v0";
  
  private static final String NORMALIZE_ENDPOINT = "normalize";
  private static final String INTERPRET_ENDPOINT = "interpret";
  
  /**
   * The HTTP client used to execute requests.
   */
  private final HttpClient client;
  /**
   * The API key provided to you in the developer profile. Used to authenticate all
   * client requests.
   */
  private final String apiKey;
  
  /**
   * Constructs a new Maluuba nAPI client object with the specified API key.
   * By default, an HTTP client backend powered by {@code java.net} will be used.
   * 
   * @see <a href="http://developer.maluuba.com">http://developer.maluuba.com</a>
   * @param apiKey the API key provided to you in the developer profile
   */
  public MaluubaNAPIClient(String apiKey) {
    this.apiKey = apiKey;
    this.client = new NetHttpClient();
  }
  
  /**
   * Constructs a new Maluuba nAPI client object with the specified API key and
   * using the specified HTTP client backend.
   * 
   * @see <a href="http://developer.maluuba.com">http://developer.maluuba.com</a>
   * @param apiKey the API key provided to you in the developer profile
   * @param client the HTTP client backend
   */
  public MaluubaNAPIClient(String apiKey, HttpClient client) {
    this.apiKey = apiKey;
    this.client = client;
  }
  
  /**
   * Calls the {@code /interpret} endpoint with the provided request.
   * 
   * The Interpret API provides some interpretation information about a text in
   * standard form. The key pieces of information provided are Category, Action and Entities.
   * 
   * @see <a href="http://developer.maluuba.com/interpret-api">http://developer.maluuba.com/interpret-api</a>
   * @param request the request to interpret.
   * @return a response containing a Category, an Action, and a collection of Entities.
   * @throws IOException if there was a problem communicating with the Maluuba nAPI server.
   */
  public InterpretResponse interpret(InterpretRequest request) throws IOException {
    return executeRequest(INTERPRET_ENDPOINT, request, InterpretResponse.class);
  }
  
  /**
   * Calls the {@code /normalize} endpoint with the provided request.
   * 
   * The Normalize API provides direct access to nAPI Time and Date normalizers. This is useful
   * for conversions of single word or short phrase descriptions of times or dates into a standard
   * format that is easier to develop with. Normalizers work best on descriptions of dates and
   * times, such as "in an hour" or "midnight". Times with punctuation or already in a standard
   * format don't work, eg "12:00:00 AM". For relative values, times are returned in UTC unless
   * specified.
   * 
   * @see <a href="http://developer.maluuba.com/normalize-api">http://developer.maluuba.com/normalize-api</a>
   * @param request the request to normalize.
   * @return a response containing a normalized entity.
   * @throws IOException if there was a problem communicating with the Maluuba nAPI server.
   */
  public NormalizeResponse normalize(NormalizeRequest request) throws IOException {
    return executeRequest(NORMALIZE_ENDPOINT, request, NormalizeResponse.class);
  }

  private <T> T executeRequest(String endpoint, NAPIRequest request, Class<T> responseClass) throws IOException {
    request.setApiKey(apiKey);
    HttpResponse response = client.request(new HttpRequest(getUrlForEndpoint(endpoint, request.toQueryString())));
    return ObjectMapperPool.INSTANCE.getMapper().readValue(response.getContent(), responseClass);
  }
  
  private static URL getUrlForEndpoint(String endpoint, String queryString) throws MalformedURLException {
    return new URL(String.format("%s/%s/%s?%s",
        BASE_NAPI_URL,
        NAPI_VERSION,
        endpoint,
        queryString));
  }
}
