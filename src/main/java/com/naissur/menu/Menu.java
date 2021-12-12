package com.naissur.menu;

import com.naissur.tracker.StepTracker;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private static final StepTracker tracker = StepTracker.getInstance();
    private static final Menu instance = new Menu();

    private Menu() {
        // приватный конструктор для предотвращения создания объектов Menu вручную
    }

    public static Menu getInstance() {
        return instance;
    }

    /**
     * Вывод на экран основного меню.
     */
    public void printMainMenu() {
        System.out.println("1. Ввести количество шагов за определённый день.");
        System.out.println("2. Напечатать статистику за определённый месяц.");
        System.out.println("3. Изменить цель по количеству шагов в день.");
        System.out.println("4. Выйти из приложения.");
    }

    /**
     * Запрос команды из основного меню у пользователя.
     *
     * @return введённая пользователем команда.
     */
    public String askForCommand() {
        System.out.println("Введите номер команды:");
        return scanner.next();
    }

    /**
     * Запрашивает у пользователя месяц, день и количество шагов,
     * а затем сохраняет введённую информацию в хранилище класса StepTracker.
     */
    public void saveNumberOfSteps() {
        String month = askForMonth();
        int day = askForDay();
        int numberOfSteps = askForNumberOfSteps();
        tracker.saveNumberOfSteps(month, day, numberOfSteps);
        System.out.println();
    }

    private String askForMonth() {
        System.out.println("Введите название месяца:");
        return scanner.next();
    }

    private int askForDay() {
        System.out.println("Введите номер дня:");
        return scanner.nextInt();
    }

    private int askForNumberOfSteps() {
        System.out.println("Введите количество шагов:");
        return scanner.nextInt();
    }

    /**
     * Запрашивает у пользователя новое положительное количество шагов
     * и устанавливает его в качестве целевого в классе StepTracker.
     */
    public void changeTargetNumberOfSteps() {
        System.out.println("Введите новое целевое количество шагов.");
        int targetNumberOfSteps = -1;
        while (targetNumberOfSteps < 0) {
            System.out.println("Количество шагов не должно быть отрицательным");
            targetNumberOfSteps = scanner.nextInt();
        }
        tracker.setTargetNumberOfSteps(targetNumberOfSteps);
        System.out.println();
    }

    public void printCommandDoesNotExist() {
        System.out.println("Извините, такой команды пока не существует.");
    }

    public void printGoodBye() {
        System.out.println("До встречи!");
    }
}
