package DSD.T1.Resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import DSD.T1.Entity.Cliente;
import DSD.T1.Repository.ClienteRepository;
import jakarta.validation.Valid;

@RestController
public class ClienteResource {
	private ClienteRepository repo;

	public ClienteResource(ClienteRepository repo) {
		this.repo = repo;
	}

	@PostMapping("/cliente/insert")
	public ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente) {
		Cliente savedCliente = repo.save(cliente);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id)")
				.buildAndExpand(savedCliente.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/cliente/list")
	public List<Cliente> allCliente() {
		return repo.findAll();
	}

	@GetMapping("/cliente/get/{cpf}")
	public Cliente getCliente(@PathVariable String cpf) throws Exception {
		Optional<Cliente> cliente = Optional.ofNullable(repo.findByCpf(cpf));
		if (cliente.isEmpty()) {
			throw new Exception("erro no cpf: " + cpf);
		}
		return cliente.get();
	}

	@DeleteMapping("/cliente/remove/{id}")
	public void deleteCliente(@PathVariable int id) {
		repo.deleteById(id);
	}
	

}
