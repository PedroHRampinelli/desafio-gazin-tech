package com.api.desafio_gazin.service;

import com.api.desafio_gazin.domain.Desenvolvedor;
import com.api.desafio_gazin.domain.Nivel;
import com.api.desafio_gazin.domain.dto.DesenvolvedorDto;
import com.api.desafio_gazin.exception.exceptions.BusinessException;
import com.api.desafio_gazin.exception.exceptions.ResourceNotFoundException;
import com.api.desafio_gazin.mapper.DesenvolvedorMapper;
import com.api.desafio_gazin.repository.DesenvolvedorRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesenvolvedorService {


    private final DesenvolvedorRepository desenvolvedorRepository;

    private final DesenvolvedorMapper desenvolvedorMapper;

    private final NivelService nivelService;

    public DesenvolvedorService(DesenvolvedorRepository desenvolvedorRepository, DesenvolvedorMapper desenvolvedorMapper, NivelService nivelService) {
        this.desenvolvedorRepository = desenvolvedorRepository;
        this.desenvolvedorMapper = desenvolvedorMapper;
        this.nivelService = nivelService;
    }

    public Page<DesenvolvedorDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));

        Page<Desenvolvedor> listDesenvolvedor = desenvolvedorRepository.findAll(pageable);

        if(listDesenvolvedor.isEmpty()) {
            throw new ResourceNotFoundException("Não há registros");
        }

        List<DesenvolvedorDto> listDto = desenvolvedorMapper.toListDto(listDesenvolvedor.getContent());

        return new PageImpl<>(listDto, pageable, listDesenvolvedor.getTotalElements());

    }

    public DesenvolvedorDto createDesenvolvedor(DesenvolvedorDto desenvolvedorDto) {
        Optional<Nivel> nivelExistente = nivelService.findById(desenvolvedorDto.getNivelId());
        if(nivelExistente.isEmpty()) {
            throw new BusinessException("Deve existir um nível antes de criar um desenvolvedor");
        }

        Desenvolvedor createdDev = desenvolvedorRepository.save(desenvolvedorMapper.toEntity(desenvolvedorDto));
        return desenvolvedorMapper.toDto(createdDev);
    }

    public DesenvolvedorDto updateDesenvolvedor(Integer id, DesenvolvedorDto desenvolvedorDto) {

        Optional<Desenvolvedor> desenvolvedorExistente = desenvolvedorRepository.findById(id);
        if(desenvolvedorExistente.isEmpty()) {
            throw new BusinessException("Desenvolvedor não encontrado");
        }
        desenvolvedorExistente.get().setNome(desenvolvedorDto.getNome());
        desenvolvedorExistente.get().setSexo(desenvolvedorDto.getSexo());
        desenvolvedorExistente.get().setDataNascimento(desenvolvedorDto.getDataNascimento());

        Optional<Nivel> nivelExistente = nivelService.findById(desenvolvedorDto.getNivelId());
        if(nivelExistente.isEmpty()) {
            throw new BusinessException("Nível não existente");
        }

        desenvolvedorExistente.get().setNivelId(nivelExistente.get().getId());

        desenvolvedorExistente.get().setHobby(desenvolvedorDto.getHobby());

        Desenvolvedor updatedDev = desenvolvedorRepository.save(desenvolvedorExistente.get());
        return desenvolvedorMapper.toDto(updatedDev);
    }

    public void deleteDesenvolvedor(Integer id) {
        Optional<Desenvolvedor> desenvolvedorId = desenvolvedorRepository.findById(id);

        if(desenvolvedorId.isEmpty()) {
            throw new BusinessException("Desenvolvedor não encontrado");
        }
        desenvolvedorRepository.deleteById(id);

    }

    public int countByNivelId(Integer nivelId) {

        int quantidadeDevs = desenvolvedorRepository.countByNivelId(nivelId);

        return quantidadeDevs;

    }
}
