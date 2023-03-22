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
import DSD.T1.Entity.Transportador;
import DSD.T1.Repository.TransportadorRepository;
import jakarta.validation.Valid;

@RestController
public class TransportadorResource {
	private TransportadorRepository repo;

	public TransportadorResource(TransportadorRepository repo) {
		this.repo = repo;
	}

	@PostMapping("/transportador/insertOrUpdate")
	public ResponseEntity<Transportador> createTransportador(@Valid @RequestBody Transportador transportador) {
		Transportador savedTransportador= repo.save(transportador);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id)")
				.buildAndExpand(savedTransportador.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/transportador/list")
	public List<Transportador> allTransportador() {
		return repo.findAll();
	}

	@GetMapping("/transportador/get/{cpf}")
	public Transportador getTransportador(@PathVariable String cpf) throws Exception {
		Optional<Transportador> transportador = Optional.ofNullable(repo.findByCpf(cpf));
		if (transportador.isEmpty()) {
			throw new Exception("erro no cpf: " + cpf);
		}
		return transportador.get();
	}

	@DeleteMapping("/transportador/delete/{id}")
	public void deleteTransportador(@PathVariable int id) {
		repo.deleteById(id);
	}

}
