package EVENTO;

public class Organizador {

    private String nome;
    private String contato;


    public Organizador(String nome, String contato) {
        this.nome = nome;
        this.contato = contato;
    }

    public String getnome() {
        return nome;
    }
    public void setnome(String nome) {
        this.nome = nome;
    }
    public String getcontato() {
        return contato;
    }
    public void setcontato(String contato) {
        this.contato = contato;
    }
    @Override 
    public String toString() {
        return "Organizador [nome=" + nome + ", contato=" + contato + "]";
    }
}
