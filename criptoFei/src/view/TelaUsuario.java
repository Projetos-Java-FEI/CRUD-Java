package view;

import controller.ControllerUsuario;
import java.awt.Color;
import javax.swing.ImageIcon;
import model.User;
import service.SessionManager;


public class TelaUsuario extends javax.swing.JFrame {

    
    public TelaUsuario() {
        initComponents();
        User user = SessionManager.getUser();
        
        // usa o metodo da classe SessionManager para pegar valores do user logado
        lblNomeUsuario.setText(user.getNome()); 
        
        this.getContentPane().setBackground(new Color(42,42,42));
        setIconImage(new ImageIcon(getClass().getResource("/imagens/logoCriptoFei.png")).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        lblNomeUsuario = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblCarteira = new javax.swing.JLabel();
        lblHome1 = new javax.swing.JLabel();
        lblCripto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CriptoFEI");
        setResizable(false);

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 29, 58));
        jLabel3.setText("Bem-vindo,");

        lblNomeUsuario.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblNomeUsuario.setForeground(new java.awt.Color(0, 29, 58));
        lblNomeUsuario.setText("Nome");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNomeUsuario)
                .addGap(0, 762, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblNomeUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 544, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblCarteiraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCarteiraMouseClicked
        Carteira carteira = new Carteira();
        carteira.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblCarteiraMouseClicked

    private void lblHome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHome1MouseClicked
    }//GEN-LAST:event_lblHome1MouseClicked

    private void lblCriptoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCriptoMouseClicked
        Cripto cripto = new Cripto();
        cripto.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblCriptoMouseClicked
    
    private ControllerUsuario c;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCarteira;
    private javax.swing.JLabel lblCripto;
    private javax.swing.JLabel lblHome1;
    private javax.swing.JLabel lblNomeUsuario;
    // End of variables declaration//GEN-END:variables
}
