package EVENTO.Entidades;

public class Organizador {

    private String nome;
    private String contato;


    public Organizador(String nome, String contato) {
        this.nome = nome;
        this.contato = contato;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getContato() {
        return contato;
    }
    public void setContato(String contato) {
        this.contato = contato;
    }
    @Override 
    public String toString() {
        return "Organizador [nome=" + nome + ", contato=" + contato + "]";
    }
}
