package com.upwork.sync.repository

import com.upwork.sync.models.InterestingFact
import com.upwork.sync.models.jpa.InterestingFactEntity
import groovy.transform.CompileStatic

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@CompileStatic
interface InterestingFactRepository {

    void save(@NotNull InterestingFactEntity fact)

    Optional<InterestingFact> findLatestFactBySyncType(@NotBlank String syncType)

}