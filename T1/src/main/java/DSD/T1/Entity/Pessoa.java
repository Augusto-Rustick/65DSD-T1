package DSD.T1.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public abstract class Pessoa {

	@Id
	@GeneratedValue
	private Integer id;
	@Size(min = 11, message = "O telefone deve ter pelo menos 11 caracteres")
	protected String cpf;
	@Size(min = 3, message = "O telefone deve ter pelo menos 3 caracteres")
	protected String nome;
	@Size(min = 10, message = "O telefone deve ter pelo menos 10 caracteres")
	protected String endereco;

	public Pessoa() {

	}

	public Pessoa(String cpf, String nome, String endereco) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Pessoa [cpf=" + cpf + ", nome=" + nome + ", endereco=" + endereco + "]";
	}
}
