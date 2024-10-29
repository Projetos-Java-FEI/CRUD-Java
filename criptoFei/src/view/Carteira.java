package view;

import controller.ControllerOperacoes;
import java.awt.Color;
import javax.swing.ImageIcon;
import model.User;
import DAO.DAO_Usuario;
import util.SessionManager;

public class Carteira extends javax.swing.JFrame {

    public Carteira() {
        initComponents();
        
        c = new ControllerOperacoes(this, SessionManager.getUser());
        
        User user = SessionManager.getUser();
        lblNome.setText(user.getNome());
        lblCPF.setText(user.formatarCpf(user.getCpf()));
        
        lblSaldo.setText(c.getSaldo().toString());
        
        this.getContentPane().setBackground(new Color(228,228,228));
        setIconImage(new ImageIcon(getClass().getResource("/imagens/logoCriptoFei.png")).getImage());
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblHome1 = new javax.swing.JLabel();
        lblCripto = new javax.swing.JLabel();
        lblCarteira = new javax.swing.JLabel();
        panelSaldo = new javax.swing.JPanel();
        btnTransferir = new javax.swing.JButton();
        lblNome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblCPF = new javax.swing.JLabel();
        btnSacar = new javax.swing.JButton();
        panelExtrato = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        btnExtrato = new javax.swing.JButton();
        panelDivisao = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnConsultarSaldo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CriptoFEI");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusable(false);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(1, 101, 198));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/logoCriptoFei.png"))); // NOI18N

        lblHome1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHome1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/home.png"))); // NOI18N
        lblHome1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHome1MouseClicked(evt);
            }
        });

        lblCripto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCripto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/crypto-currency.png"))); // NOI18N
        lblCripto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCripto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCriptoMouseClicked(evt);
            }
        });

        lblCarteira.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCarteira.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/wallet.png"))); // NOI18N
        lblCarteira.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCarteira.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCarteiraMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCripto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblCarteira, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblHome1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(41, 41, 41)
                .addComponent(lblHome1)
                .addGap(43, 43, 43)
                .addComponent(lblCarteira)
                .addGap(53, 53, 53)
                .addComponent(lblCripto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelSaldo.setBackground(new java.awt.Color(81, 137, 172));
        panelSaldo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(228, 228, 228), 5, true));
        panelSaldo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelSaldo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        btnTransferir.setBackground(new java.awt.Color(255, 255, 255));
        btnTransferir.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnTransferir.setForeground(new java.awt.Color(1, 101, 198));
        btnTransferir.setText("Transferir");
        btnTransferir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        btnTransferir.setBorderPainted(false);
        btnTransferir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTransferir.setFocusPainted(false);
        btnTransferir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferirActionPerformed(evt);
            }
        });

        lblNome.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblNome.setForeground(new java.awt.Color(255, 255, 255));
        lblNome.setText("Nome");

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("R$");

        lblSaldo.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblSaldo.setForeground(new java.awt.Color(255, 255, 255));
        lblSaldo.setText("saldo");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Saldo Disponível");

        lblCPF.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblCPF.setForeground(new java.awt.Color(255, 255, 255));
        lblCPF.setText("CPF");

        btnSacar.setBackground(new java.awt.Color(255, 255, 255));
        btnSacar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSacar.setForeground(new java.awt.Color(1, 101, 198));
        btnSacar.setText("Sacar");
        btnSacar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        btnSacar.setBorderPainted(false);
        btnSacar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSacar.setFocusPainted(false);
        btnSacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSaldoLayout = new javax.swing.GroupLayout(panelSaldo);
        panelSaldo.setLayout(panelSaldoLayout);
        panelSaldoLayout.setHorizontalGroup(
            panelSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSaldoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSaldoLayout.createSequentialGroup()
                        .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelSaldoLayout.createSequentialGroup()
                        .addComponent(lblCPF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSaldoLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(btnTransferir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(panelSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSaldoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSacar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(51, 51, 51))
        );
        panelSaldoLayout.setVerticalGroup(
            panelSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSaldoLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCPF)
                .addGap(16, 16, 16)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSaldo)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(panelSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTransferir, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSacar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        panelExtrato.setBackground(new java.awt.Color(144, 185, 213));
        panelExtrato.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(228, 228, 228), 5, true));
        panelExtrato.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelExtrato.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        label1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("ULTIMAS TRANSAÇÕES");

        btnExtrato.setBackground(new java.awt.Color(255, 255, 255));
        btnExtrato.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnExtrato.setForeground(new java.awt.Color(1, 101, 198));
        btnExtrato.setText("Extrato Completo");
        btnExtrato.setToolTipText("");
        btnExtrato.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(144, 185, 213), 4, true));
        btnExtrato.setBorderPainted(false);
        btnExtrato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExtrato.setFocusPainted(false);

        javax.swing.GroupLayout panelExtratoLayout = new javax.swing.GroupLayout(panelExtrato);
        panelExtrato.setLayout(panelExtratoLayout);
        panelExtratoLayout.setHorizontalGroup(
            panelExtratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelExtratoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelExtratoLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(btnExtrato, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );
        panelExtratoLayout.setVerticalGroup(
            panelExtratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelExtratoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(525, 525, 525)
                .addComponent(btnExtrato, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelDivisao.setBackground(new java.awt.Color(114, 164, 197));
        panelDivisao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(228, 228, 228), 5, true));
        panelDivisao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelDivisao.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("DIVISÃO DO DINHEIRO");

        btnConsultarSaldo.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultarSaldo.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnConsultarSaldo.setForeground(new java.awt.Color(1, 101, 198));
        btnConsultarSaldo.setText("Saldo Total");
        btnConsultarSaldo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        btnConsultarSaldo.setBorderPainted(false);
        btnConsultarSaldo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConsultarSaldo.setFocusPainted(false);
        btnConsultarSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarSaldoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDivisaoLayout = new javax.swing.GroupLayout(panelDivisao);
        panelDivisao.setLayout(panelDivisaoLayout);
        panelDivisaoLayout.setHorizontalGroup(
            panelDivisaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDivisaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDivisaoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConsultarSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelDivisaoLayout.setVerticalGroup(
            panelDivisaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDivisaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addComponent(btnConsultarSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 29, 58));
        jLabel4.setText("Dashboard");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelDivisao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addComponent(panelExtrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelDivisao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelExtrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setSaldo(String saldo) {
        lblSaldo.setText(saldo);
    }
    
    private void lblHome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHome1MouseClicked
        TelaUsuario telaUser = new TelaUsuario();
        telaUser.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblHome1MouseClicked

    private void lblCarteiraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCarteiraMouseClicked

    }//GEN-LAST:event_lblCarteiraMouseClicked

    private void lblCriptoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCriptoMouseClicked
        Cripto cripto = new Cripto();
        cripto.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblCriptoMouseClicked

    private void btnTransferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferirActionPerformed
        c.realizarDeposito();
    }//GEN-LAST:event_btnTransferirActionPerformed

    private void btnSacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacarActionPerformed
        c.realizarSaque();
    }//GEN-LAST:event_btnSacarActionPerformed

    private void btnConsultarSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarSaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConsultarSaldoActionPerformed
    
                                        

  
    private ControllerOperacoes c;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultarSaldo;
    private javax.swing.JButton btnExtrato;
    private javax.swing.JButton btnSacar;
    private javax.swing.JButton btnTransferir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private java.awt.Label label1;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblCarteira;
    private javax.swing.JLabel lblCripto;
    private javax.swing.JLabel lblHome1;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JPanel panelDivisao;
    private javax.swing.JPanel panelExtrato;
    private javax.swing.JPanel panelSaldo;
    // End of variables declaration//GEN-END:variables
}
