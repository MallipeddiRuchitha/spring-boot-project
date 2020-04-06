package com.springboot.imdb.demo.service;

import com.springboot.imdb.demo.dao.ActorRepository;
import com.springboot.imdb.demo.dao.MovieActorRepository;
import com.springboot.imdb.demo.entity.Actor;
import com.springboot.imdb.demo.entity.Movie;
import com.springboot.imdb.demo.entity.MovieActor;
import com.springboot.imdb.demo.model.MovieActorIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MovieActorServiceImpl implements MovieActorService{
    private MovieActorRepository movieActorRepository;

    @Autowired
    public MovieActorServiceImpl(MovieActorRepository movieActorRepository) {
        this.movieActorRepository = movieActorRepository;
    }

    @Override
    public List<MovieActor> findAll() {
        return (List<MovieActor>) movieActorRepository.findAll();
    }

    @Override
    public MovieActor findById(MovieActorIdentity id) {
        Optional<MovieActor> result = movieActorRepository.findById(id);

        MovieActor movieActor = null;

        if (result.isPresent()) {
            movieActor = result.get();
        } else {

            throw new RuntimeException("Did not find movie actor id - " + id);
        }

        return movieActor;
    }
    @Override
    public void save(MovieActor movieActor) {
        movieActorRepository.save(movieActor);
    }

    @Override
    public void deleteById(MovieActorIdentity id) {
        movieActorRepository.deleteById(id);
    }


    @Override
    public List<Actor> findActorsByMovieId(String movieId){

    return movieActorRepository.findActorsByMovieId(movieId);
    }
    @Override
    public List<Movie> findMoviesByActorId(String actorId){

        return movieActorRepository.findMoviesByActorId(actorId);
    }
}

