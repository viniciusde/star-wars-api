package com.denarde.api.starwars.StarWarsAPI.resource.v1.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@With
@JsonDeserialize(builder = SwPlanetDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
@Value
public class SwPlanetDTO {

    @NotEmpty @Length(min = 5, max = 20)
    private String name;
    @Length(max = 20)
    private String climate;
    @Length(max = 20)
    private String terrain;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder{

    }
}
