package com.example.BMS.BookMyShow.Design.services;

import org.springframework.stereotype.Service;

@Service
public interface ICacheService {

    void set(String key, Object value);

    Object get(String key);

    void delete(String key);

    void getAllKeysAndValues();

    void deleteAll();
}
