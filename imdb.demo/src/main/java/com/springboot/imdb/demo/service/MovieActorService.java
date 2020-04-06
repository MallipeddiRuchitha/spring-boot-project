package com.springboot.imdb.demo.service;

import com.springboot.imdb.demo.entity.Actor;
import com.springboot.imdb.demo.entity.Movie;
import com.springboot.imdb.demo.entity.MovieActor;
import com.springboot.imdb.demo.model.MovieActorIdentity;

import java.util.List;

public interface MovieActorService {

    List<MovieActor> findAll();

    MovieActor findById(MovieActorIdentity id);

    void save(MovieActor movieActor);


    void deleteById(MovieActorIdentity id);

     List<Actor> findActorsByMovieId(String movieId);

    List<Movie> findMoviesByActorId(String actorId);
}
