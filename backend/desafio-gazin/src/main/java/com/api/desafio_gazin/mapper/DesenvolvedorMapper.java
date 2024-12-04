package com.api.desafio_gazin.mapper;

import com.api.desafio_gazin.domain.Desenvolvedor;
import com.api.desafio_gazin.domain.dto.DesenvolvedorDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DesenvolvedorMapper {

    public DesenvolvedorDto toDto(Desenvolvedor desenvolvedor) {
        DesenvolvedorDto desenvolvedorDto = new DesenvolvedorDto();
        BeanUtils.copyProperties(desenvolvedor, desenvolvedorDto);

        return desenvolvedorDto;

    }

    public Desenvolvedor toEntity(DesenvolvedorDto desenvolvedorDto) {
        Desenvolvedor desenvolvedor = new Desenvolvedor();
        BeanUtils.copyProperties(desenvolvedorDto, desenvolvedor);

        return desenvolvedor;
    }

    public List<DesenvolvedorDto> toListDto(List<Desenvolvedor> desenvolvedor) {
        List<DesenvolvedorDto> desenvolvedorDto = new ArrayList<>();

        desenvolvedor.forEach(dev -> {
            desenvolvedorDto.add(toDto(dev));
        });

        return desenvolvedorDto;
    }

}
