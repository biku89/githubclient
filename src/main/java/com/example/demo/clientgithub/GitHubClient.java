package com.example.demo.clientgithub;

import com.example.demo.clientgithub.model.RepositoryGithubDTO;
import com.example.demo.model.FeignConfig;
import com.example.demo.model.GithubClientFallback;
import lombok.Builder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "githubClient",configuration = FeignConfig.class, fallbackFactory = GithubClientFallback.class)
public interface GitHubClient {
    @GetMapping("/repos/{owner}/{repo}")
    RepositoryGithubDTO getRepo(
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repo
    );

}
