package controller;

import DAO.Conexao;
import DAO.DAO_Usuario;
import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.User;
import service.SessionManager;
import view.Carteira;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;



public class ControllerOperacoes {
    private Carteira view;
    private User user;
 

    public ControllerOperacoes(Carteira view, User user) {
        this.view = view;
        this.user = user;
    }

    public ControllerOperacoes() {
    }
    
    
public void realizarDeposito(){
        
        Conexao conexao = new Conexao();
        
        JPanel panel = new JPanel();
        JTextField valorField = new JTextField(10); // Campo para o valor a ser depositado
        JPasswordField senhaField = new JPasswordField(10); // Campo para a senha

        panel.add(new JLabel("Valor do Depósito:"));
        panel.add(valorField);
        panel.add(Box.createVerticalStrut(15)); // Espaço entre os campos
        panel.add(new JLabel("Senha:"));
        panel.add(senhaField);
        
        int option = JOptionPane.showConfirmDialog(null, panel, "Depósito em Conta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            // Obtem o valor e a senha inseridos pelo usuário
            BigDecimal valor;
            try {
                Connection conn = conexao.getConnection();
                DAO_Usuario dao = new DAO_Usuario(conn);
                try {
                    valor = new BigDecimal(valorField.getText());
                    String senha = new String(senhaField.getPassword());

                    if(dao.verificarSenha(senha)) {
                        String msg = dao.depositar(SessionManager.getUser(), valor);
                        view.setSaldo(getSaldo());
                        view.atualizarExtrato(); 
                        
                        if(msg.startsWith("Erro")) {
                            JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, msg);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Senha inválida.", "Erro", JOptionPane.ERROR_MESSAGE);

                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Valor de depósito inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
               e.printStackTrace();
            }
        }
    }

    public void realizarSaque() {
        Conexao conexao = new Conexao();
        JPanel panel = new JPanel();
        JTextField valorField = new JTextField(10);
        JPasswordField senhaField = new JPasswordField(10);

        panel.add(new JLabel("Valor do Saque:"));
        panel.add(valorField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(new JLabel("Senha:"));
        panel.add(senhaField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Saque em Conta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                Connection conn = conexao.getConnection();
                DAO_Usuario dao = new DAO_Usuario(conn);
                try {
                    BigDecimal valor = new BigDecimal(valorField.getText());
                    String senha = new String(senhaField.getPassword());

                    if (dao.verificarSenha(senha)) {
                        String msg = dao.sacar(SessionManager.getUser(), valor);
                        view.setSaldo(getSaldo());
                        view.atualizarExtrato();

                        if (msg.startsWith("Erro")) {
                            JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, msg);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Valor de saque inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    public String getSaldo() {
        
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO_Usuario dao = new DAO_Usuario(conn);
            String saldoFormatado = String.format("%.2f", 
            dao.obterSaldoReais(SessionManager.getUser().getId()).doubleValue()); 
            
            return saldoFormatado;

        } catch(SQLException e) {            
            e.printStackTrace();
        }
        return "inválido";
    }
    
    public static void mostrarGrafico(int idUser, JPanel panel) throws SQLException {
        Map<String, Double> saldoCripto = DAO_Usuario.getSaldoCriptos(idUser);
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        saldoCripto.forEach(dataset::setValue);
        
        JFreeChart chart = ChartFactory.createPieChart(
            "Visão geral",
            dataset,
            true, 
            true, 
            false 
        );
        
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setOutlineVisible(false);
        plot.setBackgroundPaint(new Color(63,63,63)); 
        plot.setSectionPaint("BTC", Color.RED);
        plot.setSectionPaint("ETH", Color.GREEN); 
        plot.setSectionPaint("XRP", Color.YELLOW); 
        plot.setSectionPaint("BRL", new Color(50,153,254));
        chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 16));
        chart.getTitle().setPaint(new Color(50,153,254));
        chart.setBackgroundPaint(new Color(63,63,63));

        plot.setLabelFont(new Font("SansSerif", Font.BOLD, 12));
        plot.setLabelPaint(Color.WHITE);
        plot.setLabelBackgroundPaint(null);
        plot.setLabelOutlinePaint(null);
        plot.setLabelShadowPaint(null);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setSize(panel.getSize());
        chartPanel.setVisible(true);
        
        panel.add(chartPanel);
        panel.revalidate();
        panel.repaint();
    }

    
}


