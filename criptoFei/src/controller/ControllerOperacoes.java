package controller;

import DAO.Conexao;
import DAO.DAO_Usuario;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.User;
import util.SessionManager;
import view.Carteira;



public class ControllerOperacoes {
    private Carteira view;
    private SessionManager user;

    public ControllerOperacoes(Carteira view, SessionManager user) {
        this.view = view;
        this.user = user;
    }

    public ControllerOperacoes() {
    }
    
    
    public void realizarDeposito() {
        
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO_Usuario dao = new DAO_Usuario(conn);
            User user1 = user.getUser();
            dao.depositar(user1);

        } catch(SQLException e) {            
            JOptionPane.showMessageDialog(view, "Depósito não realizado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String getSaldo() {
        
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO_Usuario dao = new DAO_Usuario(conn);
            int id = user.getUser().getId();
            double saldo = dao.obterSaldo(id).doubleValue();
            String saldoFormatado = String.format("%.2f", saldo); 
            return saldoFormatado;

        } catch(SQLException e) {            
            e.printStackTrace();
        }
        return "inválido";
    }
}
