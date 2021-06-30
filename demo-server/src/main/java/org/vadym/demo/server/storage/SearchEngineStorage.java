package org.vadym.demo.server.storage;

import java.util.Optional;

/**
 * Storage where all the documents are stored.
 *
 * @author Vadym Pechenoha
 */
public interface SearchEngineStorage {

    /**
     * Populates the storage with a new document: its key and contents.
     *
     * @param documentKey key of the document
     * @param documentContents a String of words separated by spaces
     */
    void put(String documentKey, String documentContents);

    /**
     * Retrieves a requested document by its key from the storage.
     *
     * @param documentKey key of the document
     * @return document contents represented in form of a String of words separated by spaces.
     *         The result is wrapped in {@link Optional}. Empty Optional is returned in case
     *         if the requested document is absent in the storage.
     */
    Optional<String> get(String documentKey);
}
