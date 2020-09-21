package com.infoshare.eventmanagers.utils;

import com.infoshare.eventmanagers.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Printer {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String DEFAULT_MSG = "Brak takiej opcji \n";
    private static final String DETAILS_MSG = "Szczegóły pojedyńczego wydarzenia";
    private static final String NEXT_5_MSG = "Następne pozycje";
    private static final String PREVIOUS_5_MSG = "Poprzednie pozycje";
    private final List<Event> eventList;
    private final Scanner scanner = new Scanner(System.in);

    public Printer(List<Event> eventList) {
        this.eventList = eventList;
    }

    public static void runListView(List<Event> eventList) {
        new Printer(eventList).listView();
    }

    public void listView() {
        int start = 0;
        boolean next = true;
        while (next) {
            Utils.clearScreen();
            printElements(start);

            if (start == 0 && eventList.size() <= 5) {
                Utils.printMenu(new String[]{DETAILS_MSG});
                switch (Integer.parseInt(scanner.nextLine().trim())) {
                    case 1:
                        runOneEventView();
                        break;
                    case 2:
                        next = false;
                        break;
                    default:
                        STDOUT.info(DEFAULT_MSG);
                }

            } else if (start == 0) {
                Utils.printMenu(new String[]{NEXT_5_MSG, DETAILS_MSG});
                switch (Integer.parseInt(scanner.nextLine().trim())) {
                    case 1:
                        start += 5;
                        break;
                    case 2:
                        runOneEventView();
                        break;
                    case 3:
                        next = false;
                        break;
                    default:
                        STDOUT.info(DEFAULT_MSG);
                }
            } else if (start > 0 && start < eventList.size() - 5) {
                Utils.printMenu(new String[]{NEXT_5_MSG, PREVIOUS_5_MSG, DETAILS_MSG});
                switch (Integer.parseInt(scanner.nextLine().trim())) {
                    case 1:
                        start += 5;
                        break;
                    case 2:
                        start -= 5;
                        break;
                    case 3:
                        runOneEventView();
                        break;
                    case 4:
                        next = false;
                        break;
                    default:
                        STDOUT.info(DEFAULT_MSG);
                }
            } else {
                Utils.printMenu(new String[]{PREVIOUS_5_MSG, DETAILS_MSG});
                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1:
                        start -= 5;
                        break;
                    case 2:
                        runOneEventView();
                        break;
                    case 3:
                        next = false;
                        break;
                    default:
                        STDOUT.info(DEFAULT_MSG);
                }
            }
        }
    }

    private void printElements(int start) {
        if ((start + 5) < eventList.size()) {
            for (int i = start; i < start + 5; i++) {
                eventList.get(i).printAsElement();
            }
        } else {
            for (int i = start; i < eventList.size(); i++) {
                eventList.get(i).printAsElement();
            }
        }
        Utils.printLine();
        STDOUT.info("\n");
    }

    private void runOneEventView() {
        while (true) {
            STDOUT.info("\nProszę podać Id wydarzenia ( lub 0 by wrócić) : ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            if (id == 0) {
                return;
            }
            List<Event> collect = eventList.parallelStream().filter(input -> input.getId() == id).collect(Collectors.toList());
            if (collect.isEmpty()) {
                STDOUT.info("Brak wydarzenia o podanym indeksie \n");
            } else {
                oneEventView(collect.get(0));
                break;
            }
        }
    }

    private void oneEventView(Event event) {

        int index;
        boolean nextLoop = true;
        index = eventList.indexOf(event);
        while (nextLoop) {
            Utils.clearScreen();
            eventList.get(index).printFull();
            if (eventList.size() == 1) {
                Utils.printMenu(new String[]{});
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        nextLoop = false;
                        break;
                    default:
                        STDOUT.info(DEFAULT_MSG);
                }
            } else if (index == 0) {
                Utils.printMenu(new String[]{"Następne wydarzenie"});
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        index++;
                        break;
                    case 2:
                        nextLoop = false;
                        break;
                    default:
                        STDOUT.info(DEFAULT_MSG);
                }
            } else if (index > 0 && index < eventList.size() - 1) {
                Utils.printMenu(new String[]{"Następne wydarzenie", "Poprzednie wydarzenie"});
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        index++;
                        break;
                    case 2:
                        index--;
                        break;
                    case 3:
                        nextLoop = false;
                        break;
                    default:
                        STDOUT.info(DEFAULT_MSG);
                }
            } else {
                Utils.printMenu(new String[]{"Poprzednie wydarzenie"});
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        index--;
                        break;
                    case 2:
                        nextLoop = false;
                        break;
                    default:
                        STDOUT.info(DEFAULT_MSG);
                }
            }
        }
    }
}
