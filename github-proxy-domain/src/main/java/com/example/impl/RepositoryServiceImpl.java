package com.example.impl;


import com.example.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {
    private final GitHubClient gitHubClient;
    private final RepositoryProvider repositoryProvider;

    public Repository getRepository(String owner, String repo) {
        RepositoryGithubDTO repositoryGithubDTO = gitHubClient.getRepo(owner, repo);
        return RepositoryFactory.create(repositoryGithubDTO);

    }

    public Repository addRepository(String owner, String repo) {
        RepositoryGithubDTO repositoryGithubDTO = gitHubClient.getRepo(owner, repo);
        return RepositoryFactory.create(repositoryGithubDTO);

    }

    public Repository getLocalRepository(String owner, String repo) {
        return repositoryProvider.findByFullName(owner + "/" + repo) // utwrzyć w provider metody ->
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public Repository updateRepository(String owner, String repo){
        RepositoryGithubDTO repositoryGithubDTO = gitHubClient.getRepo(owner,repo);

        Repository repository = repositoryProvider.findByFullName(owner + "/" + repo)
                .orElseThrow(() -> new RuntimeException("Repository not found"));

        repository.setFullName(repositoryGithubDTO.fullName());
        repository.setDescription(repositoryGithubDTO.description());
        repository.setStars(repositoryGithubDTO.stars());
        repository.setCloneUrl(repositoryGithubDTO.cloneUrl());
        repository.setCreatedAt(repositoryGithubDTO.createdAt());

        Repository repositoryUpdated = repositoryProvider.saveRepository(repository);
        return repositoryUpdated;
    }

    public void deleteRepository(String owner, String repo){
        Repository repository = repositoryProvider.findByFullName(owner + "/" + repo)
                .orElseThrow(() -> new RuntimeException("Repository not found"));
        repositoryProvider.deleteRepository(repository);
    }
}
