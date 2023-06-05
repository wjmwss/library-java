package cn.srd.itcp.sugar.cache.redisson.common.core;

import cn.srd.itcp.sugar.tool.exceptions.UnsupportedOperationException;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Redisson 缓存操作（List）
 *
 * @author wjm
 * @since 2023-01-12 10:37:12
 */
public class RedissonListCaches implements RedissonCacheTemplate {

    /**
     * protected block constructor
     */
    protected RedissonListCaches() {
    }

    /**
     * singleton pattern
     */
    protected static final RedissonListCaches INSTANCE = new RedissonListCaches();

    @Override
    public Object get(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<T> get(String... keys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<T> get(Collection<String> keys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> Map<String, V> getMap(String... keys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> Map<String, V> getMap(Collection<String> keys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getAndSet(String key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getAndDelete(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(String... keys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(Collection<String> keys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> boolean compareAndSet(String key, T expectedValue, T updateValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<T> getByNamespace(String namespace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<T> getByPattern(String pattern) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int deleteByNamespace(String namespace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int deleteByPattern(String pattern) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> void set(String key, T value, Duration expiration) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> boolean setIfExists(String key, T value, Duration expiration) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> boolean setIfAbsent(String key, T value, Duration expiration) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T getAndSet(String key, T value, Class<T> oldClazz, Duration expiration) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getExpirationTime(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getTimeToLive(String key) {
        throw new UnsupportedOperationException();
    }

}
