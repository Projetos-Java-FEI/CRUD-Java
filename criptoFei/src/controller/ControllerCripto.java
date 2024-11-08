package controller;

import DAO.Conexao;
import DAO.DAO_Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;
import view.Cripto;


public class ControllerCripto {
    private Cripto view;

    public ControllerCripto(Cripto view) {
        this.view = view;
    }

    public ControllerCripto() {
    }
    
    
    
    public void carregarCriptos() {
        DefaultTableModel modelo = (DefaultTableModel) view.getTblCriptos().getModel();
        modelo.setRowCount(0); // garante que não tenha nenhuma coluna na tabela do view
        
        Conexao c = new Conexao();
        try {
            Connection conn = c.getConnection();
            DAO_Usuario dao = new DAO_Usuario(conn);
            
            ArrayList<Object[]> criptos = dao.getCriptos();
            
            // Inverte o arraylist para poder sempre mostrar as operações mais recentes 
            Collections.reverse(criptos); 
            
            for (Object[] linha : criptos) { // for-each para acessar todos os Object[] do arraylist
                modelo.addRow(linha); // adiciona o valor de linha para a tabela no view
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
}
