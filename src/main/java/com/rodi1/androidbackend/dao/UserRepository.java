package com.rodi1.androidbackend.dao;

import com.rodi1.androidbackend.entity.Authority;
import com.rodi1.androidbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// This annotation is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects. not necessary
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
