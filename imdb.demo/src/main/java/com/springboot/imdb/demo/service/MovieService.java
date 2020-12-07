package com.springboot.imdb.demo.service;

import com.springboot.imdb.demo.entity.Actor;
import com.springboot.imdb.demo.entity.Movie;
import com.springboot.imdb.demo.entity.MovieActor;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAll();

    Optional<Movie> findById(String id);

    //void save(Movie movie);
    Movie saveNewMovie(Movie movie);
    Movie updateMovie(Movie movie);

    void deleteById(String id);
}
