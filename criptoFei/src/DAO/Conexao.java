package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    public Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "123456");
        return connection;
    }
    
}
