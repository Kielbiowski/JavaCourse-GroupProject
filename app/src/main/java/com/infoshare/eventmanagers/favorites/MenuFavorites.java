package com.infoshare.eventmanagers.favorites;

import com.infoshare.eventmanagers.Menu;
import com.infoshare.eventmanagers.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class MenuFavorites {

    private final static Logger LOGGER = LogManager.getLogger(Menu.class);

    /**
     * Declaration of Scanner with console implementation.
     */
    Scanner scanner = new Scanner(System.in);

    Favorites favorites = new Favorites();

    /**
     * Declaration and initialization of String Array, which include available options
     * in Menu of Favorites.
     */
     static final String[] menuListFavorites = {"Wyświetl listę ulubionych wydarzeń", "Dodaj wydarzenie do listy ulubionych",
            "Usuń wydarzenie z listy ulubionych" };

    /**
     * Shows Menu of Favorites Events with all options in switch instruction, which could be
     * chosen by input in Scanner.
     */
    public void showFavoriteMenu() {
        boolean next = true;
        while (next) {
            Favorites.printFavoriteMenu();
            int choice = Utils.makeAChoice();
            switch (choice) {
                case 1:
                    Utils.printLine();
                    LOGGER.info("Wybrano opcję: " + menuListFavorites[0] + "\n");
                    favorites.viewFavorites();
                    break;
                case 2:
                    Utils.printLine();
                    LOGGER.info("Wybrano opcję: " + menuListFavorites[1] + "\n");
                    favorites.addToFavorites();
                    break;
                case 3:
                    Utils.printLine();
                    LOGGER.info("Wybrano opcję: " + menuListFavorites[2] + "\n");
                    favorites.deleteFromFavorites();
                    break;
                case 4:
                    next = false;
                    break;
                default:
                    LOGGER.info("Brak takiej opcji \n");
            }
        }
    }

}
