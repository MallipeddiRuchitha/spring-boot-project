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

public class ActorRestController {
    private ActorService actorService;
    private MovieActorService movieActorService;
   // private MovieService movieService;
    @Autowired
    public ActorRestController(ActorService actorService, MovieActorService movieActorService) {
        this.actorService = actorService;
        this.movieActorService = movieActorService;
        //this.movieService = movieService;
    }

    @GetMapping("/actors")
    public List<Actor> findAllActors() {
        return actorService.findAll();
    }




    @GetMapping("/actors/{actorId}/movies")
    public List<Movie> findMoviesOfActor(@PathVariable String actorId)

    { return movieActorService.findMoviesByActorId(actorId);



    }

    @PostMapping("/actors")
    public Actor addActor(@RequestBody Actor actor) {



try{
    actorService.findById(actor.getActorId());
}
catch(RuntimeException exception){
    actorService.save(actor);
    return actor;
        }
        throw new RuntimeException("Actor id already exists");


       // return actor;
    }


    @PutMapping("/actors")
    public Actor updateActor(@RequestBody Actor actor) {

        actorService.save(actor);

        return actor;
    }
    @DeleteMapping("/actors/{actorId}")
    public String deleteActor(@PathVariable String actorId) {


Actor actor=actorService.findById(actorId);
        // throw exception if null

        if (actor == null) {
            throw new RuntimeException("Actor id not found - " + actorId);
        }

        actorService.deleteById(actorId);

        return "Deleted actor id - " + actorId;
    }







}
