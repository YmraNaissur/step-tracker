package com.naissur.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {
    @Test
    void testConvertStepsToKilometers() {
        assertEquals(1.77375, Converter.convertStepsToKilometers(2365));
    }

    @Test
    void testConvertStepsToKilocalories() {
        assertEquals(234.45, Converter.convertStepsToKilocalories(4689));
    }
}