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
    
    public String adicionarMoeda(String simbolo, String nome, BigDecimal cotacao, BigDecimal taxaCompra, BigDecimal taxaVenda) throws SQLException {
        String sqlCripto = "INSERT INTO criptos (simbolo, nome, cotacao, taxa_compra, taxa_venda) VALUES (?, ?, ?, ?, ?)";
        String sqlHistorico = "INSERT INTO historico_cripto (simbolo, cotacao, data) VALUES (?, ?, ?)";

        try (PreparedStatement stmtCripto = conn.prepareStatement(sqlCripto);
             PreparedStatement stmtHistorico = conn.prepareStatement(sqlHistorico)) {

            // Inserir a nova criptomoeda na tabela criptos
            stmtCripto.setString(1, simbolo);
            stmtCripto.setString(2, nome);
            stmtCripto.setBigDecimal(3, cotacao);
            stmtCripto.setBigDecimal(4, taxaCompra);
            stmtCripto.setBigDecimal(5, taxaVenda);

            int rowsInserted = stmtCripto.executeUpdate();

            if (rowsInserted > 0) {
                // Inserir o histórico de cotação na tabela historico_cripto
                stmtHistorico.setString(1, simbolo);
                stmtHistorico.setBigDecimal(2, cotacao); // O valor da cotação é o valor inicial
                stmtHistorico.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now())); // Data/hora atual

                stmtHistorico.executeUpdate();

                return "Moeda adicionada com sucesso e histórico registrado!";
            } else {
                return "Falha ao adicionar moeda.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao adicionar moeda: " + e.getMessage();
        }
    }

    
    public String excluirMoeda(String simbolo) throws SQLException {
    // SQL para excluir a moeda pelo símbolo
    String sql = "DELETE FROM criptos WHERE simbolo = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, simbolo);  // Definindo o símbolo da moeda a ser excluída

        int rowsDeleted = stmt.executeUpdate();  // Executando a exclusão

        if (rowsDeleted > 0) {
            return "Moeda excluída com sucesso!";
        } else {
            return "Erro -> Moeda não encontrada.";
        }
    } catch (SQLException e) {
        return "Erro ao excluir moeda: " + e.getMessage();
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
                + " (simbolo, cotacao, data)"
                + " VALUES (?, ?, ?)";

        try (PreparedStatement selectStmt = conn.prepareStatement(sqlSelect);
             ResultSet rs = selectStmt.executeQuery()) {

            while (rs.next()) {
                String simbolo = rs.getString("simbolo");
                BigDecimal cotacaoAntiga = rs.getBigDecimal("cotacao");

                // Verifica se o símbolo é diferente de "BRL" (Real) antes de atualizar a cotação
                if (!simbolo.equals("BRL")) {
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




}
   

