package com.basespringboot.repository;

import com.basespringboot.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findByAdventureId(Long adventureId);
    Optional<Entry> findByIdAndAdventureId(Long id, Long adventureId);
}