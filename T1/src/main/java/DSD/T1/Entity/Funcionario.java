package DSD.T1.Entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class Funcionario extends Pessoa {

	@NotNull
	private String ctps;
	@NotNull
	private int quantidadeVendas;

	public Funcionario() {
		super();
	}

	public Funcionario(String cpf, String nome, String endereco, String ctps, int quantidadeVendas) {
		super(cpf, nome, endereco);
		this.ctps = ctps;
		this.quantidadeVendas = quantidadeVendas;
	}

	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}

	public int getQuantidadeVendas() {
		return quantidadeVendas;
	}

	public void setQuantidadeVendas(int quantidadeVendas) {
		this.quantidadeVendas = quantidadeVendas;
	}

	@Override
	public String toString() {
		return "Funcionario [ctps=" + ctps + ", quantidadeVendas=" + quantidadeVendas + "]";
	}

}
