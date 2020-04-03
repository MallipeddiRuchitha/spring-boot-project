package com.springboot.imdb.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.imdb.demo.model.MovieActorIdentity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="movie_actor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(MovieActorIdentity.class)
public class MovieActor {

    @Id
    @Column(name="movie_id")
    private String movieId;
    @Id
    @Column(name="actor_id")
    private String actorId;


    @Column(name="actor_role")
    private String actorRole;

    @ManyToOne
    @JoinColumn(name = "move_id",referencedColumnName = "movie_id",insertable=false, updatable=false)

    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "actor_id", referencedColumnName = "actor_id",insertable=false, updatable=false)

    private Actor actor;


}
