package io.board.like.controller;

import io.board.like.service.ArticleLikeService;
import io.board.like.service.response.ArticleLikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ArticleLikeController {

    private final ArticleLikeService articleLikeService;

    @GetMapping("/v1/article-like/articles/{articleId}/users/{userId}")
    public ArticleLikeResponse read(
            @PathVariable("articleId") Long articleId,
            @PathVariable("userId") Long userId
    ) {

        return articleLikeService.read(articleId, userId);
    }

    @GetMapping("/v1/article-like/articles/{articleId}")
    public Long count(
            @PathVariable("articleId") Long articleId
    ) {

        return articleLikeService.count(articleId);
    }

    @PostMapping("/v1/article-like/articles/{articleId}/users/{userId}/pessimistic-lock-1")
    public void likePessimisticLock1(
            @PathVariable("articleId") Long articleId,
            @PathVariable("userId") Long userId
    ) {

        articleLikeService.likePessimisticLock1(articleId, userId);
    }

    @DeleteMapping("/v1/article-like/articles/{articleId}/users/{userId}/pessimistic-lock-1")
    public void unLikePessimisticLock1(
            @PathVariable("articleId") Long articleId,
            @PathVariable("userId") Long userId
    ) {

        articleLikeService.unLikePessimisticLock1(articleId, userId);
    }

    @PostMapping("/v1/article-like/articles/{articleId}/users/{userId}/pessimistic-lock-2")
    public void likePessimisticLock2(
            @PathVariable("articleId") Long articleId,
            @PathVariable("userId") Long userId
    ) {

        articleLikeService.likePessimisticLock2(articleId, userId);
    }

    @DeleteMapping("/v1/article-like/articles/{articleId}/users/{userId}/pessimistic-lock-2")
    public void unLikePessimisticLock2(
            @PathVariable("articleId") Long articleId,
            @PathVariable("userId") Long userId
    ) {

        articleLikeService.unLikePessimisticLock2(articleId, userId);
    }

    @PostMapping("/v1/article-like/articles/{articleId}/users/{userId}/optimistic-lock")
    public void likeOptimisticLock3(
            @PathVariable("articleId") Long articleId,
            @PathVariable("userId") Long userId
    ) {

        articleLikeService.likeOptimisticLock(articleId, userId);
    }

    @DeleteMapping("/v1/article-like/articles/{articleId}/users/{userId}/optimistic-lock")
    public void unLikeOptimisticLock2(
            @PathVariable("articleId") Long articleId,
            @PathVariable("userId") Long userId
    ) {

        articleLikeService.unLikeOptimisticLock(articleId, userId);
    }
}
