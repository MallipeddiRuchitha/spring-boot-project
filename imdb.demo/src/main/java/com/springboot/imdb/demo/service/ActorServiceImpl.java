package com.springboot.imdb.demo.service;

import com.springboot.imdb.demo.dao.ActorRepository;
import com.springboot.imdb.demo.entity.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService{
    private ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public List<Actor> findAll() {
        return (List<Actor>) actorRepository.findAll();
    }

    @Override
    public Actor findById(String id) {
        Optional<Actor> result = actorRepository.findById(id);

        Actor actor = null;

        if (result.isPresent()) {
            actor = result.get();
        } else {

            throw new RuntimeException("Did not find actor id - " + id);
        }

        return actor;
    }
    @Override
    public void save(Actor actor) {
        actorRepository.save(actor);
    }

    @Override
    public void deleteById(String id) {
        actorRepository.deleteById(id);
    }

}