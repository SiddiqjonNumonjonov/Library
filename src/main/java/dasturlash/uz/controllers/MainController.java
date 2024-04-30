package dasturlash.uz.controllers;

import dasturlash.uz.containers.ComponentContainer;
import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.utils.MD5Util;

import java.util.Scanner;
public class MainController {
    public  void start() {
        ComponentContainer.tableRepository.createTable();
        ComponentContainer.initService.initAdmin();
        boolean loop = true;
        while (loop) {
            printMenu();
            int action = getAction();

            switch (action) {
                case 1 :
                    break;
                case 2:break;
                case 3:break;
                case 4:
                    login();
                    break;
                case 5:
                    registration();
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("something went wrong !!!!");
            }
        }

    }

    private void registration() {

        System.out.println("enter name : ");
        String name = ComponentContainer.scannerForStr.nextLine();

        System.out.println("enter surname : ");
        String surname = ComponentContainer.scannerForStr.nextLine();

        System.out.println("enter login : ");
        String login = ComponentContainer.scannerForStr.nextLine();

        System.out.println("enter password : ");
        String password = ComponentContainer.scannerForStr.nextLine();

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setPassword(MD5Util.getMd5(password));
        profileDTO.setLogin(login);
        profileDTO.setSurname(surname);
        profileDTO.setName(name);

       var isAdded = ComponentContainer.authService.registration(profileDTO);
       if(isAdded) {
           System.out.println("regeitreted successfully");
       }else{
           System.err.println("something went wrong !!!!");
           System.out.println();
       }
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("enter login : ");
        String login = scanner.nextLine();

        System.out.println("enter password : ");
        String password = scanner.nextLine();

       ComponentContainer.authService.login(login,password);

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
        return ComponentContainer.scannerForDigit.nextInt();
    }
}
