package com.basespringboot.service;

import com.basespringboot.dto.FormDto;
import com.basespringboot.entity.Form;
import com.basespringboot.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}