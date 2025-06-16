package EVENTO.Entidades;

public class EventoPresencial extends Evento {

    private int capacidadeMaxima;
    

    public EventoPresencial(int id, String nome, String data, Organizador organizador, Local local, String Categoria, int capacidadeMaxima) {
        super(id, nome, data, organizador, local, Categoria);
        this.capacidadeMaxima = capacidadeMaxima;
        
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }
    
    @Override
    public String getDescriçãoEvento() {
        return "Evento Presencial: " + getNome() + ", Data: " + getData() + ", Local: " + getLocal().getNome() + ", Capacidade Máxima: " + capacidadeMaxima;

}

  @Override
    public String getStatus() {
        
        return capacidadeMaxima > 0 ? "Disponível" : "Lotação Esgotada";
        

    }
    
}