package com.example.demo.model;

import com.example.demo.clientgithub.GitHubClient;
import com.example.demo.clientgithub.model.RepositoryGithubDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class GithubClientFallback implements FallbackFactory<GitHubClient> {
    @Override
    public GitHubClient create(Throwable cause){
        return new GitHubClient() {
            @Override
            public RepositoryGithubDTO getRepo(String owner, String repo) {
                return RepositoryGithubDTO.builder()
                        .fullName(owner + "/" + repo)
                        .description("My description")
                        .cloneUrl("cloneUrl")
                        .stars(2)
                        .build();
            }
        };
    }
}
