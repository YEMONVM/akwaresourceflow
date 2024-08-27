package com.akwaresourceflow.security.services;

import com.akwaresourceflow.repositories.AppUserRepo;
import com.akwaresourceflow.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final AppUserRepo appUserRepo;

    @Autowired
    public JwtUserDetailsService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepo.findByUsername(username);

        if (appUser == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(appUser.getRole().name())); // Correct role retrieval

        return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(),
                authorities);
    }
}
