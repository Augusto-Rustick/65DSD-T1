package DSD.T1.Resource;

import org.springframework.web.bind.annotation.RestController;
import DSD.T1.Repository.FuncionarioRepository;

@RestController
public class FuncionarioResource {
	private FuncionarioRepository repo;

	public FuncionarioResource(FuncionarioRepository repo) {
		this.repo = repo;
	}
}
