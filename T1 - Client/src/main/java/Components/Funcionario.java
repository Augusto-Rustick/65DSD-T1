package Components;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Funcionario {

    @JsonProperty("nome")
    private final String nome;
    @JsonProperty("id")
    private final int id;
    @JsonProperty("quantidadeVendas")
    private final int quantidadeVendas;
    @JsonProperty("endereco")
    private final String endereco;
    @JsonProperty("ctps")
    private final String ctps;
    @JsonProperty("cpf")
    private final String cpf;


    public Funcionario() {
        this.cpf = null;
        this.ctps = null;
        this.endereco = null;
        this.quantidadeVendas = 0;
        this.id = 0;
        this.nome = null;
    }

    public Funcionario(int id, String nome, int quantidadeVendas, String endereco, String ctps, String cpf) {
        this.id = id;
        this.nome = nome;
        this.quantidadeVendas = quantidadeVendas;
        this.endereco = endereco;
        this.ctps = ctps;
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return getNome();
    }

}
