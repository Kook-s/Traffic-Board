package io.board.like.controller;

import io.board.like.service.ArticleLikeService;
import io.board.like.service.response.ArticleLikeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/v1/article-like/articles/{articleId}/users/{userId}")
    public void like(
            @PathVariable("articleId") Long articleId,
            @PathVariable("userId") Long userId
    ) {

        articleLikeService.like(articleId, userId);
    }

    @DeleteMapping("/v1/article-like/articles/{articleId}/users/{userId}")
    public void unLike(
            @PathVariable("articleId") Long articleId,
            @PathVariable("userId") Long userId
    ) {

        articleLikeService.unLike(articleId, userId);
    }
}
