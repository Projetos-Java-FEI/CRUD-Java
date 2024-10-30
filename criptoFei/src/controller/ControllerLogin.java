package controller;

import DAO.Conexao;
import DAO.DAO_Usuario;
import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
import util.SessionManager;
import view.Login;
import view.TelaUsuario;

public class ControllerLogin {
    private Login view;

    public ControllerLogin(Login view) {
        this.view = view;
    }

    public void loginUser() {
        // Criamos o objeto User
        User user = new User();
        user.setCpf(view.getTxtCpf().getText());
        char[] password = view.getTxtSenha().getPassword();
        user.setSenha(new String(password));

        // Criamos o objeto de conexão
        Conexao c = new Conexao();

        try {
            // Fazemos a conexão
            Connection conn = c.getConnection();

            // Criamos o objeto para executar a query de login passando a conexão
            DAO_Usuario dao = new DAO_Usuario (conn);
            ResultSet res = dao.verificarLogin(user); // Usando a nova função

            // Dando certo exibimos um modal de Login Efetuado
            if (res.next()) {
                JOptionPane.showMessageDialog(view, "Login efetuado!", 
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
                
                // Pega o ID e outros dados do usuário retornados
                int id = res.getInt("id_user");
                String nome = res.getString("nome");
                String usuario = res.getString("cpf"); // ou o que você considerar como usuário
                String senha = res.getString("senha");

                // Criamos o objeto User com o ID
                User user1 = new User(id, nome, usuario, senha, "Investidor"); // Defina o tipo de usuário conforme necessário
                SessionManager.setUser(user1);
                TelaUsuario userTela = new TelaUsuario();
                view.dispose();
                userTela.setVisible(true);

            } else {
                // Dando errado modal de Login não efetuado
                JOptionPane.showMessageDialog(view, "Login não efetuado!", 
                        "Aviso", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            // Em casos de exceptions fazemos o tratamento com catch e modais
            JOptionPane.showMessageDialog(view, "Erro de conexão!!", 
                    "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }
}
