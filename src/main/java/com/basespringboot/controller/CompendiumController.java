package com.basespringboot.controller;

import com.basespringboot.entity.CompendiumEntry;
import com.basespringboot.service.CompendiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/compendium")
@RestController 
@RequiredArgsConstructor 
public class CompendiumController {
    
    private final CompendiumService compendiumService;

    @PostMapping("import")
    public Map<String, Integer> importAll() {
        return Map.of("imported", compendiumService.importAll());
    }

    @GetMapping 
    public List<CompendiumEntry> findAll() {
        return compendiumService.findAll();
    }

    @GetMapping("/{id}")
    public CompendiumEntry findById(@PathVariable Long id) {
        return compendiumService.findById(id);
    }
}
