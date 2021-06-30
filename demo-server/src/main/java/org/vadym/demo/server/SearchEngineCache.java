package org.vadym.demo.server;

import java.util.Set;

/**
 * Cache that enables quick access to the documents containing given tokens.
 *
 * @author Vadym Pechenoha
 */
public interface SearchEngineCache {

    /**
     * Populates the cache with a new document.
     *
     * @param documentKey key of the document
     * @param documentContents a String of words separated by spaces
     */
    void put(String documentKey, String documentContents);

    /**
     * Finds those documents that contain all the given tokens.
     *
     * @param tokens a String of words separated by spaces
     * @return a set of keys of those documents that contain all the given tokens
     */
    Set<String> find(String tokens);
}
