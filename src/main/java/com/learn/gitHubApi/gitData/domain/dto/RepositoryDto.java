package com.learn.gitHubApi.gitData.domain.dto;

public record RepositoryDto(String name, boolean fork, OwnerDto owner) {}
