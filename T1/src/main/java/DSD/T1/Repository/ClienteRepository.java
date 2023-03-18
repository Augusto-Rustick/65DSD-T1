package DSD.T1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import DSD.T1.Entity.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
