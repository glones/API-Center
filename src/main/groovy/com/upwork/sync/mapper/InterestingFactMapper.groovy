package com.upwork.sync.mapper

import com.upwork.sync.models.InterestingFact
import com.upwork.sync.models.jpa.InterestingFactEntity
import groovy.transform.CompileStatic
import jakarta.inject.Singleton

import static com.upwork.sync.SyncTypes.JSON_CATALOG_SYNC_AGENT
import static com.upwork.sync.SyncTypes.YAML_CATALOG_SYNC_AGENT
import static com.upwork.sync.SyncTypes.TEXT_CATALOG_SYNC_AGENT

@CompileStatic
@Singleton
class InterestingFactMapper {

    InterestingFact toInterestingFactModel(InterestingFactEntity interestingFactEntity) {
        if (interestingFactEntity == null) {
            return null
        }

        InterestingFact interestingFact = new InterestingFact()

        if (interestingFactEntity.getFact() != null) {
            interestingFact.setFact(interestingFactEntity.getFact())
        }

        return interestingFact
    }

    private InterestingFactEntity toInterestingFactEntity(InterestingFact interestingFact, String syncType) {
        if (interestingFact == null) {
            return null
        }

        InterestingFactEntity interestingFactEntity = new InterestingFactEntity()

        interestingFactEntity.setSyncType(syncType)
        interestingFactEntity.setCreatedDate(new Date())
        if (interestingFact.getFact() != null) {
            interestingFactEntity.setFact(interestingFact.getFact())
        }

        return interestingFactEntity
    }

    InterestingFactEntity toJsonInterestingFactEntity(InterestingFact interestingFact) {
        return toInterestingFactEntity(interestingFact, JSON_CATALOG_SYNC_AGENT)
    }

    InterestingFactEntity toYamlInterestingFactEntity(InterestingFact interestingFact) {
        return toInterestingFactEntity(interestingFact, YAML_CATALOG_SYNC_AGENT)
    }

    InterestingFactEntity toTextInterestingFactEntity(InterestingFact interestingFact) {
        return toInterestingFactEntity(interestingFact, TEXT_CATALOG_SYNC_AGENT)
    }
}
