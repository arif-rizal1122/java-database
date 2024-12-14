package arif.rizal.data;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatmentTest {

    @Test
    void testCreateStatment() throws SQLException{
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connection.createStatement();

            String sql = """
                    INSERT INTO customers(id, name, email) VALUES
                    ('arif', 'rizal', 'rizal@gmail.com');
                    """;
        int update = statement.executeUpdate(sql);
        System.out.println(update);
            statement.close();
            connection.close();

    }

    @Test
    void testDeleteStatment() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = """
                     DELETE FROM customers;
                    """;
        int update = statement.executeUpdate(sql);
        System.out.println(update);
        statement.close();
        connection.close();
    }

    @Test
    void testExecuteQueryStatment() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = """
                     SELECT * FROM customers;
                    """;

        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.close();
        statement.close();
        connection.close();
    }





}
