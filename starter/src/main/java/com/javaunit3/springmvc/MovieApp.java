package com.javaunit3.springmvc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class MovieApp {
    public static void main(String[] args) {
       // get the application context
        AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext(MovieApp.class);
        //  retrieve the bean from spring container
        BestMovieService bestMovieService =applicationContext .getBean("bestMovieService", BestMovieService.class);
        // call method getBestMovie() on bean
        Movie bestMovie = bestMovieService.getBestMovie();

        System.out.println("Title: " + bestMovie.getTitle());
        System.out.println("Maturity Rating: " + bestMovie.getMaturityRating());
        System.out.println("Genre: " + bestMovie.getGenre());
    }
}
