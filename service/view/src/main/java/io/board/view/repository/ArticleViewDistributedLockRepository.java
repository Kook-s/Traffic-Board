package io.board.view.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class ArticleViewDistributedLockRepository {

    private final StringRedisTemplate stringRedisTemplate;

    // view::article::{article_id}::user::{user_id}::lock
    private static final String KEY_FORMAT = "view::article::%s::user::%s::lock";

    public boolean lock(Long articleId, Long userId, Duration ttl) {
        String key = generatorKey(articleId, userId);
        return stringRedisTemplate.opsForValue().setIfAbsent(key, "", ttl);
    }

    private String generatorKey(Long articleId, Long userId) {
        return String.format(KEY_FORMAT, articleId, userId);
    }
}
