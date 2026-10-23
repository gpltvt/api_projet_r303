package com.basespringboot.controller;

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
}
