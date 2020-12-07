package com.springboot.imdb.demo.dao;

import com.springboot.imdb.demo.entity.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends CrudRepository<Actor, String>  {
}
