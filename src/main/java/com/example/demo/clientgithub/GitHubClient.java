package com.example.demo.clientgithub;

import com.example.demo.clientgithub.model.RepositoryGithubDTO;
import lombok.Builder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "github", url = "https://api.github.com" )
public interface GitHubClient {
    @GetMapping("/repos/{owner}/{repo}")
    RepositoryGithubDTO getRepo(
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repo
    );

}
