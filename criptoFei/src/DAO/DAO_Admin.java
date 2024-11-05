package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.ResultSet;
import model.User;

public class DAO_Admin {
    private Connection conn;
    
    public DAO_Admin(Connection conn) {
        this.conn = conn;
    }
    
    public void excluir() throws SQLException {
        // Painel para entrada do CPF
        JPanel panel = new JPanel();
        JTextField cpfField = new JTextField(11);
        panel.add(cpfField);
        int option = JOptionPane.showConfirmDialog(null, panel, "Digite o CPF do usuário a ser excluído", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String cpf = cpfField.getText();

            // Consulta para verificar se o CPF existe e obter os dados do usuário
            String sqlCheck = "SELECT nome, user_type FROM users WHERE cpf = ?";
            try (PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck)) {
                stmtCheck.setString(1, cpf);
                try (ResultSet rs = stmtCheck.executeQuery()) {
                    if (rs.next()) {
                        // Exibe os dados do usuário encontrados e pergunta se deseja excluir
                        String nome = rs.getString("nome");
                        String userType = rs.getString("user_type");
                        int confirm = JOptionPane.showConfirmDialog(
                                null,
                                "Deseja realmente excluir o usuário?\nNome: " + nome + "\nTipo: " + userType,
                                "Confirmação de Exclusão",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE
                        );

                        if (confirm == JOptionPane.YES_OPTION) {
                            // Se confirmado, exclui o usuário
                            String sqlDelete = "DELETE FROM users WHERE cpf = ?";
                            try (PreparedStatement stmtDelete = conn.prepareStatement(sqlDelete)) {
                                stmtDelete.setString(1, cpf);
                                int rowsDeleted = stmtDelete.executeUpdate();
                                if (rowsDeleted > 0) {
                                    JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro ao excluir o usuário.");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Exclusão cancelada.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário com CPF " + cpf + " não encontrado.");
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar usuário: " + e.getMessage());
                throw e;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.");
        }
    }
    
    public void consultarSaldo() throws SQLException {
        // Painel para entrada do CPF
        JPanel panel = new JPanel();
        JTextField cpfField = new JTextField(11);
        panel.add(cpfField);
        int option = JOptionPane.showConfirmDialog(null, panel, "Digite o CPF para consulta", JOptionPane.OK_CANCEL_OPTION);
       

        if (option == JOptionPane.OK_OPTION) {
            String cpf = cpfField.getText();

            String sqlCheck = "SELECT id_user, nome FROM users WHERE cpf = ?";
            try (PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck)) {
                stmtCheck.setString(1, cpf);
                try (ResultSet rs = stmtCheck.executeQuery()) {
                    if (rs.next()) {
                        int userId = rs.getInt("id_user");
                        String nome = rs.getString("nome");
                        String sql = "SELECT saldo_real, saldo_btc, saldo_eth, saldo_xrp FROM carteira WHERE id_user = ?";
                        
                        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                                stmt.setInt(1, userId);
                                try (ResultSet rsSaldo = stmt.executeQuery()) {
                                    if (rsSaldo.next()) {
                                        double saldoReal = rsSaldo.getDouble("saldo_real");
                                        double saldoBtc = rsSaldo.getDouble("saldo_btc");
                                        double saldoEth = rsSaldo.getDouble("saldo_eth");
                                        double saldoXrp = rsSaldo.getDouble("saldo_xrp");
                                        
                                        String message = String.format(
                                                "Nome: %s\n"
                                                + "CPF: %s\n\n"
                                                + "Saldos:\n"
                                                + "R$ %.2f\n"
                                                + "%.8f BTC\n"
                                                + "%.8f ETH\n"
                                                + "%.8f XRP\n", nome, cpf, saldoReal, saldoBtc, saldoEth, saldoXrp);
                                        JOptionPane.showMessageDialog(null, message, "Consulta de Saldo", JOptionPane.INFORMATION_MESSAGE);
                                    
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Carteira não encontrada para este CPF", "Erro", JOptionPane.ERROR_MESSAGE);
                                    }       
                                }   
                        }   
                    }   
                    else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado com o CPF informado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
 
    public int getNumeroUsuarios() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM users";
        try (PreparedStatement stmt = conn.prepareStatement(sql); 
            ResultSet rs = stmt.executeQuery()) {
            if(rs.next()) {
                int qtd = rs.getInt("total") - 2;
                return qtd;
            }
        }
        return 0;
    }
    
    public double getSaldos(User user) throws SQLException {
        String sql = "SELECT saldo_real, saldo_btc, saldo_eth, saldo_xrp FROM carteira WHERE id_user = ?";
                        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, user.getId());
                try (ResultSet rsSaldo = stmt.executeQuery()) {
                    if (rsSaldo.next()) {
                        double saldoReal = rsSaldo.getDouble("saldo_real");
                        double saldoBtc = rsSaldo.getDouble("saldo_btc");
                        double saldoEth = rsSaldo.getDouble("saldo_eth");
                        double saldoXrp = rsSaldo.getDouble("saldo_xrp");
                        
                    }
                } catch(SQLException e) {
                    
                }

        }
       return 0;
    }
}