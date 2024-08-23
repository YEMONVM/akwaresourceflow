package com.akwaresourceflow.models;

import com.akwaresourceflow.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String lastname;
    private String firstname;
    private String phone;
    private String email;
    private String address;

    @OneToOne(mappedBy = "appuser")
    private Employee employee;

    public AppUser(String username, Role role, String password, String lastname, String firstname) {
        this.username = username;
        this.role = role;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
    }
}
