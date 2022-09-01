package com.javaunit3.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component

public class BestMovieService {
//    @Autowired
    private  Movie movie ;

    // dependency injection using Setter method
//    @Autowired
//    public void setMovie(Movie movie){
//        this.movie = movie;
//    }
// dependency injection using Constructor
//    @Autowired
//    public BestMovieService(Movie movie) {
//        this.movie = movie;
//    }

    public Movie getBestMovie(){
        return movie;
    }
//  dependency injection  use the Qualifier annotation
    @Autowired
    public BestMovieService(@Qualifier("titanicMovie") Movie movie) {
        this.movie = movie;
    }

}

