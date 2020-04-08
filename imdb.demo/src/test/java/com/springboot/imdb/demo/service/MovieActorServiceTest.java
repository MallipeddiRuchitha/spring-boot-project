package com.springboot.imdb.demo.service;

import com.springboot.imdb.demo.dao.ActorRepository;
import com.springboot.imdb.demo.dao.MovieActorRepository;
import com.springboot.imdb.demo.entity.Actor;
import com.springboot.imdb.demo.entity.Movie;
import com.springboot.imdb.demo.entity.MovieActor;
import com.springboot.imdb.demo.model.MovieActorIdentity;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class MovieActorServiceTest {

    //@Autowired
    private MovieActorService movieActorService;

    //@MockBean
    private MovieActorRepository movieActorRepository;
    @BeforeEach
    public  void init(){
        movieActorRepository=mock(MovieActorRepository.class);
        movieActorService=new MovieActorServiceImpl(movieActorRepository);
        //MockitoAnnotations.initMocks(this);
    }


    @Test
    void findAll() {
        when(movieActorRepository.findAll()).thenReturn(Stream.of(
                new MovieActor("M123","A123","hero",null,null),new MovieActor("M234","A123","heroine",null,null)).collect(Collectors.toList()));
        assertEquals(2,movieActorService.findAll().size());

    }

    @Test
    void findById() {
           MovieActorIdentity movieActorIdentity=new MovieActorIdentity("M123","A123");
        MovieActor movieActor=new MovieActor("M123", "A123", "hero", null,null);
        when(movieActorRepository.findById(movieActorIdentity)).thenReturn(of(movieActor));
        assertEquals(of(movieActor),movieActorService.findById("M123","A123"));


        try{
            movieActorService.findById("M234","A654");
            fail("This should have thrown an exception");
        }
        catch(Exception exception){

            assertEquals("Did not find movie actor with movie id M234  and actor Id A654",exception.getMessage());
        }

    }

    @Test
    void saveNewMovieActor() {

        MovieActor movieActor=new MovieActor("M123", "A123", "hero", null,null);
        //Actor actor=new Actor("A123", "ram", "M", null);
        //when(actorRepository.findById("A123")).thenThrow(new RuntimeException("Actor id not found A123" ));
        //when(actorRepository.findById("A123")).thenReturn();
        when(movieActorRepository.save(movieActor)).thenReturn(movieActor);
        assertEquals(movieActor,movieActorService.saveNewMovieActor(movieActor));
        MovieActor movieActor1=new MovieActor("M1234", "A123", "hero", null,null);
        MovieActorIdentity movieActorIdentity=new MovieActorIdentity("M1234","A123");
        when(movieActorRepository.findById(movieActorIdentity)).thenReturn(of(movieActor1));

        try{
            movieActorService.saveNewMovieActor(movieActor1);
            fail("This should have thrown an exception");
        }
        catch(Exception exception){

            Assert.assertEquals("movie actor  already  exists with movie id M1234  and actor Id A123",exception.getMessage());
        }


    }

    @Test
    void updateMovieActor() {
        MovieActor movieActor=new MovieActor("M123", "A123", "hero", null,null);
        when(movieActorRepository.save(movieActor)).thenReturn(movieActor);

        assertEquals(movieActor,movieActorService.updateMovieActor(movieActor));
    }


   @Test
    void deleteById() {
       MovieActor movieActor=new MovieActor("M1234", "A123", "hero", null,null);
       MovieActorIdentity movieActorIdentity=new MovieActorIdentity("M1234","A123");
        when(movieActorRepository.findById(movieActorIdentity)).thenReturn(of(movieActor));
        movieActorService.deleteById("M1234","A123");
        verify(movieActorRepository,times(1)).deleteById(movieActorIdentity);
       MovieActorIdentity movieActorIdentity1=new MovieActorIdentity("M123","A123");
        try {
            movieActorService.deleteById("M123","A123");
            fail("This should have thrown an exception");
        }
        catch(Exception exception ){

            Assert.assertEquals("Did not find movie actor with movie id M123  and actor Id A123",exception.getMessage());
        }

        verify(movieActorRepository,times(0)).deleteById(movieActorIdentity1);



    }
    @Test
    void findActorsByMovieId() {
        when(movieActorRepository.findActorByMovieId("M123")).thenReturn(Stream.of(
                new Actor("A123","ram","M",null),new Actor("A234","leo","F",null)).collect(Collectors.toList()));
        assertEquals(2,movieActorService.findActorsByMovieId("M123").size());
        when(movieActorRepository.findActorByMovieId("M1234")).thenReturn(new ArrayList<Actor>());
        assertEquals(0,movieActorService.findActorsByMovieId("M1234").size());


    }

    @Test
    void findMoviesByActorId() {
        when(movieActorRepository.findMoviesByActorId("A123")).thenReturn(Stream.of(
                new Movie("M123","movie1","director1",7,"1h30min","its about suspicious murder case","horror",null),
                new Movie("M1234","movie2","director2",6,"1h30min","its about suspicious murder case","horror",null))
                .collect(Collectors.toList()));
        assertEquals(2,movieActorService.findMoviesByActorId("A123").size());
        when(movieActorRepository.findMoviesByActorId("A1234")).thenReturn(new ArrayList<Movie>());
        assertEquals(0,movieActorService.findMoviesByActorId("A1234").size());


    }
}