package arif.rizal.data;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class DateTimeTimestampTest {
    @Test
    void testDateTimeTimestamp() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = """
                INSERT INTO sample_time(sample_time, sample_date, sample_timestamp) VALUES (?, ?, ?)
                """;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setTime(1, new Time(System.currentTimeMillis()));
        statement.setDate(2, new Date(System.currentTimeMillis()));
        statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        statement.executeUpdate();

        String sqlSelect = "SELECT * FROM sample_time";
        ResultSet resultSet = statement.executeQuery(sqlSelect);
        while (resultSet.next()){
            System.out.println("set time : " + resultSet.getTime("sample_time"));
            System.out.println("set date : " + resultSet.getDate("sample_date"));
            System.out.println("timestamp : " + resultSet.getTimestamp("sample_timestamp"));
        }
    }

}
