package com.banco.api.controller;

import com.banco.api.dto.MovimientoDto;
import com.banco.api.dto.MovimientoReporteDto;
import com.banco.api.dto.ObjectResponse;
import com.banco.api.mapper.MovimientoMapper;
import com.banco.api.model.Movimiento;
import com.banco.api.service.interfaces.IMovimientoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    @GetMapping("/reportes")
    public ResponseEntity<ObjectResponse> reporte(@RequestParam(value = "identificacion") String identificacion, @RequestParam(value = "fechaInicial") String fechaInicial, @RequestParam(value = "fechaFinal") String fechaFinal ) {
        List<Movimiento> movimientos = movimientoService.findByClienteAndFechas(identificacion,stringToLocalDateTime(fechaInicial+" 00:00:00"),stringToLocalDateTime(fechaFinal+" 23:59:59"));
        List<MovimientoReporteDto> movimientoDtos = movimientoMapper.listsmovimientoToMovimientoReporteDto(movimientos);

        return new ResponseEntity<>(
                ObjectResponse.builder().code(HttpStatus.OK.value()).message("Exitoso").value(movimientoDtos).build(),
                HttpStatus.OK);
    }

    public LocalDateTime stringToLocalDateTime(String dateTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, formatter);
    }
}
