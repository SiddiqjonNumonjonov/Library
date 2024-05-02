package dasturlash.uz.utils;

import dasturlash.uz.dtos.ProfileDTO;

public class ProfileValidationUtil {
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
