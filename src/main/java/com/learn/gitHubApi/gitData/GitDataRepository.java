package com.learn.gitHubApi.gitData;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GitDataRepository {
    List<RepositoryDto> getRepositories(String username);
    List<GitDataResponseDto> getRepositoriesWithBranches(String owner, List<RepositoryDto> gitUserRepositories);
}
