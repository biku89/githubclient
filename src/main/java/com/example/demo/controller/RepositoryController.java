package com.example.demo.controller;

import com.example.demo.clientgithub.GitHubClient;
import com.example.demo.clientgithub.model.RepositoryGithubDTO;
import com.example.demo.model.Repository;
import com.example.demo.model.RepositoryDTO;
import com.example.demo.service.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/repositories")
public class RepositoryController {
    private final RepositoryService repositoryService;

    @GetMapping("/{owner}/{repo}")
    public RepositoryDTO getRepository(
            @PathVariable String owner,
            @PathVariable String repo
    ){
        return repositoryService.getRepository(owner,repo);
    }

    @PostMapping("/{owner}/{repo}")
    public RepositoryDTO addRepository(
            @PathVariable String owner,
            @PathVariable String repo
    ){
      return repositoryService.addRepository(owner,repo);
    }

}
