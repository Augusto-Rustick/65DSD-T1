package Components;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Departamento {

    @JsonProperty("produto")
    private final String produto;
    @JsonProperty("nome")
    private final String nome;
    @JsonProperty("quantidadeEstoque")
    private int quantidadeEstoque;
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

    public String getProduto() {
        return produto;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public String toString() {
        assert getNome() != null;
        assert getProduto() != null;
        String depNome = getNome().substring(0, 1).toUpperCase() + getNome().substring(1).toLowerCase();
        String prodNome =  getProduto().substring(0, 1).toUpperCase() +  getProduto().substring(1).toLowerCase();
        return depNome + " : " + prodNome;
    }

}
