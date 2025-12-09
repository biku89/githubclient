package com.example;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/local/repositories")
public class RepositoryLocalController {
    private final RepositoryService repositoryService;
    private final RepositoryMapper repositoryMapper;

    @GetMapping("/{owner}/{repo}")
    public RepositoryDTO getLocalRepository(
            @PathVariable String owner,
            @PathVariable String repo
    ) {
        Repository repositoryEntity = repositoryService.getLocalRepository(owner, repo);
        return repositoryMapper.repoToDTO(repositoryEntity);
    }
}
