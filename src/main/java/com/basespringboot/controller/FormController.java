package com.basespringboot.controller;

import java.util.List;
import com.basespringboot.dto.FormDto;
import com.basespringboot.entity.Form;
import com.basespringboot.service.FormService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/forms")
@RestController 
@RequiredArgsConstructor

public class FormController {
    
    private final FormService formService;

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public Form save(@Valid @RequestBody FormDto form){
        return formService.save(form);
    }

    @GetMapping("/{id}")
    public Form findById(@PathVariable Long id){
        return formService.findById(id);
    }

    @GetMapping 
    public List<Form> findAll(){
        return formService.findAll();
    }

    @PutMapping("/{id}")
    public Form update(@PathVariable Long id, @Valid @RequestBody FormDto form){
        return formService.update(id, form);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable  Long id){
        formService.delete(id);
    }
}
