package com.springboot.imdb.demo.service;

import com.springboot.imdb.demo.entity.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> findAll();

    Actor findById(String id);

    void save(Actor actor);

    void deleteById(String id);

}
