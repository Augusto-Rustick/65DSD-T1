package DSD.T1.Entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "departamento")
public class Departamento {

	@Id
	@GeneratedValue
	private Integer id;
	@Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres")
	@Column(unique = true)
	private String nome;
	@Size(min = 3, message = "O produto deve ter pelo menos 3 caracteres")
	protected String produto;
	@NotNull
	private int quantidadeEstoque;
	
    private List<Integer> pessoas;

	public Departamento() {
	}

	public Departamento(String nome, String produto, int quantidadeEstoque) {
		this.nome = nome;
		this.produto = produto;
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	public List<Integer> getPessoas() {
		return pessoas;
	}

	public void addPessoas(Integer pessoaId) {
		this.pessoas.add(pessoaId);
	}

	@Override
	public String toString() {
		return "Departamento [nome=" + nome + ", produto=" + produto + ", quantidadeEstoque=" + quantidadeEstoque + "]";
	}

}
