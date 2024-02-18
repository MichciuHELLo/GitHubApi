package com.learn.gitHubApi.gitData.application.controller;

import com.learn.gitHubApi.gitData.domain.dto.GitDataResponseDto;
import com.learn.gitHubApi.gitData.domain.repository.GitDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GitDataController {

    @Autowired
    private GitDataService gitDataService;

    @GetMapping("/{username}")
    public List<GitDataResponseDto> getGitData(@PathVariable String username) {
        var gitUserRepositories = gitDataService.getRepositories(username);
        return gitDataService.getRepositoriesWithBranches(username, gitUserRepositories);
    }

}
