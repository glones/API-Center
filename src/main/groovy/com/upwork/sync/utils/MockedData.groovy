package com.upwork.sync.utils

import com.upwork.sync.models.InterestingFact

class MockedData {

    //    Method which mock the data, while have no real connection to the API
    static List<InterestingFact> mockData() {
        InterestingFact interestingFact = new InterestingFact()
        interestingFact.setFact("You check your cats pulse on the inside of the back thigh, where the leg joins to the body. Normal for cats: 110-170 beats per minute.")

        return List.of(interestingFact)
    }

}
