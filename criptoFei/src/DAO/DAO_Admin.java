package DAO;

import controller.ControllerAdmin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.User;

public class DAO_Admin {
    private Connection conn;
    ControllerAdmin c = new ControllerAdmin();
    
    public DAO_Admin(Connection conn) {
        this.conn = conn;
    }
    
    public String excluir(String cpf) throws SQLException {
        
    String sqlCheck = "SELECT nome, user_type FROM users WHERE cpf = ?";
    try (PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck)) {
        stmtCheck.setString(1, cpf);
        try (ResultSet rs = stmtCheck.executeQuery()) {
            if (rs.next()) {
                // Exibe os dados do usuário encontrados e pergunta se deseja excluir
                String nome = rs.getString("nome");
                String userType = rs.getString("user_type");
                if  (c.isPositive(nome, userType)) { // confirmação do JOptionPane pelo usuário
                    String sqlDelete = "DELETE FROM users WHERE cpf = ?";
                    try (PreparedStatement stmtDelete = conn.prepareStatement(sqlDelete)) {
                        stmtDelete.setString(1, cpf);
                        int rowsDeleted = stmtDelete.executeUpdate();
                        if (rowsDeleted > 0) {
                            return "Usuário excluído com sucesso.";
                        } else {
                            return "Erro ao excluir o usuário.";
                        }
                    }
                } else {
                    return "Exclusão cancelada.";
                }
            } else {
                return "Usuário com CPF " + cpf + " não encontrado.";
            }
        }
    } catch (SQLException e) {
        return "Erro ao buscar usuário: " + e.getMessage();        
    }
} 

    
    public String consultarSaldo(String cpf) throws SQLException {
        String sqlCheck = "SELECT id_user, nome FROM users WHERE cpf = ?";
        try (PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck)) {
            stmtCheck.setString(1, cpf);
            try (ResultSet rs = stmtCheck.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("id_user");
                    String nome = rs.getString("nome");

                    // Consultar saldos na tabela 'carteira' para o usuário específico
                    String sqlSaldo = "SELECT simbolo, saldo FROM carteira WHERE id_user = ?";
                    try (PreparedStatement stmtSaldo = conn.prepareStatement(sqlSaldo)) {
                        stmtSaldo.setInt(1, userId);
                        try (ResultSet rsSaldo = stmtSaldo.executeQuery()) {
                            StringBuilder saldoMessage = new StringBuilder();
                            saldoMessage.append(String.format("Nome: %s\nCPF: %s\n\nSaldos:\n", nome, cpf));

                            // Processa os saldos das criptomoedas
                            while (rsSaldo.next()) {
                                String simbolo = rsSaldo.getString("simbolo");
                                double saldo = rsSaldo.getDouble("saldo");

                                saldoMessage.append(String.format("%s: %.8f\n", simbolo, saldo));
                            }

                            // Caso não haja saldo, retorna uma mensagem
                            if (saldoMessage.length() == 0) {
                                return "Erro -> Carteira não encontrada para este CPF";
                            }

                            return saldoMessage.toString();
                        }
                    }
                } else {
                    return "Erro -> Usuário não encontrado para este CPF";
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