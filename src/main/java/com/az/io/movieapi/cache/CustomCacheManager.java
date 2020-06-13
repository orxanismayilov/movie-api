package com.az.io.movieapi.cache;


import com.az.io.movieapi.model.Metadata;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomCacheManager {

    private static CustomCacheManager instance;
    private final Map<String, List<Metadata>> map;
    private long cacheTime;
    private static final long life=24*60*60;

    private CustomCacheManager() {
        map= Collections.synchronizedMap(new HashMap<>());

    }

    synchronized public static CustomCacheManager getInstance(){
        if (instance== null) {
            instance=new CustomCacheManager();
        }
        return instance;
    }

    synchronized public List<Metadata> get(String key) {
        return map.get(key);
    }

    public void add(String key,List<Metadata> metadata) {
        cacheTime=System.currentTimeMillis();
        map.put(key,metadata);
    }

    public boolean isExist(String key) {
        return map.containsKey(key);
    }

    public boolean isExpired() {
        return System.currentTimeMillis()>cacheTime+life;
    }
}
