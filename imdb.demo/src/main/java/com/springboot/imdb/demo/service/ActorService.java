package com.springboot.imdb.demo.service;

import com.springboot.imdb.demo.entity.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorService {
    List<Actor> findAll();

    Optional<Actor> findById(String id);

    Actor saveNewActor(Actor actor);
    Actor updateActor(Actor actor);

    String deleteById(String id);

}
