package com.akwaresourceflow.security.jwt.models;

import com.akwaresourceflow.models.AppUser;
import java.io.Serializable;

public class JwtResponse implements Serializable {

    private final String jwttoken;
    private String id;
    private AppUser appUser;

    public JwtResponse(String jwttoken, String id, AppUser appUser) {
        this.jwttoken = jwttoken;
        this.id = id;
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

    public AppUser getUser() {
        return appUser;
    }

    public void setUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
