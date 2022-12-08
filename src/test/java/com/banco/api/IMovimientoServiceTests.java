package com.banco.api;


import com.banco.api.exception.ForbiddenException;
import com.banco.api.exception.NotFoundException;
import com.banco.api.model.Cuenta;
import com.banco.api.model.Enum;
import com.banco.api.model.Movimiento;
import com.banco.api.repository.CuentaRepository;
import com.banco.api.repository.MovimientoRepository;
import com.banco.api.service.impl.MovimientoService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@ActiveProfiles("local")
class IMovimientoServiceTests {


	@Mock
	private MovimientoRepository movimientoRepository;

	@Mock
	private CuentaRepository cuentaRepository;

	@InjectMocks
	private MovimientoService movimientoService;

	private Cuenta cuenta;
	private Movimiento movimiento;

	@BeforeEach
	@DisplayName("injectDependency")
	void injectionTest() {
		cuenta = Cuenta.builder().cntId(1L).cntSaldoInicial(BigDecimal.valueOf(2000)).cntNumero(123456L).build();
		movimiento = Movimiento.builder()
				.mvtCuenta(cuenta)
				.mvtValor(BigDecimal.valueOf(1000))
				.mvtTipo(Enum.EnumTipoMovimiento.RETIRO.toString()).build();
		movimientoService = new MovimientoService(movimientoRepository,cuentaRepository);
	}

	@Test
	@DisplayName("saveCuentaExistente")
	@Order(1)
	@Transactional(propagation = Propagation.REQUIRED)
	void saveCuentaExistente(){

		when(cuentaRepository.findCuentaByCntNumero(anyLong())).thenReturn(Optional.ofNullable(cuenta));
		when(movimientoRepository.save(movimiento)).thenReturn(movimiento);

		assertDoesNotThrow(() -> movimientoService.save(movimiento));
	}

	@Test
	@DisplayName("saveCuentaNoExistente")
	@Order(2)
	@Transactional(propagation = Propagation.REQUIRED)
	void saveCuentaNoExistente(){
		when(cuentaRepository.findCuentaByCntNumero(anyLong())).thenReturn(Optional.empty());
		org.assertj.core.api.Assertions.assertThatExceptionOfType(NotFoundException.class).isThrownBy(() ->movimientoService.save(movimiento));
	}


	@Test
	@DisplayName("saveRetiro")
	@Order(3)
	@Transactional(propagation = Propagation.REQUIRED)
	void saveRetiro(){
		when(cuentaRepository.findCuentaByCntNumero(anyLong())).thenReturn(Optional.ofNullable(cuenta));
		when(movimientoRepository.save(movimiento)).thenReturn(movimiento);
		assertEquals(new BigDecimal(-1000), movimientoService.save(movimiento).getMvtValor());
	}

	@Test
	@DisplayName("saveDeposito")
	@Order(4)
	@Transactional(propagation = Propagation.REQUIRED)
	void saveDeposito(){
		movimiento.setMvtTipo(Enum.EnumTipoMovimiento.DEPOSITO.toString());
		when(cuentaRepository.findCuentaByCntNumero(anyLong())).thenReturn(Optional.ofNullable(cuenta));
		when(movimientoRepository.save(movimiento)).thenReturn(movimiento);
		assertEquals(new BigDecimal(3000), movimientoService.save(movimiento).getMvtSaldo());
	}


	@Test
	@DisplayName("saveRetiroSaldoCero")
	@Order(5)
	@Transactional(propagation = Propagation.REQUIRED)
	void saveRetiroSaldoCero(){
		cuenta.setCntSaldoInicial(BigDecimal.ZERO);
		when(cuentaRepository.findCuentaByCntNumero(anyLong())).thenReturn(Optional.ofNullable(cuenta));
		when(movimientoRepository.save(movimiento)).thenReturn(movimiento);
		org.assertj.core.api.Assertions.assertThatExceptionOfType(ForbiddenException.class).isThrownBy(() ->movimientoService.save(movimiento));
	}

	@Test
	@DisplayName("saveRetiroMayorAlSaldo")
	@Order(6)
	@Transactional(propagation = Propagation.REQUIRED)
	void saveRetiroMayorAlSaldo(){
		movimiento.setMvtValor(BigDecimal.valueOf(50000));
		when(cuentaRepository.findCuentaByCntNumero(anyLong())).thenReturn(Optional.ofNullable(cuenta));
		when(movimientoRepository.save(movimiento)).thenReturn(movimiento);
		org.assertj.core.api.Assertions.assertThatExceptionOfType(ForbiddenException.class).isThrownBy(() ->movimientoService.save(movimiento));
	}


}
