package com.denarde.api.starwars.StarWarsAPI.service;

import com.denarde.api.starwars.StarWarsAPI.exception.BusinessException;
import com.denarde.api.starwars.StarWarsAPI.integration.SWAPIIntegration;
import com.denarde.api.starwars.StarWarsAPI.integration.dto.PlanetDTO;
import com.denarde.api.starwars.StarWarsAPI.model.Planet;
import com.denarde.api.starwars.StarWarsAPI.repository.PlanetRepository;
import com.denarde.api.starwars.StarWarsAPI.resource.v1.dto.SwPlanetDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class PlanetService {

    private final PlanetRepository repository;
    private final SWAPIIntegration swapiIntegration;

    public Page<Planet> query(Optional<String> idFilter, Optional<String> nameFilter, Pageable pageable){
        Example example = Example.of(Planet.builder()
                .id(idFilter.orElse(null))
                .name(nameFilter.orElse(null))
                .build());
        return repository.findAll(example, pageable);
    }

    public Planet save(SwPlanetDTO planetDTO) {
        if(checkIfAlreadyExistWithName(planetDTO.getName())) {
            throw new BusinessException(BAD_REQUEST, "Planet already exists ");
        }
        return repository.save(Planet.builder()
                .name(planetDTO.getName())
                .climate(planetDTO.getClimate())
                .terrain(planetDTO.getTerrain())
                .filmsQty(getTotalFilmsByPlanet(planetDTO.getName()))
                .build());
    }

    public void remove(String id){
        Planet planet = repository.findById(id).orElseThrow(()-> new BusinessException(NOT_FOUND, "Planet not found"));
        repository.delete(planet);
    }

    private boolean checkIfAlreadyExistWithName(String name) {
        return repository.findByName(name).isPresent();
    }

    private Integer getTotalFilmsByPlanet(String name)  {
        Optional<PlanetDTO> planetDTO = getPlanetByName(name);
        if(planetDTO.isPresent()) {
            List<String> films = Optional.ofNullable(planetDTO.get().getFilms()).orElse(Collections.emptyList());
            return films.size();
        }
        return 0;
    }

    private Optional<PlanetDTO> getPlanetByName(String name)  {
        List<PlanetDTO> planetDTOList = Optional.ofNullable(swapiIntegration.findPlanetByName(name)).get().getResults();
        return planetDTOList.stream().filter(p-> name.equals(p.getName())).findFirst();
    }
}
