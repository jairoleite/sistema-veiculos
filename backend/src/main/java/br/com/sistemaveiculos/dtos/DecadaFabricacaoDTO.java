package br.com.sistemaveiculos.dtos;

import java.math.BigInteger;

import lombok.Getter;

@Getter
public class DecadaFabricacaoDTO {

	private BigInteger qtdVeiculo;
	private Integer decada;
	
	public DecadaFabricacaoDTO(Object[] obj) {
		this.qtdVeiculo = (BigInteger) obj[0];
		this.decada = (Integer) obj[1];
	}
	
	
}
