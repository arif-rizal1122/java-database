package arif.rizal.data;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInjectionTest {

    @Test
    void testSQLSelectWhereWTest() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String username = "admin";
        String password = "adminn";
        String sql = "SELECT * FROM admin WHERE username = '" + username + "' AND password = '" + password + "'";
        ResultSet resultSet = statement.executeQuery(sql);

       if (resultSet.next()){
            System.out.println("login success : " + resultSet.getString("username"));
        } else {
            System.out.println("gagal login : ");
        }

       resultSet.close();
       statement.close();
       connection.close();
    }


    @Test
    void testSQLInjectionTest() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String username = "admin'; #"; // SQL INJECTION
        String password = "admins";
        String sql = "SELECT * FROM admin WHERE username = '" + username + "' AND password = '" + password + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println(sql); // SELECT * FROM admin WHERE username = 'admin'; #' AND password = 'admins'
        if (resultSet.next()){
            System.out.println("login success : " + resultSet.getString("username"));
        } else {
            System.out.println("gagal login : ");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }


}
