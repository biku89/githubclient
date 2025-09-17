package com.example.demo.service;

import com.example.demo.clientgithub.GitHubClient;
import com.example.demo.clientgithub.model.RepositoryGithubDTO;
import com.example.demo.mapper.RepositoryMapper;
import com.example.demo.model.Repository;
import com.example.demo.model.RepositoryDTO;
import com.example.demo.model.RepositoryUpdateCommand;
import com.example.demo.repository.RepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepositoryService {
    private final GitHubClient gitHubClient;
    private final RepositoryMapper repositoryMapper;
    private final RepositoryJpa repositoryJpa;

    public RepositoryDTO getRepository(String owner, String repo) {
        RepositoryGithubDTO repositoryGithubDTO = gitHubClient.getRepo(owner, repo);
        return repositoryMapper.toDTO(repositoryGithubDTO);

    }

    public RepositoryDTO addRepository(String owner, String repo) {
        RepositoryGithubDTO repositoryGithubDTO = gitHubClient.getRepo(owner, repo);

        Repository repository = new Repository();
        repository.setDescription(repositoryGithubDTO.description());
        repository.setStars(repositoryGithubDTO.stars());
        repository.setCloneUrl(repositoryGithubDTO.cloneUrl());
        repository.setFullName(repositoryGithubDTO.fullName());//to do wyniesienia do innej metody
        repositoryJpa.save(repository);
        return repositoryMapper.toDTO(repositoryGithubDTO);
    }

    public RepositoryDTO getLocalRepository(String owner, String repo) {
        return repositoryJpa.findByFullName(owner + "/" + repo)
                .map(repositoryMapper::repoToDTO)
                .orElseThrow(() -> new RuntimeException("Repository not found")); // porób własne wyjątki
    }

    public RepositoryDTO updateRepository(String owner, String repo, RepositoryUpdateCommand repositoryUpdate){
        Repository repository = repositoryJpa.findByFullName(owner + "/" + repo)
                .orElseThrow(() -> new RuntimeException("Repository not found"));

        repository.setDescription(repositoryUpdate.getDescription());
        repository.setStars(repositoryUpdate.getStars());
        repository.setCloneUrl(repositoryUpdate.getCloneUrl());
        repository.setCreatedAt(repositoryUpdate.getCreatedAt()); //do wyniesienia

        Repository repositoryUpdated = repositoryJpa.save(repository);
        return repositoryMapper.repoToDTO(repositoryUpdated);
    }

    public void deleteRepository(String owner, String repo){
        Repository repository = repositoryJpa.findByFullName(owner + "/" + repo)
                .orElseThrow(() -> new RuntimeException("Repository not found"));
        repositoryJpa.delete(repository);
    }
}
