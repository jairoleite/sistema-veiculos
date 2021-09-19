package br.com.sistemaveiculos.dtos;

import java.math.BigInteger;

import lombok.Getter;

@Getter
public class MarcaFabricanteDTO {

	private BigInteger qtdVeiculo;
	private String marca;
	
	public MarcaFabricanteDTO(Object[] obj) {
		this.qtdVeiculo = (BigInteger) obj[0];
		this.marca = (String) obj[1];
	}
}
