package com.upwork.controllers

import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.views.View

@CompileStatic
@Controller
class MenuController {

    //    Starting page viewer.
    @Get
    @View("index")
    HttpResponse<?> showMenu() {
        return HttpResponse.ok()
    }

}
