package com.leti;

import java.util.Scanner;

/**
 * Main application class coordinating all components
 * Demonstrates OOP principles and class relationships
 */
public class WikipediaSearch {
    private final WikipediaService wikipediaService;
    private final BrowserService browserService;
    private final Scanner scanner;

    public WikipediaSearch() {
        this.wikipediaService = new WikipediaService();
        this.browserService = new BrowserService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main entry point of the application
     */
    public static void main(String[] args) {
        WikipediaSearch app = new WikipediaSearch();
        app.run();
    }

    /**
     * Main application loop
     */
    public void run() {
        displayWelcomeMessage();

        while (true) {
            try {
                String query = getSearchQuery();
                if (shouldExit(query)) break;

                SearchResult results = wikipediaService.search(query);
                results.displayResults();

                if (results.hasResults()) {
                    handleArticleSelection(results);
                }

            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("👋 Program finished.");
    }

    private void displayWelcomeMessage() {
        System.out.println("=== WIKIPEDIA SEARCH APPLICATION ===");
        System.out.println("Object-Oriented Programming Lab");
        System.out.println("===================================");
    }

    private String getSearchQuery() {
        System.out.print("\n🔍 Enter search query: ");
        return scanner.nextLine();
    }

    private boolean shouldExit(String query) {
        return "exit".equalsIgnoreCase(query);
    }

    private void handleArticleSelection(SearchResult results) {
        System.out.print("🌐 Enter article number to open (0 to cancel): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0 && choice <= results.getTotalCount()) {
                Article selected = results.getArticle(choice - 1);
                browserService.openArticleInBrowser(selected);
            } else if (choice != 0) {
                System.out.println("❌ Invalid article number");
            }
        } catch (Exception e) {
            System.out.println("❌ Error opening article: " + e.getMessage());
        }
    }
}