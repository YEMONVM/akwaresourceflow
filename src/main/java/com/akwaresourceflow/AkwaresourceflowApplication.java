package com.akwaresourceflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AkwaresourceflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkwaresourceflowApplication.class, args);
        // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Mot de passe à crypter
        // String rawPassword = "Tirhc1mada";

        // Crypter le mot de passe
        // String encodedPassword = passwordEncoder.encode(rawPassword);

        // Afficher le mot de passe crypté
        // System.out.println("Mot de passe crypté : " + encodedPassword);
    }

}
