package dasturlash.uz.services;

import dasturlash.uz.controllers.AdminController;
import dasturlash.uz.controllers.StudentController;
import dasturlash.uz.controllers.StuffController;
import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.repositories.ProfileRepository;
import dasturlash.uz.utils.MD5Util;

import java.time.LocalDateTime;

public class AuthService {
  private   ProfileRepository profileRepository = new ProfileRepository();
  private StudentController studentController = new StudentController();
  private AdminController adminController = new AdminController();
  private StuffController stuffController = new StuffController();
    public void login(String login,String password) {
       ProfileDTO profileDTO =  profileRepository.getByLogin(login);

       if(profileDTO == null) {
           return;
       }

       String encodedPassword = MD5Util.getMd5(password);

       if(!encodedPassword.equals(profileDTO.getPassword())) {
           return;
       }

       if(!profileDTO.getStatus().equals(ProfileStatus.ACTIVE)) {
           return;
       }

       if(profileDTO.getRole().equals(ProfileRole.ADMIN)) {
          adminController.start();
       } else if (profileDTO.getRole().equals(ProfileRole.STUDENT)) {
           studentController.start();
       }else {
           stuffController.start();
       }

    }

    public Boolean registration(ProfileDTO profileDTO) {
        ProfileDTO profile = profileRepository.getByLogin(profileDTO.getLogin());

        if(profile != null) {
            return false;
        }
        profileDTO.setCreatedAt(LocalDateTime.now());
        profileDTO.setUpdatedAt(null);
        profileDTO.setRole(ProfileRole.STUDENT);
        profileDTO.setStatus(ProfileStatus.ACTIVE);
        if(isValid(profileDTO)) {
         return profileRepository.add(profileDTO);
        }
        return false;
    }
    public Boolean isValid(ProfileDTO profileDTO) {
        if(profileDTO.getName() == null || profileDTO.getName().isBlank() || profileDTO.getName().trim().length() < 3) {
            return false;
        }

        if(profileDTO.getSurname() == null || profileDTO.getSurname().isBlank() || profileDTO.getSurname().trim().length() < 3) {
            return false;
        }

        if(profileDTO.getPassword() == null || profileDTO.getPassword().isBlank() || profileDTO.getPassword().trim().length() < 5) {
            return false;
        }
        return profileDTO.getLogin() != null && !profileDTO.getLogin().isBlank() && profileDTO.getLogin().trim().length() >= 3;
    }
}
