package com.example.server.soa.dao.redis.config;

import com.example.server.soa.dao.redis.RedisClient;
import com.example.server.soa.dao.redis.impl.RedisClientSingle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class RedisConfig {

    @Value("${REDIS_SINGLE_ADDRESS}")
    private String singleNodes;
    @Value("${REDIS_SINGLE_PASSWORD}")
    private String singlePassword;
    @Value("${REDIS_SINGLE_TIMEOUT}")
    private Integer singleTimeout;

    @Value("${REDIS_CLUSTER_ADDRESS}")
    private String clusterNodes;
    @Value("${REDIS_CLUSTER_PASSWORD}")
    private String clusterPassword;
    @Value("${REDIS_CLUSTER_TIMEOUT}")
    private Integer clusterTimeout;
    @Value("${REDIS_CLUSTER_MAX_ATTEMPTS}")
    private Integer maxAttempts;

    @Bean
    public RedisClient redisClient() {
        return new RedisClientSingle();
    }

    /**
     * 配置Redis客户端单机版
     */
    @Bean
    public JedisPool jedisPool() {
        String[] split = singleNodes.split(":");
        return new JedisPool(
                jedisPoolConfig(),
                split[0],
                Integer.parseInt(split[1]),
                singleTimeout,
                singlePassword
        );
    }

    /**
     * 配置Redis客户端集群版
     */
    //@Bean
    public JedisCluster jedisCluster() {
        String[] sNodes = clusterNodes.split(",");
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        for (String node : sNodes) {
            String[] hp = node.split(":");
            nodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
        }
        return new JedisCluster(
                nodes,
                clusterTimeout,
                clusterTimeout,
                maxAttempts,
                clusterPassword,
                jedisPoolConfig()
        );
    }

    private JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 连接池最大连接数,使用负值表示没有限制,默认8
        jedisPoolConfig.setMaxTotal(8);
        // 连接池中的最大空闲连接,默认8
        jedisPoolConfig.setMaxIdle(8);
        // 连接池中的最小空闲连接,默认0
        jedisPoolConfig.setMinIdle(0);
        // 连接池最大阻塞等待时间,使用负值表示没有限制,默认-1L
        jedisPoolConfig.setMaxWaitMillis(15000);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个,默认false
        jedisPoolConfig.setTestOnBorrow(true);
        // 在空闲时检查有效性,默认false
        jedisPoolConfig.setTestWhileIdle(true);
        return jedisPoolConfig;
    }
}
