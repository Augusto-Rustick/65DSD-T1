package Components;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Produto {

    @JsonProperty("produto")
    private final String produto;
    @JsonProperty("nome")
    private final String nome;
    @JsonProperty("quantidadeEstoque")
    private final int quantidadeEstoque;
    @JsonProperty("id")
    private final int id;

    public Produto() {
        this.id = 0;
        this.produto = null;
        this.nome = null;
        this.quantidadeEstoque = 0;
    }

    public Produto(int id, String produto, String nome, int quantidadeEstoque) {
        this.id = id;
        this.produto = produto;
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getId() {
        return id;
    }

    public String getProduto() {
        return produto;
    }

    @Override
    public String toString() {
        return getProduto();
    }

}
