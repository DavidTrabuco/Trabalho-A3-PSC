package EVENTO.Entidades;

public class Local {

    private String nome;
    private String endereco;
     
    public Local(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getEndereco(){
        return endereco;
    }
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    @Override
    public String toString() {
        return "Local [nome=" + nome + ", endereco=" + endereco + "]";
    }
    
}
