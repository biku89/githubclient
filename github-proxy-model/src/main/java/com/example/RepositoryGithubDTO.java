package com.example;

import lombok.Builder;

@Builder
public record RepositoryGithubDTO(
        String fullName,
        String description,
        String cloneUrl,
        Integer stars,
        String createdAt)
 {
}
