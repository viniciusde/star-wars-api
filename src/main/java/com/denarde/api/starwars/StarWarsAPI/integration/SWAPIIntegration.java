package com.denarde.api.starwars.StarWarsAPI.integration;

import com.denarde.api.starwars.StarWarsAPI.integration.dto.PlanetWrapperDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "swapi", url = "https://swapi.dev/api")
public interface SWAPIIntegration {

    @GetMapping(value="/planets")
    PlanetWrapperDTO findPlanetByName(@RequestParam(value="search") String search);
}
