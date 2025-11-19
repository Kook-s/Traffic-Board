package io.board.articleread.service.event.handler;

import io.board.common.event.Event;
import io.board.common.event.EventPayload;

public interface EventHandler <T extends EventPayload>{
    void handle(Event<T> event);
    boolean supports(Event<T> event);
}
