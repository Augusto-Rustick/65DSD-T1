package DSD.T1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import DSD.T1.Entity.Pessoa;

public interface PessoaRepository  extends JpaRepository<Pessoa, Integer> {

	@Query("SELECT p FROM Pessoa p WHERE p.departamento = ?1")
	List<Pessoa> findAllPessoaByDepartamantoId(Integer departamentoId);
}
