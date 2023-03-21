package DSD.T1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import DSD.T1.Entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

	@Query("SELECT f FROM Funcionario f WHERE f.cpf = ?1")
	Funcionario findByCpf(String cpf);
}
