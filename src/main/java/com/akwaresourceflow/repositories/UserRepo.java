package com.akwaresourceflow.repositories;

import com.akwaresourceflow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByLogin(String login);
}
