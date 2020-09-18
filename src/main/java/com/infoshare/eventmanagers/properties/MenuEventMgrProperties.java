package com.infoshare.eventmanagers.properties;


import com.infoshare.eventmanagers.properties.SortingOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Scanner;

import static com.infoshare.eventmanagers.Main.SETTINGS;

public class MenuEventMgrProperties {

    private final static Logger LOGGER = LogManager.getLogger(MenuEventMgrProperties.class);
    private final static String MENU_LINE = getLine();
    private final static Scanner SCANNER = new Scanner(System.in);
    
    public MenuEventMgrProperties() {
    }

    public void displayPropertiesMenu() {
        while (true) {
            LOGGER.info("\n########## Ustawienia ##########");
            LOGGER.info("Obecnie ustawienia:\n");
            LOGGER.info(SETTINGS.toString());
            LOGGER.info("""
                    1: Zmień tryb sortowania.
                    2: Zmień format daty.
                    3: Przywróć ustawienia domyślne.
                    4: Zapisz ustawienia.
                    Naciśnij [ENTER] aby wrócić do głównego menu""");
            LOGGER.info(MENU_LINE);
            LOGGER.info("Wybierz opcję:");
            String userInput = SCANNER.nextLine();
            if (userInput.equals("")) return;
            else
                try {
                    int input = Integer.parseInt(userInput);
                    switch (input) {
                        case 1 -> displaySortingOrderMenu();
                        case 2 -> displayDateFormatMenu();
                        case 3 -> {
                            SETTINGS.resetProperties();
                            LOGGER.info("Przywrócono ustawienia domyślne.");
                        }
                        case 4 -> {
                            SETTINGS.saveProperties();
                            LOGGER.info("Ustawienia zapisane.");
                        }
                        default -> {
                            return;
                        }
                    }
                } catch (NumberFormatException e) {
                    LOGGER.warn("Nie rozumiem. Wybierz opcję z listy.");
                }
        }
    }

    /**
     * Displays menu to change the sorting order
     */
    private void displaySortingOrderMenu() {
        while (true) {
            LOGGER.info("Wybierz porządek sortowania");
            LOGGER.info("Obecnie sortuję po: " + SETTINGS.getSortingOrder());
            LOGGER.info("""
                    Wybierz opcję:
                    1: Sortuj po organizatorze,
                    2: Sortuj po dacie.
                    Naciśnij [ENTER] aby wrócić do głównego menu""");
            LOGGER.info(MENU_LINE);
            LOGGER.info("Wybierz opcję:");
            String userInput = SCANNER.nextLine();
            if (userInput.equals("")) return;
            else
                try {
                    int input = Integer.parseInt(userInput);
                    switch (input) {
                        case 1 -> {
                            SETTINGS.setSortingOrder(
                                    SortingOrder.ORGANIZATOR.toString());
                            return;
                        }
                        case 2 -> {
                            SETTINGS.setSortingOrder(
                                    SortingOrder.DATA.toString());
                            return;
                        }
                        default -> {
                            return;
                        }
                    }
                } catch (NumberFormatException e) {
                    LOGGER.warn("Nie rozumiem. Wybierz opcję z listy");
                }
        }
    }

    /**
     * Display window to change the date format
     */
    private void displayDateFormatMenu() {
        while (true) {
            LOGGER.info("Zmień format daty");
            LOGGER.info("Obecny format to: " + SETTINGS.getDateFormatAsString());
            LOGGER.info(MENU_LINE);
            LOGGER.info("Podaj nowy format daty lub " +
                    "naciśnij enter aby wrócić do poprzedniego menu.");
            SCANNER.reset();
            String userInput = SCANNER.nextLine();
            if (userInput.equals("")) {
                return;
            } else {
                SETTINGS.setDateFormat(userInput);
            }
        }
    }

    private static String getLine() {
        return "─".repeat(80);
    }
}
