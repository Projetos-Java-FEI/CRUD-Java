/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

/**
 *
 * @author Conta
 */
public class DAO_Usuario {
    private Connection conn;

    public DAO_Usuario(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet consultar(User user) throws SQLException{
        
        String sql = "select * from users where nome = ? AND senha = ?";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        
        statement.setString(1, user.getNome());
        statement.setString(2, user.getSenha());
        
        statement.execute();
        
        ResultSet resultado = statement.getResultSet();
        
        return resultado;
    }
    
    public void cadastro(User user) throws SQLException {
        String sqlInsertUser = "INSERT INTO users (nome, cpf, senha, user_type) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sqlInsertUser, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, user.getNome());
        statement.setString(2, user.getCpf());
        statement.setString(3, user.getSenha());
        statement.setString(4, "Investidor");
        statement.executeUpdate();

        // Obter o ID do usuário 
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int idUser = generatedKeys.getInt(1);
        
            // Adicionar criptomoedas padrões à carteira
            adicionarCriptomoedas(idUser);
        }

        generatedKeys.close();
        statement.close();
        conn.close();
    }

    private void adicionarCriptomoedas(int idUser) throws SQLException {
        String sqlInsertCarteira = "INSERT INTO carteira (id_user, id_cripto, quantidade) VALUES (?, ?, ?)";
        PreparedStatement carteiraStatement = conn.prepareStatement(sqlInsertCarteira);
    
        // Adicionar Reais (BRL)
        carteiraStatement.setInt(1, idUser);
        carteiraStatement.setString(2, "BRL");
        carteiraStatement.setBigDecimal(3, BigDecimal.ZERO);
        carteiraStatement.executeUpdate();

        // Adicionar Bitcoin (BTC)
        carteiraStatement.setInt(1, idUser);
        carteiraStatement.setString(2, "BTC");
        carteiraStatement.setBigDecimal(3, BigDecimal.ZERO);
        carteiraStatement.executeUpdate();

        // Adicionar Ethereum (ETH)
        carteiraStatement.setInt(1, idUser);
        carteiraStatement.setString(2, "ETH");
        carteiraStatement.setBigDecimal(3, BigDecimal.ZERO);
        carteiraStatement.executeUpdate();

        // Adicionar Ripple (XRP)
        carteiraStatement.setInt(1, idUser);
        carteiraStatement.setString(2, "XRP");
        carteiraStatement.setBigDecimal(3, BigDecimal.ZERO);
        carteiraStatement.executeUpdate();

        carteiraStatement.close();
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
    
    
}
