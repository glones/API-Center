package com.upwork.controllers

import com.upwork.sync.facade.SyncFacade
import groovy.transform.CompileStatic
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import jakarta.inject.Inject

@CompileStatic
@Controller("/sync")
class SyncController {

    @Inject
    private SyncFacade syncFacade

    // Sync the data from any existing API,
    @Get(uri = "/{syncType}")
    List<?> doSync(String syncType) {
        return syncFacade.sync(syncType)
    }
}
