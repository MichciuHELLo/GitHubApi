package com.learn.gitHubApi.gitData.domain.repository;

import com.learn.gitHubApi.gitData.domain.dto.GitDataResponseDto;
import com.learn.gitHubApi.gitData.domain.dto.RepositoryDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GitDataService {
    List<RepositoryDto> getRepositories(String username);
    List<GitDataResponseDto> getRepositoriesWithBranches(String owner, List<RepositoryDto> gitUserRepositories);
}
