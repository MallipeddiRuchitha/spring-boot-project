package com.springboot.imdb.demo.service;

import com.springboot.imdb.demo.entity.Actor;
import com.springboot.imdb.demo.entity.Movie;
import com.springboot.imdb.demo.entity.MovieActor;
import com.springboot.imdb.demo.model.MovieActorIdentity;

import java.util.List;
import java.util.Optional;

public interface MovieActorService {

    List<MovieActor> findAll();

    Optional<MovieActor> findById(String movieId,String actorId);

    //void save(MovieActor movieActor);
    MovieActor saveNewMovieActor(MovieActor movieActor);
    MovieActor updateMovieActor(MovieActor movieActor);



    void deleteById(String movieId,String actorId);

     List<Actor> findActorsByMovieId(String movieId);

    List<Movie> findMoviesByActorId(String actorId);
}
