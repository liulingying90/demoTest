package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
@Component
/**
 * setnx命令将key设置值value,如果key不存在，这种情况等同set命令，当key存在时，什么也不做
 * getset命令先查询出原来的值，值不存在就返回nil，然后再设值
 * 注意一点的是，Redis是单线程的！所以在执行GETSET和SETNX不会存在并发的情况
 *
 */
public class RedisLock {

    private static final Logger logger=LoggerFactory.getLogger(RedisLock.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 加锁
     * @param key
     * @param value
     * @return
     */
    public boolean lock(String key,String value){
        if(stringRedisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;
        }

        //判断锁超时，防止原来的操作异常，没有运行解锁操作，防止死锁
        String currentValue=stringRedisTemplate.opsForValue().get(key);

        //如果锁过期
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue)<System.currentTimeMillis()){
            //获取上一个锁的时间,赋值新的值
            String oldValue=stringRedisTemplate.opsForValue().getAndSet(key,value);

            /*
            假设两个线程同时进来这里，因为key被占用，而且锁也过期了，获取的值currentValue=A(get取的旧值是一样的，
            两个线程的value都是B,key都是K,锁时间已经过期了，而这里面的getAndSet一次只会一个执行，也就是一个执行之后，
            上一个的value已经变成了B，只有一个线程获取的上一个值回事A，另一个线程拿到的值是B
             */
            if(!StringUtils.isEmpty(oldValue) && currentValue.equals(oldValue)){
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unlock(String key,String value){
        try{
            String currentValue=stringRedisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmpty(currentValue) && currentValue.equals(value)){
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            logger.error("redis分布式锁解锁出现异常，{}",e);
        }
    }
}
