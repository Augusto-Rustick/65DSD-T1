package DSD.T1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import DSD.T1.Entity.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

	@Query("SELECT d FROM Departamento d WHERE d.nome =:nome")
	Departamento findByNome(String nome);
}
