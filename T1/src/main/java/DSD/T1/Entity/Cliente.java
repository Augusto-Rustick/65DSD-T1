package DSD.T1.Entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente extends Pessoa {

	@Size(min = 9, message = "O telefone deve ter pelo menos 9 caracteres")
	private String telefone;
	@NotNull
	protected String email;

	public Cliente() {
		super();
	}

	public Cliente(String cpf, String nome, String endereco, String telefone, String email) {
		super(cpf, nome, endereco);
		this.telefone = telefone;
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Cliente [telefone=" + telefone + ", email=" + email + "]";
	}

}
