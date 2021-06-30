package org.vadym.demo.server.storage;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * In-memory storage where all the documents are stored.
 *
 * @author Vadym Pechenoha
 */
@Component
public class InMemoryStorage implements SearchEngineStorage {

    private final Map<String, String> documents = new HashMap<>();

    @Override
    public void put(String documentKey, String documentContents) {
        documents.put(documentKey, documentContents);
    }

    @Override
    public Optional<String> get(String documentKey) {
        return Optional.ofNullable(documents.get(documentKey));
    }
}
