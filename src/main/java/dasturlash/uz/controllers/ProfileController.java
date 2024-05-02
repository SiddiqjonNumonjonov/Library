package dasturlash.uz.controllers;

import dasturlash.uz.containers.ComponentContainer;
import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.utils.MD5Util;

public class ProfileController {
    public void start() {
        boolean loop = true;
        while(loop){
            printMenu();
            int action = getAction();

            switch (action) {
                case 1:
                    profileLists();
                    break;
                case 2:
                    searchProfile();
                    break;
                case 3:
                    changeStatus();
                    break;
                case 4:
                    addProfile();
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("something went wrong !!!!");
            }
         }

    }

    private void changeStatus() {
        System.out.println("enter id : ");
        int id = ComponentContainer.scannerForStr.nextInt();

       var isChanged =  ComponentContainer.profileService.changeStatus(id);
       if(isChanged) {
           System.out.println("changed successfully");
           return;
       }
        System.out.println("something went wrong !!!");

    }

    private void searchProfile() {
        System.out.println("enter query : ");
        String query = ComponentContainer.scannerForStr.nextLine();

       var searchedProfiles =  ComponentContainer.profileService.search(query);
       if(searchedProfiles == null) {
           System.err.println("nothing found!! ");
           return;
       }
        for (ProfileDTO profileDTO : searchedProfiles) {
            System.out.println(profileDTO.getId()+" "+profileDTO.getName()+" "+profileDTO.getSurname());
        }
    }

    private void profileLists() {
      var allProfiles =  ComponentContainer.profileService.getAllProfiles();
      if(allProfiles == null) {
          System.out.println("no profile ");
          return;
      }
      for (ProfileDTO profileDTO : allProfiles) {
          System.out.println(profileDTO.getId()+" "+profileDTO.getName()+" "+profileDTO.getSurname());
      }
    }

    private void addProfile() {
        System.out.println("enter name : ");
        String name = ComponentContainer.scannerForStr.nextLine();

        System.out.println("enter surname : ");
        String surname = ComponentContainer.scannerForStr.nextLine();

        System.out.println("enter login : ");
        String login = ComponentContainer.scannerForStr.nextLine();

        System.out.println("enter password : ");
        String password = ComponentContainer.scannerForStr.nextLine();

        System.out.println("enter role : ");
        String role = ComponentContainer.scannerForStr.nextLine().toUpperCase();

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setPassword(MD5Util.getMd5(password));
        profileDTO.setLogin(login);
        profileDTO.setSurname(surname);
        profileDTO.setName(name);
        profileDTO.setRole(ProfileRole.valueOf(role));

       var isAdded =  ComponentContainer.profileService.addProfile(profileDTO);
       if(isAdded) {
           System.out.println("added successfully");
       }



    }

    public void printMenu() {
        System.out.println("***Profile Menu***");
        System.out.println("1=>Profile lists");
        System.out.println("2=> Search profile");
        System.out.println("3=> Change profile status");
        System.out.println("4=> Add profile");
        System.out.println("0=>exit");
    }
    public int getAction() {
        return ComponentContainer.scannerForDigit.nextInt();
    }
}
