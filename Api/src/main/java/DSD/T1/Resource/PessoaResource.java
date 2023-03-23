package DSD.T1.Resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import DSD.T1.Entity.Pessoa;
import DSD.T1.Repository.PessoaRepository;
import jakarta.persistence.Entity;

@RestController
public class PessoaResource {
	private PessoaRepository repo;

	public PessoaResource(PessoaRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping("/pessoa/list/{departamentoId}")
	public List<Pessoa> allPessoas(@PathVariable Integer departamentoId) {
		return repo.findAllPessoaByDepartamantoId(departamentoId);
	}
}
