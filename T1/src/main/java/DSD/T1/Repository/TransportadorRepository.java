package DSD.T1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import DSD.T1.Entity.Transportador;

public interface TransportadorRepository extends JpaRepository<Transportador, Integer>{

	@Query("SELECT t FROM Transportador t WHERE t.cpf = ?1")
	Transportador findByCpf(String cpf);
}
