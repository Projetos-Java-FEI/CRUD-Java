package service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.RoundingMode; 

public class cotacaoService {
    private Connection conn;

    public cotacaoService(Connection conn) {
        this.conn = conn;
    }

    // Método para gerar a nova cotação com uma variação aleatória entre -5% e +5%
    private BigDecimal gerarNovaCotacao(BigDecimal cotacaoAntiga) {
        BigDecimal percentualVariacao = BigDecimal.valueOf(Math.random() * 0.1 - 0.05); // Gera variação entre -5% e +5%
        return cotacaoAntiga.add(cotacaoAntiga.multiply(percentualVariacao)).setScale(8, RoundingMode.HALF_UP); // Usa RoundingMode
    }

    // Método para atualizar as cotações de todas as criptomoedas, exceto BRL
    public void atualizarCotacoes() throws SQLException {
        String selectSql = "SELECT simbolo, cotacao FROM criptos WHERE simbolo <> 'BRL'"; // Exclui o BRL da atualização
        String updateSql = "UPDATE criptos SET cotacao = ? WHERE simbolo = ?";
        String insertHistoricoSql = "INSERT INTO historico_cripto (simbolo, cotacao) VALUES (?, ?)";

        try (PreparedStatement selectStmt = conn.prepareStatement(selectSql);
             ResultSet rs = selectStmt.executeQuery();
             PreparedStatement updateStmt = conn.prepareStatement(updateSql);
             PreparedStatement insertHistoricoStmt = conn.prepareStatement(insertHistoricoSql)) {

            while (rs.next()) {
                String simbolo = rs.getString("simbolo");
                BigDecimal cotacaoAntiga = rs.getBigDecimal("cotacao");
                BigDecimal novaCotacao = gerarNovaCotacao(cotacaoAntiga);

                // Atualiza a cotação na tabela 'criptos'
                updateStmt.setBigDecimal(1, novaCotacao);
                updateStmt.setString(2, simbolo);
                updateStmt.executeUpdate();

                // Insere o histórico da cotação na tabela 'historico_cripto'
                insertHistoricoStmt.setString(1, simbolo);
                insertHistoricoStmt.setBigDecimal(2, cotacaoAntiga); // Insere a cotação antiga como histórico
                insertHistoricoStmt.executeUpdate();
            }
        }
    }
}
