package com.springboot.imdb.demo.dao;

import com.springboot.imdb.demo.entity.Actor;
import com.springboot.imdb.demo.entity.Movie;
import org.springframework.data.repository.CrudRepository;


public interface MovieRepository extends CrudRepository<Movie,String> {
}
