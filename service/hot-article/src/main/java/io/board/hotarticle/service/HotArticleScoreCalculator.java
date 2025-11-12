package io.board.hotarticle.service;

import io.board.hotarticle.repository.ArticleCommentCountRepository;
import io.board.hotarticle.repository.ArticleLikeCountRepository;
import io.board.hotarticle.repository.ArticleViewCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HotArticleScoreCalculator {

    private final ArticleLikeCountRepository articleLikeCountRepository;
    private final ArticleViewCountRepository articleViewCountRepository;
    private final ArticleCommentCountRepository articleCommentCountRepository;

    private static final long ARTICLE_LIKE_COUNT_WIGHT = 3;
    private static final long ARTICLE_COMMENT_COUNT_WIGHT = 2;
    private static final long ARTICLE_VIEW_COUNT_WIGHT = 1;


    public long calculate(Long articleId) {
        Long articleLikeCount = articleLikeCountRepository.read(articleId);
        Long articleViewCount = articleViewCountRepository.read(articleId);
        Long articleCommentCount = articleCommentCountRepository.read(articleId);

        return articleLikeCount * ARTICLE_LIKE_COUNT_WIGHT
                + articleViewCount * ARTICLE_VIEW_COUNT_WIGHT
                + articleCommentCount * ARTICLE_COMMENT_COUNT_WIGHT;
    }
}
