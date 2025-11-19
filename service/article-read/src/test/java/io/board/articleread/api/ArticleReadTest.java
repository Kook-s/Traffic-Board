package io.board.articleread.api;

import io.board.articleread.service.response.ArticleReadResponse;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class ArticleReadTest {

    RestClient restClient = RestClient.create("http://127.0.0.1:9005");

    @Test
    void readTest() {
        ArticleReadResponse response = restClient.get()
                .uri("/v1/articles/{articleId}", 243742454166671363L)
                .retrieve()
                .body(ArticleReadResponse.class);

        System.out.println("response = " + response);
    }
}
