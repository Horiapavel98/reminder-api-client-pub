package com.horia.reminder.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class RequestBuilder {

    static int postNewReminder(Reminder reminder, String endpoint) throws IOException, URISyntaxException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        Map<String, String> body = new HashMap<>();
        body.put("name", reminder.getName());
        body.put("description", reminder.getDescription());
        body.put("dueDate", reminder.getDate());
        body.put("receiverPhoneNumber", reminder.getPhoneNumber());

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(body);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(endpoint + "/reminders"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

}
