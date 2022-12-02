package com.banco.api.mapper;

import com.banco.api.dto.ClienteDto;
import com.banco.api.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(source = "identificacion", target = "cliPersona.identificacion")
    @Mapping(source = "nombre", target = "cliPersona.perNombre")
    @Mapping(source = "direccion", target = "cliPersona.direccion")
    @Mapping(source = "telefono", target = "cliPersona.telefono")
    Cliente clienteDtoToCliente(ClienteDto clienteDto);

    @Mapping(target = "identificacion", source = "cliPersona.identificacion")
    @Mapping(target = "nombre", source = "cliPersona.perNombre")
    @Mapping(target = "direccion", source = "cliPersona.direccion")
    @Mapping(target = "telefono", source = "cliPersona.telefono")
    ClienteDto clienteToClienteDto(Cliente cliente);

    List<ClienteDto> listClienteToListClienteDto(List<Cliente> litsCliente);
}
