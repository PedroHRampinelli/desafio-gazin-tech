package com.api.desafio_gazin.controller;

import com.api.desafio_gazin.domain.Nivel;
import com.api.desafio_gazin.domain.dto.DesenvolvedorDto;
import com.api.desafio_gazin.exception.exceptions.BusinessException;
import com.api.desafio_gazin.service.DesenvolvedorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class DesenvolvedorControllerTest {

    @InjectMocks
    private DesenvolvedorController desenvolvedorController;

    @Mock
    private DesenvolvedorService desenvolvedorService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreatedDev() throws Exception {
        DesenvolvedorDto desenvolvedorDto = getDesenvolvedorDto();

        when(desenvolvedorService.createDesenvolvedor(desenvolvedorDto)).thenReturn(desenvolvedorDto);

        ResponseEntity<DesenvolvedorDto> request = desenvolvedorController.createDesenvolvedor(desenvolvedorDto);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, request.getStatusCode()),
                () -> assertTrue(request.hasBody()),
                () -> assertEquals("Pedro", request.getBody().getNome()),
                () -> assertEquals("Masculino", request.getBody().getSexo()),
                () -> assertEquals("Jogos", request.getBody().getHobby()),
                () -> assertEquals("Nivel 1", request.getBody().getNivel().getNivel())
        );

    }

    @Test
    public void shouldNotCreatedDev() throws Exception {
        DesenvolvedorDto desenvolvedorDto = getDesenvolvedorDto();
        desenvolvedorDto.setNome(null);

        when(desenvolvedorService.createDesenvolvedor(desenvolvedorDto)).thenThrow(new BusinessException("Erro ao criar desenvolvedor"));

        BusinessException exception =
                assertThrows(BusinessException.class, () -> desenvolvedorController.createDesenvolvedor(desenvolvedorDto));

        assertEquals("Erro ao criar desenvolvedor", exception.getMessage());


    }

    private static DesenvolvedorDto getDesenvolvedorDto() {
        DesenvolvedorDto desenvolvedorDto = new DesenvolvedorDto();
        desenvolvedorDto.setNome("Pedro");
        desenvolvedorDto.setSexo("Masculino");
        desenvolvedorDto.setHobby("Jogos");
        desenvolvedorDto.setNivel(new Nivel(1, "Nivel 1"));
        return desenvolvedorDto;
    }


}
