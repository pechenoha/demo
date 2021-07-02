package org.vadym.demo.server.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Simple cache based by a map that enables quick access to the documents containing given tokens.
 *
 * @author Vadym Pechenoha
 */
@Component
public class SimpleCache implements SearchEngineCache {

    private static final Logger logger = LoggerFactory.getLogger(SimpleCache.class);

    /**
     * The cache is in form of map. The following data is being stored here:
     * - key: token (word)
     * - value: set of keys of those documents which contain the token (word)
     */
    private final Map<String, Set<String>> cache = new HashMap<>();

    @Override
    public void put(String documentKey, String documentContents) {
        if (isBlankContents(documentContents)) {
            return;
        }

        for (var token : getTokens(documentContents)) {
            cache.computeIfAbsent(token, key -> new HashSet<>());
            cache.get(token).add(documentKey);
        }
    }

    @Override
    public Set<String> find(String tokensString) {
        var result = new HashSet<String>();
        if (isBlankContents(tokensString)) {
            logger.warn("Nothing to look for, because the tokens haven't been provided");
            return result;
        }

        var tokens = getTokens(tokensString);
        for (var i = 0; i < tokens.length; i++) {
            var documentKeys = cache.getOrDefault(tokens[i], new HashSet<>());
            if (i == 0) {
                result.addAll(documentKeys);
            } else {
                result.retainAll(documentKeys);
            }

            // if the result is an empty set, the next set intersections will lead to the "always empty" result,
            // so there's no need to proceed
            if (result.isEmpty()) {
                break;
            }
        }

        logger.info("Search results for the tokens '{}': {}", tokensString, result);
        return result;
    }

    private String[] getTokens(String documentContents) {
        return documentContents.trim().split("\\s+");
    }

    private boolean isBlankContents(String documentContents) {
        return Objects.isNull(documentContents) || documentContents.trim().equals("");
    }
}
