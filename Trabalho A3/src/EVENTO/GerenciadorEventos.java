package EVENTO;

import java.util.ArrayList;
import java.util.List;

import EVENTO.Organizador;

public class GerenciadorEventos {
    private List<Evento> eventos;
    private int proximoId;

    public GerenciadorEventos() {
        eventos = new ArrayList<>();
        proximoId = 1; // Inicializa sem incremento
    }

    public void incluirEvento(Evento evento) {
        eventos.add(evento);
        System.out.println("Evento incluído com sucesso!");
        proximoId++;
    }

    public void incluirEvento(String nome, String data, Organizador organizador, Local local, String categoria) {
        Evento evento = new EventoPresencial(proximoId, nome, data, organizador, local, categoria, 100); // Exemplo de capacidade
        eventos.add(evento);
        System.out.println("Evento incluído!");
        proximoId++;
    }

    public void incluirEventoVirtual(String nome, String data, Organizador organizador, Local local, String categoria,
            String linkAcesso, String plataforma) {
        Evento evento = new EventoVirtual(proximoId, nome, data, organizador, local, categoria, linkAcesso, plataforma);
        eventos.add(evento);
        System.out.println("Evento virtual incluído!");
        proximoId++;
    }

    public void incluirEventoPresencial(String nome, String data, Organizador organizador, Local local, String categoria,
            int capacidadeMaxima) {
        if (capacidadeMaxima <= 0) {
            System.out.println("Capacidade máxima deve ser maior que zero.");
            return;
        }
        Evento evento = new EventoPresencial(proximoId, nome, data, organizador, local, categoria, capacidadeMaxima);
        eventos.add(evento);
        System.out.println("Evento presencial incluído!");
        proximoId++;
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

    public List<Evento> listarEventos() {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
        }
        return eventos;
    }
}
