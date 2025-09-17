package com.example.demo.service;

import com.example.demo.clientgithub.GitHubClient;
import com.example.demo.clientgithub.model.RepositoryGithubDTO;
import com.example.demo.mapper.RepositoryMapper;
import com.example.demo.model.Repository;
import com.example.demo.model.RepositoryDTO;
import com.example.demo.repository.RepositoryJpa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class RepositoryServiceTest {
    GitHubClient gitHubClient;
    RepositoryDTO repositoryDTO;
    RepositoryMapper repositoryMapper;
    RepositoryService repositoryService;
    Repository repository;
    RepositoryJpa repositoryJpa;

    @BeforeEach
    void setUp(){
        this.gitHubClient = Mockito.mock(GitHubClient.class);
        this.repositoryDTO = Mockito.mock(RepositoryDTO.class);
        this.repositoryMapper = Mappers.getMapper(RepositoryMapper.class);
        this.repositoryService = new RepositoryService(gitHubClient,repositoryMapper,repositoryJpa);
    }

    @Test
    void getRepository_RepositoryExists_ReturnedRepositoryDTO(){
        RepositoryGithubDTO repositoryGithubDTO = new RepositoryGithubDTO(
                "biku89/medical-clinic","My repositorium","https://github.com/biku89/medical-clinic", 5,"2025-01-01T00:00:00Z" );

        RepositoryDTO repositoryDTO = new RepositoryDTO(
                "biku89/medical-clinic","My repositorium","https://github.com/biku89/medical-clinic", 5,"2025-01-01T00:00:00Z" );

        when(gitHubClient.getRepo("biku89","medical-clinic")).thenReturn(repositoryGithubDTO);

        RepositoryDTO result = repositoryService.getRepository("biku89","medical-clinic");

        Assertions.assertAll(
                () -> Assertions.assertEquals("biku89/medical-clinic", result.fullName()),
                () -> Assertions.assertEquals("My repositorium", result.description())
        );
    }

}
