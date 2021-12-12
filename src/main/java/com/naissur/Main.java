package com.naissur;

import com.naissur.menu.Menu;

/**
 * @author Max Karavaev (daily.spb@yandex.ru)
 */
public class Main {

    private static final Menu menu = Menu.getInstance();

    public static void main(String[] args) {
        while (true) {
            menu.printMainMenu();
            String command = menu.askForCommand();
            switch (command) {
                case "1":
                    menu.saveNumberOfSteps();
                    break;
                case "2":
                    menu.printStatistics();
                    break;
                case "3":
                    menu.changeTargetNumberOfSteps();
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
