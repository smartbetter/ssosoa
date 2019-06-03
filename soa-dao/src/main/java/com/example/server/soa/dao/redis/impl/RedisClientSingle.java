package com.example.server.soa.dao.redis.impl;

import com.example.server.soa.dao.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 单机版实现类
 */
public class RedisClientSingle implements RedisClient {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.set(key, value);
        jedis.close();
        return result;
    }

    @Override
    public String set(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.set(key, value);
        jedis.close();
        return result;
    }

    @Override
    public String setex(String key, int seconds, String value) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.setex(key, seconds, value);
        jedis.close();
        return result;
    }

    @Override
    public String setex(byte[] key, int seconds, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.setex(key, seconds, value);
        jedis.close();
        return result;
    }

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    @Override
    public byte[] get(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        byte[] result = jedis.get(key);
        jedis.close();
        return result;
    }

    @Override
    public String getSet(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.getSet(key, value);
        jedis.close();
        return result;
    }

    @Override
    public byte[] getSet(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        byte[] result = jedis.getSet(key, value);
        jedis.close();
        return result;
    }

    @Override
    public Long del(String... keys) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.del(keys);
        jedis.close();
        return result;
    }

    @Override
    public Long del(byte[]... keys) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.del(keys);
        jedis.close();
        return result;
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    @Override
    public Long incr(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    @Override
    public Long decr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.decr(key);
        jedis.close();
        return result;
    }

    @Override
    public Long decr(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.decr(key);
        jedis.close();
        return result;
    }

    @Override
    public Long incrBy(String key, Long value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incrBy(key, value);
        jedis.close();
        return result;
    }

    @Override
    public Long incrBy(byte[] key, Long value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incrBy(key, value);
        jedis.close();
        return result;
    }

    @Override
    public Long decrBy(String key, Long value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.decrBy(key, value);
        jedis.close();
        return result;
    }

    @Override
    public Long decrBy(byte[] key, Long value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.decrBy(key, value);
        jedis.close();
        return result;
    }

    @Override
    public Long append(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.append(key, value);
        jedis.close();
        return result;
    }

    @Override
    public Long append(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.append(key, value);
        jedis.close();
        return result;
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key, seconds);
        jedis.close();
        return result;
    }

    @Override
    public Long expire(byte[] key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key, seconds);
        jedis.close();
        return result;
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    @Override
    public Long ttl(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    @Override
    public Long hset(String key, String field, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(key, field, value);
        jedis.close();
        return result;
    }

    @Override
    public Long hset(byte[] key, byte[] field, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(key, field, value);
        jedis.close();
        return result;
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.hget(key, field);
        jedis.close();
        return result;
    }

    @Override
    public byte[] hget(byte[] key, byte[] field) {
        Jedis jedis = jedisPool.getResource();
        byte[] result = jedis.hget(key, field);
        jedis.close();
        return result;
    }

    @Override
    public Long hdel(String key, String... field) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(key, field);
        jedis.close();
        return result;
    }

    @Override
    public Long hdel(byte[] key, byte[]... field) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(key, field);
        jedis.close();
        return result;
    }
}
