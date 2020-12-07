package com.springboot.imdb.demo.service;

import com.springboot.imdb.demo.dao.ActorRepository;
import com.springboot.imdb.demo.entity.Actor;
import com.springboot.imdb.demo.entity.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
import junit.runner.Version;

//System.out.println("JUnit version is: " + Version.id());
@RunWith(MockitoJUnitRunner.class)
class ActorServiceTest {


    //@MockBean
    //@Mock
    private ActorRepository actorRepository;
   //@Autowired
   // @InjectMocks
    private ActorServiceImpl actorService;
    @BeforeEach
    public  void init(){
        actorRepository=mock(ActorRepository.class);
        actorService=new ActorServiceImpl(actorRepository);
        //MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        //System.out.println("JUnit version is: " + Version.id());
        //actorRepository=mock(ActorRepository.class);
        //actorService=new ActorServiceImpl(actorRepository);
        /*Actor actor1=new Actor("A123","ram","M",null);
       Actor actor2=new Actor("A234","leo","F",null);
        //System.out.println(actor1.getActorId());
       List<Actor> actors=Stream.of(
                actor1,actor2).collect(Collectors.toList());
        //System.out.println(actors.get(0).getActorId());*/
        Mockito.when(actorRepository.findAll()).thenReturn(Stream.of(new Actor("A123","ram","M",null),
                new Actor("A234","leo","F",null)
                )
                .collect(Collectors.toList()));
        assertEquals(2,actorService.findAll().size());

    }

    @Test
    void findById() {

        Actor actor=new Actor("A123", "ram", "M", null);
       when(actorRepository.findById("A123")).thenReturn(of(actor));
        assertEquals(of(actor),actorService.findById("A123"));


        try{
            actorService.findById("A234");
            fail("This should have thrown an exception");
        }
        catch(Exception exception){

            assertEquals("Actor id not found - A234",exception.getMessage());
        }

    }

    @Test
    void saveNewActor() {
        Actor actor=new Actor("A123", "ram", "M", null);
        //when(actorRepository.findById("A123")).thenThrow(new RuntimeException("Actor id not found A123" ));
        //when(actorRepository.findById("A123")).thenReturn();
        when(actorRepository.save(actor)).thenReturn(actor);
        assertEquals(actor,actorService.saveNewActor(actor));
        Actor actor1=new Actor("A1234", "leo", "M", null);
        when(actorRepository.findById("A1234")).thenReturn(of(actor1));

        try{
            actorService.saveNewActor(actor1);
            fail("This should have thrown an exception");
        }
        catch(Exception exception){

            Assert.assertEquals("Actor id already exists - A1234",exception.getMessage());
        }


    }

    @Test
    void updateActor() {
        Actor actor=new Actor("A123", "ram", "M", null);
        //when(actorRepository.findById("A123")).thenReturn(Optional.of(actor));
        when(actorRepository.save(actor)).thenReturn(actor);

        assertEquals(actor,actorService.updateActor(actor));
    }

    @Test
    void deleteById() {
        Actor actor=new Actor("A123", "ram", "M", null);
        when(actorRepository.findById("A123")).thenReturn(of(actor));
        actorService.deleteById("A123");
        verify(actorRepository,times(1)).deleteById("A123");


        try {
            actorService.deleteById("A1234");
            fail("This should have thrown an exception");
        }
        catch(Exception exception ){

            Assert.assertEquals("Actor id not found - A1234",exception.getMessage());
        }

        verify(actorRepository,times(0)).deleteById("A1234");



    }
}