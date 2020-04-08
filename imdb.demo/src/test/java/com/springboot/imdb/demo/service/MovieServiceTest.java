package com.springboot.imdb.demo.service;

import com.springboot.imdb.demo.dao.MovieRepository;
import com.springboot.imdb.demo.entity.Actor;
import com.springboot.imdb.demo.entity.Movie;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest

class MovieServiceTest {
    @Autowired
    private MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;

    @Test
    void findAll() {
        when(movieRepository.findAll()).thenReturn(Stream.of(
                new Movie("M123","movie1","director1",7,"1h30min","its about suspicious murder case","horror",null),
                new Movie("M1234","movie2","director2",6,"1h30min","its about suspicious murder case","horror",null))
                .collect(Collectors.toList()));
        assertEquals(2,movieService.findAll().size());
    }

    @Test
    void findById() {
       Movie movie=new Movie("M123","movie1","director1",7,"1h30min","its about suspicious murder case","horror",null);
        when(movieRepository.findById("M123")).thenReturn(of(movie));
        assertEquals(of(movie),movieService.findById("M123"));


        try{
            movieService.findById("M234");
            fail("This should have thrown an exception");
        }
        catch(Exception exception){

            assertEquals("Movie id not found - M234",exception.getMessage());
        }
    }

    @Test
    void saveNewMovie() {
        Movie movie=new Movie("M123","movie1","director1",7,"1h30min","its about suspicious murder case","horror",null);
        when(movieRepository.save(movie)).thenReturn(movie);
        assertEquals(movie,movieService.saveNewMovie(movie));
       Movie movie1= new Movie("M1234","movie2","director2",6,"1h30min","its about suspicious murder case","horror",null);
        when(movieRepository.findById("M1234")).thenReturn(of(movie1));

        try{
            movieService.saveNewMovie(movie1);
            fail("This should have thrown an exception");
        }
        catch(Exception exception){

            Assert.assertEquals("Movie id already exists - M1234",exception.getMessage());
        }
    }

    @Test
    void updateMovie() {
        Movie movie=new Movie("M123","movie1","director1",7,"1h30min","its about suspicious murder case","horror",null);

        when(movieRepository.save(movie)).thenReturn(movie);

        assertEquals(movie,movieService.updateMovie(movie));
    }

    @Test
    void deleteById() {
        Movie movie=new Movie("M123","movie1","director1",7,"1h30min","its about suspicious murder case","horror",null);
        when(movieRepository.findById("M123")).thenReturn(of(movie));
        movieService.deleteById("M123");
        verify(movieRepository,times(1)).deleteById("M123");


        try {
            movieService.deleteById("M1234");
            fail("This should have thrown an exception");
        }
        catch(Exception exception ){

            Assert.assertEquals("Movie id not found - M1234",exception.getMessage());
        }

        verify(movieRepository,times(0)).deleteById("M1234");


    }
}