package org.vadym.demo.server.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vadym.demo.server.cache.SearchEngineCache;
import org.vadym.demo.server.storage.SearchEngineStorage;

import java.util.NoSuchElementException;

/**
 * Document controller allowing to operate on document entities.
 *
 * @author Vadym Pechenoha
 */
@RestController
@RequestMapping("/document")
public class DocumentController {

    private final SearchEngineStorage storage;

    private final SearchEngineCache cache;

    public DocumentController(SearchEngineStorage storage, SearchEngineCache cache) {
        this.storage = storage;
        this.cache = cache;
    }

    @PutMapping("/{key}")
    public void put(@PathVariable String key, @RequestBody ContentsInput contentsInput) {
        var contents = contentsInput.getContents();
        storage.put(key, contents);
        cache.put(key, contents);
    }

    @GetMapping("/{key}")
    public String get(@PathVariable String key) {
        return storage.get(key)
                .orElseThrow(NoSuchElementException::new);
    }

    static class ContentsInput {

        private String contents;

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }
    }
}
