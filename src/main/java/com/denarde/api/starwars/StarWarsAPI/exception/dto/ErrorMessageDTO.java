package com.denarde.api.starwars.StarWarsAPI.exception.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

@Value
@With
@JsonDeserialize(builder = ErrorMessageDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class ErrorMessageDTO {

    @Builder.Default
    LocalDateTime timestamp = now();
    String error;
    List<String> message;
    String path;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
