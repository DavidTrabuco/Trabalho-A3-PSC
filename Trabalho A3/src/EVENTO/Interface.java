package EVENTO;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Interface extends JFrame {
    private GerenciadorEventos gerenciador;
    private JTable tabelaEventos;
    private EventoTableModel tableModel;
    private JTextField txtNome, txtLocalNome, txtLocalEndereco, txtOrganizadorNome, txtOrganizadorContato, txtCategoria, txtCapacidade, txtLink, txtPlataforma;
    private JFormattedTextField txtData;
    private JComboBox<String> cbTipoEvento;
    private JButton btnAdicionar, btnEditar, btnExcluir, btnSalvar;
    private int editIndex = -1;

    public Interface() {
        gerenciador = new GerenciadorEventos();
        setTitle("Gerenciamento de Eventos Culturais");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painel = new JPanel(new BorderLayout());
        setContentPane(painel);

        // Tabela de eventos
        tableModel = new EventoTableModel(gerenciador);
        tabelaEventos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaEventos);
        painel.add(scrollPane, BorderLayout.CENTER);

        // Painel de formulário
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos do formulário
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelFormulario.add(new JLabel("Tipo de Evento:"), gbc);
        cbTipoEvento = new JComboBox<>(new String[]{"Presencial", "Virtual"});
        gbc.gridx = 1;
        painelFormulario.add(cbTipoEvento, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelFormulario.add(new JLabel("Nome do Evento:"), gbc);
        txtNome = new JTextField(20);
        gbc.gridx = 1;
        painelFormulario.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        painelFormulario.add(new JLabel("Data (dd/MM/yyyy):"), gbc);
        txtData = new JFormattedTextField(DateTimeFormatter.ofPattern("dd/MM/yyyy").toFormat());
        txtData.setColumns(20);
        gbc.gridx = 1;
        painelFormulario.add(txtData, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        painelFormulario.add(new JLabel("Nome do Local:"), gbc);
        txtLocalNome = new JTextField(20);
        gbc.gridx = 1;
        painelFormulario.add(txtLocalNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        painelFormulario.add(new JLabel("Endereço do Local:"), gbc);
        txtLocalEndereco = new JTextField(20);
        gbc.gridx = 1;
        painelFormulario.add(txtLocalEndereco, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        painelFormulario.add(new JLabel("Nome do Organizador:"), gbc);
        txtOrganizadorNome = new JTextField(20);
        gbc.gridx = 1;
        painelFormulario.add(txtOrganizadorNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        painelFormulario.add(new JLabel("Contato do Organizador:"), gbc);
        txtOrganizadorContato = new JTextField(20);
        gbc.gridx = 1;
        painelFormulario.add(txtOrganizadorContato, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        painelFormulario.add(new JLabel("Categoria:"), gbc);
        txtCategoria = new JTextField(20);
        gbc.gridx = 1;
        painelFormulario.add(txtCategoria, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        painelFormulario.add(new JLabel("Capacidade Máxima (Presencial):"), gbc);
        txtCapacidade = new JTextField(20);
        gbc.gridx = 1;
        painelFormulario.add(txtCapacidade, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        painelFormulario.add(new JLabel("Link de Acesso (Virtual):"), gbc);
        txtLink = new JTextField(20);
        gbc.gridx = 1;
        painelFormulario.add(txtLink, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        painelFormulario.add(new JLabel("Plataforma (Virtual):"), gbc);
        txtPlataforma = new JTextField(20);
        gbc.gridx = 1;
        painelFormulario.add(txtPlataforma, gbc);

        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");
        btnSalvar = new JButton("Salvar");
        btnSalvar.setEnabled(false);

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnSalvar);

        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        painelFormulario.add(painelBotoes, gbc);

        painel.add(painelFormulario, BorderLayout.SOUTH);

        // Ações dos botões
        btnAdicionar.addActionListener(e -> adicionarEvento());
        btnEditar.addActionListener(e -> editarEvento());
        btnExcluir.addActionListener(e -> excluirEvento());
        btnSalvar.addActionListener(e -> salvarEvento());

        // Habilitar/desabilitar botões com base na seleção
        tabelaEventos.getSelectionModel().addListSelectionListener(e -> {
            boolean selecionado = tabelaEventos.getSelectedRow() >= 0;
            btnEditar.setEnabled(selecionado);
            btnExcluir.setEnabled(selecionado);
        });
    }

    private void adicionarEvento() {
        if (!validarCampos()) return;

        Organizador organizador = new Organizador(txtOrganizadorNome.getText(), txtOrganizadorContato.getText());
        Local local = new Local(txtLocalNome.getText(), txtLocalEndereco.getText());
        String tipoEvento = (String) cbTipoEvento.getSelectedItem();

        if (tipoEvento.equals("Presencial")) {
            try {
                int capacidade = Integer.parseInt(txtCapacidade.getText());
                if (capacidade <= 0) {
                    JOptionPane.showMessageDialog(this, "Capacidade máxima deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                gerenciador.incluirEventoPresencial(txtNome.getText(), txtData.getText(), organizador, local, txtCategoria.getText(), capacidade);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Capacidade deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            gerenciador.incluirEventoVirtual(txtNome.getText(), txtData.getText(), organizador, local, txtCategoria.getText(), txtLink.getText(), txtPlataforma.getText());
        }

        if (!validarData(txtData.getText())) {
            JOptionPane.showMessageDialog(this, "A data do evento não pode ser no passado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.fireTableDataChanged();
        limparFormulario();
    }

    private void editarEvento() {
        int selectedRow = tabelaEventos.getSelectedRow();
        if (selectedRow >= 0) {
            editIndex = selectedRow;
            Evento evento = gerenciador.consultarEvento(selectedRow + 1); // IDs começam em 1
            if (evento != null) {
                txtNome.setText(evento.getNome());
                txtData.setText(evento.getData());
                txtLocalNome.setText(evento.getLocal().getNome());
                txtLocalEndereco.setText(evento.getLocal().getEndereco());
                txtOrganizadorNome.setText(evento.getOrganizador().getNome());
                txtOrganizadorContato.setText(evento.getOrganizador().getContato());
                txtCategoria.setText(evento.getCategoria());

                if (evento instanceof EventoPresencial) {
                    cbTipoEvento.setSelectedItem("Presencial");
                    txtCapacidade.setText(String.valueOf(((EventoPresencial) evento).getCapacidadeMaxima()));
                    txtLink.setText("");
                    txtPlataforma.setText("");
                } else if (evento instanceof EventoVirtual) {
                    cbTipoEvento.setSelectedItem("Virtual");
                    txtCapacidade.setText("");
                    txtLink.setText(((EventoVirtual) evento).getLinkAcesso());
                    txtPlataforma.setText(((EventoVirtual) evento).getPlataforma());
                }

                btnAdicionar.setEnabled(false);
                btnEditar.setEnabled(false);
                btnExcluir.setEnabled(false);
                btnSalvar.setEnabled(true);
            }
        }
    }

    private void salvarEvento() {
        if (!validarCampos()) return;

        Organizador organizador = new Organizador(txtOrganizadorNome.getText(), txtOrganizadorContato.getText());
        Local local = new Local(txtLocalNome.getText(), txtLocalEndereco.getText());
        gerenciador.alterarEvento(editIndex + 1, txtNome.getText(), txtData.getText(), organizador, local, txtCategoria.getText());

        if (!validarData(txtData.getText())) {
            JOptionPane.showMessageDialog(this, "A data do evento não pode ser no passado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.fireTableDataChanged();
        limparFormulario();
        btnAdicionar.setEnabled(true);
        btnEditar.setEnabled(true);
        btnExcluir.setEnabled(true);
        btnSalvar.setEnabled(false);
        editIndex = -1;
    }

    private void excluirEvento() {
        int selectedRow = tabelaEventos.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este evento?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                gerenciador.excluirEvento(selectedRow + 1);
                tableModel.fireTableDataChanged();
            }
        }
    }

    private boolean validarCampos() {
        String tipoEvento = (String) cbTipoEvento.getSelectedItem();
        if (txtNome.getText().isEmpty() || txtData.getText().isEmpty() || txtLocalNome.getText().isEmpty() ||
                txtLocalEndereco.getText().isEmpty() || txtOrganizadorNome.getText().isEmpty() ||
                txtOrganizadorContato.getText().isEmpty() || txtCategoria.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tipoEvento.equals("Presencial") && txtCapacidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Capacidade máxima é obrigatória para eventos presenciais!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tipoEvento.equals("Virtual") && (txtLink.getText().isEmpty() || txtPlataforma.getText().isEmpty())) {
            JOptionPane.showMessageDialog(this, "Link de acesso e plataforma são obrigatórios para eventos virtuais!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validarData(String dataStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataEvento = LocalDate.parse(dataStr, formatter);
            LocalDate hoje = LocalDate.now();
            return !dataEvento.isBefore(hoje);
        } catch (Exception e) {
            return false;
        }
    }

    private void limparFormulario() {
        txtNome.setText("");
        txtData.setText("");
        txtLocalNome.setText("");
        txtLocalEndereco.setText("");
        txtOrganizadorNome.setText("");
        txtOrganizadorContato.setText("");
        txtCategoria.setText("");
        txtCapacidade.setText("");
        txtLink.setText("");
        txtPlataforma.setText("");
        cbTipoEvento.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Interface app = new Interface();
            app.setVisible(true);
        });
    }
}

class EventoTableModel extends javax.swing.table.AbstractTableModel {
    private GerenciadorEventos gerenciador;
    private String[] colunas = {"ID", "Nome", "Data", "Local", "Categoria", "Descrição", "Status"};

    public EventoTableModel(GerenciadorEventos gerenciador) {
        this.gerenciador = gerenciador;
    }

    @Override
    public int getRowCount() {
        return gerenciador.listarEventos().size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Evento evento = gerenciador.listarEventos().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return evento.getId();
            case 1:
                return evento.getNome();
            case 2:
                return evento.getData();
            case 3:
                return evento.getLocal().getNome();
            case 4:
                return evento.getCategoria();
            case 5:
                return evento.getDescriçãoEvento();
            case 6:
                return evento.getStatus();
            default:
                return "";
        }
    }

}