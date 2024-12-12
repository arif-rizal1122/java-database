package arif.rizal.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

   @BeforeAll
   static void beforeAll() {
      try {
         Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
         DriverManager.registerDriver(mysqlDriver);
      } catch (SQLException e) {
         Assertions.fail(e);
      }
   }

   @Test
   void testConnection(){
      String jdbc = "jdbc:mysql://localhost:3306/belajar-java-database";
      String username = "root";
      String password = "";

      try {
         Connection connection = DriverManager.getConnection(jdbc, username, password);
         System.out.println("success connect ke database !!!");
      } catch (SQLException e) {
         Assertions.fail(e);
      }
   }

   @Test
   void testCloseConnection(){
      String jdbc = "jdbc:mysql://localhost:3306/belajar-java-database";
      String username = "root";
      String password = "";
      try (Connection connection = DriverManager.getConnection(jdbc, username, password)){
         System.out.println("connection is close !!!");
      } catch (SQLException e) {
         Assertions.fail(e);
      }
   }



}
