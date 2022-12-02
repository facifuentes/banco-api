package com.banco.api.controller;

import com.banco.api.dto.CuentaDto;
import com.banco.api.dto.ObjectResponse;
import com.banco.api.mapper.CuentaMapper;
import com.banco.api.model.Cuenta;
import com.banco.api.service.interfaces.ICuentaService;
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

@Api(tags = "Cuenta")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/cuenta")
public class CuentaController {

    public static final String URL_CONTROLLER = "/cuenta";
    private final ICuentaService cuentaService;
    private final CuentaMapper cuentaMapper;

    @PostMapping
    public ResponseEntity<ObjectResponse> save(@Valid @RequestBody CuentaDto dto) {
        Cuenta cuenta = cuentaService.save(cuentaMapper.cuentaDtoToCuenta(dto));
        CuentaDto cuentaDto= cuentaMapper.cuentaToCuentaDto(cuenta);
        return new ResponseEntity<>(
                ObjectResponse.builder().code(HttpStatus.OK.value()).message("Exitoso").value(cuentaDto).build(),
                HttpStatus.OK);
    }


}
