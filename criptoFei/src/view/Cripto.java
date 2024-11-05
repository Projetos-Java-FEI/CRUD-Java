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
        
        this.getContentPane().setBackground(new Color(228,228,228));
        setIconImage(new ImageIcon(getClass().getResource("/imagens/logoCriptoFei.png")).getImage());
    }

    public JTable getTblCriptos() {
        return tblCriptos;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblHome1 = new javax.swing.JLabel();
        lblCripto = new javax.swing.JLabel();
        lblCarteira = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCriptos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CriptoFEI");
        setBackground(new java.awt.Color(0, 29, 58));
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel2.setText("Criptos");

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
                .addContainerGap(374, Short.MAX_VALUE))
        );

        tblCriptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblCriptos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 472, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblHome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHome1MouseClicked
        TelaUsuario telaUser = new TelaUsuario();
        telaUser.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblHome1MouseClicked

    private void lblCarteiraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCarteiraMouseClicked
        Carteira c = new Carteira();
        c.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblCarteiraMouseClicked

   
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCarteira;
    private javax.swing.JLabel lblCripto;
    private javax.swing.JLabel lblHome1;
    private javax.swing.JTable tblCriptos;
    // End of variables declaration//GEN-END:variables
}
