package com.upwork.sync.json.agents

import com.upwork.sync.SyncTypes
import com.upwork.sync.facade.SyncAgent
import com.upwork.sync.json.services.JsonHttpClient
import com.upwork.sync.mapper.InterestingFactMapper
import com.upwork.sync.models.InterestingFact
import com.upwork.sync.models.jpa.InterestingFactEntity
import com.upwork.sync.services.InterestingFactRepositoryImpl
import groovy.transform.CompileStatic
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
@CompileStatic
class JsonCatalogSyncAgent implements SyncAgent{

    private static final String API_URL = "https://catfact.ninja/fact"

    @Inject
    private InterestingFactMapper mapper

    @Inject
    InterestingFactRepositoryImpl repository

    @Inject
    private JsonHttpClient jsonHttpClient

    @Override
    List<?> doSync() {
        List<InterestingFact> interestingFacts = jsonHttpClient.fetchData(API_URL)

        interestingFacts.forEach(f -> {
            InterestingFactEntity interestingFactEntity = mapper.toJsonInterestingFactEntity(f)

            repository.save(interestingFactEntity)
        })

        return interestingFacts
    }

//    It was agreed that currently this method returns mock of true value.
    @Override
    Boolean isUpdateAvailable() {
        Random random = new Random()

        return random.nextBoolean()
    }

    @Override
    String getSupportedSubType() {
        return SyncTypes.JSON_CATALOG_SYNC_AGENT
    }
}
