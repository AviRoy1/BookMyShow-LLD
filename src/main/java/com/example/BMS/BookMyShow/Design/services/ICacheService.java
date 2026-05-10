package com.example.BMS.BookMyShow.Design.services;

import org.springframework.stereotype.Service;

@Service
public interface ICacheService {

    void save(String key, Object value);

    Object get(String key);

    void delete(String key);


}
