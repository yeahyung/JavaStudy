package Caching;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class CachingTest {

    Cache<String, String> cache;

    @BeforeEach
    public void setup() {
        cache = Caffeine.newBuilder()
                .expireAfterAccess(20, TimeUnit.MILLISECONDS)
                .maximumSize(3) // Size Eviction 에 대한 정책은 TinyLFU
                .evictionListener((String key, String value, RemovalCause cause) ->
                        System.out.printf("Key %s was evicted (%s)%n", key, cause))
                .removalListener((String key, String value, RemovalCause cause) ->
                        System.out.printf("Key %s was removed (%s)%n", key, cause))
                .build();
    }

    @Test
    void cachingTest() {
        cache.put("first", "1");
        cache.put("second", "2");
        cache.put("third", "3");
        cache.put("forth", "4");
        // cache eviction 은 asynchronously 하게 실행되기 때문에 cleanUp 을 해야 정확한 값을 확인할 수 있음
        cache.cleanUp();
        Assertions.assertThat(cache.estimatedSize()).isEqualTo(3L);
        Assertions.assertThat(cache.getIfPresent("first")).isNull();
    }

}
