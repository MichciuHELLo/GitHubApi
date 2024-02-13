package com.learn.gitHubApi.gitData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GitDataController {

    @Autowired
    private GitDataRepository gitDataRepository;

    @GetMapping("/{username}")
    public List<GitDataResponseDto> getGitData(@PathVariable String username) {
        List<RepositoryDto> gitUserRepositories = gitDataRepository.getRepositories(username);
        return gitDataRepository.getRepositoriesWithBranches(username, gitUserRepositories);
    }

}
