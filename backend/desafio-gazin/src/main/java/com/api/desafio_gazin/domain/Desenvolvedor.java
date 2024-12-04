package com.api.desafio_gazin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "desenvolvedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Desenvolvedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, optional = false)
//    @JoinColumn(name = "nivel_id")
    @Column(name = "nivel_id")
    private int nivelId;

    private String nome;

    private String sexo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data de nascimento é obrigatório")
    private LocalDate dataNascimento;

    private String hobby;

    @NotNull(message = "Idade é obrigatório")
    private int idade;


}
