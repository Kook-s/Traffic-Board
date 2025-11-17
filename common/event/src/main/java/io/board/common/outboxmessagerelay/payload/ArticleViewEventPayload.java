package io.board.common.outboxmessagerelay.payload;

import io.board.common.outboxmessagerelay.EventPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleViewEventPayload implements EventPayload {
    private Long articleId;
    private Long articleViewCount;
}
