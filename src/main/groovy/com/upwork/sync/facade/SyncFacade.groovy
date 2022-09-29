package com.upwork.sync.facade

import com.upwork.sync.models.InterestingFact
import com.upwork.sync.services.InterestingFactRepositoryImpl
import groovy.transform.CompileStatic
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.apache.commons.lang3.StringUtils

import java.util.function.Function
import java.util.stream.Collectors

@CompileStatic
@Singleton
class SyncFacade {

    private final Map<String, SyncAgent> agents

    @Inject
    private InterestingFactRepositoryImpl repository

    SyncFacade(List<SyncAgent> syncAgents) {
        agents = buildAgentDictionary(syncAgents)
    }

    private Map<String, SyncAgent> buildAgentDictionary(final List<SyncAgent> syncAgents) {
        return syncAgents.stream().collect(Collectors.toMap(SyncAgent::getSupportedSubType, Function.identity()))
    }

    List<?> sync(String syncType) {
        SyncAgent syncAgent = resolveAgent(syncType)
        Optional<InterestingFact> factOptional = repository.findLatestFactBySyncType(syncType)

        if (syncAgent == null) {
            throw new IllegalArgumentException("Unable to resolve sync type " + syncType)
        }

        if (factOptional.isPresent()) {
            return List.of(factOptional.get())
        }

        syncAgent.doSync()
    }

    SyncAgent resolveAgent(String subType) {
        String syncType = StringUtils.trim(subType)

        return agents.get(syncType)
    }
}
