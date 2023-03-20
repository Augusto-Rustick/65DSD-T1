package DSD.T1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import DSD.T1.Entity.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Query("SELECT c FROM Cliente c WHERE c.cpf = ?1")
    Cliente findByCpf(String cpf);
}
