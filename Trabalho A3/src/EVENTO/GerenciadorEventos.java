package EVENTO;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorEventos {
    private List<Evento> eventos;
    private int proximoId = 1;

    public GerenciadorEventos() {
        eventos = new ArrayList<>();
        proximoId = 1;
    }
    

public  void incluirEvento(Evento evento) {
    eventos.add(evento);
    System.out.println("Evento incluído!");
}



    public void incluirEvento(String nome, String data, Organizador organizador, Local local, String categoria) {
        Evento evento = new Evento(proximoId++, nome, data, organizador, local, categoria);
        eventos.add(evento);
        System.out.println("Evento incluído!");
    }
    public void incluirEventoVirtual(String nome, String data, Organizador organizador, Local local, String categoria,
            String linkAcesso, String plataforma) {
        Evento evento = new EventoVirtual(proximoId++, nome, data, organizador, local, categoria, linkAcesso,
                plataforma);
        eventos.add(evento);
        System.out.println("Evento virtual incluído!");
    }
    public void incluirEventoPresencial(String nome, String data, Organizador organizador, Local local, String categoria,
            int capacidadeMaxima) {
        Evento evento = new EventoPresencial(proximoId++, nome, data, organizador, local, categoria, capacidadeMaxima);
        eventos.add(evento);
        System.out.println("Evento presencial incluído!");
    }

    public Evento consultarEvento(int id) {
        for (Evento evento : eventos) {
            if (evento.getId() == id) {
                return evento;
            }
        }
        return null;
    }

    public boolean alterarEvento(int id, String novoNome, String novaData, Organizador novoOrganizador, Local novoLocal,
            String novaCategoria) {
        Evento evento = consultarEvento(id);
        if (evento != null) {
            evento.setNome(novoNome);
            evento.setData(novaData);
            evento.setOrganizador(novoOrganizador);
            evento.setLocal(novoLocal);
            evento.setCategoria(novaCategoria);
            System.out.println("Evento alterado!");
            return true;
        }
        return false;
    }

    public boolean excluirEvento(int id) {
        Evento evento = consultarEvento(id);
        if (evento != null) {
            eventos.remove(evento);
            System.out.println("Evento excluído!");
            return true;
        }
        return false;
    }

    public void listarEventos() {
        if (eventos.isEmpty()) { // Verifica se a lista de eventos está vazia
            System.out.println("Nenhum evento cadastrado.");
        } else {
            for (Evento evento : eventos) {
                System.out.println(evento);
            }
        }
    }
   


    

}
