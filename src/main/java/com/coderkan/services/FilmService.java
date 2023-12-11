package com.coderkan.services;


import org.springframework.http.ResponseEntity;

import com.coderkan.Dto.FilmDto;
import com.coderkan.models.Film;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface FilmService {

    public Film getMovieDetails(String imdbId);

    public Film insertMovie(String imdbId);
    
}
