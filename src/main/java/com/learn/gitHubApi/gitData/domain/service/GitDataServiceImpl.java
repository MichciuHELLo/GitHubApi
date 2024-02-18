package com.learn.gitHubApi.gitData.domain.service;

import com.learn.gitHubApi.gitData.domain.dto.BranchDto;
import com.learn.gitHubApi.gitData.domain.dto.GitDataResponseDto;
import com.learn.gitHubApi.gitData.domain.dto.RepositoryDto;
import com.learn.gitHubApi.gitData.domain.exception.exceptions.ResponseNotFoundException;
import com.learn.gitHubApi.gitData.domain.repository.GitDataService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitDataServiceImpl implements GitDataService {

    private final String GIT_HUB_PREFIX = "https://api.github.com/";

    @Override
    public List<RepositoryDto> getRepositories(String username) {
        try {
            RestClient restClient = RestClient.create();
            return restClient.get()
                    .uri(GIT_HUB_PREFIX + "users/" + username + "/repos")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
        } catch (NoSuchMethodError | HttpClientErrorException e) {
            throw new ResponseNotFoundException("User '" + username + "' not found!");
        }
    }

    @Override
    public List<GitDataResponseDto> getRepositoriesWithBranches(String owner, List<RepositoryDto> gitUserRepositories) {
        try {
            List<GitDataResponseDto> gitUserReposWithBranches = new ArrayList<>();

            for (RepositoryDto gitUserRepository : gitUserRepositories) {
                RestClient restClient = RestClient.create();
                List<BranchDto> branchDtoList = restClient.get()
                        .uri(GIT_HUB_PREFIX + "repos/" + owner + "/" + gitUserRepository.name() + "/branches")
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {
                        });

                var gitDataResponseDto = new GitDataResponseDto(gitUserRepository.name(), owner, branchDtoList);
                if (!gitUserRepository.fork()) {
                    gitUserReposWithBranches.add(gitDataResponseDto);
                }
            }
            return gitUserReposWithBranches;
        } catch (NoSuchMethodError | HttpClientErrorException e) {
            throw new ResponseNotFoundException("Owner '" + owner + "' not found!");
        }
    }
}
