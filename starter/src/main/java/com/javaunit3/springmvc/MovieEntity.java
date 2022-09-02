package com.javaunit3.springmvc;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name  = "movies")  // table movies mapped with class MovieEntity
public class MovieEntity {
    @Id   //primary key of  table
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // Auto increment and primary key
    @Column(name = "movie_id")
    private Integer id;

    @Column (name = "title")
    private String title;
    @Column (name = "maturity_rating")
    private String maturityRating;
    @Column (name = "genre")
    private String Genre;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private List<VoteEntity> votes;

    public List<VoteEntity> getVotes() {
        return votes;
    }

    public void setVotes(List<VoteEntity> votes) {
        this.votes = votes;
    }
    public void addVote(VoteEntity vote){
        this.votes.add(vote);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }
}