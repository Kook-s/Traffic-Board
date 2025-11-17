package io.board.hotarticle.service.eventhandler;

import io.board.common.outboxmessagerelay.Event;
import io.board.common.outboxmessagerelay.EventPayload;

public interface EventHandler<T extends EventPayload> {

    void handle(Event<T> event);

    boolean supports(Event<T> event);

    Long findArticleId(Event<T> event);
}
