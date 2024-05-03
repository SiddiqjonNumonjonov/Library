package dasturlash.uz.controllers;

import dasturlash.uz.containers.ComponentContainer;

import java.util.Scanner;

public class StudentController {
    public void start() {
            boolean loop = true;
            while (loop) {
                printMenu();
                int action = getAction();

                switch (action) {
                    case 1:
                        ComponentContainer.bookController.bookLists();
                        break;
                    case 2:
                        ComponentContainer.bookController.search();
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 0:
                        loop = false;
                        break;
                    default:
                        System.out.println("something went wrong !!!!");
                }

            }
        }
    public void printMenu() {
        System.out.println("***Student Menu***");
        System.out.println("1=>Book lists");
        System.out.println("2=> Search");
        System.out.println("3=> Take book");
        System.out.println("4=>Return book");
        System.out.println("5=> Books on hand");
        System.out.println("6=>Take book history");
        System.out.println("0=>exit");
    }
    public int getAction() {
        return ComponentContainer.scannerForDigit.nextInt();
    }
}
