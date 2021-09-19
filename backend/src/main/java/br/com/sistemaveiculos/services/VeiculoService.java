package br.com.sistemaveiculos.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import br.com.sistemaveiculos.dtos.DecadaFabricacaoDTO;
import br.com.sistemaveiculos.dtos.MarcaFabricanteDTO;
import br.com.sistemaveiculos.dtos.VeiculoDTO;
import br.com.sistemaveiculos.entities.Veiculo;
import br.com.sistemaveiculos.repositories.VeiculoRepository;
import br.com.sistemaveiculos.services.exceptions.VeiculoException;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository repository;

	@Transactional(readOnly = true)
	public List<VeiculoDTO> listarTodos() {
		return repository.findAll().stream().map(v -> new VeiculoDTO(v)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<VeiculoDTO> listaPorVeiculo(String veiculo) {
	    String pesquisa = StringUtils.isBlank(veiculo) ? "%%" : "%"+veiculo+"%";
	    
	    return repository.listaPorVeiculo(pesquisa).stream().map(v -> new VeiculoDTO(v)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public VeiculoDTO pegaPorId(Long id) {
		Optional<Veiculo> optVeiculo = repository.findById(id);
		
		Veiculo veiculo = optVeiculo.orElseThrow(() -> new VeiculoException("Não existe o veículo com o id "+id+""));
		
		return new VeiculoDTO(veiculo);
	}
	
	@Transactional(readOnly = true)
	public Integer pegaQtdNaoVendidos() {
		return repository.qtdNaoVendidos();
	}
	
	@Transactional(readOnly = true)
	public List<DecadaFabricacaoDTO> listaDistribuicaoPorDecada() {
		return repository.listaDistribuicaoPorDecada().stream().map(v -> new DecadaFabricacaoDTO(v)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<MarcaFabricanteDTO> listaDistribuicaoPorMarca() {
		return repository.listaDistribuicaoPorMarca().stream().map(v -> new MarcaFabricanteDTO(v)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<VeiculoDTO> listaRegistroUltimaSemana() {
		return repository.listaRegistradoUltimaSemana().stream().map(v -> new VeiculoDTO(v)).collect(Collectors.toList());
	}
	
	@Transactional
	public VeiculoDTO salvar(VeiculoDTO dto) {
		Veiculo veiculo = new Veiculo();
		copiaVeiculo(dto, veiculo);
		repository.save(veiculo);

		return new VeiculoDTO(veiculo);
	}

	@Transactional
	public VeiculoDTO alterar(Long id, VeiculoDTO dto) {
		try {
			Veiculo veiculo = repository.getById(id);
			copiaVeiculo(dto, veiculo);
			veiculo = repository.save(veiculo);
			return new VeiculoDTO(veiculo);			
		} 
		catch (EntityNotFoundException e) {
			throw new VeiculoException("Não foi possível alterar o veículo!", e);	
		}
	}
	
	@Transactional
	public void alterarParcial(Long id, Map<String, Object> campos) {
		Optional<Veiculo> optVeiculo = repository.findById(id);
		
		if(optVeiculo.isPresent()) {
			campos.forEach((chave, valor) -> {
				Field campo = ReflectionUtils.findField(Veiculo.class, chave);
				campo.setAccessible(true);
				ReflectionUtils.setField(campo, optVeiculo.get(), valor);
			});
			
			repository.save(optVeiculo.get());
		}
		else {
			throw new VeiculoException("Não foi possível encontrado o veículo de id "+id+"!");
		}
	}

	public void deletar(Long id) {
		try {
			repository.deleteById(id);
		} 
		catch (EmptyResultDataAccessException e) {
			throw new VeiculoException("Não foi possível deletar o veículo!", e);
		}
	}

	private void copiaVeiculo(VeiculoDTO dto, Veiculo veiculo) {
		veiculo.setVeiculo(dto.getVeiculo());
		veiculo.setMarca(dto.getMarca());
		veiculo.setAno(dto.getAno());
		veiculo.setDescricao(dto.getDescricao());
		veiculo.setVendido(dto.getVendido());
	}

}
