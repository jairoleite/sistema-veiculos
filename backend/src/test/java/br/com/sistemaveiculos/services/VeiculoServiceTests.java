package br.com.sistemaveiculos.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.sistemaveiculos.dtos.VeiculoDTO;
import br.com.sistemaveiculos.entities.Veiculo;
import br.com.sistemaveiculos.repositories.VeiculoRepository;
import br.com.sistemaveiculos.services.exceptions.VeiculoException;
import br.com.sistemaveiculos.tests.Factory;

@ExtendWith(SpringExtension.class)
public class VeiculoServiceTests {

	@InjectMocks
	private VeiculoService service;

	@Mock
	private VeiculoRepository repository;

	private long existeId;
	private long naoExisteId;
	private int semana;
	private Veiculo veiculo;
	private VeiculoDTO veiculoDTO;


	@BeforeEach
	void inicial() throws Exception {

		existeId = 1L;
		semana = 7;
		naoExisteId = 200L;
		veiculo = Factory.criaVeiculo();
		veiculoDTO = Factory.criaVeiculoDTO();

		when(repository.save(ArgumentMatchers.any())).thenReturn(veiculo);

		when(repository.findById(existeId)).thenReturn(Optional.of(veiculo));
		when(repository.findById(naoExisteId)).thenReturn(Optional.empty());

		doNothing().when(repository).deleteById(existeId);
		doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(naoExisteId);
	}

	@Test
	public void verificaResultadosDaUltimaSemana() {

		List<VeiculoDTO> listaRegistroUltimaSemana = service.listaRegistroUltimaSemana();

		listaRegistroUltimaSemana.forEach(v -> {
			LocalDateTime created = v.getCreated();

			long diferencaDias = ChronoUnit.DAYS.between(created, LocalDateTime.now());
			Assertions.assertTrue(diferencaDias <= semana);
		});
	}

	@Test
	public void salvaVeiculoEVerificaSeAdicionou() {
		VeiculoDTO dto = service.salvar(veiculoDTO);
		assertFalse(Objects.isNull(dto));
	}
	
	@Test
	public void deletaEVerificaEmptyResultDataAccessExceptionSeNaoExiste() {
		
		Assertions.assertThrows(VeiculoException.class, () -> {
			service.deletar(naoExisteId);
		});

		verify(repository, times(1)).deleteById(naoExisteId);
	}

}
