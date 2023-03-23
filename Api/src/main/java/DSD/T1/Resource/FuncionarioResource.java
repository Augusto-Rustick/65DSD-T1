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
import DSD.T1.Entity.Funcionario;
import DSD.T1.Repository.FuncionarioRepository;
import jakarta.validation.Valid;

@RestController
public class FuncionarioResource {
	private FuncionarioRepository repo;

	public FuncionarioResource(FuncionarioRepository repo) {
		this.repo = repo;
	}

	@PostMapping("/funcionario/insertOrUpdate")
	public ResponseEntity<Funcionario> createCliente(@Valid @RequestBody Funcionario funcionario) {
		Funcionario savedFuncionario = repo.save(funcionario);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id)")
				.buildAndExpand(savedFuncionario.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/funcionario/list")
	public List<Funcionario> allFuncionarios() {
		return repo.findAll();
	}

	@GetMapping("/funcionario/get/{cpf}")
	public Funcionario getFuncionario(@PathVariable String cpf) throws Exception {
		Optional<Funcionario> funcionario = Optional.ofNullable(repo.findByCpf(cpf));
		if (funcionario.isEmpty()) {
			throw new Exception("erro no cpf : " + cpf);
		}
		return funcionario.get();
	}

	@DeleteMapping("/funcionario/delete/{id}")
	public void deleteFuncionario(@PathVariable int id) {
		repo.deleteById(id);
	}
}
