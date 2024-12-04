package com.api.desafio_gazin.repository;

import com.api.desafio_gazin.domain.Nivel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NivelRepository extends JpaRepository<Nivel, Integer> {
    Page<Nivel> findAll(Pageable pageable);
}
