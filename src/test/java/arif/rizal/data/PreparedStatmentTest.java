package arif.rizal.data;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatmentTest {
    @Test
    void testPreparedStatment() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String username = "admin'; #";
        // String username = "admin";
        String password = "admin";

        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            System.out.println("login success : " + resultSet.getString("username"));
        } else {
            System.out.println("login gagal : ");
        }


        preparedStatement.close();
        connection.close();
    }


}
