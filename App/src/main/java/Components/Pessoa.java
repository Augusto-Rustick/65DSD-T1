package Components;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Pessoa {

    @JsonProperty("nome")
    private final String nome;
    @JsonProperty("id")
    private final int id;
    @JsonProperty("quantidadeVendas")
    private int quantidadeVendas;
    @JsonProperty("carregamento")
    private int carregamento;
    @JsonProperty("endereco")
    private final String endereco;
    @JsonProperty("ctps")
    private final String ctps;
    @JsonProperty("telefone")
    private String telefone;
    @JsonProperty("cpf")
    private final String cpf;
    @JsonProperty("departamento")
    private final String departamento;


    public Pessoa() {
        this.cpf = null;
        this.ctps = null;
        this.endereco = null;
        this.quantidadeVendas = 0;
        this.id = 0;
        this.nome = null;
        this.departamento = null;
        this.carregamento = 0;
        this.telefone = null;
    }

    public Pessoa(int id, String nome, int quantidadeVendas, String endereco, String ctps, String cpf, String departamento, int carregamento, String telefone) {
        this.id = id;
        this.nome = nome;
        this.quantidadeVendas = quantidadeVendas;
        this.endereco = endereco;
        this.ctps = ctps;
        this.cpf = cpf;
        this.departamento = departamento;
        this.carregamento = carregamento;
        this.telefone = telefone;
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

    public String getDepartamento() {
        return departamento;
    }

    public int getQuantidadeVendas() {
        return quantidadeVendas;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCtps() {
        return ctps;
    }

    public String getCpf() {
        return cpf;
    }

    public void setQuantidadeVendas(int quantidadeVendas) {
        this.quantidadeVendas = quantidadeVendas;
    }

    public int getCarregamento() {
        return carregamento;
    }

    public void setCarregamento(int carregamento) {
        this.carregamento = carregamento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
