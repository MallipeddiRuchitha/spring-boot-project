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
import java.util.stream.Collectors;

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
    public Optional<MovieActor> findById(String movieId,String actorId) {
        MovieActorIdentity movieActorIdentity=new MovieActorIdentity(movieId,actorId);
        Optional<MovieActor> movieActor= movieActorRepository.findById(movieActorIdentity);
        if (!movieActor.isPresent()) {
            throw new RuntimeException("Did not find movie actor with movie id "+movieId+"  and actor Id "+actorId);
        }


        /*MovieActor movieActor = null;

        if (result.isPresent()) {
            movieActor = result.get();
        } else {

            throw new RuntimeException("Did not find movie actor id - " + id);
        }*/

        return movieActor;
    }
    @Override
    public MovieActor saveNewMovieActor(MovieActor movieActor){
        String movieId=movieActor.getMovieId();
        String actorId=movieActor.getActorId();
        MovieActorIdentity movieActorIdentity=new MovieActorIdentity(movieId,actorId);
        Optional<MovieActor> tempMovieActor= movieActorRepository.findById(movieActorIdentity);
        if (tempMovieActor.isPresent()) {

            throw new RuntimeException("movie actor  already  exists with movie id "+movieId+"  and actor Id "+actorId);
        }

          return( movieActorRepository.save(movieActor));

    }
    @Override
    public MovieActor updateMovieActor(MovieActor movieActor){

        return(movieActorRepository.save(movieActor));

    }


    @Override
    public void deleteById(String movieId,String actorId) {
        MovieActorIdentity movieActorIdentity=new MovieActorIdentity(movieId,actorId);
        Optional<MovieActor> movieActor= movieActorRepository.findById(movieActorIdentity);
        if (!movieActor.isPresent()) {
            throw new RuntimeException("Did not find movie actor with movie id "+movieId+"  and actor Id "+actorId);
        }


        movieActorRepository.deleteById(movieActorIdentity);
    }


    @Override
    public List<Actor> findActorsByMovieId(String movieId){
        List<MovieActor> movieActors=movieActorRepository.findByMovieId(movieId);
        List<Actor> actors =  movieActors.stream()
                .map(movieActor -> movieActor.getActor())
                .collect(Collectors.toList());

        return actors;
    }
    @Override
    public List<Movie> findMoviesByActorId(String actorId){
       /* Optional<Movie> movie=movieActorRepository.findMovieByActorId(actorId);
        if(!movie.isPresent())*/
        List<MovieActor> movieActors=movieActorRepository.findByActorId(actorId);
        List<Movie> movies =  movieActors.stream()
                .map(movieActor -> movieActor.getMovie())
                .collect(Collectors.toList());

        return movies;

        //return movieActorRepository.findMoviesByActorId(actorId);
    }
}


