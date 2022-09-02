package com.javaunit3.springmvc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private BestMovieService bestMovieService;

    @Autowired
    private SessionFactory sessionFactory;
    @RequestMapping("/")
    public String getIndexPage(){
        return "index";
    }


    @RequestMapping("/bestMovie")
    public String getBestMoviePage(Model model) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<MovieEntity> movieEntityList = session.createQuery(" from MovieEntity").list();
        movieEntityList.sort(Comparator.comparingInt(movieEntity -> movieEntity.getVotes().size()));

        MovieEntity movieEntityWithMostVotes = movieEntityList.get(movieEntityList.size()-1);
        List<String> voterNames = new ArrayList<>();
        for(VoteEntity vote : movieEntityWithMostVotes.getVotes() ) {
            System.out.println(vote.getVoterName());
            voterNames.add(vote.getVoterName());

        }
        String voterNamesList = String.join(", ",voterNames);
        System.out.println("Voter names :" + voterNamesList);
        model.addAttribute("bestMovie",movieEntityWithMostVotes.getTitle());
        model.addAttribute("bestMovieVoters" ,voterNamesList);

        session.getTransaction().commit();

        return "bestMovie";
    }
    @RequestMapping("/voteForBestMovieForm")
    public String voteForBestMovieFormPage(Model model) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<MovieEntity> movieEntityList = session.createQuery(" from MovieEntity").list();
        session.getTransaction().commit();
        model.addAttribute("movies" , movieEntityList);
        return "voteForBestMovie";
    }

    @RequestMapping("/voteForBestMovie")
    public String voteForBestMovie(HttpServletRequest request , Model model){

        String voterName = request.getParameter("voterName");

        String movieId = request.getParameter("movieId");

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        MovieEntity movieEntity = (MovieEntity) session.get(MovieEntity.class,Integer.parseInt(movieId));
        VoteEntity newVote = new VoteEntity();
        newVote.setVoterName(voterName);

        movieEntity.addVote(newVote);
        session.update(movieEntity);
        session.getTransaction().commit();
        return "voteForBestMovie";

    }

    @RequestMapping("/addMovieForm")
    public String addMovieForm () {
        return "addMovie";
    }

    @RequestMapping("/addMovie")
    public String addMovie(HttpServletRequest request , Model model){
        String movieTitle = request.getParameter("movieTitle");
        String maturityRating = request.getParameter("maturityRating");
        String genre = request.getParameter("genre");

        model.addAttribute("BestMovieVote",movieTitle);
        MovieEntity movieAdded = new MovieEntity();
        movieAdded.setTitle(movieTitle);
        movieAdded.setMaturityRating(maturityRating);
        movieAdded.setGenre(genre);
        Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            session.save(movieAdded);
            session.getTransaction().commit();
            return "addMovie";

    }

}

