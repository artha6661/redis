package com.coderkan.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderkan.models.Film;
import com.google.common.base.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, String> {
    Film findByImdbID(String imdbId);
}
