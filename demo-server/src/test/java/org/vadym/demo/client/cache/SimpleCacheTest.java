package org.vadym.demo.client.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleCacheTest {

    private final SimpleCache cache = new SimpleCache();

    @Test
    public void findNonExistingTokensTest() {
        var result = cache.find("no_existing_token1 no_existing_token2");
        assertTrue(result.isEmpty(), "the result should be an empty set");
    }

    @Test
    public void findNonExistingTokenInNonEmptyCacheTest() {
        cache.put("some_document", "existing_token some_other_token");
        var result = cache.find("no_existing_token");
        assertTrue(result.isEmpty(), "the result should be an empty set");
    }

    @Test
    public void findOneTokenInOneDocumentTest() {
        var documentKey = "my_document";
        cache.put(documentKey, "existing_token some_other_token");
        var result = cache.find("existing_token");

        assertFalse(result.isEmpty(), "the result should not be an empty set");
        assertEquals(1, result.size(), "there is only a single document having the requested token");
        assertTrue(result.contains(documentKey), "the result should contain the right document key");
    }

    @Test
    public void findOneTokenInMultipleDocumentsTest() {
        for (int i = 0; i < 3; i++) {
            var documentKey = "my_document_" + i;
            cache.put(documentKey, "common_token some_other_token_" + i);
        }
        cache.put("other_document", "its own tokens");
        var result = cache.find("common_token");

        assertFalse(result.isEmpty(), "the result should not be an empty set");
        assertEquals(3, result.size(), "there are three documents having the requested token");
        assertTrue(result.contains("my_document_0"), "the result should contain all the right document keys");
        assertTrue(result.contains("my_document_1"), "the result should contain all the right document keys");
        assertTrue(result.contains("my_document_2"), "the result should contain all the right document keys");
    }

    @Test
    public void findMultipleTokensInMultipleDocumentsTest() {
        for (int i = 0; i < 3; i++) {
            var documentKey = "my_document_" + i;
            cache.put(documentKey, "common_token_1 common_token_2 some_other_token_" + i);
        }
        cache.put("other_document", "its own tokens");
        cache.put("some_other_document", "its another tokens");
        var result = cache.find("common_token_1 common_token_2");

        assertFalse(result.isEmpty(), "the result should not be an empty set");
        assertEquals(3, result.size(), "there are three documents having the requested token");
        assertTrue(result.contains("my_document_0"), "the result should contain all the right document keys");
        assertTrue(result.contains("my_document_1"), "the result should contain all the right document keys");
        assertTrue(result.contains("my_document_2"), "the result should contain all the right document keys");
    }
}
