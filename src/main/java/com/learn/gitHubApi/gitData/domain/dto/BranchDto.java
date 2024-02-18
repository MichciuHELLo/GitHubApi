package com.learn.gitHubApi.gitData.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto {
    private String name;
    private CommitDto commit;
}
