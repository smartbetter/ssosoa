package com.example.server.soa.dao;

import com.example.server.soa.dao.redis.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisClientTests {

    @Autowired
    private RedisClient redisClient;

    @Test
    public void testSet() {
        String result = redisClient.set("test:set", "{\"code\":\"0\"}");
        log.info("testSet result: {}", result); // result: OK
    }

    @Test
    public void testExpire() {
        Long expire = redisClient.expire("test:set", 30);
        log.info("testExpire result: {}", expire); // result: 1
    }

    @Test
    public void testGet() {
        String result = redisClient.get("test:set");
        log.info("testGet result: {}", result);
    }

    @Test
    public void testDel() {
        Long result = redisClient.del("test:set");
        redisClient.expire("test:set", 30);
        log.info("testDel result: {}", result); // result: 1
    }
}
