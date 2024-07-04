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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService userDetailsService;
    private AppUserService appUserService;

    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                                       JwtUserDetailsService userDetailsService, AppUserService appUserService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.appUserService = appUserService;
    }


    @PostMapping(value = "/auth/signin")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        final String USERNAME = authenticationRequest.getUsername();
        final String PASSWORD = authenticationRequest.getPassword();

        authenticate(USERNAME, PASSWORD);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(USERNAME);

        AppUser appUser = appUserService.getAppUser(USERNAME);
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername(), appUser));
    }



    /* @PostMapping(value = "/auth/signup")
     public ResponseEntity<?> saveUser(@RequestBody Utilisateur utilisateur) throws Exception {
         return ResponseEntity.ok(utilisateurService.save(utilisateur));

     }
     */
    /* @PostMapping(value = "/auth/signup")
    public ResponseEntity<?> saveAppointment(@RequestBody Appointment appointment) throws Exception {
        return ResponseEntity.ok(appointmentService.save(appointment));
    } */

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}

