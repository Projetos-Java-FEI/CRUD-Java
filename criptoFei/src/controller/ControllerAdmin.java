package controller;

import DAO.Conexao;
import javax.swing.JOptionPane;
import view.Administrador;
import DAO.DAO_Usuario;
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
    
}
