package dasturlash.uz.controllers;

import dasturlash.uz.repositories.TableRepository;

import java.util.Scanner;

public class MainController {
    public  void start() {
        TableRepository tableRepository = new TableRepository();
        tableRepository.createTable();
        boolean loop = true;
        while (loop) {
            printMenu();
            int action = getAction();

            switch (action) {
                case 1 :
                    break;
                case 2:break;
                case 3:break;
                case 4:break;
                case 5:break;
                case 0:break;
                default:
                    System.out.println("something went wrong !!!!");
            }
        }

    }
    public void printMenu() {
        System.out.println("****Menu****");
        System.out.println("1=> Book Lists");
        System.out.println("2=>Search ");
        System.out.println("3=> By category");
        System.out.println("4=> Login");
        System.out.println("5=> Registration");
        System.out.println("0 => Exit");
        System.out.print("choose one  of them : ");
    }
    public int getAction() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
