package com.springboot.imdb.demo.model;


import lombok.*;

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

    private String movieId;


    private String actorId;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieActorIdentity that = (MovieActorIdentity) o;

        if (!movieId.equals(that.movieId)) return false;
        return actorId.equals(that.actorId);
    }

    @Override
    public int hashCode() {
        int result = movieId.hashCode();
        result = 31 * result + actorId.hashCode();
        return result;
    }
}