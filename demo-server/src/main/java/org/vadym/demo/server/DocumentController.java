package org.vadym.demo.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Document controller allowing to operate on document entities.
 *
 * @author Vadym Pechenoha
 */
@RestController("/document")
public class DocumentController {

    @PutMapping("/{key}")
    public void put(@PathVariable String key, String contents) {
    }

    @GetMapping("/{key}")
    public String get(@PathVariable String key) {
        return "dummy";
    }
}
