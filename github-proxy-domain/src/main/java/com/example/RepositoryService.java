package com.example;

public interface RepositoryService {
    Repository getRepository(String owner, String repo);

    Repository addRepository(String owner, String repo);

    Repository getLocalRepository(String owner, String repo);

    Repository updateRepository(String owner, String repo);

    void deleteRepository(String owner, String repo);


}
