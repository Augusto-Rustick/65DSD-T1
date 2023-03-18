package DSD.T1.Resource;

import org.springframework.web.bind.annotation.RestController;
import DSD.T1.Repository.ClienteRepository;

@RestController
public class ClienteResource {
	private ClienteRepository repo;

	public ClienteResource(ClienteRepository repo) {
		this.repo = repo;
	}
}
