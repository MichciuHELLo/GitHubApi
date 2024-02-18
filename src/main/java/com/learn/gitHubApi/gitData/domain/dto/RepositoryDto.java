package com.learn.gitHubApi.gitData.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryDto {
    private String name;
    private boolean fork;
    private OwnerDto owner;
}
