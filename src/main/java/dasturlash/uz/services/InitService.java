package dasturlash.uz.services;

import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.repositories.ProfileRepository;
import dasturlash.uz.utils.MD5Util;

import java.time.LocalDateTime;

public class InitService {
   private ProfileRepository profileRepository  = new ProfileRepository();
    public void initAdmin() {
        String admin = "admin";
        ProfileDTO profileDTO = profileRepository.getByLogin(admin);

        if(profileDTO == null) {
            ProfileDTO adminProfile = new ProfileDTO();
            adminProfile.setName("Sidddiqjon");
            adminProfile.setSurname("Numonjonov");
            adminProfile.setRole(ProfileRole.ADMIN);
            adminProfile.setStatus(ProfileStatus.ACTIVE);
            adminProfile.setCreatedAt(LocalDateTime.now());
            adminProfile.setLogin("admin");
            adminProfile.setUpdatedAt(null);
            adminProfile.setPassword(MD5Util.getMd5("123"));
            profileRepository.add(adminProfile);
        }
    }
}
