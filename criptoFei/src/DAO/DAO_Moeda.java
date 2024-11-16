package DAO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Moeda;
import service.extratoService;
import service.cotacaoService;



public class DAO_Moeda {
    private Connection conn;
    

    public DAO_Moeda(Connection conn) {
        this.conn = conn;
        
    }
    
    public String adicionarMoeda(String simbolo, String nome, BigDecimal cotacao, BigDecimal taxaCompra, BigDecimal taxaVenda) throws SQLException {
        String sqlCripto = "INSERT INTO criptos (simbolo, nome, cotacao, taxa_compra, taxa_venda) VALUES (?, ?, ?, ?, ?)";
        String sqlHistorico = "INSERT INTO historico_cripto (simbolo, cotacao, data) VALUES (?, ?, ?)";

        try (PreparedStatement stmtCripto = conn.prepareStatement(sqlCripto);
             PreparedStatement stmtHistorico = conn.prepareStatement(sqlHistorico)) {

            // Inserir a nova criptomoeda na tabela criptos
            stmtCripto.setString(1, simbolo);
            stmtCripto.setString(2, nome);
            stmtCripto.setBigDecimal(3, cotacao);
            stmtCripto.setBigDecimal(4, taxaCompra);
            stmtCripto.setBigDecimal(5, taxaVenda);

            int rowsInserted = stmtCripto.executeUpdate();

            if (rowsInserted > 0) {
                // Inserir o histórico de cotação na tabela historico_cripto
                stmtHistorico.setString(1, simbolo);
                stmtHistorico.setBigDecimal(2, cotacao); // O valor da cotação é o valor inicial
                stmtHistorico.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now())); // Data/hora atual

                stmtHistorico.executeUpdate();

                return "Moeda adicionada com sucesso e histórico registrado!";
            } else {
                return "Falha ao adicionar moeda.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao adicionar moeda: " + e.getMessage();
        }
    }

    
    public String excluirMoeda(String simbolo) throws SQLException {
    // SQL para excluir a moeda pelo símbolo
    String sql = "DELETE FROM criptos WHERE simbolo = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, simbolo);  // Definindo o símbolo da moeda a ser excluída

        int rowsDeleted = stmt.executeUpdate();  // Executando a exclusão

        if (rowsDeleted > 0) {
            return "Moeda excluída com sucesso!";
        } else {
            return "Erro -> Moeda não encontrada.";
        }
    } catch (SQLException e) {
        return "Erro ao excluir moeda: " + e.getMessage();
    }
}


    
    public BigDecimal getTaxa(String simbolo, boolean isVenda) throws SQLException {
        String sqlGetTaxa = isVenda ? "SELECT taxa_venda FROM criptos WHERE simbolo = ?" 
                                    : "SELECT taxa_compra FROM criptos WHERE simbolo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlGetTaxa)) {
            stmt.setString(1, simbolo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return isVenda ? rs.getBigDecimal("taxa_venda") : rs.getBigDecimal("taxa_compra");
                } else {
                    throw new SQLException("Criptomoeda não encontrada.");
                }
            }
        }
    }

    public BigDecimal getSaldo(int idUser, String simbolo) throws SQLException {
        String sqlGetSaldo = "SELECT saldo FROM carteira WHERE id_user = ? AND simbolo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlGetSaldo)) {
            stmt.setInt(1, idUser);
            stmt.setString(2, simbolo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("saldo");
                } else {
                    return BigDecimal.ZERO;
                }
            }
        }
    }

    public void inserirMoedaNaCarteira(int idUser, String simbolo) throws SQLException {
        String sqlInsertMoeda = "INSERT INTO carteira (id_user, simbolo, saldo) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sqlInsertMoeda)) {
            stmt.setInt(1, idUser);
            stmt.setString(2, simbolo);
            stmt.setBigDecimal(3, BigDecimal.ZERO);
            stmt.executeUpdate();
        }
    }

    public void atualizarSaldoCompra(int idUser, String simboloMoeda, BigDecimal valorCompra, BigDecimal quantidade) throws SQLException {
        String sqlUpdateSaldo = "UPDATE carteira SET saldo = saldo - ? WHERE id_user = ? AND simbolo = 'BRL'; "
                + "UPDATE carteira SET saldo = saldo + ? WHERE id_user = ? AND simbolo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlUpdateSaldo)) {
            stmt.setBigDecimal(1, valorCompra);
            stmt.setInt(2, idUser);
            stmt.setBigDecimal(3, quantidade);
            stmt.setInt(4, idUser);
            stmt.setString(5, simboloMoeda);
            stmt.executeUpdate();
        }
    }
    
    public void atualizarSaldoVenda(int idUser, String simboloMoeda, BigDecimal valorVenda, BigDecimal quantidade) throws SQLException {
        String sqlUpdateSaldo = "UPDATE carteira SET saldo = saldo - ? WHERE id_user = ? AND simbolo = ?; "
                + "UPDATE carteira SET saldo = saldo + ? WHERE id_user = ? AND simbolo = 'BRL'";
        try (PreparedStatement stmt = conn.prepareStatement(sqlUpdateSaldo)) {
            stmt.setBigDecimal(1, quantidade);
            stmt.setInt(2, idUser);
            stmt.setString(3, simboloMoeda);
            stmt.setBigDecimal(4, valorVenda);
            stmt.setInt(5, idUser);
            stmt.executeUpdate();
        }
    }
    
    private boolean moedaExistenteNaCarteira(int idUser, String simbolo) throws SQLException {
    String sql = "SELECT COUNT(*) FROM carteira WHERE id_user = ? AND simbolo = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idUser);
        stmt.setString(2, simbolo);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0; // > 0, eh pq existe na carteira
            }
        }
    }
    return false; 
}

    public String comprarMoeda(Moeda moeda, int idUser, BigDecimal quantidade) throws SQLException {
        BigDecimal valorCompra = quantidade.multiply(BigDecimal.valueOf(moeda.getCotacao()));
        BigDecimal taxaCompra = getTaxa(moeda.getSimbolo(), false);
        System.out.println(taxaCompra);
        valorCompra = valorCompra.multiply(BigDecimal.ONE.add(taxaCompra));
        

        BigDecimal saldoBRL = getSaldo(idUser, "BRL");
        if (saldoBRL.compareTo(valorCompra) < 0) {
            return "Erro -> Saldo insuficiente para realizar a compra.";
        }

        if (!moedaExistenteNaCarteira(idUser, moeda.getSimbolo())) {
            inserirMoedaNaCarteira(idUser, moeda.getSimbolo());
        }

        atualizarSaldoCompra(idUser, moeda.getSimbolo(), valorCompra, quantidade);

        extratoService extratoService = new extratoService(conn);
        extratoService.registrarExtrato(idUser, quantidade, "- " + String.format("R$ %.2f", valorCompra), "Compra " + moeda.getSimbolo(), LocalDateTime.now().withNano(0));

        cotacaoService cotacaoService = new cotacaoService(conn);
        cotacaoService.atualizarCotacoes();

        return "Compra de " + quantidade + " " + moeda.getSimbolo() + " realizada com sucesso!";
    }


    public String venderMoeda(Moeda moeda, int idUser, BigDecimal quantidade) throws SQLException {
        BigDecimal valorVenda = quantidade.multiply(BigDecimal.valueOf(moeda.getCotacao()));

        BigDecimal taxaVenda = getTaxa(moeda.getSimbolo(), true);
        valorVenda = valorVenda.multiply(BigDecimal.ONE.subtract(taxaVenda));

        BigDecimal saldoMoeda = getSaldo(idUser, moeda.getSimbolo());
        if (saldoMoeda.compareTo(quantidade) < 0) {
            return "Erro -> Você não possui essa quantidade de " + moeda.getSimbolo();
        }

        atualizarSaldoVenda(idUser, moeda.getSimbolo(), valorVenda, quantidade);

        extratoService extratoService = new extratoService(conn);
        extratoService.registrarExtrato(idUser, quantidade, "+ " + String.format("R$ %.2f", valorVenda), 
                                        "Venda " + moeda.getSimbolo(), LocalDateTime.now().withNano(0));

        cotacaoService cotacaoService = new cotacaoService(conn);
        cotacaoService.atualizarCotacoes();
      

        return "Venda de " + quantidade + " " + moeda.getSimbolo() + " realizada com sucesso!";
    }



    public void atualizarCotacoes() throws SQLException {
        String sqlSelect = "SELECT simbolo, cotacao FROM criptos";
        String sqlUpdate = "UPDATE criptos SET cotacao = ? WHERE simbolo = ?";
        String sqlInsertHistorico = "INSERT INTO historico_cripto"
                + " (simbolo, cotacao, data)"
                + " VALUES (?, ?, ?)";

        try (PreparedStatement selectStmt = conn.prepareStatement(sqlSelect);
             ResultSet rs = selectStmt.executeQuery()) {

            while (rs.next()) {
                String simbolo = rs.getString("simbolo");
                BigDecimal cotacaoAntiga = rs.getBigDecimal("cotacao");

                // Verifica se o símbolo é diferente de "BRL" (Real) antes de atualizar a cotação
                if (!simbolo.equals("BRL")) {
                    // Calcular nova cotação com variação entre -5% e +5%
                    BigDecimal variacao = cotacaoAntiga.multiply(BigDecimal.valueOf(Math.random() * 0.1 - 0.05)); // variação entre -5% e +5%
                    BigDecimal novaCotacao = cotacaoAntiga.add(variacao).setScale(2, RoundingMode.HALF_UP); // Ajustar para 2 casas decimais

                    // Inserir a cotação antiga no histórico
                    try (PreparedStatement insertHistoricoStmt = conn.prepareStatement(sqlInsertHistorico)) {
                        insertHistoricoStmt.setString(1, simbolo);
                        insertHistoricoStmt.setBigDecimal(2, cotacaoAntiga);
                        insertHistoricoStmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now())); // data/hora atual
                        insertHistoricoStmt.executeUpdate();
                    }

                    // Atualizar a nova cotação na tabela de criptos
                    try (PreparedStatement updateStmt = conn.prepareStatement(sqlUpdate)) {
                        updateStmt.setBigDecimal(1, novaCotacao);
                        updateStmt.setString(2, simbolo);
                        updateStmt.executeUpdate();
                    }
                }
            }
        }
    }

    public ArrayList<Object[]> getCriptos() throws SQLException {
        ArrayList listaCriptos = new ArrayList<>();
        String sql = "SELECT simbolo, nome, cotacao FROM criptos WHERE simbolo != 'BRL'";
        PreparedStatement stmt = conn.prepareStatement(sql); 
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()) {
            
            Object[] criptos = new Object[3]; 
            criptos[0] = rs.getString("simbolo");
            criptos[1] = rs.getString("nome");
            criptos[2] = rs.getDouble("cotacao");
            
            listaCriptos.add(criptos);
        }
        
        return listaCriptos; // retorna o arraylist com os valores das criptos
    }
    
    public ArrayList<Object[]> getCriptosUsuario(int userId) throws SQLException {
        ArrayList<Object[]> listaCriptosUser = new ArrayList<>();
        String sql = "SELECT simbolo, saldo FROM carteira WHERE simbolo != 'BRL' AND id_user = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    Object[] criptos = new Object[2];
                    criptos[0] = rs.getDouble("saldo");
                    criptos[1] = rs.getString("simbolo");
                    
                    listaCriptosUser.add(criptos);
                }
            }
        }
        return listaCriptosUser;
    } 
}
   

