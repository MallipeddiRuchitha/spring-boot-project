package com.springboot.imdb.demo.service;

import com.springboot.imdb.demo.entity.Actor;
import com.springboot.imdb.demo.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();

    Movie findById(String id);

    void save(Movie movie);

    void deleteById(String id);
}
