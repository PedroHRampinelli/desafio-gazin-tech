package com.api.desafio_gazin.domain.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NivelDto {

    @Id
    private int id;

    @NotBlank(message = "O campo NIVEL é obrigatório")
    private String nivel;

}
