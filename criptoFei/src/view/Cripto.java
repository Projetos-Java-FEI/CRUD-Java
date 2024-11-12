package view;

import controller.ControllerCripto;
import controller.ControllerOperacoes;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.sql.SQLException;

public class Cripto extends javax.swing.JFrame {
    
    public Cripto() {
        initComponents();
        cOperacoes = new ControllerOperacoes();
        c = new ControllerCripto(this);
      
        lblSaldo.setText(String.format("R$ %s", cOperacoes.getSaldo()));
        c.carregarCriptos();
        c.carregarCriptosUsuario();
               
        this.getContentPane().setBackground(new Color(42,42,42));
        setIconImage(new ImageIcon(getClass().getResource("/imagens/logoCriptoFei.png")).getImage());
    }
    
    public void atualizarCriptosUsuarios() {
        c.carregarCriptosUsuario();
    }
    
    public void atualizarCriptos() {
        c.carregarCriptos();
    }

    public JTable getTblCriptos() {
        return tblCriptos;
    }
    
    public JTable getTblCriptosUser() {
        return tblCriptosUser;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblCarteira = new javax.swing.JLabel();
        lblHome1 = new javax.swing.JLabel();
        lblCripto = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCriptosUser = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCriptos = new javax.swing.JTable();
        btnComprarCripto = new javax.swing.JButton();
        btnVenderCripto = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();
        lblSair = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CriptoFEI");
        setBackground(new java.awt.Color(0, 29, 58));
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 153, 255));
        jLabel2.setText("Criptos");

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

        jPanel1.setBackground(new java.awt.Color(63, 63, 63));

        jScrollPane2.setBackground(new java.awt.Color(42, 42, 42));
        jScrollPane2.setForeground(new java.awt.Color(42, 42, 42));

        tblCriptosUser.setBackground(new java.awt.Color(42, 42, 42));
        tblCriptosUser.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblCriptosUser.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        tblCriptosUser.setForeground(new java.awt.Color(255, 255, 255));
        tblCriptosUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Quantidade", "Símbolo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCriptosUser.setEnabled(false);
        tblCriptosUser.setGridColor(new java.awt.Color(255, 255, 255));
        tblCriptosUser.setRowHeight(40);
        tblCriptosUser.setSelectionBackground(new java.awt.Color(42, 42, 42));
        tblCriptosUser.setSelectionForeground(new java.awt.Color(42, 42, 42));
        tblCriptosUser.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tblCriptosUser);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 153, 255));
        jLabel1.setText("Suas Criptos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 125, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(62, 62, 62));

        jScrollPane1.setBackground(new java.awt.Color(42, 42, 42));
        jScrollPane1.setForeground(new java.awt.Color(42, 42, 42));

        tblCriptos.setBackground(new java.awt.Color(42, 42, 42));
        tblCriptos.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        tblCriptos.setForeground(new java.awt.Color(255, 255, 255));
        tblCriptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Símbolo", "Nome", "Cotação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCriptos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblCriptos.setEditingColumn(0);
        tblCriptos.setEditingRow(0);
        tblCriptos.setFocusable(false);
        tblCriptos.setGridColor(new java.awt.Color(255, 255, 255));
        tblCriptos.setName("Extrato"); // NOI18N
        tblCriptos.setRowHeight(40);
        tblCriptos.setSelectionForeground(new java.awt.Color(42, 42, 42));
        tblCriptos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCriptos.setShowGrid(true);
        jScrollPane1.setViewportView(tblCriptos);

        btnComprarCripto.setBackground(new java.awt.Color(42, 42, 42));
        btnComprarCripto.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnComprarCripto.setForeground(new java.awt.Color(102, 153, 255));
        btnComprarCripto.setText("Comprar Cripto");
        btnComprarCripto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 153, 255), 1, true));
        btnComprarCripto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnComprarCripto.setFocusable(false);
        btnComprarCripto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnComprarCriptoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnComprarCriptoMouseExited(evt);
            }
        });
        btnComprarCripto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarCriptoActionPerformed(evt);
            }
        });

        btnVenderCripto.setBackground(new java.awt.Color(42, 42, 42));
        btnVenderCripto.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnVenderCripto.setForeground(new java.awt.Color(102, 153, 255));
        btnVenderCripto.setText("Vender Cripto");
        btnVenderCripto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 153, 255), 1, true));
        btnVenderCripto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVenderCripto.setFocusable(false);
        btnVenderCripto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVenderCriptoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVenderCriptoMouseExited(evt);
            }
        });
        btnVenderCripto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderCriptoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 153, 255));
        jLabel3.setText("Saldo:");

        lblSaldo.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblSaldo.setForeground(new java.awt.Color(102, 153, 255));
        lblSaldo.setText("saldo");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnComprarCripto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnVenderCripto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnComprarCripto, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnVenderCripto, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(lblSaldo)))
                .addGap(30, 30, 30))
        );

        lblSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/botaoDesligar.png"))); // NOI18N
        lblSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSairMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSair)
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblSair)))
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblCarteiraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCarteiraMouseClicked
        Carteira carteira = new Carteira();
        carteira.setLocationRelativeTo(null); 
        carteira.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblCarteiraMouseClicked

    private void lblHome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHome1MouseClicked
        TelaUsuario telaUser = new TelaUsuario();
        telaUser.setLocationRelativeTo(null);
        telaUser.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblHome1MouseClicked

    private void lblCriptoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCriptoMouseClicked
    }//GEN-LAST:event_lblCriptoMouseClicked

    private void btnComprarCriptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarCriptoActionPerformed
        c.compraCripto(getTblCriptos());
    }//GEN-LAST:event_btnComprarCriptoActionPerformed

    private void btnVenderCriptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderCriptoActionPerformed
        c.vendeCripto(getTblCriptos());
    }//GEN-LAST:event_btnVenderCriptoActionPerformed

    private void btnComprarCriptoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComprarCriptoMouseEntered
        btnComprarCripto.setBackground(new Color(50,153,254));
        btnComprarCripto.setForeground(new Color(42,42,42));
        btnComprarCripto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 42, 42), 2, false));
    }//GEN-LAST:event_btnComprarCriptoMouseEntered

    private void btnComprarCriptoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComprarCriptoMouseExited
        btnComprarCripto.setBackground(new Color(42,42,42));
        btnComprarCripto.setForeground(new Color(50,153,254));
        btnComprarCripto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(50,153,254), 1, false));
    }//GEN-LAST:event_btnComprarCriptoMouseExited

    private void btnVenderCriptoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVenderCriptoMouseEntered
        btnVenderCripto.setBackground(new Color(50,153,254));
        btnVenderCripto.setForeground(new Color(42,42,42));
        btnVenderCripto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 42, 42), 2, false));
    }//GEN-LAST:event_btnVenderCriptoMouseEntered

    private void btnVenderCriptoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVenderCriptoMouseExited
        btnVenderCripto.setBackground(new Color(42,42,42));
        btnVenderCripto.setForeground(new Color(50,153,254));
        btnVenderCripto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(50,153,254), 1, false));
    }//GEN-LAST:event_btnVenderCriptoMouseExited

    private void lblSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSairMouseClicked
        Login l = new Login();
        l.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblSairMouseClicked

   
    ControllerOperacoes cOperacoes;
    ControllerCripto c;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComprarCripto;
    private javax.swing.JToggleButton btnVenderCripto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCarteira;
    private javax.swing.JLabel lblCripto;
    private javax.swing.JLabel lblHome1;
    private javax.swing.JLabel lblSair;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JTable tblCriptos;
    private javax.swing.JTable tblCriptosUser;
    // End of variables declaration//GEN-END:variables
}
