package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.enums.Role;
import com.akwaresourceflow.models.AppUser;
import com.akwaresourceflow.repositories.AppUserRepo;
import com.akwaresourceflow.services.Interfaces.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepo appUserRepo;
    private final PasswordEncoder passwordEncoder;

    public AppUserServiceImpl(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public List<AppUser> getAllAppUsers() {
        List<AppUser> listAppUsers = appUserRepo.findAll();
        if (!listAppUsers.isEmpty()) {
            log.info("App users list retrieved!");
            return listAppUsers;
        } else {
            log.warn("No app users found!");
        }
        return Collections.emptyList();
    }

    @Override
    public AppUser getAppUser(String username) {
        return appUserRepo.findByUsername(username);
    }

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        try {
            log.info("App user has been created successfully!");
            return appUserRepo.save(appUser);
        } catch (Exception e) {
            throw new RuntimeException("Error saving user: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteAppUser(Long idAppUser) {
        try {
            appUserRepo.deleteById(idAppUser);
            log.info("App user has been deleted!");
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user: " + e.getMessage(), e);
        }
    }

    @Override
    public AppUser updateAppUser(Long idAppUser, AppUser newAppUser) {
        Optional<AppUser> existingUser = appUserRepo.findById(idAppUser);
        if (existingUser.isPresent()) {
            AppUser userToUpdate = existingUser.get();
            userToUpdate.setLastname(newAppUser.getLastname());
            userToUpdate.setFirstname(newAppUser.getFirstname());
            userToUpdate.setUsername(newAppUser.getUsername());
            userToUpdate.setPassword(passwordEncoder.encode(newAppUser.getPassword()));
            userToUpdate.setRole(newAppUser.getRole());
            return appUserRepo.save(userToUpdate);
        } else {
            log.warn("No app user with the following ID has been found: " + idAppUser);
            return null;
        }
    }

    @Override
    public int totalNumberAppUsers() {
        return appUserRepo.findAll().size();
    }

    @Override
    public List<AppUser> getAppUsersByRole(Role role) {
        return appUserRepo.findByRole(role);
    }

    @Override
    public boolean userExists(String username) {
        return appUserRepo.findByUsername(username) != null;
    }

    @Override
    public AppUser getAppUserById(Long idAppUser) {
        Optional<AppUser> appUser = appUserRepo.findById(idAppUser);
        return appUser.orElse(null);
    }

    @Override
    public List<AppUser> getAppUsersByStation(Long stationId) {
        return appUserRepo.findByStationId(stationId);
    }
}
