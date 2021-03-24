package com.denarde.api.starwars.StarWarsAPI.integration.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.List;

@With
@JsonDeserialize(builder = PlanetDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
@Value
public class PlanetDTO {

    private String name;
    private List<String> films;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder{

    }
}
