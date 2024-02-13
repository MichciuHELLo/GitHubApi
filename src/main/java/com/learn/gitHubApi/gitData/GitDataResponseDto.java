package com.learn.gitHubApi.gitData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GitDataResponseDto {
    private String repoName;
    private String ownerLogin;
    private List<BranchDto> branchDtoList;
}
