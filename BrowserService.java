package com.leti;

import java.awt.Desktop;
import java.net.URI;

/**
 * Handles browser operations using Java Desktop class
 * Separates browser logic from main application
 */
public class BrowserService {

    public void openArticleInBrowser(Article article) throws Exception {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URI(article.getUrl()));
                System.out.println("✅ Opening: " + article.getTitle());
                System.out.println("🔗 " + article.getUrl());
            } else {
                throw new Exception("Browser action not supported");
            }
        } else {
            throw new Exception("Desktop not supported");
        }
    }

    public boolean isBrowserSupported() {
        return Desktop.isDesktopSupported() &&
                Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
    }
}