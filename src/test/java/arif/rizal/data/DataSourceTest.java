//package arif.rizal.data;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class DataSourceTest {
//    @Test
////    void testConnectionPool() {
////
//////        HikariConfig config = new HikariConfig();
//////        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//////        config.setJdbcUrl("jdbc:mysql://localhost:3306/belajar-java-database");
//////        config.setUsername("root");
//////        config.setPassword("");
//////
//////        // Konfigurasi pool koneksi database:
//////        // Mengatur jumlah maksimum koneksi yang dapat dibuat di pool
//////        // Artinya maksimal ada 18 koneksi yang dapat digunakan secara bersamaan
//////        config.setMaximumPoolSize(18);
//////        // Mengatur jumlah koneksi minimum yang tetap ada di pool
//////        // Meskipun tidak ada aktivitas, akan selalu ada setidaknya 5 koneksi siap digunakan
//////        config.setMinimumIdle(5);
//////        // Mengatur waktu maksimal (dalam milidetik) koneksi dapat menganggur
//////        // Jika koneksi tidak digunakan selama 60 detik, akan dilepaskan
//////        config.setIdleTimeout(60_000);
//////        // Mengatur waktu maksimal hidup suatu koneksi
//////        // Koneksi akan ditutup setelah 10 menit (600.000 milidetik)
//////        // Ini membantu mencegah kebocoran koneksi dan memastikan koneksi yang fresh
//////        config.setMaxLifetime(10 * 60_000);
////        try {
////            // Membuat HikariDataSource menggunakan konfigurasi yang telah dibuat sebelumnya
////            // HikariDataSource adalah implementasi konkret dari Connection Pool
////            HikariDataSource hikariDataSource = new HikariDataSource(config);
////            // Mendapatkan koneksi dari connection pool
////            // Metode getConnection() akan:
////            // 1. Mengambil koneksi yang tersedia dari pool
////            // 2. Membuat koneksi baru jika tidak ada koneksi yang tersedia
////            // 3. Menunggu jika semua koneksi sedang digunakan (tergantung konfigurasi)
////            Connection connection = hikariDataSource.getConnection();
////            System.out.println("sukkses mengambil connection : ");
////
////            // Mengembalikan koneksi ke pool
////            // close() tidak berarti menutup koneksi sepenuhnya,
////            // tetapi mengembalikannya ke pool untuk digunakan kembali
////            connection.close();
////            System.out.println("sukkses mengembalikan connection : ");
////
////            // Menutup seluruh connection pool
////            // Ini akan menutup semua koneksi yang ada di pool
////            hikariDataSource.close();
////            System.out.println("sukksess menutup pool");
////        } catch (SQLException e){
////
////            Assertions.fail(e);
////        }
////    }
//
////    @Test
////    void testUtilConn() throws SQLException {
////        Connection connection = ConnectionUtil.getDataSource().getConnection();
////        System.out.println("sukkses mengambil connection : ");
////        connection.close();
////        System.out.println("sukkses mengembalikan connection : ");
////        hikariDataSource.close();
////        System.out.println("sukksess menutup pool");
////    }
//
//
//
//
//}
