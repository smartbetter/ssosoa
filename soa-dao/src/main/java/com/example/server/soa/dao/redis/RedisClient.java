package com.example.server.soa.dao.redis;

public interface RedisClient {

    // ================ 存储字符串String ================
    /**
     * 设置一个key的value值
     *
     * key名设计规范 - 业务名(或数据库名):表名:id
     * value设计规范 - string类型控制在10KB以内，hash、list、set、zset元素个数不要超过5000
     *
     * @param key
     * @param value
     * @return Status code reply
     */
    String set(String key, String value);

    String set(byte[] key, byte[] value);

    /**
     * 设置key-value并设置过期时间
     *
     * @param key
     * @param seconds 过期时间(s)
     * @param value
     * @return Status code reply
     */
    String setex(String key, int seconds, String value);

    String setex(byte[] key, int seconds, byte[] value);

    /**
     * 返回key的value
     *
     * @param key
     * @return Bulk reply
     */
    String get(String key);

    byte[] get(byte[] key);

    /**
     * 设置一个key的value,并获取设置前的值
     *
     * @param key
     * @param value
     * @return Bulk reply
     */
    String getSet(String key, String value);

    byte[] getSet(byte[] key, byte[] value);

    /**
     * 删除指定的key(一个或多个)
     *
     * @param keys
     * @return Integer reply, specifically: an integer greater than 0 if one or more keys were removed
     *         0 if none of the specified key existed
     */
    Long del(String... keys);

    Long del(byte[]... keys);

    /**
     * 执行原子加1操作,如果没有就默认创建0并递增1
     *
     * @param key
     * @return Integer reply, this commands will reply with the new value of key after the increment.
     */
    Long incr(String key);

    Long incr(byte[] key);

    /**
     * 整数原子减1,如果没有就默认创建0并递减1
     *
     * @param key
     * @return Integer reply, this commands will reply with the new value of key after the increment.
     */
    Long decr(String key);

    Long decr(byte[] key);

    /**
     * 执行原子增加一个整数,如果没有就默认创建0
     *
     * @param key
     * @param value
     * @return Integer reply, this commands will reply with the new value of key after the increment.
     */
    Long incrBy(String key, Long value);

    Long incrBy(byte[] key, Long value);

    /**
     * 原子减指定的整数,如果没有就默认创建0
     *
     * @param key
     * @param value
     * @return Integer reply, this commands will reply with the new value of key after the increment.
     */
    Long decrBy(String key, Long value);

    Long decrBy(byte[] key, Long value);

    /**
     * 追加指定内容到key上,如果有则追加内容,如果没有则新创建
     *
     * @param key
     * @param value
     * @return Integer reply, specifically the total length of the string after the append operation.
     */
    Long append(String key, String value);

    Long append(byte[] key, byte[] value);

    /**
     * 设置一个key的过期的秒数
     *
     * @param key
     * @param seconds
     * @return Integer reply, specifically: 1: the timeout was set. 0: the timeout was not set since
     *         the key already has an associated timeout (this may happen only in Redis versions &lt;
     *         2.1.3, Redis &gt;= 2.1.3 will happily update the timeout), or the key does not exist.
     */
    Long expire(String key, int seconds);

    Long expire(byte[] key, int seconds);

    /**
     * 获取key的有效时间,还有多久过期(s)
     *
     * @param key
     * @return Integer reply, returns the remaining time to live in seconds of a key that has an
     *         EXPIRE. If the Key does not exists or does not have an associated expire, -1 is
     *         returned.
     */
    Long ttl(String key);

    Long ttl(byte[] key);

    // ================ 存储哈希Hash ================
    /**
     * 设置hash里面的一个字段的值
     *
     * @param key
     * @param field Hash键
     * @param value Hash值
     * @return If the field already exists, and the HSET just produced an update of the value, 0 is
     *         returned, otherwise if a new field is created 1 is returned.
     */
    Long hset(String key, String field, String value);

    Long hset(byte[] key, byte[] field, byte[] value);

    /**
     * 获取hash中field的值
     *
     * @param key
     * @param field Hash键
     * @return Hash值
     */
    String hget(String key, String field);

    byte[] hget(byte[] key, byte[] field);

    /**
     * 删除一个或多个hash的field
     *
     * @param key
     * @param field Hash键
     * @return If the field was present in the hash it is deleted and 1 is returned, otherwise 0 is
     *         returned and no operation is performed.
     */
    Long hdel(String key, String... field);

    Long hdel(byte[] key, byte[]... field);
}
