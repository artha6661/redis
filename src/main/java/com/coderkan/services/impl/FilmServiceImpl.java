package com.coderkan.services.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coderkan.Dto.FilmDto;
import com.coderkan.models.Film;
import com.coderkan.repositories.FilmRepository;
import com.coderkan.services.FilmService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@CacheConfig(cacheNames = "apiEksternal")
@Slf4j
public class FilmServiceImpl implements FilmService{

	private final String apiKey = "7e8ee9c1";
    private final String apiUrl = "http://www.omdbapi.com";
    
    private final RestTemplate restTemplate;

    @Autowired
    private FilmRepository filmRepository;


    @Autowired
    public FilmServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable(cacheNames = "film", key = "'film_info_' + #imdbId", sync = true)
    public Film getMovieDetails(String imdbId) {

        return this.filmRepository.findByImdbID(imdbId);
    }

    @Override
    @CachePut(value = "film", key = "'film_info_' + #imdbId")
    public Film insertMovie(String imdbId) {

        String omdbApiUrl = apiUrl+"?i="+ imdbId + "&apikey=" + apiKey;
        FilmDto responseData = restTemplate.getForObject(omdbApiUrl, FilmDto.class);
        Film film = new Film(responseData);
        Film findBy = this.filmRepository.findByImdbID(imdbId);
        log.info("data :" + findBy);

        if(Objects.isNull(findBy)){
            filmRepository.save(film);
        }
        else{
                return null;
            }
        return film;
        }
    
}
