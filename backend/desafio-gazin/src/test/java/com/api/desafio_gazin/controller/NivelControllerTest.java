package com.api.desafio_gazin.controller;

import com.api.desafio_gazin.domain.Nivel;
import com.api.desafio_gazin.domain.dto.DesenvolvedorDto;
import com.api.desafio_gazin.domain.dto.NivelDto;
import com.api.desafio_gazin.exception.exceptions.BusinessException;
import com.api.desafio_gazin.service.DesenvolvedorService;
import com.api.desafio_gazin.service.NivelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class NivelControllerTest {


    @InjectMocks
    private NivelController nivelController;

    @Mock
    private NivelService nivelService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreatedNivel() throws Exception {
        NivelDto nivelDto = getNivelDto();

        when(nivelService.createNivel(nivelDto)).thenReturn(nivelDto);

        ResponseEntity<NivelDto> request = nivelController.createNivel(nivelDto);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, request.getStatusCode()),
                () -> assertTrue(request.hasBody()),
                () -> assertEquals("Nivel 1", request.getBody().getNivel())
        );

    }

    @Test
    public void shouldNotCreatedNivel() throws Exception {
        NivelDto nivelDto = getNivelDto();
        nivelDto.setNivel(null);

        when(nivelService.createNivel(nivelDto)).thenThrow(new RuntimeException("Erro ao criar nível"));

        BusinessException exception =
                assertThrows(BusinessException.class, () -> nivelController.createNivel(nivelDto));


        assertEquals("Erro ao criar nível", exception.getMessage());


    }

    private static NivelDto getNivelDto() {
        NivelDto nivelDto = new NivelDto();
        nivelDto.setNivel("Nivel 1");

        return nivelDto;
    }


}
