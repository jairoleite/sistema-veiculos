package br.com.sistemaveiculos.dtos;

import br.com.sistemaveiculos.entities.Veiculo;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VeiculoDTO {

	private Long id;
	private String veiculo;
	private String marca;
	private Integer ano;
	private String descricao;
	private Boolean vendido;
	
	public VeiculoDTO() {
	}
	
	public VeiculoDTO(Veiculo entidade) {
		this.id = entidade.getId();
		this.veiculo = entidade.getMarca();
		this.marca = entidade.getMarca();
		this.ano = entidade.getAno();
		this.descricao = entidade.getDescricao();
		this.vendido = entidade.getVendido();
	}
	
}
