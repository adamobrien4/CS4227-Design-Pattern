package main.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpService {
    private static final HttpClient client = HttpClient.newHttpClient();

    private HttpService(){}

    public static HttpClient getClient() {
        return client;
    }

    public static String get(String uri) {
        System.out.println("Getting URI: " + uri);
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(uri))
                .build();

        return handleResponse(request);
    }

    public static String post(String uri, String queryBody) {
        System.out.println(queryBody);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.ofString(queryBody))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        return handleResponse(request);
    }

    public static String delete(String uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .DELETE()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        return handleResponse(request);
    }

    private static String handleResponse(HttpRequest request) {
        try {
            HttpResponse<String> response = HttpService.getClient().send(request, HttpResponse.BodyHandlers.ofString());

            if( response.statusCode() == 200 && response.body().length() > 0){
                return response.body();
            } else {
                System.out.println("API request returned with an error");
                System.out.println(response.body());
                return null;
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
