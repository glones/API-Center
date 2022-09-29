package com.upwork.yaml

import com.upwork.controllers.SyncController
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class YamlSyncTest extends Specification {

    @Inject
    SyncController syncController

    void 'successful csv sync'() {
        when:
        def result = syncController.doSync("yaml-catalog")

        then:
        result != null
    }

}
