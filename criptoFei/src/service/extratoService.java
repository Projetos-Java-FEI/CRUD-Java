package service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class extratoService {
    private final Connection conn;

    public extratoService(Connection conn) {
        this.conn = conn;
    }

    public void registrarExtrato(int idUser, BigDecimal quantidade, String tipoOp, String tipoOperacao, LocalDateTime date) throws SQLException {
        String sqlInsertOp = "INSERT INTO extrato (id_user, quantidade, operacao_reais, tipo_operacao, data) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sqlInsertOp)) {
            statement.setInt(1, idUser);
            statement.setBigDecimal(2, quantidade);
            statement.setString(3, tipoOp);
            statement.setString(4, tipoOperacao);
            statement.setTimestamp(5, Timestamp.valueOf(date));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
