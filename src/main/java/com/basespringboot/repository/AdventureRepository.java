package com.basespringboot.repository;

import com.basespringboot.entity.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface AdventureRepository extends JpaRepository<Adventure, Long> {

}