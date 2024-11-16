package controller;

import DAO.Conexao;
import DAO.DAO_Moeda;
import javax.swing.JOptionPane;
import view.Administrador;
import DAO.DAO_Usuario;
import DAO.DAO_Admin;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
    
    public void adicionarMoeda() {
        // painel para entrada de dados da nova moeda
        JPanel panel = new JPanel();
        JTextField simboloField = new JTextField(10); 
        JTextField nomeField = new JTextField(20);    
        JTextField cotacaoField = new JTextField(10);  
        JTextField taxaCompraField = new JTextField(5); 
        JTextField taxaVendaField = new JTextField(5);  

        panel.add(new JLabel("Símbolo:"));
        panel.add(simboloField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(new JLabel("Cotação:"));
        panel.add(cotacaoField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(new JLabel("Taxa de Compra (%):"));
        panel.add(taxaCompraField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(new JLabel("Taxa de Venda (%):"));
        panel.add(taxaVendaField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Adicionar Moeda", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            // obtém e valida os dados inseridos pelo usuário
            String simbolo = simboloField.getText().toUpperCase();
            String nome = nomeField.getText();
            BigDecimal cotacao;
            Double taxaCompra;
            Double taxaVenda;

            try {
                cotacao = new BigDecimal(cotacaoField.getText());
               
                taxaCompra = Double.parseDouble((taxaCompraField.getText()))/100; 
               
                taxaVenda = Double.parseDouble((taxaVendaField.getText()))/100; 

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor inválido para cotação ou taxa.");
                return;
            }

            Conexao conexao = new Conexao();
            try {
                Connection conn = conexao.getConnection();
                DAO_Moeda daoMoeda = new DAO_Moeda(conn);
                String msg = daoMoeda.adicionarMoeda(simbolo, nome, cotacao,  new BigDecimal(taxaCompra), new BigDecimal(taxaVenda));

                if (msg.startsWith("Moeda adicionada com sucesso")) {
                    JOptionPane.showMessageDialog(null, msg);
                } else {
                    JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.");
        }
    }
    public void excluirMoeda() {
        // painel para entrada do símbolo da moeda a ser excluída
        JPanel panel = new JPanel();
        JTextField simboloField = new JTextField(10);
        JPasswordField senhaField = new JPasswordField(10);  

        panel.add(new JLabel("Símbolo da Moeda a Ser Excluída:"));
        panel.add(simboloField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(new JLabel("Senha:"));
        panel.add(senhaField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Excluir Moeda", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            Conexao c = new Conexao();

            try {
                // fazendo a conexão com o banco
                Connection conn = c.getConnection();

                String simbolo = simboloField.getText().toUpperCase();
                String senha = new String(senhaField.getPassword());

                DAO_Usuario daoUsuario = new DAO_Usuario(conn);
                
                if (daoUsuario.verificarSenha(senha)) {
                    // pergunta de confirmação antes de excluir a moeda
                    int confirm = JOptionPane.showConfirmDialog(null, 
                        "Tem certeza que deseja excluir a moeda " + simbolo + "?", 
                        "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                    if (confirm == JOptionPane.YES_OPTION) {
                        // chamando o método para excluir a moeda
                        DAO_Moeda daoMoeda = new DAO_Moeda(conn);
                        String msg = daoMoeda.excluirMoeda(simbolo);  // exclusão da moeda
                        if (msg.startsWith("Erro")) {
                            JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, msg); 
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Operação cancelada.");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Senha incorreta. A exclusão não foi realizada.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir moeda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.");
        }
    }




    
    public void atualizaCotacao() {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            DAO_Moeda dao = new DAO_Moeda(conn);
            dao.atualizarCotacoes();
        } catch (SQLException e) {
            e.printStackTrace();  // exibe a mensagem de erro no console
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cotações: " + e.getMessage());
        }
    }

    
    public void excluiInvestidor() {
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO_Admin dao = new DAO_Admin(conn);
            String msg = dao.excluir(getCpf());
            if(!msg.isEmpty()) {
                JOptionPane.showMessageDialog(null, msg);
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
    
    public void getExtratoUsuario() {
        Conexao conn = new Conexao();
        JPanel panel = new JPanel();
        
        JTextField simboloField = new JTextField(10);  // Campo para o símbolo da moeda
        JPasswordField senhaField = new JPasswordField(10);  // Campo para a senha do usuário

        panel.add(new JLabel("CPF do usuário:"));
        panel.add(simboloField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(new JLabel("Senha:"));
        panel.add(senhaField);
        
        int option = JOptionPane.showConfirmDialog(null, panel, "Consultar Extrato", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
        String cpf = simboloField.getText();
        String senha = new String(senhaField.getPassword());
        
        try {
            Connection c = conn.getConnection();
            DAO_Admin dao = new DAO_Admin(c);
            ArrayList<Object[]> extrato = dao.getExtratoCpf(cpf);
            
            StringBuilder extratoStr = new StringBuilder("Extrato:\n");
            for (Object[] dadosExtrato : extrato) {
                extratoStr.append("Quantidade: ").append(dadosExtrato[0]).append(" | ")
                          .append("Operação (R$): ").append(dadosExtrato[1]).append(" | ")
                          .append("Tipo de Operação: ").append(dadosExtrato[2]).append(" | ")
                          .append("Data: ").append(dadosExtrato[3]).append("\n\n");
            }
            
            JOptionPane.showMessageDialog(null, extratoStr.toString(), "Extrato do Usuário", JOptionPane.INFORMATION_MESSAGE);
            
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar o extrato.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Operação cancelada.");    
            }
    }
    
}
