package com.akwaresourceflow.security.services;

import com.akwaresourceflow.repositories.UserRepo;
import com.akwaresourceflow.models.User;
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

    private final UserRepo userRepo;

    @Autowired
    public JwtUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        User user = userRepo.findByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException("Admin not found with username: " + login);
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(user.getRole())));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                authorities);
    }
}
