package DSD.T1.Entity;

public class Departamento {
	private String nome, produto;
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
