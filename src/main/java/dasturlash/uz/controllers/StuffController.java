package dasturlash.uz.controllers;

import dasturlash.uz.containers.ComponentContainer;
public class StuffController {
    public void start() {
        boolean loop = true;
        while (loop){
            printMenu();
            int command = getAction();
            switch (command) {
                case 1:
                    break;
                case 2:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("something went wrong !!!!");
            }
        }

    }
    public void printMenu() {
        System.out.println("**Stuff Menu***");
        System.out.println("1=> Book");
        System.out.println("2=>Student");
        System.out.println("0=>exit");

        System.out.println("enter action : ");
    }
    public int getAction() {
        return ComponentContainer.scannerForDigit.nextInt();
    }
}
