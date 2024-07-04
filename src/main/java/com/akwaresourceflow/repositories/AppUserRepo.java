package com.akwaresourceflow.repositories;

import com.akwaresourceflow.enums.Role;
import com.akwaresourceflow.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
    ArrayList<AppUser> findByRole(Role role);
}
