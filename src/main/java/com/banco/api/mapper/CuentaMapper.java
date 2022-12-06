package com.banco.api.mapper;

import com.banco.api.dto.CuentaDto;
import com.banco.api.model.Cuenta;
import com.banco.api.model.Enum;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    @Mapping(source = "idClient", target = "cntCliente.cliId")
    Cuenta cuentaDtoToCuenta(CuentaDto cuentaDto);

    @Mapping(source = "cntCliente.cliId", target = "idClient")
    CuentaDto cuentaToCuentaDto(Cuenta cuenta);

    List<CuentaDto> litsCuentaToCuentaDto(List<Cuenta> listCuenta);

    @BeforeMapping
    default void beforeCuentaToCuentaDto(CuentaDto cuentaDto, @MappingTarget Cuenta cuenta) {
        cuenta.setCntTipo(cuentaDto.getCntTipo().toString());
    }

    @BeforeMapping
    default void beforeCuentaToCuentaDto(Cuenta cuenta, @MappingTarget CuentaDto cuentaDto) {
        cuentaDto.setCntTipo(Enum.EnumTipoCuenta.valueOf(cuenta.getCntTipo()));
    }
}
