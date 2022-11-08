package Caching;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;

import java.util.concurrent.TimeUnit;

public class Caching {
    public static void main(String[] args) throws InterruptedException {
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterAccess(20, TimeUnit.MILLISECONDS)
                .maximumSize(10000)
                .evictionListener((String key, String value, RemovalCause cause) ->
                        System.out.printf("Key %s was evicted (%s)%n", key, cause))
                .removalListener((String key, String value, RemovalCause cause) ->
                        System.out.printf("Key %s was removed (%s)%n", key, cause))
                .build();

        // Lookup entry, null if not found
        String value = cache.getIfPresent("id1");
        System.out.println(value);

        // Lookup entry, if absent -> compute(function), if cannot compute -> null
        value = cache.get("id2", k -> "aa");
        System.out.println(value);

        // Insert or Update Entry
        cache.put("id3", "value3");

        // Remove an Entry
        cache.invalidate("id3");

        cache.put("id4", "value4");

        Thread.sleep(10);

        System.out.println(cache.getIfPresent("id2"));
        System.out.println(cache.getIfPresent("id4"));

        Thread.sleep(10);

        System.out.println(cache.getIfPresent("id2"));

        Thread.sleep(10);
        System.out.println(cache.getIfPresent("id2"));
        System.out.println(cache.getIfPresent("id4"));

        Thread.sleep(100);
        System.out.println(cache.getIfPresent("id2"));
    }
}
