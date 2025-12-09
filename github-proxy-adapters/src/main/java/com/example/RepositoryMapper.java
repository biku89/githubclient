package com.example;


import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RepositoryMapper {
    RepositoryDTO toDTO(RepositoryGithubDTO repositoryGithubDTO);
    RepositoryDTO repoToDTO(Repository repositoryEntity);
    Repository toPojo(RepositoryEntity repositoryEntity);
    RepositoryEntity toEntity(Repository repository);
}
