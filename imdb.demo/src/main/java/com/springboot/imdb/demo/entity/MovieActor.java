package com.springboot.imdb.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private String movieId;
    @Id

    private String actorId;


    @Column(name="actor_role")
    private String actorRole;

   @ManyToOne
  //@MapsId("movie_id")
  // @JsonBackReference
   @JsonIgnore
    @JoinColumn(name = "movie_id",referencedColumnName = "movie_id",insertable=false ,updatable=false)

     private Movie movie;
  /*  @JsonIgnore
    public Movie getMovie(){
        return movie;
    }*/

    @ManyToOne
    //@MapsId("actor_id")
    //@JsonBackReference

   @JsonIgnore
    @JoinColumn(name = "actor_id",referencedColumnName = "actor_id",insertable=false ,updatable=false)
    private  Actor actor;
    /*@JsonIgnore
    public Actor getActor(){
        return actor;
    }
*/
}
