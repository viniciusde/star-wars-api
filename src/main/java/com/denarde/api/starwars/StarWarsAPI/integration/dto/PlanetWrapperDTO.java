package com.denarde.api.starwars.StarWarsAPI.integration.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.List;

@With
@JsonDeserialize(builder = PlanetWrapperDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
@Value
public class PlanetWrapperDTO {

    private List<PlanetDTO> results;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder{

    }
}
