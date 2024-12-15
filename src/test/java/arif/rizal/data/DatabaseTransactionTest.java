package arif.rizal.data;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class DatabaseTransactionTest {
   @Test
   void testCommit() throws SQLException{
       Connection connection = ConnectionUtil.getDataSource().getConnection();
       String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
       connection.setAutoCommit(false);

       for (int i = 0; i < 5; i++) {
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setString(1, "Q");
           statement.setString(2, "Q");
           statement.executeUpdate();
           statement.close();
       }

       connection.commit();
       connection.close();
   }

    @Test
    void testRallback() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
        connection.setAutoCommit(false);

        for (int i = 0; i < 5; i++) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Q");
            statement.setString(2, "Q");
            statement.executeUpdate();
            statement.close();
        }

        connection.rollback();
        connection.close();
    }



}


