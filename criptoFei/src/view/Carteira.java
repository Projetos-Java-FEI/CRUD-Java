package view;

import controller.ControllerExtrato;
import controller.ControllerOperacoes;
import java.awt.Color;
import javax.swing.ImageIcon;
import model.User;
import javax.swing.JTable;
import service.SessionManager;

public class Carteira extends javax.swing.JFrame {

    public Carteira() {
        initComponents();
        cExtrato = new ControllerExtrato(this);
        cExtrato.carregarExtrato();
        
        c = new ControllerOperacoes(this, SessionManager.getUser());
        
        User user = SessionManager.getUser();
        lblNome.setText(user.getNome());
        lblCPF.setText(user.formatarCpf(user.getCpf()));
        
        lblSaldo.setText(c.getSaldo().toString());
        
        this.getContentPane().setBackground(new Color(42,42,42));
        setIconImage(new ImageIcon(getClass().getResource("/imagens/logoCriptoFei.png")).getImage());
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblCarteira = new javax.swing.JLabel();
        lblHome1 = new javax.swing.JLabel();
        lblCripto = new javax.swing.JLabel();
        panelSaldo = new javax.swing.JPanel();
        btnTransferir = new javax.swing.JButton();
        lblNome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblCPF = new javax.swing.JLabel();
        btnSacar = new javax.swing.JButton();
        panelDivisao = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnConsultarSaldo = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblExtrato = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CriptoFEI");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusable(false);
        setMaximumSize(null);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(63, 63, 63));

        lblCarteira.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCarteira.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/wallet.png"))); // NOI18N
        lblCarteira.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCarteira.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCarteiraMouseClicked(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(366, 366, 366)
                .addComponent(lblHome1)
                .addGap(126, 126, 126)
                .addComponent(lblCarteira)
                .addGap(126, 126, 126)
                .addComponent(lblCripto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCripto)
                    .addComponent(lblHome1)
                    .addComponent(lblCarteira))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        panelSaldo.setBackground(new java.awt.Color(63, 63, 63));
        panelSaldo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(63, 63, 63), 5, true));
        panelSaldo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelSaldo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        btnTransferir.setBackground(new java.awt.Color(42, 42, 42));
        btnTransferir.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnTransferir.setForeground(new java.awt.Color(50, 153, 254));
        btnTransferir.setText("Depositar");
        btnTransferir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(50, 153, 254), 1, true));
        btnTransferir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTransferir.setFocusPainted(false);
        btnTransferir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTransferirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTransferirMouseExited(evt);
            }
        });
        btnTransferir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferirActionPerformed(evt);
            }
        });

        lblNome.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblNome.setForeground(new java.awt.Color(50, 153, 254));
        lblNome.setText("Nome");

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(50, 153, 254));
        jLabel1.setText("R$");

        lblSaldo.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblSaldo.setForeground(new java.awt.Color(50, 153, 254));
        lblSaldo.setText("saldo");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(50, 153, 254));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Saldo Disponível");

        lblCPF.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblCPF.setForeground(new java.awt.Color(50, 153, 254));
        lblCPF.setText("CPF");

        btnSacar.setBackground(new java.awt.Color(42, 42, 42));
        btnSacar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSacar.setForeground(new java.awt.Color(50, 153, 254));
        btnSacar.setText("Sacar");
        btnSacar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(50, 153, 254), 1, true));
        btnSacar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSacar.setFocusPainted(false);
        btnSacar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSacarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSacarMouseExited(evt);
            }
        });
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
                    .addComponent(lblCPF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSaldoLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(btnTransferir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(panelSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(panelSaldoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSacar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        panelSaldoLayout.setVerticalGroup(
            panelSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSaldoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCPF)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSaldo)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(panelSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTransferir, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSacar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        panelDivisao.setBackground(new java.awt.Color(63, 63, 63));
        panelDivisao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(63, 63, 63), 5, true));
        panelDivisao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelDivisao.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("DIVISÃO DO DINHEIRO");

        btnConsultarSaldo.setBackground(new java.awt.Color(42, 42, 42));
        btnConsultarSaldo.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnConsultarSaldo.setForeground(new java.awt.Color(50, 153, 254));
        btnConsultarSaldo.setText("Saldo Total");
        btnConsultarSaldo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(50, 153, 254), 1, true));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(btnConsultarSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(63, 63, 63));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(63, 63, 63), 5, true));

        jScrollPane1.setBackground(new java.awt.Color(42, 42, 42));
        jScrollPane1.setForeground(new java.awt.Color(42, 42, 42));
        jScrollPane1.setToolTipText("");

        tblExtrato.setBackground(new java.awt.Color(42, 42, 42));
        tblExtrato.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        tblExtrato.setForeground(new java.awt.Color(255, 255, 255));
        tblExtrato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Quantidade", "Variação", "Operação", "Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblExtrato.setEnabled(false);
        tblExtrato.setGridColor(new java.awt.Color(255, 255, 255));
        tblExtrato.setOpaque(false);
        tblExtrato.setRowHeight(30);
        tblExtrato.setShowGrid(true);
        jScrollPane1.setViewportView(tblExtrato);
        if (tblExtrato.getColumnModel().getColumnCount() > 0) {
            tblExtrato.getColumnModel().getColumn(2).setMinWidth(90);
            tblExtrato.getColumnModel().getColumn(3).setMinWidth(100);
        }

        jLabel6.setBackground(new java.awt.Color(63, 63, 63));
        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(50, 153, 254));
        jLabel6.setText("Extrato");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/logoCriptoFei.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1004, 1004, 1004))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDivisao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(panelDivisao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void atualizarExtrato() {
        cExtrato.carregarExtrato();
    }
    
    public void setSaldo(String saldo) {
        lblSaldo.setText(saldo);
    }

    public JTable getTblExtrato() {
        return tblExtrato;
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

    private void btnTransferirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransferirMouseEntered
        btnTransferir.setBackground(new Color(50,153,254));
        btnTransferir.setForeground(new Color(42,42,42));
        btnTransferir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 42, 42), 2, false));
    }//GEN-LAST:event_btnTransferirMouseEntered

    private void btnSacarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSacarMouseEntered
        btnSacar.setBackground(new Color(50,153,254));
        btnSacar.setForeground(new Color(42,42,42));
        btnSacar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 42, 42), 2, false));
    }//GEN-LAST:event_btnSacarMouseEntered

    private void btnTransferirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransferirMouseExited
        btnTransferir.setBackground(new Color(42,42,42));
        btnTransferir.setForeground(new Color(50,153,254));
        btnTransferir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(50,153,254), 1, false));
        
    }//GEN-LAST:event_btnTransferirMouseExited

    private void btnSacarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSacarMouseExited
        btnSacar.setBackground(new Color(42,42,42));
        btnSacar.setForeground(new Color(50,153,254));
        btnSacar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(50,153,254), 1, false));
    }//GEN-LAST:event_btnSacarMouseExited
    
                                        

    private ControllerExtrato cExtrato;
    private ControllerOperacoes c;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultarSaldo;
    private javax.swing.JButton btnSacar;
    private javax.swing.JButton btnTransferir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblCarteira;
    private javax.swing.JLabel lblCripto;
    private javax.swing.JLabel lblHome1;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JPanel panelDivisao;
    private javax.swing.JPanel panelSaldo;
    private javax.swing.JTable tblExtrato;
    // End of variables declaration//GEN-END:variables
}
