package br.com.sistemaveiculos.tests;

import br.com.sistemaveiculos.dtos.VeiculoDTO;
import br.com.sistemaveiculos.entities.Veiculo;

public final class Factory {
	
	private Factory() {}
	
	public static Veiculo criaVeiculo() {
		return new Veiculo(1L, "Civic", "Honda", 2010, "Semi novo", false);		
	}
	
	public static VeiculoDTO criaVeiculoDTO() {
		Veiculo veiculo = criaVeiculo();
		return new VeiculoDTO(veiculo);
	}
}
