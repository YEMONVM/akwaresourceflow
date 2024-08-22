/*package com.akwaresourceflow.models;

import com.akwaresourceflow.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import jakarta.persistence.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
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

    public AppUser(String username, Role role, String password, String lastname, String firstname) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;
    }

    public String getLogin() {
        return this.username;
    }


}
 */
package com.akwaresourceflow.models;

import com.akwaresourceflow.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Prevent password from being serialized
    private String password;

    private String lastname;

    private String firstname;

    private String phone;

    private String email;

    private String address;

    // Constructor for initializing a new user with necessary details
    public AppUser(String username, Role role, String password, String lastname, String firstname, String phone, String email, String address) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    // Method to get login, which is equivalent to username
    public String getLogin() {
        return this.username;
    }

    // Optionally, add a method to securely update the password, ensuring it's hashed before storing
    public void setPassword(String password) {
        // Ideally, hash the password here before storing it
        this.password = password;
    }
}
