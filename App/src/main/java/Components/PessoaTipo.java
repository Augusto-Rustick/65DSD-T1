package Components;

public enum PessoaTipo {

    FUNCIONARIO("funcionario"),
    TRANSPORTADOR("transportador");

    private String nome;

    public String getNome() {
        return nome;
    }

    PessoaTipo(String nome){
        this.nome = nome;
    }

}
