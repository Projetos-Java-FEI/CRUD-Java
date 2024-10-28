package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "SELECT * FROM users WHERE nome = ? AND senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getNome());
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

       
    //Esse excluir vai servir futuramente para o ADM, o investidor não utiliza
    //esse método.
    
    public void excluir(User user) throws SQLException{
       
       String sql = "delete from users where cpf = ?";
       PreparedStatement statement = conn.prepareStatement(sql);
       statement.setString(1, user.getCpf());
       statement.execute();
       conn.close();
             
   }
        // Método para obter o ID do usuário baseado no CPF
    private int getUserId(String cpf) throws SQLException {
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
    
    
}
