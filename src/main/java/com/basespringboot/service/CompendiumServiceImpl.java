package com.basespringboot.service;

import com.basespringboot.dto.CompendiumApiEntry;
import com.basespringboot.dto.CompendiumApiResponse;
import com.basespringboot.entity.CompendiumEntry;
import com.basespringboot.repository.CompendiumEntryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service 
@Transactional 
@RequiredArgsConstructor 
public class CompendiumServiceImpl implements CompendiumService {

    private static final String ALL_URL = "https://api.hyrule-compendium.com/v3/compendium/all";
    private final CompendiumEntryRepository compendiumEntryRepository;
    private final RestTemplate restTemplate;

    @Override 
    public int importAll() {
        CompendiumApiResponse response;
        try {
            response = restTemplate.getForObject(ALL_URL, CompendiumApiResponse.class);
        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "API Hyrule Compendium indisponible");
        }
        if (response == null || response.data() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Réponse invalide de l'API Hyrule Compendium");
        }

        Set<Integer> existingApiIds = compendiumEntryRepository.findAll().stream().map(CompendiumEntry::getApiId).collect(Collectors.toSet());
        
        List<CompendiumEntry> toSave = response.data().stream()
                .filter(apiEntry -> !existingApiIds.contains(apiEntry.id()))
                .map(this::toEntity)
                .toList();

        compendiumEntryRepository.saveAll(toSave);
        return toSave.size();
    }

    @Override 
    @Transactional(readOnly = true)
    public List<CompendiumEntry> findAll() {
        return compendiumEntryRepository.findAll();
    }

    @Override 
    @Transactional(readOnly = true)
    public CompendiumEntry findById(Long id) {
        return compendiumEntryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Element " + id + "introuvable"));
    }
    
    private CompendiumEntry toEntity(CompendiumApiEntry apiEntry) {
        CompendiumEntry entry = new CompendiumEntry();
        entry.setApiId(apiEntry.id());
        entry.setName(apiEntry.name());
        entry.setDescription(apiEntry.description());
        entry.setCategory(apiEntry.category());
        entry.setImage(apiEntry.image());
        entry.setDlc(apiEntry.dlc());
        if (apiEntry.commonLocations() != null) {
            entry.setCommonLocations(String.join(", ", apiEntry.commonLocations()));
        }
        return entry;
    }
}
