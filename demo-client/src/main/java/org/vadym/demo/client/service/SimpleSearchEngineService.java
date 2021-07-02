package org.vadym.demo.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.vadym.demo.client.exception.SimpleSearchEngineException;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Set;

/**
 * Service used to communicate with the Simple Search Engine server.
 *
 * @author Vadym Pechenoha
 */
@Service
public class SimpleSearchEngineService {

    private static final Logger logger = LoggerFactory.getLogger(SimpleSearchEngineService.class);

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(5);

    private final WebClient webClient;

    public SimpleSearchEngineService(WebClient simpleSearchEngineClient) {
        this.webClient = simpleSearchEngineClient;
    }

    public String getDocument(String key) {
        return webClient
                .get()
                .uri("/document/" + key)
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    var message = "Cannot retrieve a document with a key: " + key;
                    logger.warn(message);
                    return Mono.error(new SimpleSearchEngineException(message));
                })
                .bodyToMono(String.class)
                .block(REQUEST_TIMEOUT);
    }

    public void putDocument(String key, String contents) {
        webClient
                .put()
                .uri("/document/" + key)
                .bodyValue("{ \"contents\" : \"" + contents + "\" }")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    var message = "Cannot put a document with a key: " + key;
                    logger.warn(message);
                    return Mono.error(new SimpleSearchEngineException(message));
                })
                .bodyToMono(Void.class)
                .block(REQUEST_TIMEOUT);
    }

    public Set<?> search(String query) {
        return webClient
                .get()
                .uri("/search?query=" + query)
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    var message = "Cannot make the search: " + query;
                    logger.warn(message);
                    return Mono.error(new SimpleSearchEngineException(message));
                })
                .bodyToMono(Set.class)
                .block(REQUEST_TIMEOUT);
    }
}
