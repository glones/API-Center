package com.upwork.cyclone

import com.upwork.sync.json.agents.JsonCatalogSyncAgent
import com.upwork.sync.plainText.agents.TextCatalogDataAgent
import com.upwork.sync.yaml.agents.YamlCatalogDataAgent
import groovy.transform.CompileStatic
import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Inject
import jakarta.inject.Singleton

@CompileStatic
@Singleton
class CycloneExecutor {

    @Inject
    private JsonCatalogSyncAgent jsonCatalogSyncAgent

    @Inject
    private YamlCatalogDataAgent yamlCatalogDataAgent

    @Inject
    private TextCatalogDataAgent textCatalogDataAgent

    @Scheduled(fixedDelay = "45s", initialDelay = "1m")
    void runTask() {
        if (Boolean.TRUE.equals(jsonCatalogSyncAgent.isUpdateAvailable())) {
            jsonCatalogSyncAgent.doSync()
        }

        if (Boolean.TRUE.equals(yamlCatalogDataAgent.isUpdateAvailable())) {
            yamlCatalogDataAgent.doSync()
        }

        if (Boolean.TRUE.equals(textCatalogDataAgent.isUpdateAvailable())) {
            textCatalogDataAgent.doSync()
        }
    }
}
