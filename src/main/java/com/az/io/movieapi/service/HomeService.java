package com.az.io.movieapi.service;

import com.az.io.movieapi.model.Metadata;

import java.util.List;

public interface HomeService {

    List<Metadata> getHomePage();

    void resetCache();
}
