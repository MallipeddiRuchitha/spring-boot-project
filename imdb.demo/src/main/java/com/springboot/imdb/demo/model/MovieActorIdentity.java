package com.springboot.imdb.demo.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class MovieActorIdentity implements Serializable {
    @Column(name="movie_id")
    private String movieId;

    @Column(name="actor_id")
    private String actorId;
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        MovieActorIdentity movieActorIdentity = (MovieActorIdentity) object;

        if (!movieId.equals( movieActorIdentity.movieId)) return false;
        return actorId.equals( movieActorIdentity.actorId);
    }

    @Override
    public int hashCode() {
        int result = movieId.hashCode();
        result = 31 * result + actorId.hashCode();
        return result;
    }
}