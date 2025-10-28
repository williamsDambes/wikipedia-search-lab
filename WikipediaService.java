package com.leti;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;

/**
 * Service class for Wikipedia API interactions
 * Handles HTTP requests and JSON parsing
 */
public class WikipediaService {
    private final String apiUrl;
    private final String userAgent;

    public WikipediaService() {
        this.apiUrl = "https://ru.wikipedia.org/w/api.php";
        this.userAgent = "University-Lab-Project/1.0";
    }

    /**
     * Performs search using Wikipedia API
     * @param query search term
     * @return SearchResult containing articles
     */
    public SearchResult search(String query) throws Exception {
        String jsonResponse = executeSearch(query);
        return parseSearchResults(jsonResponse);
    }

    private String executeSearch(String query) throws Exception {
        String encodedQuery = URLEncoder.encode(query, "UTF-8");
        String urlString = buildSearchUrl(encodedQuery);

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", userAgent);

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        return response.toString();
    }

    private String buildSearchUrl(String encodedQuery) {
        return apiUrl + "?action=query&list=search&format=json&srlimit=10&srsearch=" + encodedQuery;
    }

    private SearchResult parseSearchResults(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonArray searchArray = jsonObject.getAsJsonObject("query").getAsJsonArray("search");

        List<Article> articles = new ArrayList<>();
        for (int i = 0; i < searchArray.size(); i++) {
            JsonObject articleJson = searchArray.get(i).getAsJsonObject();
            Article article = new Article(
                    articleJson.get("pageid").getAsInt(),
                    articleJson.get("title").getAsString(),
                    articleJson.get("snippet").getAsString()
            );
            articles.add(article);
        }

        return new SearchResult(articles);
    }
}