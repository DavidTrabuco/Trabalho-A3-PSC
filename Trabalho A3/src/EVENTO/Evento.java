package EVENTO;

public class Evento {

    private int id;
    private String nome;
    private String data;
    private Organizador organizador;
    private Local local;
    private String categoria;

    public Evento(int id, String nome, String data, Organizador organizador, Local local, String categoria) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.organizador = organizador;
        this.local = local;
        this.categoria = categoria;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public Organizador getOrganizador() {
        return organizador;
    }
    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }
    public Local getLocal() {
        return local;
    }
    public void setLocal(Local local) {
        this.local = local;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    

}
