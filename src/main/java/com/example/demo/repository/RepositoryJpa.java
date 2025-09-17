package com.example.demo.repository;

import com.example.demo.model.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryJpa extends JpaRepository<Repository, Long> {
    Optional<Repository> findByFullName(String fullName);
    //Optional<Repository> findByOwnerAndName(String owner, String repo);

}
