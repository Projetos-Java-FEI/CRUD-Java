package DAO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import model.Moeda;
import service.extratoService;
import service.cotacaoService;



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
                tax = tax.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                
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
    
    public void comprarMoeda(Moeda moeda, int idUser, BigDecimal quantidade) throws SQLException {
        BigDecimal valorCompra = quantidade.multiply(BigDecimal.valueOf(moeda.getCotacao()));

        // Verifica saldo em BRL na carteira
        String sqlCheckSaldo = "SELECT saldo_real FROM carteira WHERE id_user = ?";
        try (PreparedStatement checkSaldoStmt = conn.prepareStatement(sqlCheckSaldo)) {
            checkSaldoStmt.setInt(1, idUser);
            try (ResultSet rs = checkSaldoStmt.executeQuery()) {
                if (rs.next()) {
                    BigDecimal saldoAtual = rs.getBigDecimal("saldo_real");

                    if (saldoAtual.compareTo(valorCompra) >= 0) {
                        // Atualizar saldo da carteira: subtrai BRL e adiciona a quantidade de moeda
                        String sqlUpdateCarteira = "UPDATE carteira SET saldo_real = saldo_real - ?, saldo_" + moeda.getSimbolo() + " = saldo_" + moeda.getSimbolo() + " + ? WHERE id_user = ?";
                        try (PreparedStatement updateCarteiraStmt = conn.prepareStatement(sqlUpdateCarteira)) {
                            updateCarteiraStmt.setBigDecimal(1, valorCompra);
                            updateCarteiraStmt.setBigDecimal(2, quantidade);
                            updateCarteiraStmt.setInt(3, idUser);

                            int rowsUpdated = updateCarteiraStmt.executeUpdate();
                            if (rowsUpdated > 0) {
                                // Chamada ao serviço de extrato
                                extratoService extratoService = new extratoService(conn);
                                extratoService.registrarExtrato(idUser,
                                        quantidade,
                                        "- " + valorCompra.toString(),
                                        "Compra de " + moeda.getSimbolo(),
                                        LocalDateTime.now().withNano(0));
                                JOptionPane.showMessageDialog(null, "Compra de " + quantidade + " " + moeda.getSimbolo() + " realizada com sucesso!");

                                // Atualiza as cotações após a compra
                                cotacaoService cotacaoService = new cotacaoService(conn);
                                cotacaoService.atualizarCotacoes();
                            } else {
                                JOptionPane.showMessageDialog(null, "Erro ao atualizar a carteira.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Saldo insuficiente para realizar a compra.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
                }
            }
        }
    }

    public void venderMoeda(Moeda moeda, int idUser, BigDecimal quantidade) throws SQLException {
        BigDecimal valorVenda = quantidade.multiply(BigDecimal.valueOf(moeda.getCotacao()));

        // Verifica saldo da moeda específica na carteira
        String sqlCheckSaldoMoeda = "SELECT saldo_" + moeda.getSimbolo() + " FROM carteira WHERE id_user = ?";
        try (PreparedStatement checkSaldoMoedaStmt = conn.prepareStatement(sqlCheckSaldoMoeda)) {
            checkSaldoMoedaStmt.setInt(1, idUser);
            try (ResultSet rs = checkSaldoMoedaStmt.executeQuery()) {
                if (rs.next()) {
                    BigDecimal saldoMoeda = rs.getBigDecimal("saldo_" + moeda.getSimbolo());

                    if (saldoMoeda.compareTo(quantidade) >= 0) {
                        // Atualizar saldo da carteira: adiciona BRL e subtrai a quantidade de moeda
                        String sqlUpdateCarteira = "UPDATE carteira SET saldo_real = saldo_real + ?, saldo_" + moeda.getSimbolo() + " = saldo_" + moeda.getSimbolo() + " - ? WHERE id_user = ?";
                        try (PreparedStatement updateCarteiraStmt = conn.prepareStatement(sqlUpdateCarteira)) {
                            updateCarteiraStmt.setBigDecimal(1, valorVenda);
                            updateCarteiraStmt.setBigDecimal(2, quantidade);
                            updateCarteiraStmt.setInt(3, idUser);

                            int rowsUpdated = updateCarteiraStmt.executeUpdate();
                            if (rowsUpdated > 0) {
                                // Chamada ao serviço de extrato
                                extratoService extratoService = new extratoService(conn);
                                extratoService.registrarExtrato(idUser,
                                        quantidade, "+ " + valorVenda.toString(),
                                        "Venda de " + moeda.getSimbolo(),
                                        LocalDateTime.now().withNano(0));
                                JOptionPane.showMessageDialog(null, "Venda de " + quantidade + " " + moeda.getSimbolo() + " realizada com sucesso!");

                                // Atualiza as cotações após a venda
                                cotacaoService cotacaoService = new cotacaoService(conn);
                                cotacaoService.atualizarCotacoes();
                                
                            } else {
                                JOptionPane.showMessageDialog(null, "Erro ao atualizar a carteira.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Saldo insuficiente de " + moeda.getSimbolo() + " para realizar a venda.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
                }
            }
        }
    }


    public void atualizarCotacoes() throws SQLException {
        String sqlSelect = "SELECT simbolo, cotacao FROM criptos";
        String sqlUpdate = "UPDATE criptos SET cotacao = ? WHERE simbolo = ?";
        String sqlInsertHistorico = "INSERT INTO historico_cripto"
                + " (simbolo, valor_antigo, data_hora)"
                + " VALUES (?, ?, ?)";

        try (PreparedStatement selectStmt = conn.prepareStatement(sqlSelect);
             ResultSet rs = selectStmt.executeQuery()) {

            while (rs.next()) {
                String simbolo = rs.getString("simbolo");
                BigDecimal cotacaoAntiga = rs.getBigDecimal("cotacao");

                // Calcular nova cotação com variação entre -5% e +5%
                BigDecimal variacao = cotacaoAntiga.multiply(BigDecimal.valueOf(Math.random() * 0.1 - 0.05)); // variação entre -5% e +5%
                BigDecimal novaCotacao = cotacaoAntiga.add(variacao).setScale(2, RoundingMode.HALF_UP); // Ajustar para 2 casas decimais

                // Inserir a cotação antiga no histórico
                try (PreparedStatement insertHistoricoStmt = conn.prepareStatement(sqlInsertHistorico)) {
                    insertHistoricoStmt.setString(1, simbolo);
                    insertHistoricoStmt.setBigDecimal(2, cotacaoAntiga);
                    insertHistoricoStmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now())); // data/hora atual
                    insertHistoricoStmt.executeUpdate();
                }

                // Atualizar a nova cotação na tabela de criptos
                try (PreparedStatement updateStmt = conn.prepareStatement(sqlUpdate)) {
                    updateStmt.setBigDecimal(1, novaCotacao);
                    updateStmt.setString(2, simbolo);
                    updateStmt.executeUpdate();
                }
            }
        }
}



}
   

