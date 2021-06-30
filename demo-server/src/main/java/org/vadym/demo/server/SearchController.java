package org.vadym.demo.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Search controller allowing to find necessary tokens in the existing documents.
 *
 * @author Vadym Pechenoha
 */
@RestController("/search")
public class SearchController {

    private SearchEngineCache cache;

    public SearchController(SearchEngineCache cache) {
        this.cache = cache;
    }

    @GetMapping
    public Set<String> search(@RequestParam String query) {
        return cache.find(query);
    }
}
