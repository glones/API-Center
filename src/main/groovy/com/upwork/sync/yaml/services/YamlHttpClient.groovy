package com.upwork.sync.yaml.services

import com.upwork.sync.models.InterestingFact
import groovy.transform.CompileStatic
import io.micronaut.core.async.publisher.Publishers
import io.micronaut.http.HttpRequest
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.http.uri.UriBuilder
import jakarta.inject.Singleton
import reactor.core.publisher.Mono

import static com.upwork.sync.utils.MockedData.mockData
import static io.micronaut.http.HttpHeaders.ACCEPT

@CompileStatic
@Singleton
class YamlHttpClient {

    private final HttpClient httpClient

    YamlHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient
    }

    List<InterestingFact> fetchData(String url) {
        URI uri = buildUri(url)

        HttpRequest<?> req = HttpRequest.GET(uri)
                .header(ACCEPT, MediaType.APPLICATION_YAML)

//        return Mono.from(httpClient.retrieve(req, Argument.listOf(InterestingFact.class))).block() <-- need to be returned

        return Mono.from(new Publishers.JustPublisher<>(mockData())).block()
    }

    private URI buildUri(String url) {
        return UriBuilder
                .of(url)
                .build()
    }
}
