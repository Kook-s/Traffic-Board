package io.board.hotarticle.service.eventhandler;

import io.board.common.event.Event;
import io.board.common.event.EventType;
import io.board.common.event.payload.ArticleViewEventPayload;
import io.board.hotarticle.repository.ArticleViewCountRepository;
import io.board.hotarticle.utils.TimeCalculatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleViewEventHandler implements EventHandler<ArticleViewEventPayload> {

    private final ArticleViewCountRepository articleViewCountRepository;

    @Override
    public void handle(Event<ArticleViewEventPayload> event) {
        ArticleViewEventPayload payload = event.getPayload();
        articleViewCountRepository.createOrUpdate(
                payload.getArticleId(),
                payload.getArticleViewCount(),
                TimeCalculatorUtils.calculateDurationToMidnight()
        );
    }

    @Override
    public boolean supports(Event<ArticleViewEventPayload> event) {
        return EventType.ARTICLE_VIEWED == event.getType();
    }

    @Override
    public Long findArticleId(Event<ArticleViewEventPayload> event) {
        return event.getPayload().getArticleId();
    }
}
