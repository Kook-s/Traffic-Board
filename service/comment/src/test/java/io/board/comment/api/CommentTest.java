package io.board.comment.api;

import io.board.comment.entity.Comment;
import io.board.comment.service.response.CommentPageResponse;
import io.board.comment.service.response.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class CommentTest {

    RestClient restClient = RestClient.create("http://localhost:9001");
    @Test
    void creat() {
        CommentResponse response1 = createComment(new CommentCreateRequest(1L, "my comment1", null, 1L));
        CommentResponse response2 = createComment(new CommentCreateRequest(1L, "my comment2", response1.getCommentId(), 1L));
        CommentResponse response3 = createComment(new CommentCreateRequest(1L, "my comment3", response1.getCommentId(), 1L));

        System.out.println("commentId=%s".formatted(response1.getCommentId()));
        System.out.println("\tcommentId=%s".formatted(response2.getCommentId()));
        System.out.println("\tcommentId=%s".formatted(response3.getCommentId()));


    }

    CommentResponse createComment(CommentCreateRequest request) {
        return restClient.post()
                .uri("/v1/comments")
                .body(request)
                .retrieve()
                .body(CommentResponse.class);

    }

    @Test
    void read() {
        CommentResponse response = restClient.get()
                .uri("/v1/comments/{commentId}", 244779747787235328L)
                .retrieve()
                .body(CommentResponse.class);

        System.out.println("response = " + response);
    }

    @Test
    void delete() {
//        commentId=244779747787235328
//        commentId=244779748026310656
//        commentId=244779748089225216

        restClient.delete()
                .uri("/v1/comments/{commentId}", 244779748089225216L)
                .retrieve();
    }

    @Test
    void readAll() {
        CommentPageResponse response = restClient.get()
                .uri("/v1/comments?articleId=1&page=1&pageSize=10")
                .retrieve()
                .body(CommentPageResponse.class);

        System.out.println("response.getCommentCount() = " + response.getCommentCount());
        for(CommentResponse comment : response.getComments()) {
            if(!comment.getCommentId().equals(comment.getParentCommentId())) {
                System.out.print("\t");
            }
            System.out.println("comment.getCommentId() = " + comment.getCommentId());
        }

    }

    /**
     * comment.getCommentId() = 244783539237474304
     * 	comment.getCommentId() = 244783539333943304
     * comment.getCommentId() = 244783539237474305
     * 	comment.getCommentId() = 244783539342331920
     * comment.getCommentId() = 244783539237474306
     *
     * 	comment.getCommentId() = 244783539333943296
     * comment.getCommentId() = 244783539237474307
     * 	comment.getCommentId() = 244783539350720512
     * comment.getCommentId() = 244783539237474308
     * 	comment.getCommentId() = 244783539350720516
     */
    @Test
    void readAllInfiniteScroll() {
        List<CommentResponse> response1 = restClient.get()
                .uri("/v1/comments/infinite-scroll?articleId=1&pageSize=5")
                .retrieve()
                .body(new ParameterizedTypeReference<List<CommentResponse>>() {
                });

        System.out.println("firstPage");
        for(CommentResponse comment : response1) {
            if(!comment.getCommentId().equals(comment.getParentCommentId())) {
                System.out.print("\t");
            }
            System.out.println("comment.getCommentId() = " + comment.getCommentId());
        }

        Long lastParentCommentId = response1.get(response1.size() - 1).getParentCommentId();
        Long lastCommentId = response1.get(response1.size() - 1).getCommentId();


        List<CommentResponse> response2 = restClient.get()
                .uri("/v1/comments/infinite-scroll?articleId=1&pageSize=5&lastParentCommentId=%s&lastCommentId=%s".formatted(lastParentCommentId, lastCommentId))
                .retrieve()
                .body(new ParameterizedTypeReference<List<CommentResponse>>() {
                });

        System.out.println("secondPage");
        for(CommentResponse comment : response2) {
            if(!comment.getCommentId().equals(comment.getParentCommentId())) {
                System.out.print("\t");
            }
            System.out.println("comment.getCommentId() = " + comment.getCommentId());
        }

    }


    @Getter
    @AllArgsConstructor
    public static class CommentCreateRequest {

        private Long articleId;
        private String content;
        private Long parentCommentId;
        private Long writerId;
    }
}
