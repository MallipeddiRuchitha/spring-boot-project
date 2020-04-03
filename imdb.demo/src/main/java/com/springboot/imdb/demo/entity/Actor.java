package com.springboot.imdb.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="actor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Actor {
    @Id
    @Column(name="actor_id")
    private String actorId;

    @Column(name="name")
    private String name;

    @Column(name="gender")
    private String gender;

    @OneToMany(mappedBy = "actor")
    private List<MovieActor> movieActors;
    @JsonIgnore // to break endless loop in bi-directional association
    public List<MovieActor> getMovieActors() {
        return movieActors;
    }


}
