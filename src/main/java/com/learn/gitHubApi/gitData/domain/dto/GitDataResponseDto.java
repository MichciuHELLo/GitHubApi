package com.learn.gitHubApi.gitData.domain.dto;

import java.util.List;

public record GitDataResponseDto(String repoName, String ownerLogin, List<BranchDto> branchDtoList) {}
