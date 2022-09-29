package com.upwork.sync.yaml.agents

import com.upwork.sync.SyncTypes
import com.upwork.sync.facade.SyncAgent
import com.upwork.sync.mapper.InterestingFactMapper
import com.upwork.sync.models.InterestingFact
import com.upwork.sync.models.jpa.InterestingFactEntity
import com.upwork.sync.services.InterestingFactRepositoryImpl
import com.upwork.sync.yaml.services.YamlHttpClient
import groovy.transform.CompileStatic
import jakarta.inject.Inject
import jakarta.inject.Singleton

@CompileStatic
@Singleton
class YamlCatalogDataAgent implements SyncAgent {

    private static final String API_URL = "https://some-yaml-api"

    @Inject
    InterestingFactRepositoryImpl repository

    @Inject
    private InterestingFactMapper mapper

    @Inject
    private YamlHttpClient yamlHttpClient

    @Override
    List<?> doSync() {
        List<InterestingFact> interestingFacts = yamlHttpClient.fetchData(API_URL)

        interestingFacts.forEach(f -> {
            InterestingFactEntity interestingFactEntity = mapper.toYamlInterestingFactEntity(f)

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
        return SyncTypes.YAML_CATALOG_SYNC_AGENT
    }
}
