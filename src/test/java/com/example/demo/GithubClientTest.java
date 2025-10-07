package com.example.demo;

import com.example.demo.clientgithub.GitHubClient;
import com.example.demo.clientgithub.model.RepositoryGithubDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@AutoConfigureWireMock(port = 8585)
@SpringBootTest
public class GithubClientTest {
    @Autowired
    GitHubClient gitHubClient;
    @Autowired
    WireMockServer wireMockServer;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldGetRepository() throws JsonProcessingException {
        String owner = "biku89";
        String repo = "medical-clinic";
        RepositoryGithubDTO repositoryGithubDTO = RepositoryGithubDTO
                .builder()
                .fullName("biku89/medical-clinic")
                .stars(1)
                .build();

        //wireMock.stubFor- przy pomocy stubfor mówimy dla jakiego requestu wysłanego do zmokowanego serwera
        // co ma zostać zwrócne. W tym przypadku jeśli zostanie wysłany request get pod adres /repos/biku89/medical-clinic
        //willReturn- czyli ma zostać zwrócona odpowiedź http(aResponse())który będzie zawierał w body
        //dane repositygithubDTO zamienione na json'a oraz odpowiedź ma zawierać header content-type o wartości
        // aplication json czyli header który odpowiada za to zeby poinformować w jakim formacie są dane w body.

        wireMockServer.stubFor(get(urlEqualTo(String.format("/repos/%s/%s", owner, repo)))
                .willReturn(aResponse()
                        .withBody(objectMapper.writeValueAsString(repositoryGithubDTO))
                        .withHeader("content-type", "application/json")));

        RepositoryGithubDTO result = gitHubClient.getRepo(owner, repo);

        assertAll(
                () -> assertEquals("biku89/medical-clinic", result.fullName())
        );
    }

    @Test
    void shouldRetry() {
        String owner = "biku89";
        String repo = "medical-clinic";

        wireMockServer.stubFor(get(urlEqualTo(String.format("/repos/%s/%s", owner, repo)))
                .willReturn(aResponse()
                        .withStatus(503)
                        .withHeader("Retry-After", "10")));

        try {
            gitHubClient.getRepo(owner, repo);
        } catch (Exception exception) {


            verify(3, getRequestedFor(urlEqualTo("/repos/" + owner + "/" + repo)));
        }
    }

    @Test
    void shouldReturnFallback(){
        String owner = "biku89";
        String repo = "medical-clinic";

        wireMockServer.stubFor(get(urlEqualTo(String.format("/repos/%s/%s", owner, repo)))
                .willReturn(aResponse()
                        .withStatus(500)));

        RepositoryGithubDTO result = gitHubClient.getRepo(owner,repo);

        assertAll(
                () -> assertEquals("biku89/medical-clinic", result.fullName()),
                () -> assertEquals("My description", result.description()),
                () -> assertEquals("cloneUrl", result.cloneUrl()),
                () -> assertEquals(2, result.stars())
        );

    }
}
