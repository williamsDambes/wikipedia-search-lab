package com.leti;

/**
 * Represents a Wikipedia article with its properties
 * Encapsulates article data and provides access methods
 */
public class Article {
    private final int pageId;
    private final String title;
    private final String snippet;
    private final String url;

    public Article(int pageId, String title, String snippet) {
        this.pageId = pageId;
        this.title = title;
        this.snippet = snippet;
        this.url = "https://ru.wikipedia.org/w/index.php?curid=" + pageId;
    }

    // Getters
    public int getPageId() { return pageId; }
    public String getTitle() { return title; }
    public String getSnippet() { return snippet; }
    public String getUrl() { return url; }

    @Override
    public String toString() {
        return String.format("Article{title='%s', pageId=%d}", title, pageId);
    }
}