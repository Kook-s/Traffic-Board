package io.board.articleread.service.event.handler;

import io.board.articleread.repository.ArticleIdListRepository;
import io.board.articleread.repository.ArticleQueryModel;
import io.board.articleread.repository.ArticleQueryModelRepository;
import io.board.articleread.repository.BoardArticleCountRepository;
import io.board.common.event.Event;
import io.board.common.event.EventType;
import io.board.common.event.payload.ArticleCreatedEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class ArticleCreatedEventHandler implements EventHandler<ArticleCreatedEventPayload> {

    private final ArticleIdListRepository articleIdListRepository; //게시글 아이디 목록
    private final BoardArticleCountRepository boardArticleCountRepository; // 게시글 수를 저장
    private final ArticleQueryModelRepository articleQueryModelRepository;

    @Override
    public void handle(Event<ArticleCreatedEventPayload> event) {
        ArticleCreatedEventPayload payload = event.getPayload();
        articleQueryModelRepository.create(
                ArticleQueryModel.create(payload),
                Duration.ofDays(1)
        );
        articleIdListRepository.add(payload.getBoardId(), payload.getArticleId(), 1000L);
        boardArticleCountRepository.createOrUpdate(payload.getBoardId(), payload.getBoardArticleCount());
    }

    @Override
    public boolean supports(Event<ArticleCreatedEventPayload> event) {
        return EventType.ARTICLE_CREATED == event.getType();
    }
}
