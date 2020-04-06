package com.springboot.imdb.demo.rest;

import com.springboot.imdb.demo.entity.Actor;
import com.springboot.imdb.demo.entity.Movie;
import com.springboot.imdb.demo.entity.MovieActor;
import com.springboot.imdb.demo.service.ActorService;
import com.springboot.imdb.demo.service.MovieActorService;
import com.springboot.imdb.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController

public class MovieRestController {
   // private ActorService actorService;
    private MovieActorService movieActorService;
    private MovieService movieService;
    @Autowired
    public MovieRestController( MovieActorService movieActorService, MovieService movieService) {
        //this.actorService = actorService;
        this.movieActorService = movieActorService;
        this.movieService = movieService;
    }


    @GetMapping("/movies")
    public List<Movie> findAllMovies() {
        return movieService.findAll();
    }


    @GetMapping("/movies/{movieId}")
    public Movie getMovie(@PathVariable String movieId) {


        Movie movie=movieService.findById(movieId);
        if (movie == null) {
            throw new RuntimeException("movie id not found - " + movieId);
        }

        return movie;
    }

    @GetMapping("/movies/{movieId}/actors")
    public List<Actor> findActorsInMovie(@PathVariable String movieId)

    { return movieActorService.findActorsByMovieId(movieId);

    }
    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie movie) {



        try{
            movieService.findById(movie.getMovieId());
        }
        catch(RuntimeException exception){
            movieService.save(movie);
            return movie;
        }
        throw new RuntimeException("Movie id already exists");


        // return actor;
    }


    @PutMapping("/movies")
    public Movie updateMovie(@RequestBody Movie movie) {

        movieService.save(movie);

        return movie;
    }
    @DeleteMapping("/movies/{movieId}")
    public String deleteMovie(@PathVariable String movieId) {


        Movie movie=movieService.findById(movieId);
        // throw exception if null


        movieService.deleteById(movieId);
        return "Deleted movie id - " + movieId;
    }




}
