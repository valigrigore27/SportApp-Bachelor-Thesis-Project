package com.example.sport.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.json.JSONObject;

@Service
public class GoogleSearchService {

    @Value("${google.api.key}")
    private String apiKey;

    @Value("${google.search.cx}")
    private String cx;

    private final RestTemplate restTemplate;

    public GoogleSearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getFirstImageUrl(String searchQuery) {

        searchQuery += " gif";

        // URL-ul pentru cererea API
        String url = UriComponentsBuilder.fromHttpUrl("https://www.googleapis.com/customsearch/v1")
                .queryParam("key", apiKey)
                .queryParam("cx", cx)
                .queryParam("q", searchQuery)
                .queryParam("searchType", "image")
//                .queryParam("rights", "cc_publicdomain") // pt imagini cu drept de autor
                .toUriString();

        //  cererea
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println("API Response: " + response.getBody());
        // luam prima imagine
        JSONObject jsonResponse = new JSONObject(response.getBody());
        if (jsonResponse.has("items")) {
            // Extragem URL-ul primei imagini
            return jsonResponse.getJSONArray("items").getJSONObject(0).getString("link");
        }

        return null;
    }
}
