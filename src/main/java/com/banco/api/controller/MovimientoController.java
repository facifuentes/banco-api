package com.banco.api.controller;

import com.banco.api.dto.MovimientoDto;
import com.banco.api.dto.ObjectResponse;
import com.banco.api.mapper.MovimientoMapper;
import com.banco.api.model.Movimiento;
import com.banco.api.service.interfaces.IMovimientoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "movimiento")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/movimiento")
public class MovimientoController {


    private final IMovimientoService movimientoService;
    private final MovimientoMapper movimientoMapper;

    @PostMapping
    public ResponseEntity<ObjectResponse> save(@Valid @RequestBody MovimientoDto dto) {
        Movimiento movimiento = movimientoService.save(movimientoMapper.movimientoDtoToMovimiento(dto));
        MovimientoDto movimientoDto= movimientoMapper.movimientoToMovimientoDto(movimiento);
        return new ResponseEntity<>(
                ObjectResponse.builder().code(HttpStatus.OK.value()).message("Exitoso").value(movimientoDto).build(),
                HttpStatus.OK);
    }
}
