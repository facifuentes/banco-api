package com.banco.api.mapper;

import com.banco.api.dto.ClienteDto;
import com.banco.api.model.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente clienteDtoToCliente(ClienteDto clienteDto);

    ClienteDto clienteToClienteDto(Cliente cliente);

    List<ClienteDto> listClienteToListClienteDto(List<Cliente> litsCliente);
}
