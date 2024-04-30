package dasturlash.uz.services;

import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.repositories.ProfileRepository;
import dasturlash.uz.utils.MD5Util;

public class AuthService {
  private   ProfileRepository profileRepository = new ProfileRepository();
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
           System.out.println("Admin menu ");
       }

    }

}
