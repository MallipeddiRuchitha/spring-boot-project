package com.springboot.imdb.demo.rest;

import com.springboot.imdb.demo.entity.Actor;
import com.springboot.imdb.demo.entity.Movie;
import com.springboot.imdb.demo.entity.MovieActor;
import com.springboot.imdb.demo.model.MovieActorIdentity;
import com.springboot.imdb.demo.service.ActorService;
import com.springboot.imdb.demo.service.MovieActorService;
import com.springboot.imdb.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MovieActorRestController {
   // private ActorService actorService;
    private MovieActorService movieActorService;
   // private MovieService movieService;
    @Autowired
    public MovieActorRestController( MovieActorService movieActorService) {
        //this.actorService = actorService;
        this.movieActorService = movieActorService;
       // this.movieService = movieService;
    }


    @GetMapping("/movieActors")
    public List<MovieActor> findAllMovieActors() {
        return movieActorService.findAll();
    }


    @GetMapping("/movieActors/{movieId}/{actorId}")
    public MovieActor getMovieActor(@PathVariable String movieId,@PathVariable String actorId) {

        MovieActorIdentity movieActorIdentity=new MovieActorIdentity(movieId,actorId);
        MovieActor movieActor=movieActorService.findById(movieActorIdentity);
       /* if (movieActor == null) {
            throw new RuntimeException("movie id not found - " + movieId);
        }
*/
        return movieActor;
    }


    @PostMapping("/movieActors")
    public MovieActor addMovie(@RequestBody MovieActor movieActor) {




        MovieActorIdentity movieActorIdentity=new MovieActorIdentity(movieActor.getMovieId(),movieActor.getActorId());
        try{
            movieActorService.findById(movieActorIdentity);
        }
        catch(RuntimeException exception){
            movieActorService.save(movieActor);
            return movieActor;
        }
        throw new RuntimeException("MovieActor id already exists");


        // return actor;
    }


    @PutMapping("/movieActors")
    public MovieActor updateMovieActor(@RequestBody MovieActor movieActor) {

        movieActorService.save(movieActor);

        return movieActor;
    }
    @DeleteMapping("/movieActors/{movieId}/{actorId}")
    public String deleteMovie(@PathVariable String movieId,@PathVariable String actorId) {

        MovieActorIdentity movieActorIdentity=new MovieActorIdentity(movieId,actorId);
        MovieActor movieActor=movieActorService.findById(movieActorIdentity);
        // throw exception if null


        movieActorService.deleteById(movieActorIdentity);
        return "Deleted movieActor id - " + movieId+"   "+actorId;
    }

}
