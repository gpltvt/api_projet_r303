package com.basespringboot.service;

import com.basespringboot.dto.EntryDto;
import com.basespringboot.entity.Entry;

import java.util.List;

public interface EntryService {
    Entry save(Long adventureId, EntryDto entryDto);
    List<Entry> findAllByAdventure(Long adventureId);
    Entry update(Long adventureId, Long entryId, EntryDto entryDto);
    void delete(Long adventureId, Long entryId);
}
