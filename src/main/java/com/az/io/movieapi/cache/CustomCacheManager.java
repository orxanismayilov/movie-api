package com.az.io.movieapi.cache;


import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.model.Metadata;
import com.az.io.movieapi.model.Tv;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomCacheManager {

    private static CustomCacheManager instance;
    private final Map<String, List<Metadata<List<MovieDTO>>>> movieMap;
    private final Map<String, Metadata<List<Tv>>> seriesMap;
    private final Map<String, Long> cacheTimeMap;
    private static final long life = 24 * 60 * 60;

    private CustomCacheManager() {
        movieMap = Collections.synchronizedMap(new HashMap<>());
        seriesMap = Collections.synchronizedMap(new HashMap<>());
        cacheTimeMap = Collections.synchronizedMap(new HashMap<>());
    }

    synchronized public static CustomCacheManager getInstance() {
        if (instance == null) {
            instance = new CustomCacheManager();
        }
        return instance;
    }

    synchronized public List<Metadata<List<MovieDTO>>> get(String key) {
        return movieMap.get(key);
    }

    public void addMovieCache(String key, List<Metadata<List<MovieDTO>>> metadata) {
        cacheTimeMap.put(key,System.currentTimeMillis());
        movieMap.put(key, metadata);
    }

    public void addSeriesCache(String key, Metadata<List<Tv>> metadata) {
        cacheTimeMap.put(key,System.currentTimeMillis());
        seriesMap.put(key, metadata);
    }

    public boolean isMovieExist(String key) {
        return movieMap.containsKey(key);
    }

    public boolean isSeriesExist(String key) {
        return seriesMap.containsKey(key);
    }

    public boolean isExpired(String key) {
        if (cacheTimeMap.containsKey(key))
            return System.currentTimeMillis() > cacheTimeMap.get(key) + life;
        return false;
    }
}
