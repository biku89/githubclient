package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface RepositoryJpa extends JpaRepository<RepositoryEntity, Long> {
    Optional<RepositoryEntity> findByFullName(String fullName);
}