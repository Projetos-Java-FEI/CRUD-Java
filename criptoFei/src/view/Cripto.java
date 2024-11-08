package view;

import controller.ControllerCripto;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTable;


public class Cripto extends javax.swing.JFrame {
    
    public Cripto() {
        initComponents();
        
        c = new ControllerCripto(this);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCriptos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblCarteira = new javax.swing.JLabel();
        lblHome1 = new javax.swing.JLabel();
        lblCripto = new javax.swing.JLabel();
        btnComprarCripto = new javax.swing.JButton();
        btnVenderCripto = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCriptosUser = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CriptoFEI");
        setBackground(new java.awt.Color(0, 29, 58));
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel2.setText("Criptos");

        jScrollPane1.setBackground(new java.awt.Color(42, 42, 42));
        jScrollPane1.setForeground(new java.awt.Color(42, 42, 42));

        tblCriptos.setBackground(new java.awt.Color(102, 153, 255));
        tblCriptos.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tblCriptos.setForeground(new java.awt.Color(255, 255, 255));
        tblCriptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
        tblCriptos.setName("Extrato"); // NOI18N
        tblCriptos.setRowHeight(30);
        tblCriptos.setRowSelectionAllowed(true);
        tblCriptos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCriptos.setShowGrid(false);
        tblCriptos.setShowHorizontalLines(true);
        jScrollPane1.setViewportView(tblCriptos);

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

        btnComprarCripto.setText("Comprar Cripto");
        btnComprarCripto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarCriptoActionPerformed(evt);
            }
        });

        btnVenderCripto.setText("Vender Cripto");
        btnVenderCripto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderCriptoActionPerformed(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(42, 42, 42));
        jScrollPane2.setForeground(new java.awt.Color(42, 42, 42));

        tblCriptosUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
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
        tblCriptosUser.setRowHeight(25);
        jScrollPane2.setViewportView(tblCriptosUser);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jLabel2)
                .addGap(0, 879, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(btnComprarCripto)
                        .addGap(49, 49, 49)
                        .addComponent(btnVenderCripto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVenderCripto)
                            .addComponent(btnComprarCripto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblCarteiraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCarteiraMouseClicked
        Carteira carteira = new Carteira();
        carteira.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblCarteiraMouseClicked

    private void lblHome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHome1MouseClicked
        TelaUsuario telaUser = new TelaUsuario();
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

   
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Cripto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Cripto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Cripto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Cripto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Cripto().setVisible(true);
//            }
//        });
//    }
    ControllerCripto c;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComprarCripto;
    private javax.swing.JToggleButton btnVenderCripto;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCarteira;
    private javax.swing.JLabel lblCripto;
    private javax.swing.JLabel lblHome1;
    private javax.swing.JTable tblCriptos;
    private javax.swing.JTable tblCriptosUser;
    // End of variables declaration//GEN-END:variables
}
