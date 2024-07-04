package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.AppUser;

import java.util.List;

public interface AppUserService {
    AppUser getAppUser(String username);
    AppUser save(AppUser appUser);
}
