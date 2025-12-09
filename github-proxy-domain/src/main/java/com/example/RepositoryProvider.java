package com.example;

import java.util.Optional;

public interface RepositoryProvider {
    Optional<Repository> findByFullName(String fullName);
    Repository saveRepository(Repository repository);
    void deleteRepository(Repository repository);
}
