/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Conta
 */
public class connection {
    public Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "123456");
        return connection;
    }
    
}
