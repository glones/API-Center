package com.upwork.sync.services

import com.upwork.sync.mapper.InterestingFactMapper
import com.upwork.sync.models.InterestingFact
import com.upwork.sync.models.jpa.InterestingFactEntity
import com.upwork.sync.repository.InterestingFactRepository
import io.micronaut.transaction.annotation.ReadOnly
import io.micronaut.transaction.annotation.TransactionalAdvice
import jakarta.inject.Inject
import jakarta.inject.Singleton

import javax.persistence.EntityManager

@Singleton
class InterestingFactRepositoryImpl implements InterestingFactRepository {

    @Inject
    private InterestingFactMapper mapper

    private final EntityManager entityManager

    InterestingFactRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager
    }

    @Override
    @TransactionalAdvice
    void save(InterestingFactEntity fact) {
        entityManager.persist(fact)
    }

    @Override
    @ReadOnly
    Optional<InterestingFact> findLatestFactBySyncType(String syncType) {
        String qlString = "SELECT f FROM InterestingFactEntity AS f WHERE syncType = :syncType ORDER BY createdDate DESC"

        List<InterestingFactEntity> result = entityManager.createQuery(qlString, InterestingFactEntity.class)
                .setParameter("syncType", syncType)
                .getResultList()

        if (!result.isEmpty()) {
            return Optional.of(mapper.toInterestingFactModel(result.get(0)))
        }

        return Optional.empty()
    }
}
