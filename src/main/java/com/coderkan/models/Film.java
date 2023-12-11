package com.coderkan.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.coderkan.Dto.FilmDto;
import lombok.Data;

@Data
@Entity
@Table(name = "Film", schema = "public")
public class Film {
    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private long id;

    private String title;
    private String year;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    private String type;
    private String dvd;
    private String boxOffice;
    private String production;
    private String website;
    private String response;

    public Film(){}

    public Film(FilmDto filmDto){
        this.title = filmDto.getTitle();
        this.year = filmDto.getYear();
        this.rated = filmDto.getRated();
        this.released = filmDto.getReleased();
        this.runtime = filmDto.getRuntime();
        this.genre = filmDto.getGenre();
        this.director = filmDto.getDirector();
        this.writer = filmDto.getWriter();
        this.actors = filmDto.getActors();
        this.plot = filmDto.getPlot();
        this.language = filmDto.getLanguage();
        this.country = filmDto.getCountry();
        this.awards = filmDto.getAwards();
        this.poster = filmDto.getPoster();
        this.metascore = filmDto.getMetascore();
        this.imdbRating = filmDto.getImdbRating();
        this.imdbVotes = filmDto.getImdbVotes();
        this.imdbID = filmDto.getImdbID();
        this.type = filmDto.getType();
        this.dvd = filmDto.getDvd();
        this.boxOffice = filmDto.getBoxOffice();
        this.production = filmDto.getProduction();
        this.website = filmDto.getWebsite();
        this.response = filmDto.getResponse();
    }
}
