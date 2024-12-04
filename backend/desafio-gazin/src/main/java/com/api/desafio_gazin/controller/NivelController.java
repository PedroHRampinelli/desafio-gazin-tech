package com.api.desafio_gazin.controller;

import com.api.desafio_gazin.domain.Nivel;
import com.api.desafio_gazin.domain.dto.NivelDto;
import com.api.desafio_gazin.exception.exceptions.BusinessException;
import com.api.desafio_gazin.service.NivelService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/niveis")
public class NivelController {

    private final NivelService nivelService;

    public NivelController(NivelService nivelService) {
        this.nivelService = nivelService;
    }

    @GetMapping
    public ResponseEntity<Page<NivelDto>> findAll(@RequestParam int page, @RequestParam int size) {
        Page<NivelDto> niveis = nivelService.findAll(page, size);

        if(niveis.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(niveis);
        }
    }

    @PostMapping
    public ResponseEntity<NivelDto> createNivel(@RequestBody @Validated NivelDto nivelDto) {
        try {
            NivelDto createdNivel = nivelService.createNivel(nivelDto);
            return new ResponseEntity<>(createdNivel, HttpStatus.CREATED);
        }catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<NivelDto> updateNivel(@PathVariable Integer id, @RequestBody @Validated NivelDto nivelDto) {
        try {
            NivelDto createdNivel = nivelService.updateNivel(id, nivelDto);
            return new ResponseEntity<>(createdNivel, HttpStatus.CREATED);
        }catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NivelDto> deleteNivel(@PathVariable Integer id) {
        nivelService.deleteNivel(id);
        return ResponseEntity.ok().build();
    }

}
