package com.banco.api.mapper;

import com.banco.api.dto.MovimientoDto;
import com.banco.api.model.Enum;
import com.banco.api.model.Movimiento;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {

    @Mapping(source = "cuentaNumero", target = "mvtCuenta.cntNumero")
    Movimiento movimientoDtoToMovimiento(MovimientoDto movimiento);

    @Mapping(source = "mvtCuenta.cntNumero", target = "cuentaNumero")
    MovimientoDto movimientoToMovimientoDto(Movimiento movimientoDto);

    List<MovimientoDto> LitsmovimientoToMovimientoDto(List<Movimiento> listMovimientoDto);

    @BeforeMapping
    default void beforemovimientoToMovimientoDto(MovimientoDto movimientoDto, @MappingTarget Movimiento movimiento) {
        movimiento.setMvtTipo(movimientoDto.getMvtTipo().toString());
    }

    @BeforeMapping
    default void beforemovimientoToMovimientoDto(Movimiento movimiento, @MappingTarget MovimientoDto movimientoDto) {
        movimientoDto.setMvtTipo(Enum.EnumTipoMovimiento.valueOf(movimiento.getMvtTipo()));
    }
}
