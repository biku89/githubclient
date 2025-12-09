package com.example;

public record RepositoryDTO(
        String fullName,
        String description,
        String cloneUrl,
        Integer stars,
        String createdAt
) {
}
