package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.User;


public class DAO_Usuario {
    private Connection conn;

    public DAO_Usuario(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet verificarLogin(User user) throws SQLException {
        String sql = "SELECT * FROM users WHERE cpf = ? AND senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getCpf());
        statement.setString(2, user.getSenha());
        return statement.executeQuery();
    }

    
    public void cadastro(User user) throws SQLException {
        String sqlInsertUser = "INSERT INTO users (nome, cpf, senha, user_type) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sqlInsertUser);

        try {
            // Inserindo o usuário no banco de dados
            statement.setString(1, user.getNome());
            statement.setString(2, user.getCpf());
            statement.setString(3, user.getSenha());
            statement.setString(4, "Investidor");
            statement.executeUpdate();

            // Obter o ID do usuário recém-criado
            int userId = getUserId(user.getCpf()); // Método para obter o ID do usuário baseado no CPF

            // Criar carteira para o usuário
            String sqlInsertCarteira = "INSERT INTO carteira (id_user) VALUES (?)"; // Não é necessário especificar o saldo se for default
            try (PreparedStatement statementCarteira = conn.prepareStatement(sqlInsertCarteira)) {
                statementCarteira.setInt(1, userId);
                statementCarteira.executeUpdate();
                
            }       
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
}

    

    public void depositar(User user) throws SQLException {
        // Cria um painel com os campos de entrada
        JPanel panel = new JPanel();
        JTextField valorField = new JTextField(10); // Campo para o valor a ser depositado
        JPasswordField senhaField = new JPasswordField(10); // Campo para a senha

        panel.add(new JLabel("Valor do Depósito:"));
        panel.add(valorField);
        panel.add(Box.createVerticalStrut(15)); // Espaço entre os campos
        panel.add(new JLabel("Senha:"));
        panel.add(senhaField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Depósito em Conta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            // Obtem o valor e a senha inseridos pelo usuário
            BigDecimal valor;
            try {
                valor = new BigDecimal(valorField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor de depósito inválido.");
                return;
            }

            String senha = new String(senhaField.getPassword());

            // Verifica a senha antes de proceder com o depósito
            String sqlVerifySenha = "SELECT 1 FROM users WHERE id_user = ? AND senha = ?";
        
            try (PreparedStatement verifyStmt = conn.prepareStatement(sqlVerifySenha)) {
                verifyStmt.setInt(1, user.getId());
                verifyStmt.setString(2, senha);

                try (ResultSet rs = verifyStmt.executeQuery()) {
                    if (rs.next()) {
                        // Se a senha estiver correta, realiza o depósito
                        String sqlUpdateSaldo = "UPDATE carteira SET saldo_real = saldo_real + ? WHERE id_user = ?";
                    
                        try (PreparedStatement statement = conn.prepareStatement(sqlUpdateSaldo)) {
                            statement.setBigDecimal(1, valor);
                            statement.setInt(2, user.getId());

                            int rowsUpdated = statement.executeUpdate();
                            if (rowsUpdated > 0) {
                                JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso! Valor depositado: " + valor);
                                String tipoOp = String.format("+ %s", valor.toString());
                                
                                extrato(user.getId(), valor, tipoOp, "Depósito BRL", LocalDateTime.now().withNano(0));
                            } else {
                                JOptionPane.showMessageDialog(null, "Falha ao realizar o depósito. Usuário não encontrado.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha incorreta. Depósito não realizado.");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.");
        }
    }
    
    public void sacar(User user) throws SQLException {
        // Cria um painel com os campos de entrada
        JPanel panel = new JPanel();
        JTextField valorField = new JTextField(10); // Campo para o valor a ser depositado
        JPasswordField senhaField = new JPasswordField(10); // Campo para a senha

        panel.add(new JLabel("Valor do Saque:"));
        panel.add(valorField);
        panel.add(Box.createVerticalStrut(15)); // Espaço entre os campos
        panel.add(new JLabel("Senha:"));
        panel.add(senhaField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Saque em Conta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            // Obtem o valor e a senha inseridos pelo usuário
            BigDecimal valor;
            try {
                valor = new BigDecimal(valorField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor de saque inválido.");
                return;
            }

            String senha = new String(senhaField.getPassword());

            // Verifica a senha antes de proceder com o depósito
            String sqlVerifySenha = "SELECT 1 FROM users WHERE id_user = ? AND senha = ?";
        
            try (PreparedStatement verifyStmt = conn.prepareStatement(sqlVerifySenha)) {
                verifyStmt.setInt(1, user.getId());
                verifyStmt.setString(2, senha);

                try (ResultSet rs = verifyStmt.executeQuery()) {
                    if (rs.next()) {
                        // Se a senha estiver correta, realiza o depósito
                        String sqlUpdateSaldo = "UPDATE carteira SET saldo_real = saldo_real - ? WHERE id_user = ?";
                    
                        try (PreparedStatement statement = conn.prepareStatement(sqlUpdateSaldo)) {
                            statement.setBigDecimal(1, valor);
                            statement.setInt(2, user.getId());

                            int rowsUpdated = statement.executeUpdate();
                            if (rowsUpdated > 0) {
                                JOptionPane.showMessageDialog(null, "Saque realizado com sucesso! Valor do saque: " + valor);
                            } else {
                                JOptionPane.showMessageDialog(null, "Falha ao realizar o saque. Usuário não encontrado.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha incorreta. Saque não realizado.");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.");
        }
    }

       
    //Esse excluir vai servir futuramente para o ADM, o investidor não utiliza
    //esse método.
    
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

        // Método para obter o ID do usuário baseado no CPF
    public int getUserId(String cpf) throws SQLException {
        String sqlSelectId = "SELECT id_user FROM users WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlSelectId)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_user");
                }
            }
        }
        return -1; // Retorna -1 se o usuário não for encontrado
    }
    
    public BigDecimal obterSaldoReais(int userId) {
        BigDecimal saldo = BigDecimal.ZERO;  // Variável para armazenar o saldo

        String sql = "SELECT saldo_real FROM carteira WHERE id_user = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);  
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    saldo = rs.getBigDecimal("saldo_real"); 
                } else {
                    System.out.println("Usuário não encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return saldo;
    }
   
    public void extrato(int idUser, BigDecimal quantidade, String tipoOp, String tipoOperacao, LocalDateTime date) throws SQLException {
        String sqlInsertOp = "INSERT INTO extrato (id_user, quantidade, operacao_reais, tipo_operacao, data)"
         + " VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sqlInsertOp);

        try {
            statement.setInt(1, idUser);
            statement.setBigDecimal(2, quantidade);
            statement.setString(3, tipoOp);
            statement.setString(4, tipoOperacao);
            statement.setTimestamp(5, Timestamp.valueOf(date));
            statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
    }
     
    
}
