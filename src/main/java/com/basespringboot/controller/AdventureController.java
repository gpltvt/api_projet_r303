package com.basespringboot.controller;

import java.util.List;
import com.basespringboot.dto.AdventureDto;
import com.basespringboot.entity.Adventure;
import com.basespringboot.service.AdventureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/adventures")
@RestController 
@RequiredArgsConstructor

public class AdventureController {
    
    private final AdventureService adventureService;

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public Adventure save(@Valid @RequestBody AdventureDto avdentureDto){
        return adventureService.save(avdentureDto);
    }

    @GetMapping("/{id}")
    public Adventure findById(@PathVariable Long id){
        return adventureService.findById(id);
    }

    @GetMapping 
    public List<Adventure> findAll(){
        return adventureService.findAll();
    }

    @PutMapping("/{id}")
    public Adventure update(@PathVariable Long id, @Valid @RequestBody AdventureDto adventureDto){
        return adventureService.update(id, adventureDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable  Long id){
        adventureService.delete(id);
    }
}
