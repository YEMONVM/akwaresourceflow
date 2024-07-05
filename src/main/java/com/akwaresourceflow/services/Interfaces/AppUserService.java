package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.enums.Role;
import com.akwaresourceflow.models.AppUser;

import java.util.List;

public interface AppUserService {
    List<AppUser> getAllAppUsers();
    AppUser getAppUser(String username);
    AppUser saveAppUser(AppUser appUser);
    void deleteAppUser(Long idAppUser);
    void updateAppUser(Long idAppUser, AppUser appUser);
    int totalNumberAppUsers();
    List<AppUser> getAppUsersByRole(Role role);
    boolean userExists(String username);
}
