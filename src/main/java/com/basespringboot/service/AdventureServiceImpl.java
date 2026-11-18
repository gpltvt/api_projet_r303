package com.basespringboot.service;

import com.basespringboot.dto.AdventureDto;
import com.basespringboot.entity.Adventure;
import com.basespringboot.repository.AdventureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service 
@Transactional 
@RequiredArgsConstructor 
public class AdventureServiceImpl implements AdventureService {
    private final AdventureRepository adventureRepository;

    @Override 
    public Adventure save(AdventureDto adventureDto){
        Adventure adventure = new Adventure();
        adventure.setTitle((adventureDto.getTitle()));
        return adventureRepository.save(adventure);
    }

    @Override 
    public Adventure findById(Long id){
        return adventureRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Adventure " + id + " introuvable"));
    }

    @Override 
    public List<Adventure> findAll() {
        return adventureRepository.findAll();
    }

    @Override 
    public Adventure update(Long id, AdventureDto adventureDto){
        Adventure adventure = findById(id);
        adventure.setTitle(adventureDto.getTitle());
        return adventureRepository.save(adventure);
    }

    @Override 
    public void delete(Long id){
        adventureRepository.delete(findById(id));
    }
}