package com.naissur.tracker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StepTrackerTest {

    private static StepTracker tracker;
    private static final String TEST_MONTH_FEBRUARY = "Февраль";
    private static final String TEST_MONTH_MARCH = "Март";

    @BeforeAll
    static void setUp() {
        tracker = StepTracker.getInstance();
        tracker.saveNumberOfSteps(TEST_MONTH_FEBRUARY, 1, 5000);
        tracker.saveNumberOfSteps(TEST_MONTH_FEBRUARY, 2, 11000);
        tracker.saveNumberOfSteps(TEST_MONTH_FEBRUARY, 3, 11500);
        tracker.saveNumberOfSteps(TEST_MONTH_FEBRUARY, 4, 10500);
        tracker.saveNumberOfSteps(TEST_MONTH_FEBRUARY, 5, 600);
        tracker.saveNumberOfSteps(TEST_MONTH_FEBRUARY, 6, 8000);
        tracker.saveNumberOfSteps(TEST_MONTH_FEBRUARY, 7, 16000);
        tracker.saveNumberOfSteps(TEST_MONTH_FEBRUARY, 8, 13000);
        tracker.saveNumberOfSteps(TEST_MONTH_FEBRUARY, 9, 12000);
        tracker.saveNumberOfSteps(TEST_MONTH_FEBRUARY, 10, 13400);
        tracker.saveNumberOfSteps(TEST_MONTH_FEBRUARY, 11, 9000);
    }

    @Test
    void testSaveNumberOfSteps() {
        assertEquals(11000, tracker.getStorage().get(TEST_MONTH_FEBRUARY)[1]);
    }

    @Test
    void testCalculateTotalNumberOfSteps() {
        int totalNumberOfSteps = tracker.getTotalNumberOfSteps(TEST_MONTH_FEBRUARY);
        assertEquals(110000, totalNumberOfSteps);
    }

    @Test
    void testGetMaxNumberOfSteps() {
        int maxNumberOfSteps = tracker.getMaxNumberOfSteps(TEST_MONTH_FEBRUARY);
        assertEquals(16000, maxNumberOfSteps);
    }

    @Test
    void testGetAverageNumberOfSteps() {
        double averageNumberOfSteps = tracker.getAverageNumberOfSteps(TEST_MONTH_FEBRUARY);
        assertEquals(3666.6666666666665, averageNumberOfSteps);
    }

    @Test
    void testDistanceTravelled() {
        double distanceInKm = tracker.getDistanceTravelled(TEST_MONTH_FEBRUARY);
        // (сумма шагов * 75 см) / 100_000
        // 100_000 - это количество сантиметров в километре
        assertEquals(82.5, distanceInKm);
    }

    @Test
    void testKilocaloriesBurned() {
        double kcalBurned = tracker.getKilocaloriesBurned(TEST_MONTH_FEBRUARY);
        // (сумма шагов * 50 калорий) / 1000
        // 1000 - это количество калорий в килокалории
        assertEquals(5500, kcalBurned);
    }

    @Test
    void testGetLongestSeries() {
        assertEquals(4, tracker.getLongestSeries(TEST_MONTH_FEBRUARY));
    }

    @Test
    void testGetLongestSeriesWhenTargetStepsWalkedEachDay() {
        Arrays.fill(tracker.getStorage().get(TEST_MONTH_MARCH), 11000);
        assertEquals(30, tracker.getLongestSeries(TEST_MONTH_MARCH));
    }

    @Test
    void testGetLongestSeriesWhenLongestSeriesStopsInTheEndOfArray() {
        Arrays.fill(tracker.getStorage().get(TEST_MONTH_MARCH), 11000);
        tracker.saveNumberOfSteps(TEST_MONTH_MARCH, 10, 9000);
        assertEquals(20, tracker.getLongestSeries(TEST_MONTH_MARCH));
    }

    @Test
    void testChangeTargetNumberOfSteps() {
        tracker.setTargetNumberOfSteps(4000);
        assertEquals(6, tracker.getLongestSeries(TEST_MONTH_FEBRUARY));
        // т.к. tracker - это синглтон, вернём предыдущее значение целевого
        // количества шагов, чтобы не нарушать другие тесты.
        tracker.setTargetNumberOfSteps(10000);
    }
}