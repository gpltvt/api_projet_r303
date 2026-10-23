package com.basespringboot.service;

import com.basespringboot.dto.FormDto;
import com.basespringboot.entity.Form;

import java.util.List;

public interface FormService {
    Form save(FormDto form);

    Form findById(Long id);
    List<Form> findAll();
    Form update(Long id, FormDto form);
    void delete(Long id);
}