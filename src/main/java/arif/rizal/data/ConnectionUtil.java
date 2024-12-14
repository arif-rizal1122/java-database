package arif.rizal.data;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionUtil {

    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/belajar-java-database");
        config.setUsername("root");
        config.setPassword("");

        // Konfigurasi pool koneksi database:
        // Mengatur jumlah maksimum koneksi yang dapat dibuat di pool
        // Artinya maksimal ada 18 koneksi yang dapat digunakan secara bersamaan
        config.setMaximumPoolSize(18);
        // Mengatur jumlah koneksi minimum yang tetap ada di pool
        // Meskipun tidak ada aktivitas, akan selalu ada setidaknya 5 koneksi siap digunakan
        config.setMinimumIdle(5);
        // Mengatur waktu maksimal (dalam milidetik) koneksi dapat menganggur
        // Jika koneksi tidak digunakan selama 60 detik, akan dilepaskan
        config.setIdleTimeout(60_000);
        // Mengatur waktu maksimal hidup suatu koneksi
        // Koneksi akan ditutup setelah 10 menit (600.000 milidetik)
        // Ini membantu mencegah kebocoran koneksi dan memastikan koneksi yang fresh
        config.setMaxLifetime(10 * 60_000);

        dataSource = new HikariDataSource(config);
    };
    public static HikariDataSource getDataSource(){
        return dataSource;
    }


}
