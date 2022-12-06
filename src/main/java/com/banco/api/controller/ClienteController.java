package com.banco.api.controller;

import com.banco.api.dto.ClienteDto;
import com.banco.api.dto.ObjectResponse;
import com.banco.api.mapper.ClienteMapper;
import com.banco.api.model.Cliente;
import com.banco.api.service.impl.ClienteService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api(tags = "cliente")
@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {
    public static final String URL_CONTROLLER = "/cliente";
    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    @PostMapping
    public ResponseEntity<ObjectResponse> save(@Valid @RequestBody ClienteDto dto) {
        Cliente cliente = clienteService.save(clienteMapper.clienteDtoToCliente(dto));
        ClienteDto clienteDto = clienteMapper.clienteToClienteDto(cliente);
        return new ResponseEntity<>(
                ObjectResponse.builder().code(HttpStatus.OK.value()).message("Exitoso").value(clienteDto).build(),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ObjectResponse> delete(@Valid @NotNull @PathVariable("id") Long id) {
        clienteService.delete(id);
        return new ResponseEntity<>(
                ObjectResponse.builder().code(HttpStatus.OK.value()).message("Exitoso").build(),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjectResponse> update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody ClienteDto dto) {
        Cliente cliente = clienteService.update(clienteMapper.clienteDtoToCliente(dto));
        ClienteDto clienteDto = clienteMapper.clienteToClienteDto(cliente);
        return new ResponseEntity<>(
                ObjectResponse.builder().code(HttpStatus.OK.value()).message("Exitoso").value(clienteDto).build(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjectResponse> getById(@Valid @NotNull @PathVariable("id") Long id) {
        ClienteDto clienteDto = clienteMapper.clienteToClienteDto(clienteService.findById(id));
        return new ResponseEntity<>(
                ObjectResponse.builder().code(HttpStatus.OK.value()).message("Exitoso").value(clienteDto).build(),
                HttpStatus.OK);
    }


}
