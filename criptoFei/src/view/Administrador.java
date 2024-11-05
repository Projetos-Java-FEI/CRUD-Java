package view;

import controller.ControllerAdmin;


public class Administrador extends javax.swing.JFrame {

    public Administrador() {
        initComponents();
        c = new ControllerAdmin();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCadastrar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCadastrarCripto = new javax.swing.JButton();
        btnExcluirCripto = new javax.swing.JButton();
        btnConsultarSaldo = new javax.swing.JButton();
        btnConsultarExtrato = new javax.swing.JButton();
        btnCotacao = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCadastrar.setText("Cadastrar Investidor");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir Investidor");

        btnCadastrarCripto.setText("Cadastrar Cripto");
        btnCadastrarCripto.setMaximumSize(new java.awt.Dimension(150, 24));
        btnCadastrarCripto.setMinimumSize(new java.awt.Dimension(150, 24));
        btnCadastrarCripto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarCriptoActionPerformed(evt);
            }
        });

        btnExcluirCripto.setText("Excluir Cripto");
        btnExcluirCripto.setMaximumSize(new java.awt.Dimension(150, 24));
        btnExcluirCripto.setMinimumSize(new java.awt.Dimension(150, 24));

        btnConsultarSaldo.setText("Consultar Saldo");
        btnConsultarSaldo.setMaximumSize(new java.awt.Dimension(150, 24));
        btnConsultarSaldo.setMinimumSize(new java.awt.Dimension(150, 24));
        btnConsultarSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarSaldoActionPerformed(evt);
            }
        });

        btnConsultarExtrato.setText("Consultar Extrato");
        btnConsultarExtrato.setMaximumSize(new java.awt.Dimension(150, 24));
        btnConsultarExtrato.setMinimumSize(new java.awt.Dimension(150, 24));

        btnCotacao.setText("Atualizar Cotação");
        btnCotacao.setToolTipText("");
        btnCotacao.setMaximumSize(new java.awt.Dimension(150, 24));
        btnCotacao.setMinimumSize(new java.awt.Dimension(150, 24));
        btnCotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCotacaoActionPerformed(evt);
            }
        });

        btnSair.setText("Sair");
        btnSair.setMaximumSize(new java.awt.Dimension(150, 24));
        btnSair.setMinimumSize(new java.awt.Dimension(150, 24));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCadastrarCripto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExcluirCripto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConsultarSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConsultarExtrato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCotacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(857, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(btnCadastrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExcluir)
                .addGap(12, 12, 12)
                .addComponent(btnCadastrarCripto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExcluirCripto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConsultarSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConsultarExtrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCotacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(348, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        new Cadastro();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnConsultarSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarSaldoActionPerformed
        
    }//GEN-LAST:event_btnConsultarSaldoActionPerformed

    private void btnCadastrarCriptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarCriptoActionPerformed
        c.criarMoeda();
    }//GEN-LAST:event_btnCadastrarCriptoActionPerformed

    private void btnCotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCotacaoActionPerformed
        c.atualizaCotacao();
    }//GEN-LAST:event_btnCotacaoActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
       
    }//GEN-LAST:event_btnSairActionPerformed
    
    
    ControllerAdmin c;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCadastrarCripto;
    private javax.swing.JButton btnConsultarExtrato;
    private javax.swing.JButton btnConsultarSaldo;
    private javax.swing.JButton btnCotacao;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExcluirCripto;
    private javax.swing.JButton btnSair;
    // End of variables declaration//GEN-END:variables
}
