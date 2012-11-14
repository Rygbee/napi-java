package org.maluuba.napi.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.maluuba.napi.http.HttpClient;
import org.maluuba.napi.http.HttpRequest;
import org.maluuba.napi.http.HttpResponse;
import org.maluuba.napi.http.NetHttpClient;
import org.maluuba.napi.util.ObjectMapperPool;

public class MaluubaNAPIClient {
  public static final String BASE_NAPI_URL = "http://napi.maluuba.com";
  public static final String NAPI_VERSION = "v0";
  
  private static final String NORMALIZE_ENDPOINT = "normalize";
  private static final String INTERPRET_ENDPOINT = "interpret";
  
  private final HttpClient client;
  private final String apiKey;
  
  public MaluubaNAPIClient(String apiKey) {
    this.apiKey = apiKey;
    this.client = new NetHttpClient();
  }
  
  public MaluubaNAPIClient(String apiKey, HttpClient client) {
    this.apiKey = apiKey;
    this.client = client;
  }
  
  public InterpretResponse interpret(InterpretRequest request) throws IOException {
    return executeRequest(INTERPRET_ENDPOINT, request, InterpretResponse.class);
  }
  
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
