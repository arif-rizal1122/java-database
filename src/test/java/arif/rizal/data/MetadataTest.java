package arif.rizal.data;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class MetadataTest {
    @Test
    void testMetadata() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        System.out.println(metaData.getDatabaseProductName());
        System.out.println(metaData.getDatabaseProductVersion());

        ResultSet resultSet = metaData.getTables("belajar-java-database", null, null, null);
        while (resultSet.next()){
            String tableName = resultSet.getString("TABLE_NAME");
            System.out.println("Table : " + tableName);
        }

        connection.close();
    }

    @Test
    void testParameterMetadata() throws SQLException{
        String sql = "INSERT INTO comments(email, comment) VALUES (?,?)";
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ParameterMetaData metaData = statement.getParameterMetaData();

        System.out.println(metaData.getParameterCount());
        // System.out.println(metaData.getParameterType(1)); // driver mysql disini gak support
        // System.out.println(metaData.getParameterType(2));

        connection.close();
    }

    @Test
    void testResultSetMetadata() throws SQLException{
        String sql = "SELECT * FROM customers";
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            System.out.println("name : " + resultSetMetaData.getCatalogName(i));
            System.out.println("type : " + resultSetMetaData.getColumnType(i));
            System.out.println("type name : " + resultSetMetaData.getCatalogName(i));

        }

        statement.close();
        resultSet.close();
        connection.close();
    }


}
