package com.denarde.api.starwars.StarWarsAPI.repository;

import com.denarde.api.starwars.StarWarsAPI.model.Planet;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PlanetRepository extends PagingAndSortingRepository<Planet, String> {

    Page<Planet> findAll(Example example, Pageable pageable);

    Optional<Planet> findByName(String name);
}
