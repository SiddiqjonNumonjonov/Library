package dasturlash.uz.controllers;

import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.repositories.TableRepository;
import dasturlash.uz.services.AuthService;
import dasturlash.uz.services.InitService;
import dasturlash.uz.utils.MD5Util;

import java.util.Scanner;

public class MainController {
   private TableRepository tableRepository = new TableRepository();
   private InitService initService = new InitService();
   private AuthService authService = new AuthService();
    public  void start() {
        tableRepository.createTable();
        initService.initAdmin();
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
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter name : ");
        String name = scanner.nextLine();

        System.out.println("enter surname : ");
        String surname = scanner.nextLine();

        System.out.println("enter login : ");
        String login = scanner.nextLine();

        System.out.println("enter password : ");
        String password = scanner.nextLine();

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setPassword(MD5Util.getMd5(password));
        profileDTO.setLogin(login);
        profileDTO.setSurname(surname);
        profileDTO.setName(name);

       var isAdded =  authService.registration(profileDTO);
       if(isAdded) {
           System.out.println("regeitreted successfully");
       }

    }

    public void login() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("enter login : ");
        String login = scanner.nextLine();

        System.out.println("enter password : ");
        String password = scanner.nextLine();

        authService.login(login,password);

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
