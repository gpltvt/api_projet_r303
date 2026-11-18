package com.basespringboot.service;

import com.basespringboot.dto.AdventureDto;
import com.basespringboot.entity.Adventure;

import java.util.List;

public interface AdventureService {
    Adventure save(AdventureDto adventureDto);

    Adventure findById(Long id);
    List<Adventure> findAll();
    Adventure update(Long id, AdventureDto adventureDto);
    void delete(Long id);
}