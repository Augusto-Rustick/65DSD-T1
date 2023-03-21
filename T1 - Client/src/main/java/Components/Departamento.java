package Components;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Departamento {

    @JsonProperty("produto")
    private final String produto;
    @JsonProperty("nome")
    private final String nome;
    @JsonProperty("quantidadeEstoque")
    private final int quantidadeEstoque;
    @JsonProperty("id")
    private final int id;

    public Departamento() {
        this.id = 0;
        this.produto = null;
        this.nome = null;
        this.quantidadeEstoque = 0;
    }

    public Departamento(int id, String produto, String nome, int quantidadeEstoque) {
        this.id = id;
        this.produto = produto;
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
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
