package com.basespringboot.controller;

import com.basespringboot.dto.EntryDto;
import com.basespringboot.entity.Entry;
import com.basespringboot.service.EntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/adventures/{adventureId}/entries")
@RestController
@RequiredArgsConstructor
public class EntryController {

    private final EntryService entryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry save(@PathVariable Long adventureId, @Valid @RequestBody EntryDto entryDto) {
        return entryService.save(adventureId, entryDto);
    }

    @GetMapping
    public List<Entry> findAll(@PathVariable Long adventureId) {
        return entryService.findAllByAdventure(adventureId);
    }

    @PutMapping("/{entryId}")
    public Entry update(@PathVariable Long adventureId, @PathVariable Long entryId,
                        @Valid @RequestBody EntryDto entryDto) {
        return entryService.update(adventureId, entryId, entryDto);
    }

    @DeleteMapping("/{entryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long adventureId, @PathVariable Long entryId) {
        entryService.delete(adventureId, entryId);
    }
}