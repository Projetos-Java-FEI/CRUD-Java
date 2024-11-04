package controller;

import DAO.Conexao;
import DAO.DAO_Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.User;
import service.SessionManager;
import view.Carteira;



public class ControllerOperacoes {
    private Carteira view;
    private User user;

    public ControllerOperacoes(Carteira view, User user) {
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
            User user1 = SessionManager.getUser();
            dao.depositar(user1);
            view.setSaldo(getSaldo());
            
            // chama a função para atualizar o extrato no view após a operação
            view.atualizarExtrato(); 
            

        } catch(SQLException e) {            
            JOptionPane.showMessageDialog(view, "Depósito não realizado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void realizarSaque() {
        
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO_Usuario dao = new DAO_Usuario(conn);
            User user1 = SessionManager.getUser();
            dao.sacar(user1);
            view.setSaldo(getSaldo());
            view.atualizarExtrato(); 

        } catch(SQLException e) {            
            JOptionPane.showMessageDialog(view, "Saque não realizado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String getSaldo() {
        
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO_Usuario dao = new DAO_Usuario(conn);
            String saldoFormatado = String.format("%.2f", 
    dao.obterSaldoReais(SessionManager.getUser().getId()).doubleValue()); 
            
            return saldoFormatado;

        } catch(SQLException e) {            
            e.printStackTrace();
        }
        return "inválido";
    }
}
