package com.denarde.api.starwars.StarWarsAPI.resource.v1;

import com.denarde.api.starwars.StarWarsAPI.model.Planet;
import com.denarde.api.starwars.StarWarsAPI.resource.v1.dto.SwPlanetDTO;
import com.denarde.api.starwars.StarWarsAPI.service.PlanetService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/planets")
public class PlanetResource {

    private final PlanetService service;

    @GetMapping
    public Page getPlanets(
            @RequestParam Optional<String> id,
            @RequestParam Optional<String> name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") @Min(10) @Max(50) Integer size) {
        return service.query(id, name, PageRequest.of(page, size));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Planet addNewPlanet(@Valid @RequestBody SwPlanetDTO planetDTO) {
        return service.save(planetDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void removePlanet(@PathVariable String id) {
        service.remove(id);
    }

}
