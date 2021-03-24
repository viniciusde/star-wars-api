package com.denarde.api.starwars.StarWarsAPI.service;


import com.denarde.api.starwars.StarWarsAPI.exception.BusinessException;
import com.denarde.api.starwars.StarWarsAPI.integration.SWAPIIntegration;
import com.denarde.api.starwars.StarWarsAPI.integration.dto.PlanetDTO;
import com.denarde.api.starwars.StarWarsAPI.integration.dto.PlanetWrapperDTO;
import com.denarde.api.starwars.StarWarsAPI.model.Planet;
import com.denarde.api.starwars.StarWarsAPI.repository.PlanetRepository;
import com.denarde.api.starwars.StarWarsAPI.resource.v1.dto.SwPlanetDTO;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlanetServiceTest {

    @InjectMocks
    private PlanetService planetService;

    @Mock
    private PlanetRepository planetRepository;

    @Mock
    private SWAPIIntegration swapiIntegration;

    private Faker faker = new Faker();

    @Test
    public void mustdRemoveAPlanet() {
        when(planetRepository.findById(any())).thenReturn(Optional.of(Planet.builder().build()));
        planetService.remove(faker.idNumber().valid());
    }

    @Test(expected = BusinessException.class)
    public void mustNotRemoveAPlanet__planetNotFound() {
        when(planetRepository.findById(any())).thenReturn(Optional.empty());
        planetService.remove(faker.idNumber().valid());
    }

    @Test
    public void mustAddAPlanet() {
        when(planetRepository.findByName(any())).thenReturn(Optional.empty());
        when(swapiIntegration.findPlanetByName(any())).thenReturn(mockPlanetsAPI());
        Planet planet = planetService.save(SwPlanetDTO.builder()
                .name("terra")
                .climate(faker.weather().description())
                .terrain("")
                .build());
    }

    @Test
    public void mustAddAPlanet__noFilms() {
        when(planetRepository.findByName(any())).thenReturn(Optional.empty());
        when(swapiIntegration.findPlanetByName(any())).thenReturn(PlanetWrapperDTO.builder().results(Collections.emptyList()).build());
        Planet planet = planetService.save(SwPlanetDTO.builder()
                .name("terra")
                .climate(faker.weather().description())
                .terrain("")
                .build());
    }

    @Test(expected = BusinessException.class)
    public void mustNotAddAPlanet__planetAlreadyExist() {
        when(planetRepository.findByName(any())).thenReturn(Optional.of(Planet.builder().build()));
        planetService.save(SwPlanetDTO.builder().build());
    }

    @Test
    public void mustReturnByName(){
        String filerName = "Terra";
        Example example = Example.of(Planet.builder().name(filerName).build());
        Planet planet = Planet.builder()
                .name(filerName)
                .build();
        Page<Planet> pagedResponse = new PageImpl<>(Arrays.asList(planet, planet));
        when(planetRepository.findAll(any(), any())).thenReturn(pagedResponse);
        Page page = planetService.query(Optional.empty(), Optional.of(filerName), PageRequest.of(1, 10));
        Assert.assertTrue(page.getTotalElements() == 2);
    }

    private PlanetWrapperDTO mockPlanetsAPI(){
        return  PlanetWrapperDTO.builder()
                .results(Arrays.asList(PlanetDTO.builder()
                        .name("terra")
                        .films(Arrays.asList("movie 1", "movie 2"))
                        .build()))
                .build();
    }

}
