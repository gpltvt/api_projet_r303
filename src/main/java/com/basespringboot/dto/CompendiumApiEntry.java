package com.basespringboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CompendiumApiEntry (
        Integer id,
        String name,
        String category,
        String description,
        String image,
        Boolean dlc,
        @JsonProperty("common_locations") List<String> commonLocations
) {
}
