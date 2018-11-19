package com.example.demo.service.impl;

import com.example.demo.service.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillServiceImpl {
    @Autowired
    private RedisLock redisLock;

    private static final int TIMEOUT=10*1000;//超时时间10s

    /**
     * 活动，特价，限量100000份
     */

}
