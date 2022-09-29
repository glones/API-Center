package com.upwork.sync.plainText.agents

import com.upwork.sync.SyncTypes
import com.upwork.sync.facade.SyncAgent
import com.upwork.sync.mapper.InterestingFactMapper
import com.upwork.sync.models.InterestingFact
import com.upwork.sync.models.jpa.InterestingFactEntity
import com.upwork.sync.plainText.services.TextHttpClient
import com.upwork.sync.services.InterestingFactRepositoryImpl
import groovy.transform.CompileStatic
import jakarta.inject.Inject
import jakarta.inject.Singleton

@CompileStatic
@Singleton
class TextCatalogDataAgent implements SyncAgent{

    private static final String API_URL = "https://some-plain-text-api"

    @Inject
    private InterestingFactMapper mapper

    @Inject
    private TextHttpClient textHttpClient

    @Inject
    private InterestingFactRepositoryImpl repository

    @Override
    List<?> doSync() {
        List<InterestingFact> interestingFacts = textHttpClient.fetchData(API_URL)

        interestingFacts.forEach(f -> {
            InterestingFactEntity interestingFactEntity = mapper.toYamlInterestingFactEntity(f)

            repository.save(interestingFactEntity)
        })

        return interestingFacts
    }

    @Override
    Boolean isUpdateAvailable() {
        Random random = new Random()

        return random.nextBoolean()
    }

    @Override
    String getSupportedSubType() {
        return SyncTypes.TEXT_CATALOG_SYNC_AGENT
    }
}
