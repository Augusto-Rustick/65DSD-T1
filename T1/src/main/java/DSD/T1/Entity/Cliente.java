package DSD.T1.Entity;

public class Cliente extends Pessoa {
	private String telefone, email;

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
