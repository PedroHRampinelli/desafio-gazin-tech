package com.api.desafio_gazin.domain.dto;

import com.api.desafio_gazin.domain.Nivel;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DesenvolvedorDto {

    @Id
    private int id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    private String sexo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data de nascimento é obrigatório")
    private LocalDate dataNascimento;

    @NotBlank(message = "Hobby é obrigatório")
    private String hobby;

    private int nivelId;

    @NotNull(message = "Idade é obrigatório")
    private int idade;
}
