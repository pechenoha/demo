package org.vadym.demo.server.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vadym.demo.server.service.SimpleSearchEngineService;

import java.util.Set;

/**
 * Client controller showing how the server (Simple Search Engine) works.
 *
 * @author Vadym Pechenoha
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    private final SimpleSearchEngineService service;

    public ClientController(SimpleSearchEngineService service) {
        this.service = service;
    }

    @GetMapping("/document/{key}")
    public String getDocument(@PathVariable String key) {
        return service.getDocument(key);
    }

    @PostMapping("/documents")
    public void generateDocuments(
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size) {

        for (var i = from; i < from + size; i++) {
            var contents = String.format("common_token token%s_0 token%s_1 token%s_2", i, i, i);
            service.putDocument("document_" + i, contents);
        }
    }

    @GetMapping("/search")
    public Set<?> search(@RequestParam String query) {
        return service.search(query);
    }
}
