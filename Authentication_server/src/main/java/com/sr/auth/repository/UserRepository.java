package com.sr.auth.repository;

import com.sr.auth.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
    User save(User user);
}