package com.naissur.util;

/**
 * Класс, ответственный за конвертацию:
 * 1) Шагов в километры.
 * 2) Шагов в килокалории.
 * @author Max Karavaev (daily.spb@yandex.ru)
 */
public class Converter {

    public static final int CENTIMETERS_IN_ONE_STEP = 75;
    public static final int CENTIMETERS_IN_ONE_KILOMETER = 100_000;
    public static final int CALORIES_IN_ONE_STEP = 50;
    public static final int CALORIES_IN_KILOCALORIES = 1000;

    /**
     * Конвертация шагов в километры.
     * @param steps количество шагов.
     * @return количество километров в указанном количестве шагов.
     */
    public static double convertStepsToKilometers(int steps) {
        return convert(steps, CENTIMETERS_IN_ONE_STEP, CENTIMETERS_IN_ONE_KILOMETER);
    }

    /**
     * Конвертация шагов в килокалории.
     * @param steps количество шагов.
     * @return количество килокалорий в указанном количестве шагов.
     */
    public static double convertStepsToKilocalories(int steps) {
        return convert(steps, CALORIES_IN_ONE_STEP, CALORIES_IN_KILOCALORIES);
    }

    /**
     * Общая формула для конвертации одних единиц в другие.
     * @param convertFrom количество единиц, из которых нужно конвертировать
     *                    (например, количество шагов).
     * @param minorUnits количество минорных единиц в одной единице, из которой нужно конвертирвоать
     *                   (например, в одном шаге 75 сантиметров).
     * @param numOfMinorUnitsInMajorUnit количество минорных единиц в одной единице, в которую
     *                                   требуется конвертировать (например, в одном километре
     *                                   100 000 сантиметров).
     * @return количество единиц, в которые нужно конвертировать.
     */
    private static double convert (int convertFrom, int minorUnits, int numOfMinorUnitsInMajorUnit) {
        return (double) convertFrom * minorUnits / numOfMinorUnitsInMajorUnit;
    }
}
