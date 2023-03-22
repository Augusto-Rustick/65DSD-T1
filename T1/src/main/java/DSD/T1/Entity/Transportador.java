package DSD.T1.Entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Transportador extends Pessoa {

	@Size(min = 9, message = "O telefone deve ter pelo menos 9 caracteres")
	private String telefone;
	@NotNull
	protected int carregamento;

	public Transportador() {
		super();
	}

	public Transportador(String cpf, String nome, String endereco, String telefone, int carregamento) {
		super(cpf, nome, endereco);
		this.telefone = telefone;
		this.carregamento = carregamento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getCarregamento() {
		return carregamento;
	}

	public void setCarregamento(int carregamento) {
		this.carregamento = carregamento;
	}

	@Override
	public String toString() {
		return "Cliente [telefone=" + telefone + ", email=" + carregamento + "]";
	}

}
