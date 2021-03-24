package com.denarde.api.starwars.StarWarsAPI.model;

import lombok.Builder;
import lombok.Data;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@With
@Document
@Builder
@Data
public class Planet {

    @Id
    private String id;
    private String name;
    private String climate;
    private String terrain;
    private Integer filmsQty;

}
