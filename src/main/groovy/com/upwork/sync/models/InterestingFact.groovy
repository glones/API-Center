package com.upwork.sync.models

import groovy.transform.CompileStatic
import io.micronaut.core.annotation.Introspected

@CompileStatic
@Introspected
class InterestingFact {

    String fact

    InterestingFact() {
    }

    String getFact() {
        fact
    }

    void setFact(String fact) {
        this.fact = fact
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof InterestingFact)) return false

        InterestingFact that = (InterestingFact) o

        if (fact != that.fact) return false

        return true
    }

    int hashCode() {
        (fact != null ? fact.hashCode() : 0)
    }


    @Override
    String toString() {
        "InterestingFact{fact=$fact}"
    }
}
