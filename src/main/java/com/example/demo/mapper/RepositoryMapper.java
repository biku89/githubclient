package com.example.demo.mapper;

import com.example.demo.clientgithub.model.RepositoryGithubDTO;
import com.example.demo.model.RepositoryDTO;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;

@Mapper(componentModel = "spring")
public interface RepositoryMapper {
    RepositoryDTO toDTO(RepositoryGithubDTO repositoryGithubDTO);
}
