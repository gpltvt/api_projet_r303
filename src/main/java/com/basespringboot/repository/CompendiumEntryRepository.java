package com.basespringboot.repository;

import com.basespringboot.entity.CompendiumEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompendiumEntryRepository extends JpaRepository<CompendiumEntry, Long> {
    
}
