package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RepositoryProviderImpl implements RepositoryProvider {
    private final RepositoryJpa repositoryJpa;
    private final RepositoryMapper repositoryMapper;

    @Override
    public Optional<Repository> findByFullName(String fullName) {
        return repositoryJpa.findByFullName(fullName)
                .map(repositoryMapper::toPojo);
    }

    @Override
    public Repository saveRepository(Repository repositoryEntity) {
        RepositoryEntity toSave = repositoryMapper.toEntity(repositoryEntity);

        RepositoryEntity saved = repositoryJpa.save(toSave);
        return repositoryMapper.toPojo(saved);
    }

    @Override
    public void deleteRepository(Repository repositoryEntity) {
        RepositoryEntity toDelete = repositoryMapper.toEntity(repositoryEntity);
        repositoryJpa.delete(toDelete);

    }
}
