package org.vadym.demo.client.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryStorageTest {

    private final InMemoryStorage storage = new InMemoryStorage();

    @Test
    public void getNonExistingDocumentTest() {
        var result = storage.get("non_existing_document");
        assertTrue(result.isEmpty(), "the result should be an empty Optional");
    }

    @Test
    public void createAndGetSingleDocumentTest() {
        var documentKey = "some_document";
        var documentContents = "some tokens go here";

        storage.put(documentKey, documentContents);
        var result = storage.get(documentKey);
        assertFalse(result.isEmpty(), "the result shouldn't be an empty Optional");
        assertEquals(documentContents, result.get());
    }

    @Test
    public void createAndGetMultipleDocumentsTest() {
        for (int i = 0; i < 10; i++) {
            var documentKey = "document_" + i;
            var documentContents = "tokenA_" + i + " tokenB_" + i;

            storage.put(documentKey, documentContents);
            var result = storage.get(documentKey);
            assertFalse(result.isEmpty(), "the result shouldn't be an empty Optional");
            assertEquals(documentContents, result.get());
        }
    }
}
