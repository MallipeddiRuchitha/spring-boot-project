package com.springboot.imdb.demo.service;

import com.springboot.imdb.demo.dao.ActorRepository;
import com.springboot.imdb.demo.dao.MovieRepository;
import com.springboot.imdb.demo.entity.Actor;
import com.springboot.imdb.demo.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findAll() {
        return (List<Movie>) movieRepository.findAll();
    }

    @Override
    public Movie findById(String id) {
        Optional<Movie> result = movieRepository.findById(id);

        Movie movie= null;

        if (result.isPresent()) {
            movie = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + id);
        }

        return movie;
    }
    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void deleteById(String id) {movieRepository.deleteById(id);
    }
}
