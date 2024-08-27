package com.akwaresourceflow.security.jwt.models;

import com.akwaresourceflow.models.AppUser;
import java.io.Serializable;

public class JwtResponse implements Serializable {

    private final String jwttoken;
    private String id;
    private String role;
    private AppUser appUser;

    public JwtResponse(String jwttoken, String id, String role, AppUser appUser) {
        this.jwttoken = jwttoken;
        this.id = id;
        this.role = role;
        this.appUser = appUser;
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AppUser getUser() {
        return appUser;
    }

    public void setUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
