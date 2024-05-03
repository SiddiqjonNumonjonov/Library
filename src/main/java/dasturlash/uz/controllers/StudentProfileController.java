package dasturlash.uz.controllers;

import dasturlash.uz.containers.ComponentContainer;
import dasturlash.uz.dtos.ProfileDTO;

public class StudentProfileController {
    public void start() {
        boolean loop = true;
        while(loop) {
            printMenu();
            int action = getAction();

            switch (action) {
                case 1:
                    studentLists();
                    break;
                case 2:
                    search();
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

    private void search() {
        System.out.println("enter query : ");
        String query = ComponentContainer.scannerForStr.nextLine();

        ComponentContainer.profileService.search(query);
    }

    private void studentLists() {
       var allStudents =  ComponentContainer.profileService.getAllStudents();
       if(allStudents.isEmpty()) {
           System.err.println("no student");
           return;
       }

       for (ProfileDTO profileDTO : allStudents) {
           System.out.println(profileDTO.getId()+" "+profileDTO.getName()+" "+profileDTO.getRole());
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
