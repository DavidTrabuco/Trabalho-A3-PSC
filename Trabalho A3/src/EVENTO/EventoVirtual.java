package EVENTO;

public class EventoVirtual extends  Evento {
    private String linkAcesso;
    private String plataforma;

    public EventoVirtual(int id,String nome, String data, Organizador organizador, Local local, String categoria, String linkAcesso, String plataforma) {
        super(id, nome, data, organizador, local, categoria);
        this.linkAcesso = linkAcesso;
        this.plataforma = plataforma;
    }

    public String getLinkAcesso() {
        return linkAcesso;
    }

    public void setLinkAcesso(String linkAcesso) {
        this.linkAcesso = linkAcesso;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
    @Override
    public String toString() {
        return "EventoVirtual [linkAcesso=" + linkAcesso + ", plataforma=" + plataforma + ", " + super.toString() + "]";
    }
}
