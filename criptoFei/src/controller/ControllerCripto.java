package controller;

import DAO.Conexao;
import DAO.DAO_Moeda;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Moeda;
import service.SessionManager;
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
            DAO_Moeda dao = new DAO_Moeda(conn);
            
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
    
    public void carregarCriptosUsuario() {
        DefaultTableModel modelo = (DefaultTableModel) view.getTblCriptosUser().getModel();
    
        modelo.setRowCount(0); // garante que não tenha nenhuma coluna na tabela do view
        Conexao c = new Conexao();
        try {
            Connection conn = c.getConnection();
            DAO_Moeda dao = new DAO_Moeda(conn);
            
            ArrayList<Object[]> criptos = dao.getCriptosUsuario(SessionManager.getUser().getId());
            
            Collections.reverse(criptos);
            
            for (Object[] linha : criptos) { // for-each para acessar todos os Object[] do arraylist
                modelo.addRow(linha); // adiciona o valor de linha para a tabela no view
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
 public void compraCripto(JTable tblCripto) {
    int linhaSelecionada = tblCripto.getSelectedRow();
    Conexao c = new Conexao();
    try {
        Connection conn = c.getConnection();
        DAO_Moeda dao = new DAO_Moeda(conn);
            
        if (linhaSelecionada != -1) {
            String simbolo = (String) tblCripto.getValueAt(linhaSelecionada, 0);
            String nome = (String) tblCripto.getValueAt(linhaSelecionada, 1);
            double cotacao = (double) tblCripto.getValueAt(linhaSelecionada, 2);

            // Exibe um JOptionPane para o usuário inserir a quantidade desejada
            String quantidadeStr = JOptionPane.showInputDialog(
                null,
                "Digite a quantidade de " + nome + " que deseja comprar (Cotação: R$ " + cotacao + "):",
                "Quantidade de " + nome,
                JOptionPane.PLAIN_MESSAGE
            );

            if (quantidadeStr != null && !quantidadeStr.isEmpty()) {
                try {
                    double quantidade = Double.parseDouble(quantidadeStr);
                    
                    double valorTotal = quantidade * cotacao;

                    int confirmacao = JOptionPane.showConfirmDialog(
                        null,
                        "Você irá gastar R$ " + String.format("%.2f", valorTotal) + 
                        " para comprar " + quantidade + " unidades de " + nome + ".\nDeseja continuar?",
                        "Confirmar compra",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (confirmacao == JOptionPane.YES_OPTION) {
                        Moeda moeda = new Moeda(simbolo, nome, cotacao);
                        SessionManager sm = new SessionManager();
                        dao.comprarMoeda(moeda, sm.getUser().getId(), new BigDecimal(quantidade));
                        view.atualizarCriptosUsuarios();
                        view.atualizarCriptos();
                        
                        JOptionPane.showMessageDialog(null, "Compra realizada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Compra cancelada.");
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Quantidade inválida. Por favor, insira um número.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Operação cancelada.");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma criptomoeda para comprar!");
        } 
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
 
 public void vendeCripto(JTable tblCripto) {
    int linhaSelecionada = tblCripto.getSelectedRow();
    Conexao c = new Conexao();
    try {
        Connection conn = c.getConnection();
        DAO_Moeda dao = new DAO_Moeda(conn);
            
        if (linhaSelecionada != -1) {
            String simbolo = (String) tblCripto.getValueAt(linhaSelecionada, 0);
            String nome = (String) tblCripto.getValueAt(linhaSelecionada, 1);
            double cotacao = (double) tblCripto.getValueAt(linhaSelecionada, 2);

            // Exibe um JOptionPane para o usuário inserir a quantidade desejada
            String quantidadeStr = JOptionPane.showInputDialog(
                null,
                "Digite a quantidade de " + nome + " que deseja vender (Cotação: R$ " + cotacao + "):",
                "Quantidade de " + nome,
                JOptionPane.PLAIN_MESSAGE
            );

            if (quantidadeStr != null && !quantidadeStr.isEmpty()) {
                try {
                    double quantidade = Double.parseDouble(quantidadeStr);
                    
                    double valorTotal = quantidade * cotacao;

                    int confirmacao = JOptionPane.showConfirmDialog(
                        null,
                        "Você irá receber R$ " + String.format("%.2f", valorTotal) + 
                        " pela venda de " + quantidade + " unidades de " + nome + ".\nDeseja continuar?",
                        "Confirmar compra",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (confirmacao == JOptionPane.YES_OPTION) {
                        Moeda moeda = new Moeda(simbolo, nome, cotacao);
                        SessionManager sm = new SessionManager();
                        String msg = dao.venderMoeda(moeda, sm.getUser().getId(), new BigDecimal(quantidade));
                        view.atualizarCriptosUsuarios();
                        view.atualizarCriptos();
                        
                        if(!msg.startsWith("Erro")) {
                            JOptionPane.showMessageDialog(null, msg);  
                        } else {
                            JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Venda cancelada.");
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Quantidade inválida. Por favor, insira um número.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Operação cancelada.");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma criptomoeda para vender!");
        } 
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
}
