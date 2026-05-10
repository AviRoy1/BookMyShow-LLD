package com.example.BMS.BookMyShow.Design.services.impl;

import com.example.BMS.BookMyShow.Design.services.ICacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements ICacheService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value.toString(), 1, TimeUnit.MINUTES);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.opsForValue().getAndDelete(key);
    }

    @Override
    public void getAllKeysAndValues() {
        redisTemplate.keys("*").forEach(key -> {
            System.out.println("Key: "+key+" Value: "+redisTemplate.opsForValue().get(key));
        });
    }
}
