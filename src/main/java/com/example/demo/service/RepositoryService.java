package com.example.demo.service;

import com.example.demo.clientgithub.GitHubClient;
import com.example.demo.clientgithub.model.RepositoryGithubDTO;
import com.example.demo.mapper.RepositoryMapper;
import com.example.demo.model.RepositoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepositoryService {
    private final GitHubClient gitHubClient;
    private final RepositoryMapper repositoryMapper;

    public RepositoryDTO getRepository(String owner, String repo) {
        RepositoryGithubDTO repositoryGithubDTO = gitHubClient.getRepo(owner, repo);
        return repositoryMapper.toDTO(repositoryGithubDTO);

    }

}
