package br.com.sistemaveiculos.dtos;

import java.time.LocalDateTime;
import java.util.Objects;

import br.com.sistemaveiculos.entities.Veiculo;

public class VeiculoDTO {

	private Long id;
	private String veiculo;
	private String marca;
	private Integer ano;
	private String descricao;
	private Boolean vendido;
	private LocalDateTime created;
	
	public VeiculoDTO() {
	}
	
	public VeiculoDTO(Veiculo entidade) {
		this.id = entidade.getId();
		this.veiculo = entidade.getVeiculo();
		this.marca = entidade.getMarca();
		this.ano = entidade.getAno();
		this.descricao = entidade.getDescricao();
		this.vendido = entidade.getVendido();
		this.created = entidade.getCreated();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getVendido() {
		return vendido;
	}

	public void setVendido(Boolean vendido) {
		this.vendido = vendido;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VeiculoDTO other = (VeiculoDTO) obj;
		return Objects.equals(id, other.id);
	}
	
}
