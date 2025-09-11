package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RepositoryDTO(
        String fullName,
        String description,
        String cloneUrl,
        Integer stars,
        String createdAt) {
}
