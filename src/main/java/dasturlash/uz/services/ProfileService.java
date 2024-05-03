package dasturlash.uz.services;

import dasturlash.uz.containers.ComponentContainer;
import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.utils.ProfileValidationUtil;

import java.time.LocalDateTime;
import java.util.List;

public class ProfileService {
    public Boolean addProfile(ProfileDTO profileDTO) {

        var isValid = ComponentContainer.profileValidationUtil.isValid(profileDTO);
        if(!isValid) {
            return false;
        }
        ProfileDTO isExist = ComponentContainer.profileRepository.getByLogin(profileDTO.getLogin());
        if(isExist != null) {
            return false;
        }
        profileDTO.setCreatedAt(LocalDateTime.now());
        profileDTO.setStatus(ProfileStatus.ACTIVE);
        profileDTO.setUpdatedAt(null);

       return ComponentContainer.profileRepository.add(profileDTO);
    }



    public List<ProfileDTO> search(String query) {
        var searchedProfiles = ComponentContainer.profileRepository.search(query);
        if(searchedProfiles.isEmpty()) {
            return null;
        }
        return searchedProfiles;
    }

    public Boolean changeStatus(int id) {
        var profile = ComponentContainer.profileRepository.getById(id);
        if(profile == null) {
            return false;
        }
        if(profile.getStatus().equals(ProfileStatus.ACTIVE)) {
           return ComponentContainer.profileRepository.changeStatus(id,ProfileStatus.BLOCKED);
        } else if (profile.getStatus().equals(ProfileStatus.BLOCKED)) {
           return ComponentContainer.profileRepository.changeStatus(id,ProfileStatus.ACTIVE);
        }
        return false;
    }
    public List<ProfileDTO> getAllStudents() {
        var profiles = ComponentContainer.profileRepository.getAllStudents();

        if(profiles.isEmpty()) {
            return null;
        }
        return profiles;
    }

    public List<ProfileDTO> getAllProfiles() {
        var profiles = ComponentContainer.profileRepository.getAllProfiles();
        if(profiles.isEmpty()) {
            return null;
        }
        return profiles;
    }
}
