package controller;

import DAO.Conexao;
import DAO.DAO_Usuario;
import java.sql.Connection;
import view.Carteira;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;
import util.SessionManager;


public class ControllerExtrato {
    private Carteira view;

    public ControllerExtrato(Carteira view) {
        this.view = view;
    }
    
   public void carregarExtrato() {
        DefaultTableModel modelo = (DefaultTableModel) view.getTblExtrato().getModel();
        modelo.setRowCount(0); // garante que não tenha nenhuma coluna no extrato do view
        
        Conexao c = new Conexao();
        try {
            Connection conn = c.getConnection();
            DAO_Usuario dao = new DAO_Usuario(conn);
            
            ArrayList<Object[]> extrato = dao.getExtrato(SessionManager.getUser());
            
            // Inverte o arraylist para poder sempre mostrar as operações mais recentes 
            Collections.reverse(extrato); 
            
            for (Object[] linha : extrato) { // for-each para acessar todos os Object[] do arraylist
                modelo.addRow(linha); // adiciona o valor de linha para a tabela no view
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    
}
