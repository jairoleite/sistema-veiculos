package br.com.sistemaveiculos.resources;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemaveiculos.dtos.VeiculoDTO;
import br.com.sistemaveiculos.services.VeiculoService;

@RestController
@RequestMapping("veiculos")
public class VeiculoResource {

	@Autowired
	private VeiculoService service;

	@GetMapping
	public ResponseEntity<List<VeiculoDTO>> listaVeiculos() {
		return ResponseEntity.ok(service.listarTodos());
	}

	@GetMapping("/find")
	public ResponseEntity<List<VeiculoDTO>> buscaPorNome(@RequestParam(name = "q") String veiculo) {
		return ResponseEntity.ok(service.listaPorVeiculo(veiculo));
	}

	@GetMapping("/{id}")
	public ResponseEntity<VeiculoDTO> pegaPorId(@PathVariable Long id) {
		return ResponseEntity.ok(service.pegaPorId(id));
	}
	
	@PostMapping
	public ResponseEntity<VeiculoDTO> salva(@RequestBody VeiculoDTO dto) {
		dto = service.salvar(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> altera(@PathVariable Long id, @RequestBody VeiculoDTO dto) {
		service.alterar(id, dto);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Void> altera(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
		service.alterarParcial(id, campos);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleta(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
