package com.banco.api;

import com.banco.api.controller.ClienteController;
import com.banco.api.controller.CuentaController;
import com.banco.api.dto.ClienteDto;
import com.banco.api.dto.CuentaDto;
import com.banco.api.model.Enum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@Rollback
@SpringBootTest
@ActiveProfiles("local")
class ApiApplicationTests {

	@Autowired
	protected MockMvc mvc;


	@Test
	@DisplayName("saveClienteTest")
	@Order(1)
	@Transactional(propagation = Propagation.REQUIRED)
	void saveClienteTest() throws Exception {
		ClienteDto clienteDto = ClienteDto.builder()
				.cliEstado(true)
				.identificacion("11224554")
				.nombre("Fernando")
				.direccion("Carrrera")
				.telefono("338845664")
				.cliClave("12313").build();

		mvc.perform(MockMvcRequestBuilders
						.post(ClienteController.URL_CONTROLLER )
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapToJson(clienteDto)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@DisplayName("saveCuentaTest")
	@Order(2)
	@Transactional(propagation = Propagation.REQUIRED)
	void saveCuentaTest() throws Exception {
		CuentaDto cuentaDto = CuentaDto.builder()
				.idClient(1L).cntEstado(true)
				.cntTipo(Enum.EnumTipoCuenta.AHORROS)
				.cntSaldoInicial(BigDecimal.valueOf(2000))
				.cntNumero(11111L).build();

		mvc.perform(MockMvcRequestBuilders
						.post(CuentaController.URL_CONTROLLER )
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapToJson(cuentaDto)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		return objectMapper.writeValueAsString(obj);
	}

}
