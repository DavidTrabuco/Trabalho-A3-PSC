package EVENTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
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
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com fundo branco (Sympla-inspired)
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(Color.WHITE);
        painel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Espaçamento
        setContentPane(painel);

        // Tabela de eventos
        tableModel = new EventoTableModel(gerenciador);
        tabelaEventos = new JTable(tableModel);
        tabelaEventos.setBackground(Color.WHITE);
        tabelaEventos.setRowHeight(30); // Altura das linhas para melhor legibilidade
        tabelaEventos.setShowGrid(false); // Remove linhas de grade
        tabelaEventos.setIntercellSpacing(new Dimension(0, 0)); // Remove espaçamento entre células
        tabelaEventos.setSelectionBackground(new Color(235, 245, 255)); // Azul claro suave
        tabelaEventos.setSelectionForeground(Color.BLACK);
        tabelaEventos.setFont(new Font("Roboto", Font.PLAIN, 14)); // Fonte moderna

        // Estilizar cabeçalho da tabela
        JTableHeader header = tabelaEventos.getTableHeader();
        header.setBackground(new Color(245, 245, 245)); // Cinza claro
        header.setFont(new Font("Roboto", Font.BOLD, 14));
        header.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(tabelaEventos);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        painel.add(scrollPane, BorderLayout.CENTER);

        // Painel de formulário
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(Color.WHITE);
        painelFormulario.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Campos do formulário
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblTipoEvento = new JLabel("Tipo de Evento:");
        lblTipoEvento.setFont(new Font("Roboto", Font.BOLD, 14));
        lblTipoEvento.setForeground(new Color(33, 37, 41)); // Cinza escuro
        painelFormulario.add(lblTipoEvento, gbc);
        cbTipoEvento = new JComboBox<>(new String[]{"Presencial", "Virtual"});
        cbTipoEvento.setFont(new Font("Roboto", Font.PLAIN, 14));
        cbTipoEvento.setBackground(Color.WHITE);
        cbTipoEvento.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        gbc.gridx = 1;
        painelFormulario.add(cbTipoEvento, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblNome = new JLabel("Nome do Evento:");
        lblNome.setFont(new Font("Roboto", Font.BOLD, 14));
        lblNome.setForeground(new Color(33, 37, 41));
        painelFormulario.add(lblNome, gbc);
        txtNome = new JTextField(20);
        txtNome.setFont(new Font("Roboto", Font.PLAIN, 14));
        txtNome.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        gbc.gridx = 1;
        painelFormulario.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblData = new JLabel("Data (dd/MM/yyyy):");
        lblData.setFont(new Font("Roboto", Font.BOLD, 14));
        lblData.setForeground(new Color(33, 37, 41));
        painelFormulario.add(lblData, gbc);
        txtData = new JFormattedTextField(DateTimeFormatter.ofPattern("dd/MM/yyyy").toFormat());
        txtData.setColumns(20);
        txtData.setFont(new Font("Roboto", Font.PLAIN, 14));
        txtData.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        gbc.gridx = 1;
        painelFormulario.add(txtData, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblLocalNome = new JLabel("Nome do Local:");
        lblLocalNome.setFont(new Font("Roboto", Font.BOLD, 14));
        lblLocalNome.setForeground(new Color(33, 37, 41));
        painelFormulario.add(lblLocalNome, gbc);
        txtLocalNome = new JTextField(20);
        txtLocalNome.setFont(new Font("Roboto", Font.PLAIN, 14));
        txtLocalNome.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        gbc.gridx = 1;
        painelFormulario.add(txtLocalNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel lblLocalEndereco = new JLabel("Endereço do Local:");
        lblLocalEndereco.setFont(new Font("Roboto", Font.BOLD, 14));
        lblLocalEndereco.setForeground(new Color(33, 37, 41));
        painelFormulario.add(lblLocalEndereco, gbc);
        txtLocalEndereco = new JTextField(20);
        txtLocalEndereco.setFont(new Font("Roboto", Font.PLAIN, 14));
        txtLocalEndereco.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        gbc.gridx = 1;
        painelFormulario.add(txtLocalEndereco, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel lblOrganizadorNome = new JLabel("Nome do Organizador:");
        lblOrganizadorNome.setFont(new Font("Roboto", Font.BOLD, 14));
        lblOrganizadorNome.setForeground(new Color(33, 37, 41));
        painelFormulario.add(lblOrganizadorNome, gbc);
        txtOrganizadorNome = new JTextField(20);
        txtOrganizadorNome.setFont(new Font("Roboto", Font.PLAIN, 14));
        txtOrganizadorNome.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        gbc.gridx = 1;
        painelFormulario.add(txtOrganizadorNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel lblOrganizadorContato = new JLabel("Contato do Organizador:");
        lblOrganizadorContato.setFont(new Font("Roboto", Font.BOLD, 14));
        lblOrganizadorContato.setForeground(new Color(33, 37, 41));
        painelFormulario.add(lblOrganizadorContato, gbc);
        txtOrganizadorContato = new JTextField(20);
        txtOrganizadorContato.setFont(new Font("Roboto", Font.PLAIN, 14));
        txtOrganizadorContato.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        gbc.gridx = 1;
        painelFormulario.add(txtOrganizadorContato, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setFont(new Font("Roboto", Font.BOLD, 14));
        lblCategoria.setForeground(new Color(33, 37, 41));
        painelFormulario.add(lblCategoria, gbc);
        txtCategoria = new JTextField(20);
        txtCategoria.setFont(new Font("Roboto", Font.PLAIN, 14));
        txtCategoria.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        gbc.gridx = 1;
        painelFormulario.add(txtCategoria, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        JLabel lblCapacidade = new JLabel("Capacidade Máxima (Presencial):");
        lblCapacidade.setFont(new Font("Roboto", Font.BOLD, 14));
        lblCapacidade.setForeground(new Color(33, 37, 41));
        painelFormulario.add(lblCapacidade, gbc);
        txtCapacidade = new JTextField(20);
        txtCapacidade.setFont(new Font("Roboto", Font.PLAIN, 14));
        txtCapacidade.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        gbc.gridx = 1;
        painelFormulario.add(txtCapacidade, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        JLabel lblLink = new JLabel("Link de Acesso (Virtual):");
        lblLink.setFont(new Font("Roboto", Font.BOLD, 14));
        lblLink.setForeground(new Color(33, 37, 41));
        painelFormulario.add(lblLink, gbc);
        txtLink = new JTextField(20);
        txtLink.setFont(new Font("Roboto", Font.PLAIN, 14));
        txtLink.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        txtLink.setEnabled(false);
        gbc.gridx = 1;
        painelFormulario.add(txtLink, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        JLabel lblPlataforma = new JLabel("Plataforma (Virtual):");
        lblPlataforma.setFont(new Font("Roboto", Font.BOLD, 14));
        lblPlataforma.setForeground(new Color(33, 37, 41));
        painelFormulario.add(lblPlataforma, gbc);
        txtPlataforma = new JTextField(20);
        txtPlataforma.setFont(new Font("Roboto", Font.PLAIN, 14));
        txtPlataforma.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        txtPlataforma.setEnabled(false);
        gbc.gridx = 1;
        painelFormulario.add(txtPlataforma, gbc);

        // Listener para o tipo de evento
        cbTipoEvento.addActionListener(e -> {
            boolean isPresencial = cbTipoEvento.getSelectedItem().equals("Presencial");
            txtLink.setEnabled(!isPresencial);
            txtPlataforma.setEnabled(!isPresencial);
            txtCapacidade.setEnabled(isPresencial);
            if (isPresencial) {
                txtLink.setText("");
                txtPlataforma.setText("");
            } else {
                txtCapacidade.setText("");
            }
        });

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painelBotoes.setBackground(Color.WHITE);
        btnAdicionar = createStyledButton("Adicionar", new Color(0, 153, 102)); // Verde Sympla
        btnEditar = createStyledButton("Editar", new Color(88, 86, 214)); // Roxo Sympla
        btnExcluir = createStyledButton("Excluir", new Color(220, 53, 69)); // Vermelho
        btnSalvar = createStyledButton("Salvar", new Color(255, 193, 7)); // Amarelo
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

        // Inicializar estado dos campos
        txtLink.setEnabled(false);
        txtPlataforma.setEnabled(false);
        txtCapacidade.setEnabled(true);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Roboto", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setOpaque(true);
        // Efeito de hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        return button;
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
            Evento evento = gerenciador.consultarEvento(selectedRow + 1);
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
                    txtLink.setEnabled(false);
                    txtPlataforma.setEnabled(false);
                    txtCapacidade.setEnabled(true);
                } else if (evento instanceof EventoVirtual) {
                    cbTipoEvento.setSelectedItem("Virtual");
                    txtCapacidade.setText("");
                    txtLink.setText(((EventoVirtual) evento).getLinkAcesso());
                    txtPlataforma.setText(((EventoVirtual) evento).getPlataforma());
                    txtLink.setEnabled(true);
                    txtPlataforma.setEnabled(true);
                    txtCapacidade.setEnabled(false);
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
        txtLink.setEnabled(false);
        txtPlataforma.setEnabled(false);
        txtCapacidade.setEnabled(true);
    }

    public static void main(String[] args) {
        try {
            // Tentar usar o look and feel do sistema para uma aparência mais moderna
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            Interface app = new Interface();
            app.setVisible(true);
        });
    }
}

class EventoTableModel extends javax.swing.table.AbstractTableModel {
    private GerenciadorEventos gerenciador;
    private String[] colunas = {"ID", "Nome", "Data", "Local", "Categoria", "Status"};

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
                return evento.getStatus();
            default:
                return "";
        }
    }
}