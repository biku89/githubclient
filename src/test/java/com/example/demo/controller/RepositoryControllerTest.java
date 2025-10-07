package com.example.demo.controller;

import com.example.demo.clientgithub.GitHubClient;
import com.example.demo.clientgithub.model.RepositoryGithubDTO;
import com.example.demo.model.Repository;
import com.example.demo.model.RepositoryDTO;
import com.example.demo.repository.RepositoryJpa;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.extension.responsetemplating.helpers.WireMockHelpers.jsonPath;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureWireMock(port = 8585)
@SpringBootTest
@AutoConfigureMockMvc
public class RepositoryControllerTest {
    @Autowired
    WireMockServer wireMockServer;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    RepositoryJpa repositoryJpa;

    @Test
    void shouldGetRepository() throws Exception {
        String owner = "biku89";
        String repo = "medical-clinic";

        RepositoryGithubDTO repositoryGithubDTO = RepositoryGithubDTO
                .builder()
                .fullName("biku89/medical-clinic")
                .stars(1)
                .build();

        wireMockServer.stubFor(get(urlEqualTo(String.format("/repos/%s/%s", owner, repo)))
                .willReturn(aResponse()
                        .withBody(objectMapper.writeValueAsString(repositoryGithubDTO))
                        .withHeader("content-type", "application/json")));

        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/repositories/%s/%s", owner, repo)))
                .andDo(print());
    }

    @Test
    void shouldAddRepository() throws Exception{
        String owner = "biku89";
        String repo = "medical-clinic";

        RepositoryGithubDTO repositoryGithubDTO = new RepositoryGithubDTO(owner + "/" + repo,"description","clone",2, "created");

        wireMockServer.stubFor(get(urlEqualTo(String.format("/repos/%s/%s", owner, repo)))
                .willReturn(aResponse()
                        .withBody(objectMapper.writeValueAsString(repositoryGithubDTO))
                        .withHeader("content-type", "application/json")));

        mockMvc.perform(MockMvcRequestBuilders.post(String.format("/repositories/%s/%s", owner, repo)))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.fullName").value("biku89/medical-clinic"),
                        jsonPath("$.description").value(repositoryGithubDTO.description()),
                        jsonPath("$.cloneUrl").value(repositoryGithubDTO.cloneUrl()),
                        jsonPath("$.stars").value(repositoryGithubDTO.stars())
                );
    }

    @Test
    void shouldGetLocalRepository() throws Exception {
        String owner = "biku89";
        String repo = "medical-clinic";

        Repository repository = new Repository();
        repository.setFullName(owner + "/" + repo);
        repository.setDescription("My description");
        repository.setStars(0);

        when(repositoryJpa.findByFullName(owner + "/" + repo)).thenReturn(Optional.of(repository));


        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/local/repositories/%s/%s", owner, repo)))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.fullName").value("biku89/medical-clinic"),
                        jsonPath("$.description").value("My description")
                );
    }
}
