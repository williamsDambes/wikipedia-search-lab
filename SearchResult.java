package com.leti;

import java.util.ArrayList;
import java.util.List;

/**
 * Container for search results with utility methods
 * Demonstrates composition with Article objects
 */
public class SearchResult {
    private final List<Article> articles;
    private final int totalCount;

    public SearchResult(List<Article> articles) {
        this.articles = articles != null ? articles : new ArrayList<>();
        this.totalCount = this.articles.size();
    }

    // Business methods
    public List<Article> getArticles() { return articles; }
    public int getTotalCount() { return totalCount; }
    public boolean hasResults() { return !articles.isEmpty(); }
    public Article getArticle(int index) {
        return articles.get(index);
    }

    public void displayResults() {
        if (!hasResults()) {
            System.out.println("📭 No results found.");
            return;
        }

        System.out.println("\n📚 FOUND RESULTS: " + totalCount);
        System.out.println("==========================");

        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            System.out.println((i + 1) + ". " + article.getTitle());
            String cleanSnippet = article.getSnippet().replaceAll("<[^>]+>", "").trim();
            if (!cleanSnippet.isEmpty()) {
                System.out.println("   " + cleanSnippet);
            }
            System.out.println();
        }
    }
}