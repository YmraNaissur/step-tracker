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
        tracker.saveNumberOfSteps(TEST_MONTH, 1, 5000);
        tracker.saveNumberOfSteps(TEST_MONTH, 2, 11000);
        tracker.saveNumberOfSteps(TEST_MONTH, 3, 11500);
        tracker.saveNumberOfSteps(TEST_MONTH, 4, 10500);
        tracker.saveNumberOfSteps(TEST_MONTH, 5, 600);
        tracker.saveNumberOfSteps(TEST_MONTH, 6, 8000);
        tracker.saveNumberOfSteps(TEST_MONTH, 7, 16000);
        tracker.saveNumberOfSteps(TEST_MONTH, 8, 13000);
        tracker.saveNumberOfSteps(TEST_MONTH, 9, 12000);
        tracker.saveNumberOfSteps(TEST_MONTH, 10, 13400);
        tracker.saveNumberOfSteps(TEST_MONTH, 11, 9000);
    }

    @Test
    void testSaveNumberOfSteps() {
        assertEquals(11000, tracker.getStorage().get(TEST_MONTH)[1]);
    }

    @Test
    void testCalculateTotalNumberOfSteps() {
        int totalNumberOfSteps = tracker.getTotalNumberOfSteps(TEST_MONTH);
        assertEquals(110000, totalNumberOfSteps);
    }

    @Test
    void testGetMaxNumberOfSteps() {
        int maxNumberOfSteps = tracker.getMaxNumberOfSteps(TEST_MONTH);
        assertEquals(16000, maxNumberOfSteps);
    }

    @Test
    void testGetAverageNumberOfSteps() {
        double averageNumberOfSteps = tracker.getAverageNumberOfSteps(TEST_MONTH);
        assertEquals(3666.6666666666665, averageNumberOfSteps);
    }

    @Test
    void testDistanceTravelled() {
        double distanceInKm = tracker.getDistanceTravelled(TEST_MONTH);
        // (сумма шагов * 75 см) / 100_000
        // 100_000 - это количество сантиметров в километре
        assertEquals(82.5, distanceInKm);
    }

    @Test
    void testKilocaloriesBurned() {
        double kcalBurned = tracker.getKilocaloriesBurned(TEST_MONTH);
        // (сумма шагов * 50 калорий) / 1000
        // 1000 - это количество калорий в килокалории
        assertEquals(5500, kcalBurned);
    }

    @Test
    void testGetLongestSeries() {
        assertEquals(4, tracker.getLongestSeries(TEST_MONTH));
    }

    @Test
    void testChangeTargetNumberOfSteps() {
        tracker.setTargetNumberOfSteps(4000);
        assertEquals(6, tracker.getLongestSeries(TEST_MONTH));
        // т.к. tracker - это синглтон, вернём предыдущее значение целевого
        // количества шагов, чтобы не нарушать другие тесты.
        tracker.setTargetNumberOfSteps(10000);
    }
}