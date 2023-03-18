package DSD.T1.Resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import DSD.T1.Entity.Departamento;
import DSD.T1.Repository.DepartamentoRepository;
import jakarta.validation.Valid;

@RestController
public class DepartamentoResource {
	private DepartamentoRepository repo;

	public DepartamentoResource(DepartamentoRepository repo) {
		this.repo = repo;
	}
	

	@PostMapping("/departamento/save")
	public ResponseEntity<Departamento> createDepartamento(@Valid @RequestBody Departamento departamento) {
		Departamento savedDepartamento = repo.save(departamento);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id)")
				.buildAndExpand(savedDepartamento.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/departamento/getAll")
	public List<Departamento> allDepartamentos() {
		return repo.findAll();
	}

	@GetMapping("/departamento/getById/{id}")
	public Departamento getDepartamento(@PathVariable int id) throws Exception{
		Optional<Departamento> departamento = repo.findById(id);
		if(departamento.isEmpty()) {
			throw new Exception("erro no id: " + id);
		}
		return departamento.get();
	}
	
	@DeleteMapping("/departamento/remove/{id}")
	public void deleteDepartamento(@PathVariable int id) {
		repo.deleteById(id);

	}
}
