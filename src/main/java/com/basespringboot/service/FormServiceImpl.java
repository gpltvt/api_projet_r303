package com.basespringboot.service;

import com.basespringboot.dto.FormDto;
import com.basespringboot.entity.Form;
import com.basespringboot.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service 
@Transactional 
@RequiredArgsConstructor 
public class FormServiceImpl implements FormService {
    private final FormRepository formRepository;

    @Override 
    public Form save(FormDto formDto){
        Form form = new Form();
        form.setTitle((formDto.getTitle()));
        return formRepository.save(form);
    }

    @Override 
    public Form findById(Long id){
        return formRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Form " + id + " introuvable"));
    }

    @Override 
    public List<Form> findAll() {
        return formRepository.findAll();
    }

    @Override 
    public Form update(Long id, FormDto formDto){
        Form form = findById(id);
        form.setTitle(formDto.getTitle());
        return formRepository.save(form);
    }

    @Override 
    public void delete(Long id){
        formRepository.delete(findById(id));
    }
}