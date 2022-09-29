package com.upwork.sync.models.jpa

import groovy.transform.CompileStatic

import javax.persistence.*
import javax.validation.constraints.NotNull

@CompileStatic
@Entity
@Table(name = "interesting_fact")
class InterestingFactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    @Column(name = "fact")
    String fact

    @NotNull
    @Column(name = "syncType", nullable = false, length = 512)
    String syncType

    @NotNull
    @Column(name = "createdDate", nullable = false)
    Date createdDate

    InterestingFactEntity() {
    }

    Long getId() {
        id
    }

    void setId(Long id) {
        this.id = id
    }

    String getFact() {
        fact
    }

    void setFact(String fact) {
        this.fact = fact
    }

    String getSyncType() {
        syncType
    }

    void setSyncType(String syncType) {
        this.syncType = syncType
    }

    Date getCreatedDate() {
        createdDate
    }

    void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate
    }

    @Override
    String toString() {
        "InterestingFactEntity{id=$id, fact=$fact, syncType=$syncType, createdDate=$createdDate}"
    }
}
