package com.upwork.sync.json.services

import com.upwork.sync.models.InterestingFact
import groovy.transform.CompileStatic
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.http.uri.UriBuilder
import jakarta.inject.Singleton
import reactor.core.publisher.Mono

import static io.micronaut.http.HttpHeaders.ACCEPT

@CompileStatic
@Singleton
class JsonHttpClient {

    private final HttpClient httpClient

    JsonHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient
    }

    List<InterestingFact> fetchData(String url) {
        URI uri = buildUri(url)

        HttpRequest<?> req = HttpRequest.GET(uri)
                .header(ACCEPT, MediaType.APPLICATION_JSON)
                .header("X-CSRF-TOKEN", "lu1iC4GpGk1vVJoR4FHowXtjLgMRxpQLHzhdCnBZ")

        return Mono.from(httpClient.retrieve(req, Argument.listOf(InterestingFact.class))).block()
    }

    private URI buildUri(String url) {
        return UriBuilder
                .of(url)
                .build()
    }
}
