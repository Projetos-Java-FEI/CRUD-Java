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
import javax.swing.JPanel;
import javax.swing.JTextField;


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
            String erro = dao.excluir(getCpf());
            if(!erro.isEmpty()) {
                JOptionPane.showMessageDialog(null, erro, "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void consultaSaldo() {
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO_Admin dao = new DAO_Admin(conn);
            String message = dao.consultarSaldo(getCpf());
            
            if(!message.isEmpty() && (!message.startsWith("Erro"))) {  // verifica se a string retornada é vazia, para evitar mostrar um panel vazio
                JOptionPane.showMessageDialog(null, message, "Consulta de Saldo", JOptionPane.INFORMATION_MESSAGE);
            } else if(message.startsWith("Erro")) {
                JOptionPane.showMessageDialog(null, "Carteira não encontrada para este CPF", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
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
    
    public String getCpf() {
        JPanel panel = new JPanel();
        JTextField cpfField = new JTextField(11);
        panel.add(cpfField);
        int option = JOptionPane.showConfirmDialog(null, panel, "Digite o CPF para consulta", JOptionPane.OK_CANCEL_OPTION);
       

        if (option == JOptionPane.OK_OPTION) {
            return cpfField.getText();
        } else {
            return "";
        } 
    }
    
    public boolean isPositive(String nome, String userType) {
        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Deseja realmente excluir o usuário?\nNome: " + nome + "\nTipo: " + userType,
            "Confirmação de Exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
    );

        return (confirm == JOptionPane.YES_OPTION);
           
    }

    
}
