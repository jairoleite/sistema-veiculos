package br.com.sistemaveiculos.dtos;

import lombok.Getter;

@Getter
public class DecadaFabricacaoDTO {

	private Integer qtdVeiculo;
	private Integer decada;
	
	public DecadaFabricacaoDTO(Object[] obj) {
		this.qtdVeiculo = (Integer) obj[0];
		this.decada = (Integer) obj[1];
	}
	
	
}
