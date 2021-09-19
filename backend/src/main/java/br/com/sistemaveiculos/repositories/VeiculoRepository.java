package br.com.sistemaveiculos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sistemaveiculos.entities.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

	@Query(value = "select sum(v.*) from tb_veiculo v where vendido = false", nativeQuery = true)
	Integer qtdNaoVendidos();
	
	@Query(value = "select COUNT(*) as veiculo, "
			+ "           ano / 10 * 10 as decada "
			+ "       from tb_veiculo v "
			+ "     group by  "
			+ "       ano / 10 * 10", nativeQuery = true)
	List<Object[]> listaDistribuicaoPorDecada();
	
	@Query(value = "select COUNT(*) as veiculos, "
			+ "          marca "
			+ "        from tb_veiculo "
			+ "     group by  "
			+ "        marca", nativeQuery = true)
	List<Object[]> listaDistribuicaoPorMarca();
	
	@Query(value = "select v.* "
			+ "      from tb_veiculo v "
			+ "where  "
			+ "      CAST(created as date) between CAST( dateadd(DAY, -7, CURRENT_DATE) as date) and CAST(CURRENT_DATE as date)", nativeQuery = true)
	List<Veiculo> listaRegistradoUltimaSemana();
	
	@Query(value = "select v.* from tb_veiculo v where v.veiculo like ?1", nativeQuery = true)
	List<Veiculo> listaPorVeiculo(String veiculo);

}