package com.example;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/repositories")
@Slf4j
public class RepositoryController {
    private final RepositoryService repositoryService;
    private final RepositoryMapper repositoryMapper;

    @GetMapping("/{owner}/{repo}")
    public RepositoryDTO getRepository(
            @PathVariable String owner,
            @PathVariable String repo
    ){
        log.info("get request /repositories/{}/{}", owner, repo);
        Repository repositoryEntity = repositoryService.getRepository(owner,repo);
        RepositoryDTO repositoryDTO = repositoryMapper.repoToDTO(repositoryEntity);
        return repositoryDTO;
    }

    @PostMapping("/{owner}/{repo}")
    public RepositoryDTO addRepository(
            @PathVariable String owner,
            @PathVariable String repo
    ){
        log.info("post request /repositories/{}/{}", owner, repo);
        Repository repositoryEntity = repositoryService.addRepository(owner, repo);
        return repositoryMapper.repoToDTO(repositoryEntity);
    }

}
