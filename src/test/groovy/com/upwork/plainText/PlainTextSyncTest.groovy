package com.upwork.plainText

import com.upwork.controllers.SyncController
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class PlainTextSyncTest extends Specification {

    @Inject
    SyncController syncController

    void 'successful xml sync'() {
        when:
        def result = syncController.doSync("text-catalog")

        then:
        result != null
    }

}
