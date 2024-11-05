package controller;

import DAO.Conexao;
import DAO.DAO_Usuario;
import model.User;
import view.Cadastro;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ControllerCadastro {
    private Cadastro view;

    public ControllerCadastro(Cadastro view) {
        this.view = view;
    }
    
    public void salvarUser() {
        String nome = view.getTxtNome().getText();
        String CPF = view.getTxtCPF().getText();
        
        if (!CPF.matches("\\d{11}")) {
            JOptionPane.showMessageDialog(view, "CPF inválida! O CPF deve conter exatamente 11 números.", "Erro", JOptionPane.ERROR_MESSAGE);
        
            view.getTxtCPF().setText("");
            view.getTxtCPF().requestFocus();    
            return;
        }
        
        char[] password = view.getTxtSenha().getPassword();
        String senha = new String(password);
        
        if (!senha.matches("\\d{6}")) {
            JOptionPane.showMessageDialog(view, "Senha inválida! A senha deve conter exatamente 6 números.", "Erro", JOptionPane.ERROR_MESSAGE);
        
            view.getTxtSenha().setText("");
            view.getTxtSenha().requestFocus();    
            return;
        }
        
            
        User user = new User(nome, CPF, senha, "Investidor");
        
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO_Usuario dao = new DAO_Usuario(conn);
            dao.cadastro(user);
            JOptionPane.showMessageDialog(view, "Usuário cadastrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            view.dispose();
            
        } catch(SQLException e) {            
            JOptionPane.showMessageDialog(view, "Usuário não cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    }
