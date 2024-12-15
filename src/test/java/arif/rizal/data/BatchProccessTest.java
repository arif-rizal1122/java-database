package arif.rizal.data;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class BatchProccessTest {

    @Test
    void testBatchStatement() throws SQLException {
        String sql = "INSERT INTO comments(email, comment) VALUES ('b@gmail.com', 'c')";
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            for (int i = 0; i < 5; i++) {
                statement.addBatch(sql);
            }
            statement.executeBatch();
            String selectSql = "SELECT * FROM comments";
            try (ResultSet resultSet = statement.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    System.out.println("email : " + resultSet.getString("email"));
                    System.out.println("comment : " + resultSet.getString("comment"));
                }
            }
        }
    }

    @Test
    void testBatchInPreparedStatment() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            statement.setString(1, "Q");
            statement.setString(2, "Q");
            statement.addBatch();
        }
        statement.executeBatch();
        String selectSql = "SELECT * FROM comments";
        try (ResultSet resultSet = statement.executeQuery(selectSql)) {
            while (resultSet.next()) {
                System.out.println("email : " + resultSet.getString("email"));
                System.out.println("comment : " + resultSet.getString("comment"));
            }
        }
        statement.close();
        connection.close();

    }



}
