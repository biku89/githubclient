package com.example.demo.controller;

import com.example.demo.clientgithub.GitHubClient;
import com.example.demo.clientgithub.model.RepositoryGithubDTO;
import com.example.demo.model.RepositoryDTO;
import com.example.demo.service.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/repositories")
public class RepositoryController {
    private final GitHubClient gitHubClient;
    private final RepositoryService repositoryService;

    @GetMapping("/{owner}/{repo}")
    public RepositoryDTO getRepository(
            @PathVariable String owner,
            @PathVariable String repo
    ){
        return repositoryService.getRepository(owner,repo);
    }

}
