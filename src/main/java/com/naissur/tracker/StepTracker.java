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

    public void setTargetNumberOfSteps(int targetNumberOfSteps) {
        this.targetNumberOfSteps = targetNumberOfSteps;
    }

    // storage is needed in tests
    protected Map<String, int[]> getStorage() {
        return storage;
    }
}
