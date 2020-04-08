package com.springboot.imdb.demo.service;

import com.springboot.imdb.demo.dao.ActorRepository;
import com.springboot.imdb.demo.dao.MovieRepository;
import com.springboot.imdb.demo.entity.Actor;
import com.springboot.imdb.demo.entity.Movie;
import com.springboot.imdb.demo.entity.MovieActor;
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
    public Optional<Movie> findById(String movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (!movie.isPresent()) {
            throw new RuntimeException("Movie id not found - " + movieId);
        }


       /* Movie movie= null;

        if (result.isPresent()) {
            movie = result.get();
        } else {

            throw new RuntimeException("Did not find movie id - " + id);
        }
*/
        return movie;
    }
   // @Override
   /* public void save(Movie movie) {
        movieRepository.save(movie);
    }
*/
   @Override
   public Movie saveNewMovie(Movie movie){
       String movieId=movie.getMovieId();
       Optional<Movie> tempMovie = movieRepository.findById(movieId);
       if (tempMovie.isPresent()) {
           throw new RuntimeException("Movie id already exists - " + movieId);
       }



          return( movieRepository.save(movie));

   }
    @Override
    public Movie updateMovie(Movie movie){

        return(movieRepository.save(movie));

    }
    @Override
    public void deleteById(String movieId) {

        Optional<Movie> movie = movieRepository.findById(movieId);
        if (!movie.isPresent()) {
            throw new RuntimeException("Movie id not found - " + movieId);
        }

        movieRepository.deleteById(movieId);
    }
}
