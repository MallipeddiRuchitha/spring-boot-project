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
    public Optional<Actor> findById(String actorId) {
        Optional<Actor> actor = actorRepository.findById(actorId);
        if (!actor.isPresent()) {
            throw new RuntimeException("Actor id not found - " + actorId);
        }


        return actor;
    }

    @Override
    public Actor saveNewActor(Actor actor){
        String actorId=actor.getActorId();
        Optional<Actor> tempActor = actorRepository.findById(actorId);
        if (tempActor.isPresent()) {
            throw new RuntimeException("Actor id already exists - " + actorId);
        }

            //actorRepository.save(actor);

        return actorRepository.save(actor);
    }
    @Override
    public Actor updateActor(Actor actor){

            return actorRepository.save(actor);


    }

    @Override
    public String deleteById(String actorId) {
        Optional<Actor> actor = actorRepository.findById(actorId);
        if (!actor.isPresent()) {
            throw new RuntimeException("Actor id not found - " + actorId);
        }
        actorRepository.deleteById(actorId);
        return "Deleted actor id - " + actorId;
    }

}