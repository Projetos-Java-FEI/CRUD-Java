package controller;

import DAO.Conexao;
import DAO.DAO_Moeda;
import javax.swing.JOptionPane;
import view.Administrador;
import DAO.DAO_Usuario;
import DAO.DAO_Admin;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerAdmin {
    private Administrador view;

    public ControllerAdmin(Administrador view) {
        this.view = view;
    }

    public ControllerAdmin() {
    }
    
    
    public void consultarSaldo(String cpf) {
        
        Conexao c = new Conexao();
        String consultaCpf = JOptionPane.showInputDialog(null, "Digite o CPF do investidor:",
                "Consultar CPF", JOptionPane.PLAIN_MESSAGE);
        
        try {
            Connection conn = c.getConnection();
            DAO_Usuario dao = new DAO_Usuario(conn);
            
            BigDecimal saldo = dao.obterSaldoReais(dao.getUserId(consultaCpf));
            
            JOptionPane.showMessageDialog(null, saldo, "Saldo em reais", JOptionPane.INFORMATION_MESSAGE);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        
        
    }
    
    public void criarMoeda() {
        
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO_Moeda dao = new DAO_Moeda(conn);
            dao.adicionarMoeda();
            
        } catch(SQLException e) {
                
        }
    }
    
    public void atualizaCotacao() {
        
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO_Moeda dao = new DAO_Moeda(conn);
            dao.atualizarCotacoes();
            
        } catch(SQLException e) {
                
        }
    }
    
    public void excluiInvestidor() {
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO_Admin dao = new DAO_Admin(conn);
            dao.excluir();
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void consultaSaldo() {
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO_Admin dao = new DAO_Admin(conn);
            dao.consultarSaldo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getQntdUsuarios() {
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO_Admin dao = new DAO_Admin(conn);
            
            return dao.getNumeroUsuarios();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
}
