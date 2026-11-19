package com.basespringboot.service;

import com.basespringboot.dto.EntryDto;
import com.basespringboot.entity.Adventure;
import com.basespringboot.entity.CompendiumEntry;
import com.basespringboot.entity.Entry;
import com.basespringboot.repository.AdventureRepository;
import com.basespringboot.repository.CompendiumEntryRepository;
import com.basespringboot.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EntryServiceImpl implements EntryService {

    private final EntryRepository entryRepository;
    private final AdventureRepository adventureRepository;
    private final CompendiumEntryRepository compendiumEntryRepository;

    @Override
    public Entry save(Long adventureId, EntryDto entryDto) {
        Entry entry = new Entry();
        entry.setAdventure(findAdventure(adventureId));
        entry.setCompendiumEntry(findCompendiumEntry(entryDto.getCompendiumEntryId()));
        entry.setNote(entryDto.getNote());
        return entryRepository.save(entry);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entry> findAllByAdventure(Long adventureId) {
        findAdventure(adventureId);
        return entryRepository.findByAdventureId(adventureId);
    }

    @Override
    public Entry update(Long adventureId, Long entryId, EntryDto entryDto) {
        Entry entry = findEntry(adventureId, entryId);
        entry.setCompendiumEntry(findCompendiumEntry(entryDto.getCompendiumEntryId()));
        entry.setNote(entryDto.getNote());
        return entryRepository.save(entry);
    }

    @Override
    public void delete(Long adventureId, Long entryId) {
        entryRepository.delete(findEntry(adventureId, entryId));
    }

    private Adventure findAdventure(Long id) {
        return adventureRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aventure " + id + " introuvable"));
    }

    private CompendiumEntry findCompendiumEntry(Long id) {
        return compendiumEntryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Élément " + id + " du compendium introuvable"));
    }

    private Entry findEntry(Long adventureId, Long entryId) {
        return entryRepository.findByIdAndAdventureId(entryId, adventureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Page " + entryId + " introuvable dans l'aventure " + adventureId));
    }
}