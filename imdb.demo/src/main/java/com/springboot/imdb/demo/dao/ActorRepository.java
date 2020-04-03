package com.springboot.imdb.demo.dao;

import com.springboot.imdb.demo.entity.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor, String>  {
}
