package com.example.Task10.repositories;

import com.example.Task10.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u from User u join fetch u.roles where u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);
}
