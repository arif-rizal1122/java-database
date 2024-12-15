package arif.rizal.data;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class AutoIncrementTest {

   @Test
   void testAutoIncrement() throws SQLException {
       Connection connection = ConnectionUtil.getDataSource().getConnection();
       String email = "tamu@gmail.com";
       String comment = "jg";
       String sql = "INSERT INTO comments(email, comment) VALUES (?,?)";

       PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

       statement.setString(1, "axcel");
       statement.setString(2, "axcyl");
       statement.executeUpdate();
       ResultSet resultSet = statement.getGeneratedKeys();
       if (resultSet.next()){
           int id = resultSet.getInt(1);
           System.out.println("key genearated : " + id);

       }

       connection.close();
       statement.close();

   }

}
