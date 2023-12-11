package com.coderkan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderkan.Dto.FilmDto;
import com.coderkan.models.Film;
import com.coderkan.services.FilmService;

@RestController
public class FilmController {

    @Autowired
    private FilmService filmService;

    // @GetMapping("/movie/{id}")
    // public Film getMovieInfo(@PathVariable String idFilm) {
    //     return filmService.getDataInfo(idFilm);
    // }

    @GetMapping("/movie/{imdbId}")
    public ResponseEntity<Object> getMovieDetails(@PathVariable("imdbId") String imdbId) {
        Film film = filmService.getMovieDetails(imdbId);
        return ResponseEntity.ok().body(film);
    }

    @GetMapping("/addmovie/{imdbId}")
    public ResponseEntity<Object> insertMovie(@PathVariable("imdbId") String imdbId) {
        Film film = filmService.insertMovie(imdbId);
        return ResponseEntity.ok().body(film);
    }
    
}
