package com.basespringboot.service;

import com.basespringboot.entity.CompendiumEntry;

import java.util.List;

public interface CompendiumService {
    int importAll();
    List<CompendiumEntry> findAll();
    CompendiumEntry findById(Long id);
}
