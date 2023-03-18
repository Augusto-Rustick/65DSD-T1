package DSD.T1.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Departamento {
	
	@Id
	@GeneratedValue
	private Integer id;
	@Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres")
	private String nome;
	@Size(min = 3, message = "O produto deve ter pelo menos 3 caracteres")
	protected String produto;
	@NotNull
	private int quantidadeEstoque;
	
	public Departamento(String nome, String produto, int quantidadeEstoque) {
		super();
		this.nome = nome;
		this.produto = produto;
		this.quantidadeEstoque = quantidadeEstoque;
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

	@Override
	public String toString() {
		return "Departamento [nome=" + nome + ", produto=" + produto + ", quantidadeEstoque=" + quantidadeEstoque + "]";
	}

}
