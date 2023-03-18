package DSD.T1.Resource;

import org.springframework.web.bind.annotation.RestController;
import DSD.T1.Entity.Departamento;
import DSD.T1.Repository.DepartamentoRepository;

@RestController
public class DepartamentoResource {
	private DepartamentoRepository repo;

	public DepartamentoResource(DepartamentoRepository repo) {
		this.repo = repo;
	}
}
