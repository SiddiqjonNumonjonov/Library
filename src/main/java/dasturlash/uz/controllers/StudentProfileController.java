package dasturlash.uz.controllers;

import dasturlash.uz.containers.ComponentContainer;

public class StudentProfileController {
    public void start() {
        boolean loop = true;
        while(loop) {
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
                    System.out.println("something went wrong !!!!");
            }
    }

    }

        public void printMenu() {
            System.out.println("***Student Profile Menu***");
            System.out.println("1=>Student lists");
            System.out.println("2=> Search students");
            System.out.println("3=> block students");
            System.out.println("4=> activate  students");
            System.out.println("5=>student by book");
            System.out.println("0=>exit");
        }

        public int getAction() {
            return ComponentContainer.scannerForDigit.nextInt();
        }
}
