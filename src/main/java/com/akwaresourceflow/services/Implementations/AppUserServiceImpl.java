package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.AppUser;
import com.akwaresourceflow.services.Interfaces.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AppUserServiceImpl implements AppUserService {
    @Override
    public AppUser getAppUser(String username) {
        return null;
    }

    @Override
    public AppUser save(AppUser appUser) {
        return null;
    }
}
