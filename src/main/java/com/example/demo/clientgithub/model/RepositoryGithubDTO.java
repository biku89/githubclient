package com.example.demo.clientgithub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record RepositoryGithubDTO(
        @JsonProperty("full_name")
        String fullName,
        @JsonProperty("description")
        String description,
        @JsonProperty("clone_url")
        String cloneUrl,
        Integer stars,
        @JsonProperty("stargazers_url")
        String createdAt) {


}
