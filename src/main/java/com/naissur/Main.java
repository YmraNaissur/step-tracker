package com.naissur;

import com.naissur.menu.Menu;
import com.naissur.tracker.StepTracker;

/**
 * @author Max Karavaev (daily.spb@yandex.ru)
 */
public class Main {

    private static final Menu menu = Menu.getInstance();
    private static final StepTracker tracker = StepTracker.getInstance();

    public static void main(String[] args) {
        while (true) {
            menu.printMainMenu();
            String command = menu.askForCommand();
            switch (command) {
                case "1":
                    String month = menu.askForMonth();
                    int day = menu.askForDay();
                    int numberOfSteps = menu.askForNumberOfSteps();
                    tracker.saveNumberOfSteps(month, day, numberOfSteps);
                    break;
                case "2":
                    // TODO printStatistics
                    break;
                case "3":
                    // TODO changeTargetNumberOfSteps
                    break;
                case "4":
                    menu.printGoodBye();
                    System.exit(0);
                default:
                    menu.printCommandDoesNotExist();
            }
        }
    }
}
