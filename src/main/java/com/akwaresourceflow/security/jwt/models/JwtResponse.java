package com.akwaresourceflow.security.jwt.models;

import com.akwaresourceflow.models.User;
import java.io.Serializable;

public class JwtResponse implements Serializable {

    private final String jwttoken;
    private String id;
    private User user;

    public JwtResponse(String jwttoken, String id, User user) {
        this.jwttoken = jwttoken;
        this.id = id;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
