package DSD.T1.Entity;

import java.util.Locale.Category;

import org.springframework.context.annotation.ImportResource;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public abstract class Pessoa {

	@Id
	@GeneratedValue
	private Integer id;
	@Size(min = 11, message = "O telefone deve ter pelo menos 11 caracteres")
	@Column(unique = true)
	protected String cpf;
	@Size(min = 3, message = "O telefone deve ter pelo menos 3 caracteres")
	protected String nome;
	@Size(min = 5, message = "O telefone deve ter pelo menos 5 caracteres")
	protected String endereco;
	@NotNull
    private String departamento;

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

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Pessoa [cpf=" + cpf + ", nome=" + nome + ", endereco=" + endereco + "]";
	}
}
