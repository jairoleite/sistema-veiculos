package br.com.sistemaveiculos.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sistemaveiculos.dtos.VeiculoDTO;
import br.com.sistemaveiculos.services.VeiculoService;
import br.com.sistemaveiculos.services.exceptions.VeiculoException;
import br.com.sistemaveiculos.tests.Factory;

@WebMvcTest(VeiculoResource.class)
public class VeiculoResourceTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private VeiculoService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private Long existeId;
	private Long naoExisteId;
	private VeiculoDTO veiculoDTO;
	
	@BeforeEach
	void inicial() throws Exception {
		
		existeId = 1L;
		naoExisteId = 200L;
		
		veiculoDTO = Factory.criaVeiculoDTO();
		
		when(service.pegaPorId(existeId)).thenReturn(veiculoDTO);
		when(service.pegaPorId(naoExisteId)).thenThrow(VeiculoException.class);

		when(service.salvar(any())).thenReturn(veiculoDTO);
		
		when(service.alterar(eq(existeId), any())).thenReturn(veiculoDTO);
		when(service.alterar(eq(naoExisteId), any())).thenThrow(VeiculoException.class);
		
		doNothing().when(service).deletar(existeId);
		doThrow(VeiculoException.class).when(service).deletar(naoExisteId);
	}
	
	@Test
	public void deletaENaoRetornaNadaSeExistirId() throws Exception {
		
		ResultActions result = 
				mockMvc.perform(delete("/veiculos/{id}", existeId)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}
	
	@Test
	public void deletaENaoRetornaNadaQuandoNaoExistirId() throws Exception {
		
		ResultActions result = 
				mockMvc.perform(delete("/veiculos/{id}", naoExisteId)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void insereVeiculoERetornaDtoCriado() throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(veiculoDTO);
		
		ResultActions result = 
				mockMvc.perform(post("/veiculos")
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.veiculo").exists());
	}
	
	@Test
	public void alteraERetornaDtoQuandoExistirId() throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(veiculoDTO);
		
		ResultActions result = 
				mockMvc.perform(put("/veiculos/{id}", existeId)
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}
	
	
}












