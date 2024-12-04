package com.api.desafio_gazin.mapper;

import com.api.desafio_gazin.domain.Nivel;
import com.api.desafio_gazin.domain.dto.NivelDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NivelMapper {

    public NivelDto toDto(Nivel nivel) {
        NivelDto nivelDto = new NivelDto();
        BeanUtils.copyProperties(nivel, nivelDto);

        return nivelDto;
    }

    public Nivel toEntity(NivelDto nivelDto) {
        Nivel nivel = new Nivel();
        BeanUtils.copyProperties(nivelDto, nivel);

        return nivel;
    }

    public List<NivelDto> toListDto(List<Nivel> nivel) {
        List<NivelDto> nivelDto = new ArrayList<>();

        nivel.forEach(dev -> {
            nivelDto.add(toDto(dev));
        });

        return nivelDto;
    }





}
