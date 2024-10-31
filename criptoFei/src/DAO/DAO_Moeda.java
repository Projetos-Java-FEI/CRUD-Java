package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Moeda;

public class DAO_Moeda {
    private Connection conn;

    public DAO_Moeda(Connection conn) {
        this.conn = conn;
    }
    
    public void adicionarMoeda() {
        // Painel para entrada de dados da nova moeda
        JPanel panel = new JPanel();
        JTextField simboloField = new JTextField(10);  // Campo para o símbolo da moeda
        JTextField nomeField = new JTextField(20);     // Campo para o nome da moeda
        JTextField cotacaoField = new JTextField(10);  // Campo para a cotação da moeda
        JTextField taxField = new JTextField(5);       // Campo para a taxa da moeda
        
        panel.add(new JLabel("Símbolo:"));
        panel.add(simboloField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(new JLabel("Cotação:"));
        panel.add(cotacaoField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(new JLabel("Taxa:"));
        panel.add(taxField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Adicionar Moeda", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            // Obtém e valida os dados inseridos pelo usuário
            String simbolo = simboloField.getText();
            String nome = nomeField.getText();
            BigDecimal cotacao;
            BigDecimal tax;

            try {
                cotacao = new BigDecimal(cotacaoField.getText());
                tax = new BigDecimal(taxField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor inválido para cotação ou taxa.");
                return;
            }

            String sql = "INSERT INTO criptos (simbolo, nome, cotacao, tax) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, simbolo);
                stmt.setString(2, nome);
                stmt.setBigDecimal(3, cotacao);
                stmt.setBigDecimal(4, tax);

                int rowsInserted = stmt.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Moeda adicionada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao adicionar moeda.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar moeda: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.");
        }
    }
}
