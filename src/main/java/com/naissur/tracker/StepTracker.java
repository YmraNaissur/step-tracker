package com.naissur.tracker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс, ответственный за хранение информации о пройденных шагах,
 * логику сохранения и изменения количества шагов, расчёт статистики.
 * Для упрощения модели, считаем, что дней в месяце всегда 30 (см. ТЗ).
 * @author Max Karavaev
 */
public class StepTracker {
    private Map<String, Integer[]> storage;
    private int targetNumberOfSteps;
    private static final StepTracker instance = new StepTracker();

    private StepTracker() {
        storage = new HashMap<>();
        targetNumberOfSteps = 10_000;
        String[] MONTHS = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        Arrays.stream(MONTHS).forEach(m -> storage.put(m, new Integer[30]));
    }

    public static StepTracker getInstance() {
        return instance;
    }

    /**
     * Сохранение количества шагов за день.
     * @param month название месяца.
     * @param day номер дня.
     * @param numberOfSteps количество шагов.
     */
    public void saveNumberOfSteps(String month, int day, int numberOfSteps) {
        Integer[] monthDays = storage.get(month);
        monthDays[day - 1] = numberOfSteps;
        storage.put(month, monthDays);
    }

    // to be deleted
    public void printAll() {
        storage.forEach((key, value) -> System.out.println(key + " : " + Arrays.toString(value)));
    }

    public Map<String, Integer[]> getStorage() {
        return storage;
    }
}
