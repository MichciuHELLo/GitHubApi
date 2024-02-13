package com.learn.gitHubApi.gitData;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class GitDataService implements GitDataRepository{

    @Override
    public List<RepositoryDto> getRepositories(String username) {
        try {
            HttpResponse<String> response = getHttpResponse("https://api.github.com/users/" + username + "/repos");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return objectMapper.readValue(response.body(), new TypeReference<>() {});

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<GitDataResponseDto> getRepositoriesWithBranches(String owner, List<RepositoryDto> gitUserRepositories) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            List<GitDataResponseDto> gitUserReposWithBranches = new ArrayList<>();

            for (int i = 0; i < gitUserRepositories.size(); i++) {
                HttpResponse<String> response = getHttpResponse("https://api.github.com/repos/"+ owner + "/" + gitUserRepositories.get(i).getName() + "/branches");
                List<BranchDto> branchDtoList = objectMapper.readValue(response.body(), new TypeReference<>() {});
                GitDataResponseDto gitDataResponseDto = new GitDataResponseDto(gitUserRepositories.get(i).getName(), owner, branchDtoList);
                gitUserReposWithBranches.add(gitDataResponseDto);
            }
            return gitUserReposWithBranches;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private HttpResponse<String> getHttpResponse (String uri) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .GET()
                    .header("Accept", "application/json")
                    .build();

            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            return null;
        } catch (InterruptedException e) {
            return null;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
