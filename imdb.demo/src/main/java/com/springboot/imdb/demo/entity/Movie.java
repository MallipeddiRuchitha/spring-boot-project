package com.springboot.imdb.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movie {


    @Id
    @Column(name="movie_id")
    private String movieId;

    @Column(name="movie_name")
    private String movieName;

    @Column(name="director")
    private String director;

    @Column(name="rating")
    private int rating;

    @Column(name="duration")
    private String duration;

    @Column(name="movie_brief")
    private String movie_brief;

    @Column(name="category")
    private String category;
    // define constructors
    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<MovieActor> movieActors;











}
