package com.akwaresourceflow.controllers;

import com.akwaresourceflow.enums.Role;
import com.akwaresourceflow.models.AppUser;
import com.akwaresourceflow.services.Interfaces.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/appusers")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;

    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<AppUser>> getAllAppUsers() {
        List<AppUser> appUsers = appUserService.getAllAppUsers();
        return ResponseEntity.ok(appUsers);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{username}")
    public ResponseEntity<AppUser> getAppUser(@PathVariable String username) {
        AppUser appUser = appUserService.getAppUser(username);
        return ResponseEntity.ok(appUser);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<AppUser> createAppUser(@RequestBody AppUser appUser) {
        AppUser createdAppUser = appUserService.saveAppUser(appUser);
        return ResponseEntity.ok(createdAppUser);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateAppUser(@PathVariable Long id, @RequestBody AppUser appUser) {
        appUserService.updateAppUser(id, appUser);
        return ResponseEntity.ok(appUser);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppUser(@PathVariable Long id) {
        appUserService.deleteAppUser(id);
        return ResponseEntity.noContent().build();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/role/{role}")
    public ResponseEntity<List<AppUser>> getAppUsersByRole(@PathVariable Role role) {
        List<AppUser> appUsers = appUserService.getAppUsersByRole(role);
        return ResponseEntity.ok(appUsers);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/exists/{username}")
    public ResponseEntity<Boolean> userExists(@PathVariable String username) {
        boolean exists = appUserService.userExists(username);
        return ResponseEntity.ok(exists);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/count")
    public ResponseEntity<Integer> getTotalNumberOfAppUsers() {
        int total = appUserService.totalNumberAppUsers();
        return ResponseEntity.ok(total);
    }
}
