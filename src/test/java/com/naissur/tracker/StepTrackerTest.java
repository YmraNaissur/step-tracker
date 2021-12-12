package com.naissur.tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StepTrackerTest {

    private StepTracker tracker;

    @BeforeEach
    void setUp() {
        tracker = StepTracker.getInstance();
    }

    @Test
    void testSaveNumberOfSteps() {
        tracker.saveNumberOfSteps("Февраль", 29, 1234);
        assertEquals(1234, tracker.getStorage().get("Февраль")[28]);
    }
}