package com.learn.gitHubApi.gitData;

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
