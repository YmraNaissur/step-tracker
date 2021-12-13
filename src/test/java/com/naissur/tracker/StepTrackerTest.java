package com.naissur.tracker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StepTrackerTest {

    private static StepTracker tracker;
    private static final String TEST_MONTH = "Февраль";

    @BeforeAll
    static void setUp() {
        tracker = StepTracker.getInstance();
        tracker.saveNumberOfSteps(TEST_MONTH, 1, 2500);
        tracker.saveNumberOfSteps(TEST_MONTH, 2, 1567);
        tracker.saveNumberOfSteps(TEST_MONTH, 3, 9838);
    }

    @Test
    void testSaveNumberOfSteps() {
        assertEquals(1567, tracker.getStorage().get(TEST_MONTH)[1]);
    }

    @Test
    void testCalculateTotalNumberOfStepsInMonth() {
        int totalNumberOfSteps = tracker.getTotalNumberOfSteps(TEST_MONTH);
        assertEquals(2500 + 1567 + 9838, totalNumberOfSteps);
    }

    @Test
    void testGetMaxNumberOfStepsInMonth() {
        int maxNumberOfSteps = tracker.getMaxNumberOfSteps(TEST_MONTH);
        assertEquals(9838, maxNumberOfSteps);
    }

    @Test
    void testGetAverageNumberOfSteps() {
        double averageNumberOfSteps = tracker.getAverageNumberOfSteps(TEST_MONTH);
        assertEquals((2500 + 1567 + 9838) / 30.0, averageNumberOfSteps);
    }

    @Test
    void testDistanceTravelled() {
        double distanceInKm = tracker.getDistanceTravelled(TEST_MONTH);
        // (сумма шагов * 75 см) / 100_000
        // 100_000 - это количество сантиметров в километре
        assertEquals(10.42875, distanceInKm);
    }

    @Test
    void testKilocaloriesBurned() {
        double kcalBurned = tracker.getKilocaloriesBurned(TEST_MONTH);
        // (сумма шагов * 50 калорий) / 1000
        // 1000 - это количество калорий в килокалории
        assertEquals(695.25, kcalBurned);
    }
}