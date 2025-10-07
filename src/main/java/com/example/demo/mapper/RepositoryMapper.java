package com.example.demo.mapper;

import com.example.demo.clientgithub.model.RepositoryGithubDTO;
import com.example.demo.model.Repository;
import com.example.demo.model.RepositoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RepositoryMapper {
    RepositoryDTO toDTO(RepositoryGithubDTO repositoryGithubDTO);
    RepositoryDTO repoToDTO(Repository repository);
}
