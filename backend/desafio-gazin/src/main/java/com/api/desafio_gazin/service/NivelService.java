package com.api.desafio_gazin.service;

import com.api.desafio_gazin.domain.Desenvolvedor;
import com.api.desafio_gazin.domain.Nivel;
import com.api.desafio_gazin.domain.dto.NivelDto;
import com.api.desafio_gazin.exception.exceptions.BusinessException;
import com.api.desafio_gazin.exception.exceptions.ResourceNotFoundException;
import com.api.desafio_gazin.mapper.NivelMapper;
import com.api.desafio_gazin.repository.DesenvolvedorRepository;
import com.api.desafio_gazin.repository.NivelRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NivelService {

    private final NivelRepository nivelRepository;

    private final DesenvolvedorRepository desenvolvedorRepository;

    private final NivelMapper nivelMapper;

    public NivelService(NivelRepository nivelRepository, DesenvolvedorRepository desenvolvedorRepository, NivelMapper nivelMapper) {
        this.nivelRepository = nivelRepository;
        this.desenvolvedorRepository = desenvolvedorRepository;
        this.nivelMapper = nivelMapper;
    }

    public Page<NivelDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));

        Page<Nivel> listNivel = nivelRepository.findAll(pageable);

        if (listNivel.isEmpty()) {
            throw new ResourceNotFoundException("Não há registros");
        }

        List<NivelDto> listDto = nivelMapper.toListDto(listNivel.getContent());

        return new PageImpl<>(listDto, pageable, listNivel.getTotalElements());

    }

    public NivelDto createNivel(NivelDto nivelDto) {
        try{
            Nivel createdNivel = nivelRepository.save(nivelMapper.toEntity(nivelDto));
            return nivelMapper.toDto(createdNivel);
        }catch (Exception e) {
            throw new BusinessException("Erro ao criar nível");
        }
    }

    public NivelDto updateNivel(Integer id, NivelDto nivelDto) {
        Optional<Nivel> nivelExistente = nivelRepository.findById(id);
        if(nivelExistente.isEmpty()) {
            throw new BusinessException("Nível não encontrado");
        }

        nivelExistente.get().setNivel(nivelDto.getNivel());
        Nivel updatedNivel = nivelRepository.save(nivelExistente.get());

        return nivelMapper.toDto(updatedNivel);
    }

    public void deleteNivel(Integer id) {
        Desenvolvedor temDesenvolvedor = desenvolvedorRepository.findByNivelId(id);

        if(temDesenvolvedor != null) {
            throw new BusinessException("Não é possível deletar um nível que tenha um desenvolvedor associado");
        }

        Optional<Nivel> idNivel = findById(id);
        if(idNivel.isEmpty()) {
            throw new BusinessException("ID não encontrado");
        }

        nivelRepository.deleteById(id);
    }

    public Optional<Nivel> findById(Integer id) {
        Optional<Nivel> nivelExistente = nivelRepository.findById(id);
        if(nivelExistente.isEmpty()) {
            throw new BusinessException("Nível não encontrado");
        }
        return nivelExistente;
    }


}
