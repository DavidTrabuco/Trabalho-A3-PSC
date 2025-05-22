package EVENTO;

public class EventoPresencial extends Evento {

    private int capacidadeMaxima;

    public EventoPresencial(int id, String nome, String data, Organizador organizador, Local local, String Categoria, int capacidadeMaxima) {
        super(capacidadeMaxima, nome, data, organizador, local, Categoria);
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }
    @Override
    public String toString() {
        return "EventoPresencial [capacidadeMaxima=" + capacidadeMaxima + ", " + super.toString() + "]";
    }

}
