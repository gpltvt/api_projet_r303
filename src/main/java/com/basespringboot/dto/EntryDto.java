package com.basespringboot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntryDto {

    private String note;

    @NotNull
    private Long compendiumEntryId;
}