package view;

import controller.ControllerUsuario;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import model.User;
import service.SessionManager;


public class TelaUsuario extends javax.swing.JFrame {

    
    public TelaUsuario() {
        initComponents();
        User user = SessionManager.getUser();
        
        // usa o metodo da classe SessionManager para pegar valores do user logado
        lblNomeUsuario.setText(user.getNome()); 
        
        this.getContentPane().setBackground(new Color(228,228,228));
        setIconImage(new ImageIcon(getClass().getResource("/imagens/logoCriptoFei.png")).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        lblNomeUsuario = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblHome1 = new javax.swing.JLabel();
        lblCripto = new javax.swing.JLabel();
        lblCarteira = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CriptoFEI");
        setResizable(false);

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 29, 58));
        jLabel3.setText("Bem-vindo,");

        lblNomeUsuario.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblNomeUsuario.setForeground(new java.awt.Color(0, 29, 58));
        lblNomeUsuario.setText("Nome");

        jPanel2.setBackground(new java.awt.Color(1, 101, 198));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/logoCriptoFei.png"))); // NOI18N

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(17, Short.MAX_VALUE))
            .addComponent(lblHome1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblCarteira, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblCripto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(41, 41, 41)
                .addComponent(lblHome1)
                .addGap(43, 43, 43)
                .addComponent(lblCarteira)
                .addGap(53, 53, 53)
                .addComponent(lblCripto)
                .addContainerGap(374, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNomeUsuario)
                .addGap(0, 762, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblNomeUsuario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblHome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHome1MouseClicked

    }//GEN-LAST:event_lblHome1MouseClicked

    private void lblCarteiraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCarteiraMouseClicked
        Carteira c = new Carteira();
        c.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblCarteiraMouseClicked

    private void lblCriptoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCriptoMouseClicked
        Cripto cripto = new Cripto();
        cripto.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblCriptoMouseClicked

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
//            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new TelaUsuario().setVisible(true);
//            }
//        });
//    }
    
    private ControllerUsuario c;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCarteira;
    private javax.swing.JLabel lblCripto;
    private javax.swing.JLabel lblHome1;
    private javax.swing.JLabel lblNomeUsuario;
    // End of variables declaration//GEN-END:variables
}
