package br.com.sistemaveiculos.dtos;

import lombok.Getter;

@Getter
public class MarcaFabricanteDTO {

	private Integer qtdVeiculo;
	private String marca;
	
	public MarcaFabricanteDTO(Object[] obj) {
		this.qtdVeiculo = (Integer) obj[0];
		this.marca = (String) obj[1];
	}
}
