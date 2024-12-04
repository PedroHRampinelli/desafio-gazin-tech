package com.api.desafio_gazin.repository;

import com.api.desafio_gazin.domain.Desenvolvedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesenvolvedorRepository extends JpaRepository<Desenvolvedor, Integer> {
    Desenvolvedor findByNivelId(int id);

    int countByNivelId(Integer nivelId);
}
