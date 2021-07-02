package org.vadym.demo.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration for the Simple Search Engine Server Application.
 *
 * @author Vadym Pechenoha
 */
@Configuration
public class SimpleSearchEngineConfiguration {

    @Value("${simple-search-engine.base-url}")
    private String simpleSearchEngineBaseUrl;

    @Bean
    public WebClient simpleSearchEngineClient() {
        return WebClient.create(simpleSearchEngineBaseUrl);
    }
}
