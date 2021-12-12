package com.naissur.menu;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
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
     * @return введённая пользователем команда.
     */
    public String askForCommand() {
        System.out.println("Введите номер команды:");
        return scanner.next();
    }

    /**
     * Запрос названия месяца у пользователя.
     * @return введённое пользователем название месяца.
     */
    public String askForMonth() {
        System.out.println("Введите название месяца:");
        return scanner.next();
    }

    /**
     * Запрос номера дня у пользователя.
     * @return введённый пользователем номер дня.
     */
    public int askForDay() {
        System.out.println("Введите номер дня:");
        return scanner.nextInt();
    }

    /**
     * Запрос количества шагов у пользователя.
     * @return введённое пользователем количество шагов.
     */
    public int askForNumberOfSteps() {
        System.out.println("Введите количество шагов:");
        return scanner.nextInt();
    }

    public void printCommandDoesNotExist() {
        System.out.println("Извините, такой команды пока не существует.");
    }

    public void printGoodBye() {
        System.out.println("До встречи!");
    }
}
