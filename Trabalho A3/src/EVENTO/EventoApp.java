package EVENTO;

import java.util.Scanner;

public class EventoApp {

    public static void main(String[] args) {

        GerenciadorEventos gerenciador = new GerenciadorEventos();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("=== Sistema de Gerenciamento de Eventos Culturais ===");
            System.out.println("1. Incluir novo evento");
            System.out.println("2. Consultar um evento");
            System.out.println("3. Alterar dados de um evento");
            System.out.println("4. Excluir um evento");
            System.out.println("5. Imprimir lista de eventos");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("=== Incluir Novo Evento ===");
                    System.out.print("Tipo de evento (1 - Presencial, 2 - Virtual): ");
                    int tipoEvento = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome do evento: ");
                    String nome = scanner.nextLine();
                    System.out.print("Data do evento (DIA/MES/ANO ): ");
                    String data = scanner.nextLine();
                    System.out.print("Nome do organizador: ");
                    String nomeOrganizador = scanner.nextLine();
                    System.out.print("Contato do organizador: ");
                    String contato = scanner.nextLine();
                    System.out.print("Nome do local: ");
                    String nomeLocal = scanner.nextLine();
                    System.out.print("Endereço do local: ");
                    String endereco = scanner.nextLine();
                    System.out.print("Categoria (ex.: Show, Teatro, Exposição): ");
                    String categoria = scanner.nextLine();

                    Organizador organizador = new Organizador(nomeOrganizador, contato);
                    Local local = new Local(nomeLocal, endereco);
                    gerenciador.incluirEvento(nome, data, organizador, local, categoria);

                    if (tipoEvento == 1) {
                        System.out.print("Capacidade máxima: ");
                        int capacidade = scanner.nextInt();
                        scanner.nextLine();
                        gerenciador.incluirEventoPresencial(nome, data, organizador, local, categoria, capacidade);
                    } else if (tipoEvento == 2) {
                        System.out.print("Plataforma (ex.: Zoom, Meet): ");
                        String plataforma = scanner.nextLine();
                        System.out.print("Link de acesso: ");
                        String link = scanner.nextLine();
                        gerenciador.incluirEventoVirtual(nomeLocal, data, organizador, local, categoria, link,
                                plataforma);
                    } else {
                        System.out.println("Tipo de evento inválido.");
                    }
                    break;

                case 2:
                    System.out.print("Digite o ID do evento: ");
                    int idConsulta = scanner.nextInt();
                    Evento evento = gerenciador.consultarEvento(idConsulta);
                    if (evento != null) {
                        System.out.println(evento);
                    } else {
                        System.out.println("Evento não encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID do evento a alterar: ");
                    int idAlterar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo nome do evento: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Nova data do evento (DIA/MES/ANO): ");
                    String novaData = scanner.nextLine();
                    System.out.print("Novo nome do organizador: ");
                    String novoNomeOrganizador = scanner.nextLine();
                    System.out.print("Novo contato do organizador: ");
                    String novoContato = scanner.nextLine();
                    System.out.print("Novo nome do local: ");
                    String novoNomeLocal = scanner.nextLine();
                    System.out.print("Novo endereço do local: ");
                    String novoEndereco = scanner.nextLine();
                    System.out.print("Nova categoria: ");
                    String novaCategoria = scanner.nextLine();

                    Organizador novoOrganizador = new Organizador(novoNomeOrganizador, novoContato);
                    Local novoLocal = new Local(novoNomeLocal, novoEndereco);
                    if (gerenciador.alterarEvento(idAlterar, novoNome, novaData, novoOrganizador, novoLocal,
                            novaCategoria)) {
                        System.out.println("Evento alterado com sucesso!");
                    } else {
                        System.out.println("Evento não encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o ID do evento a excluir: ");
                    int idExcluir = scanner.nextInt();
                    if (gerenciador.excluirEvento(idExcluir)) {
                        System.out.println("Evento excluído com sucesso!");
                    } else {
                        System.out.println("Evento não encontrado.");
                    }
                    break;

                case 5:
                    gerenciador.listarEventos();
                    break;

                case 6:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 6);

        scanner.close();
    }
}
