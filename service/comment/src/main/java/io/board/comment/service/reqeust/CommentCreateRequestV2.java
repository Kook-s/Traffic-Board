package io.board.comment.service.reqeust;

import lombok.Getter;

@Getter
public class CommentCreateRequestV2 {

    private Long articleId;
    private String content;
    private String parentPath;
    private Long writerId;
}
