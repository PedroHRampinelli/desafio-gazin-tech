package com.api.desafio_gazin.controller;

import com.api.desafio_gazin.domain.dto.DesenvolvedorDto;
import com.api.desafio_gazin.domain.dto.NivelDto;
import com.api.desafio_gazin.exception.exceptions.BusinessException;
import com.api.desafio_gazin.service.DesenvolvedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/desenvolvedores")
public class DesenvolvedorController {

    private final DesenvolvedorService desenvolvedorService;

    public DesenvolvedorController(DesenvolvedorService desenvolvedorService) {
        this.desenvolvedorService = desenvolvedorService;
    }

    public ResponseEntity<Page<DesenvolvedorDto>> findAll(@RequestParam int page, @RequestParam int size) {
       Page<DesenvolvedorDto> desenvolvedores = desenvolvedorService.findAll(page, size);

        if(desenvolvedores.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(desenvolvedores);
    }

    public ResponseEntity<DesenvolvedorDto> createDesenvolvedor(@RequestBody @Validated DesenvolvedorDto desenvolvedorDto) {
        try {
            DesenvolvedorDto createdDesenvolvedor = desenvolvedorService.createDesenvolvedor(desenvolvedorDto);
            return new ResponseEntity<>(createdDesenvolvedor, HttpStatus.CREATED);
        }catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<DesenvolvedorDto> updateDesenvolvedor(@PathVariable Integer id, @RequestBody @Validated DesenvolvedorDto desenvolvedor) {
        try {
            DesenvolvedorDto desenvolvedorDto = desenvolvedorService.updateDesenvolvedor(id, desenvolvedor);
            return new ResponseEntity<>(desenvolvedorDto, HttpStatus.CREATED);
        }catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DesenvolvedorDto> deleteDesenvolvedor(@PathVariable Integer id) {
        try {
            desenvolvedorService.deleteDesenvolvedor(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @GetMapping("/devByNivel/{nivelId}")
    public int getAllDevsByNivel(@PathVariable Integer nivelId) {
        try {
            return desenvolvedorService.countByNivelId(nivelId);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

}
