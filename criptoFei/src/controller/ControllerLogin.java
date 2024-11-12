package controller;

import DAO.Conexao;
import DAO.DAO_Usuario;
import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
import service.PasswordService;
import service.SessionManager;
import view.Administrador;
import view.Login;
import view.TelaUsuario;

public class ControllerLogin {
    private Login view;

    public ControllerLogin(Login view) {
        this.view = view;
    }

    
    public void loginUser() {
    // Criamos o objeto User com o CPF e senha inseridos pelo usuário
    User user = new User();
    user.setCpf(view.getTxtCpf().getText());
    char[] password = view.getTxtSenha().getPassword();
    String senhaInserida = new String(password);
    user.setSenha(senhaInserida);

    // Criamos o objeto de conexão
    Conexao c = new Conexao();

    try {
        // Fazemos a conexão
        Connection conn = c.getConnection();
        DAO_Usuario dao = new DAO_Usuario(conn);

        // Chama o método verificarLogin para buscar o usuário pelo cpf
        ResultSet res = dao.buscarUsuarioPorCpf(user);  // Alterado para buscar apenas pelo CPF
        

        // Se o login for bem-sucedido (resultado encontrado)
        if (res.next()) {
            // Recupera a senha criptografada armazenada no banco
            String senhaCriptografadaBanco = res.getString("senha");

            // Verifica se a senha fornecida (criptografada) é a mesma que a armazenada (comparando criptografadas)
            if (PasswordService.verificarSenha(senhaInserida, senhaCriptografadaBanco)) {
                JOptionPane.showMessageDialog(view, "Login efetuado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);

                // Pega o ID e outros dados do usuário retornados
                int id = res.getInt("id_user");
                String nome = res.getString("nome");
                String cpf = res.getString("cpf");
                String tipoUsuario = res.getString("user_type");

                // Criando o usuário completo com as informações obtidas
                User usuarioCompleto = new User(id, nome, cpf, senhaCriptografadaBanco, tipoUsuario);

                // Salvando o usuário na sessão
                SessionManager.setUser(usuarioCompleto);

                // Verifica o tipo de usuário e direciona para a tela correspondente
                if ("Administrador".equals(tipoUsuario)) {
                    Administrador telaAdm = new Administrador();
                    view.dispose();
                    telaAdm.setLocationRelativeTo(null);
                    telaAdm.setVisible(true);
                } else {
                    TelaUsuario userTela = new TelaUsuario();
                    view.dispose();
                    userTela.setLocationRelativeTo(null);
                    userTela.setVisible(true);
                }
            } else {
                // Caso as senhas não coincidam
                JOptionPane.showMessageDialog(view, "Senha incorreta!", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Caso não encontre o usuário com o CPF fornecido
            JOptionPane.showMessageDialog(view, "Usuário não encontrado!", "Aviso", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException e) {
        // Em casos de exceptions fazemos o tratamento com catch e modais
        JOptionPane.showMessageDialog(view, "Erro de conexão!", "Aviso", JOptionPane.ERROR_MESSAGE);
    }
}


}
