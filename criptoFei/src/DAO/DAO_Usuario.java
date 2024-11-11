package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.User;
import org.mindrot.jbcrypt.BCrypt;
import service.SessionManager;
import service.extratoService;
import service.cotacaoService;


public class DAO_Usuario {
    private Connection conn;

    public DAO_Usuario(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet verificarLogin(User user) throws SQLException {
        String sql = "SELECT * FROM users WHERE cpf = ? AND senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getCpf());
        statement.setString(2, user.getSenha());
        return statement.executeQuery();
    }
    

    public boolean verificarSenha(String senhaFornecida) throws SQLException {
        String sqlVerifySenha = "SELECT senha FROM users WHERE id_user = ?";
        try (PreparedStatement verifyStmt = conn.prepareStatement(sqlVerifySenha)) {
            verifyStmt.setInt(1, SessionManager.getUser().getId());

            try (ResultSet rs = verifyStmt.executeQuery()) {
                if (rs.next()) {
                    String senhaHash = rs.getString("senha"); // Recupera o hash da senha
                    return BCrypt.checkpw(senhaFornecida, senhaHash); // Compara a senha fornecida com o hash
                }
            }
        }
        return false; // Retorna falso se o usuário não foi encontrado ou se a senha está incorreta
    }


    
    public void cadastro(User user) throws SQLException {
        String sqlInsertUser = "INSERT INTO users (nome, cpf, senha, user_type) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sqlInsertUser);

        try {
            // Inserindo o usuário no banco de dados
            statement.setString(1, user.getNome());
            statement.setString(2, user.getCpf());
            statement.setString(3, user.getSenha());
            statement.setString(4, "Investidor");
            statement.executeUpdate();

            // Obter o ID do usuário recém-criado
            int userId = getUserId(user.getCpf()); // método para obter o ID do usuário baseado no CPF

            String sqlInsertCarteira = "INSERT INTO carteira (id_user, simbolo, saldo) VALUES (?, ?, ?)";
            
            try (PreparedStatement statementCarteira = conn.prepareStatement(sqlInsertCarteira)) {
                
            String[] criptos = {"BTC", "ETH", "XRP", "BRL"};
            for (String cripto : criptos) {
                statementCarteira.setInt(1, userId);
                statementCarteira.setString(2, cripto);
                statementCarteira.setBigDecimal(3, BigDecimal.ZERO); // saldo inicial zero para cada cripto
                statementCarteira.executeUpdate();
            }
        }              
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
}

    public String depositar(User user, BigDecimal valor) throws SQLException {  
        String sqlUpdateSaldo = "UPDATE carteira SET saldo = saldo + ? WHERE id_user = ? AND simbolo = 'BRL'";

        try (PreparedStatement statement = conn.prepareStatement(sqlUpdateSaldo)) {
            statement.setBigDecimal(1, valor);
            statement.setInt(2, user.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                String tipoOp = String.format("+ R$ %s", valor.toString());

                // Registrar operação no extrato
                extratoService extratoService = new extratoService(conn);
                extratoService.registrarExtrato(user.getId(), valor, tipoOp, "Depósito BRL", LocalDateTime.now().withNano(0));

                // Atualiza as cotações das criptos
                cotacaoService cotacaoService = new cotacaoService(conn);
                cotacaoService.atualizarCotacoes(); // Atualiza as cotações após o depósito
                return "Depósito realizado com sucesso! Valor depositado: R$ " + valor;

            } else {
                return "Erro -> Falha ao realizar o depósito. Usuário não encontrado ou saldo não disponível.";
            }
        }
    }

    public String sacar(User user, BigDecimal valor) throws SQLException {
        String sqlCheckSaldo = "SELECT saldo FROM carteira WHERE id_user = ? AND simbolo = 'BRL'";
        String sqlUpdateSaldo = "UPDATE carteira SET saldo = saldo - ? WHERE id_user = ? AND simbolo = 'BRL'";

        try (PreparedStatement checkStmt = conn.prepareStatement(sqlCheckSaldo)) {
            checkStmt.setInt(1, user.getId());

            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    BigDecimal saldoAtual = rs.getBigDecimal("saldo");

                    if (saldoAtual.compareTo(valor) < 0) {
                        return "Erro -> Saldo insuficiente para realizar o saque.";
                    }

                    try (PreparedStatement updateStmt = conn.prepareStatement(sqlUpdateSaldo)) {
                        updateStmt.setBigDecimal(1, valor);
                        updateStmt.setInt(2, user.getId());

                        int rowsUpdated = updateStmt.executeUpdate();
                        if (rowsUpdated > 0) {
                            String tipoOp = String.format("- R$ %s", valor.toString());

                            // Registrar operação no extrato
                            extratoService extratoService = new extratoService(conn);
                            extratoService.registrarExtrato(user.getId(), valor, tipoOp, "Saque BRL", LocalDateTime.now().withNano(0));

                            // Atualiza as cotações das criptos
                            cotacaoService cotacaoService = new cotacaoService(conn);
                            cotacaoService.atualizarCotacoes();
                            return "Saque realizado com sucesso! Valor sacado: R$ " + valor;
                        }
                    }
                } else {
                    return "Erro -> Usuário não encontrado.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro -> Falha ao realizar o saque.";
        }
        return "Erro -> Operação falhou.";
    }



        // Método para obter o ID do usuário baseado no CPF
    public int getUserId(String cpf) throws SQLException {
        String sqlSelectId = "SELECT id_user FROM users WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlSelectId)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_user");
                }
            }
        }
        return -1; // retorna -1 se o usuário não for encontrado
    }
    
    public BigDecimal obterSaldoReais(int userId) {
        BigDecimal saldo = BigDecimal.ZERO;  // variável para armazenar o saldo

        // Consulta para obter o saldo da moeda 'BRL' na carteira do usuário
        String sql = "SELECT saldo FROM carteira WHERE id_user = ? AND simbolo = 'BRL'";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);  

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    saldo = rs.getBigDecimal("saldo"); 
                } else {
                    System.out.println("Saldo em Reais (BRL) não encontrado para o usuário.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return saldo;
    }

   
    public void extrato(int idUser, BigDecimal quantidade, String tipoOp, String tipoOperacao, LocalDateTime date) throws SQLException {
        String sqlInsertOp = "INSERT INTO extrato (id_user, quantidade, operacao_reais, tipo_operacao, data)"
         + " VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sqlInsertOp);

        try {
            statement.setInt(1, idUser);
            statement.setBigDecimal(2, quantidade);
            statement.setString(3, tipoOp);
            statement.setString(4, tipoOperacao);
            statement.setTimestamp(5, Timestamp.valueOf(date));
            statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
    }
     
    public ArrayList<Object[]> getExtrato(User user) throws SQLException {
        ArrayList listaExtrato = new ArrayList<>();
        String sql = "SELECT quantidade, operacao_reais, tipo_operacao, data FROM extrato WHERE id_user = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, user.getId()); 
        ResultSet rs = stmt.executeQuery();
        
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00"); 
        // formatar o número para mostrar apenas com 2 casas decimais
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // formata a data para tirar os nano segundos presentes na lib
        
        while(rs.next()) {
            
            Object[] extrato = new Object[4]; 
            extrato[0] = decimalFormat.format(rs.getBigDecimal("quantidade")).toString();
            extrato[1] = rs.getString("operacao_reais");
            extrato[2] = rs.getString("tipo_operacao");
            Timestamp timestamp = rs.getTimestamp("data");
            extrato[3] = dateFormat.format(timestamp);
            
            listaExtrato.add(extrato);
        }
        
        return listaExtrato; // retorna o arraylist com os valores do extrato
    }
    
     public boolean autenticar(String cpf, String senhaInserida) throws SQLException {
        String sql = "SELECT senha FROM users WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String senhaArmazenada = rs.getString("senha");
                    // Verifica se a senha inserida corresponde ao hash armazenado
                    return BCrypt.checkpw(senhaInserida, senhaArmazenada);
                }
            }
        }
        return false;  // Retorna falso se o CPF não foi encontrado
    }
     
    public ResultSet buscarUsuarioPorCpf(User user) throws SQLException {
        String sql = "SELECT * FROM users WHERE cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getCpf());
        return statement.executeQuery();
    }
    
    public static Map<String, Double> getSaldoCriptos(int idUser) throws SQLException {  
        Map<String, Double> saldoTotal = new HashMap<>();
        String sql = "SELECT c.simbolo, ca.saldo, c.cotacao " +
                       "FROM carteira ca " +
                       "JOIN criptos c ON ca.simbolo = c.simbolo " +
                       "WHERE ca.id_user = ?";
        
        try (PreparedStatement stmt = new Conexao().getConnection().prepareStatement(sql)) {
            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                String simbolo = rs.getString("simbolo");
                double saldo = rs.getDouble("saldo");
                double cotacao = rs.getDouble("cotacao");
                double valorTotal = saldo * cotacao;
                
                saldoTotal.put(simbolo, valorTotal);
        }
            } catch(Exception e) {
                    e.printStackTrace();
            }
        return saldoTotal;
    }


    
    
    
    
    
}
