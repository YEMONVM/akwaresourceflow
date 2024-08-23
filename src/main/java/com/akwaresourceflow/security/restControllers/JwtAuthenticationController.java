package com.akwaresourceflow.security.restControllers;

import com.akwaresourceflow.services.Interfaces.AppUserService;
import com.akwaresourceflow.models.AppUser;
import com.akwaresourceflow.security.jwt.configs.JwtTokenUtil;
import com.akwaresourceflow.security.jwt.models.JwtRequest;
import com.akwaresourceflow.security.jwt.models.JwtResponse;
import com.akwaresourceflow.security.services.JwtUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;

    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                                       JwtUserDetailsService userDetailsService, AppUserService appUserService,
                                       PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/auth/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        final String username = authenticationRequest.getUsername();
        final String password = authenticationRequest.getPassword();

        authenticate(username, password);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        AppUser appUser = appUserService.getAppUser(username);
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername(), appUser));
    }

    @PostMapping(value = "/auth/signup")
    public ResponseEntity<?> saveUser(@RequestBody AppUser appUser) throws Exception {
        // Encode the password and save the user
        return ResponseEntity.ok(appUserService.saveAppUser(appUser));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            System.out.println("Invalid credentials for user: " + username);
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
