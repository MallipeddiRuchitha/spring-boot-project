package com.springboot.imdb.demo.dao;

import com.springboot.imdb.demo.entity.Actor;
import com.springboot.imdb.demo.entity.Movie;
import com.springboot.imdb.demo.entity.MovieActor;
import com.springboot.imdb.demo.model.MovieActorIdentity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieActorRepository extends CrudRepository<MovieActor, MovieActorIdentity> {

    @Query("SELECT m.actor FROM MovieActor m  WHERE m.movieId=(:movieId)")

     List<Actor> findActorByMovieId( @Param("movieId") String movieId);

    @Query("SELECT m.movie FROM MovieActor m  WHERE m.actorId=(:actorId)")
     List<Movie> findMoviesByActorId(@Param("actorId") String actorId);

   // Optional<MovieActor> findByIdMovieIdAndIdActorId(String movieId, String actorId);


}
