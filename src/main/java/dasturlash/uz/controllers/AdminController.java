package dasturlash.uz.controllers;

import java.util.Scanner;

public class AdminController {
    public void start() {
        boolean loop = true;
        while (loop) {
            printMenu();
            int action = getAction();

            switch (action) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.err.println("something went wrong !!!");
            }
        }
    }
    public void printMenu() {
        System.out.println("***Admin Menu***");
        System.out.println("1=>Book");
        System.out.println("2=>Category");
        System.out.println("3=>Student");
        System.out.println("4=>Profile");
        System.out.println("0=>Exit");
        System.out.println("enter action : ");
    }
    public int getAction() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
