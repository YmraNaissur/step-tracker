package com.naissur.tracker;

import com.naissur.util.Converter;

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
    private final Map<String, int[]> storage;
    private int targetNumberOfSteps;
    private static final StepTracker instance = new StepTracker();

    private StepTracker() {
        storage = new HashMap<>();
        targetNumberOfSteps = 10_000;
        String[] MONTHS = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        Arrays.stream(MONTHS).forEach(m -> storage.put(m, new int[30]));
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
        int[] days = storage.get(month);
        days[day - 1] = numberOfSteps;
    }

    /**
     * Возвращает строку, содержащую статистику пройденных шагов по
     * дням, как описано в ТЗ.
     * @param month название месяца.
     */
    public String buildStatisticsByDays(String month) {
        int[] days = storage.get(month);
        StringBuilder statBuilder = new StringBuilder();
        for (int i = 1; i <= days.length; i++) {
            statBuilder.append(i).append(" день: ").append(days[i - 1]).append(", ");
        }
        // в результирующей строке уберём последнюю запятую
        return statBuilder.toString().replaceAll(", $", "");
    }

    /**
     * Возвращает общее количество шагов, пройденных за указанный месяц.
     */
    public int getTotalNumberOfSteps(String month) {
        return Arrays.stream(storage.get(month)).sum();
    }

    /**
     * Возвращает максимальное количество шагов, пройденных за указанный месяц.
     * @return мексимальное количество шагов или 0, если его не существует.
     */
    public int getMaxNumberOfSteps(String month) {
        return Arrays.stream(storage.get(month)).max().orElse(0);
    }

    /**
     * Возвращает среднее количество шагов, пройденных за указанный месяц.
     * @return среднее количество шагов или 0.0, если его не существует.
     */
    public double getAverageNumberOfSteps(String month) {
        return Arrays.stream(storage.get(month)).average().orElse(0.0);
    }

    /**
     * Рассчитывает пройденное за указанный месяц расстояние.
     * @return расстояние в километрах.
     */
    public double getDistanceTravelled(String month) {
        return Converter.convertStepsToKilometers(getTotalNumberOfSteps(month));
    }

    /**
     * Рассчитывает количество килокалорий, сожжённых за указанный месяц.
     */
    public double getKilocaloriesBurned(String month) {
        return Converter.convertStepsToKilocalories(getTotalNumberOfSteps(month));
    }

    public void setTargetNumberOfSteps(int targetNumberOfSteps) {
        this.targetNumberOfSteps = targetNumberOfSteps;
    }

    // storage is needed in tests
    protected Map<String, int[]> getStorage() {
        return storage;
    }
}
