package com.example.server.soa.dao.redis.impl;

import com.example.server.soa.dao.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * 集群版实现类
 */
public class RedisClientCluster implements RedisClient {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    @Override
    public String set(byte[] key, byte[] value) {
        return jedisCluster.set(key, value);
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return jedisCluster.setex(key, seconds, value);
    }

    @Override
    public String setex(byte[] key, int seconds, byte[] value) {
        return jedisCluster.setex(key, seconds, value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public byte[] get(byte[] key) {
        return jedisCluster.get(key);
    }

    @Override
    public String getSet(String key, String value) {
        return jedisCluster.getSet(key, value);
    }

    @Override
    public byte[] getSet(byte[] key, byte[] value) {
        return jedisCluster.getSet(key, value);
    }

    @Override
    public Long del(String... keys) {
        return jedisCluster.del(keys);
    }

    @Override
    public Long del(byte[]... keys) {
        return jedisCluster.del(keys);
    }

    @Override
    public Long incr(String key) {
        return jedisCluster.incr(key);
    }

    @Override
    public Long incr(byte[] key) {
        return jedisCluster.incr(key);
    }

    @Override
    public Long decr(String key) {
        return jedisCluster.decr(key);
    }

    @Override
    public Long decr(byte[] key) {
        return jedisCluster.decr(key);
    }

    @Override
    public Long incrBy(String key, Long value) {
        return jedisCluster.incrBy(key, value);
    }

    @Override
    public Long incrBy(byte[] key, Long value) {
        return jedisCluster.incrBy(key, value);
    }

    @Override
    public Long decrBy(String key, Long value) {
        return jedisCluster.decrBy(key, value);
    }

    @Override
    public Long decrBy(byte[] key, Long value) {
        return jedisCluster.decrBy(key, value);
    }

    @Override
    public Long append(String key, String value) {
        return jedisCluster.append(key, value);
    }

    @Override
    public Long append(byte[] key, byte[] value) {
        return jedisCluster.append(key, value);
    }

    @Override
    public Long expire(String key, int seconds) {
        return jedisCluster.expire(key, seconds);
    }

    @Override
    public Long expire(byte[] key, int seconds) {
        return jedisCluster.expire(key, seconds);
    }

    @Override
    public Long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    @Override
    public Long ttl(byte[] key) {
        return jedisCluster.ttl(key);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return jedisCluster.hset(key, field, value);
    }

    @Override
    public Long hset(byte[] key, byte[] field, byte[] value) {
        return jedisCluster.hset(key, field, value);
    }

    @Override
    public String hget(String key, String field) {
        return jedisCluster.hget(key, field);
    }

    @Override
    public byte[] hget(byte[] key, byte[] field) {
        return jedisCluster.hget(key, field);
    }

    @Override
    public Long hdel(String key, String... field) {
        return jedisCluster.hdel(key, field);
    }

    @Override
    public Long hdel(byte[] key, byte[]... field) {
        return jedisCluster.hdel(key, field);
    }
}
