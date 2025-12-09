package com.example;

public interface GitHubClient {
    RepositoryGithubDTO getRepo(
            String owner,
            String repo
    );
}
