package com.example;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RepositoryFactory {
    public static Repository create(RepositoryGithubDTO repositoryGithubDTO){

        Repository repository = new Repository();
        repository.setDescription(repositoryGithubDTO.description());
        repository.setStars(repositoryGithubDTO.stars());
        repository.setCloneUrl(repositoryGithubDTO.cloneUrl());
        repository.setFullName(repositoryGithubDTO.fullName());
        repository.setCreatedAt(repositoryGithubDTO.createdAt());
        return repository;
    }
}
