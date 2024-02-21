package com.learn.gitHubApi;

import com.learn.gitHubApi.gitData.domain.dto.GitDataResponseDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class GitDataServiceImplTests {

    private final String CORRECT_USERNAME = "MichciuHELLo";
    private final String INCORRECT_USERNAME = "MichciuHELLoxXx";

    @Autowired
    private static WebTestClient testClient;

    @BeforeAll
    static void setUp() {
        testClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Test
    public void getCorrectResponseWithGivenUsername() {
        testClient.get()
                .uri("/" + CORRECT_USERNAME)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(GitDataResponseDto.class);
    }

    @Test
    public void getNotFoundResponseWithGivenWrongUsername() {
        testClient.get()
                .uri("/" + INCORRECT_USERNAME)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                    .jsonPath("message", "User '" + INCORRECT_USERNAME + "' not found!").exists()
                    .jsonPath("status", "NOT_FOUND");
    }

}
