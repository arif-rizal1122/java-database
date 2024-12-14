# PENGENALAN JAVA DATABASE

1. JDBC singkatan dari Java Database Connectivity
2. JDBC merupakan spesifikasi API standard untuk mengakses database di Java
2. JDBC tidak bisa langsung digunakan, karena isinya hanyalah interface-interface kontrak untuk berinteraksi dengan database
3. JDBC perlu implementasi, atau kita sebut dengan Driver, seperti MySQL Driver, PostgreSQL Driver, OracleDB Driver, dan lain-lain
4. Semua interface API JDBC terdapat di package java.sql dan javax.sql
5. https://docs.oracle.com/en/java/javase/15/docs/api/java.sql/java/sql/package-summary.html
6. https://docs.oracle.com/en/java/javase/15/docs/api/java.sql/javax/sql/package-summary.html 

# DRIVER
1. Driver adalah jembatan penghubung antara JDBC dan Database Management System yang akan kita gunakan
2. Sebenarnya Driver itu berisikan class-class implementasi dari interface yang terdapat di JDBC
3. Tanpa menggunakan Driver, kita JDBC tidak bisa terkoneksi ke DBMS
4. Driver di JDBC direpresentasikan oleh interface java.sql.Driver
5. https://docs.oracle.com/en/java/javase/15/docs/api/java.sql/java/sql/Driver.html 

¥ MYSQL DRIVER
1. MySQL sudah menyediakan driver untuk JDBC
2. Kita bisa cari MySQL Driver dengan menggunakan kata kunci mysql-connector-java di https://search.maven.org/
3. Lalu tambahkan ke dependency project kita

¥ REGISTRASI DRIVER KE JDBC
1. Setelah menambah dependency MySQL Driver ke project kita
2. Kita perlu melakukan registrasi Driver terlebih dahulu
3. Untuk melakukan registrasi driver, kita bisa menggunakan static method registerDriver milik class java.sql.DriverManager

# CONNECTION
1. Setelah melakukan registrasi Driver ke JDBC, kita sekarang bisa mulai melakukan koneksi ke database
2. Untuk melakukan koneksi ke database, kita harus memberi tahu jenis database, host, port, username dan password untuk terkoneksi ke database
3. Semua itu biasanya digabungkan dalam sebuah string yang biasa disebut JDBC URL
4. Koneksi ke database direpresentasikan oleh interface java.sql.Connection
5. https://docs.oracle.com/en/java/javase/15/docs/api/java.sql/java/sql/Connection.html 

¥ MySQL JDBC URL
1. Tiap database biasanya punya format jdbc url sendiri-sendiri
2. Contohnya di MySQL, kita bisa menggunakan format seperti ini :
3. jdbc:mysql://host:port/namadatabase

¥ MEMBUAT CONNECTION
1. Untuk membuat Connection ke database, kita bisa menggunakan static method getConnection() di class java.sql.DriverManager
2. Semua method di JDBC rata-rata selalu akan menghasilkan SQLException

¥ MENUTUP CONNECTION
1. Saat kita selesai menggunakan Connection, disarankan untuk selalu menutup Connection tersebut.
2. Jika sebuah Connection tidak kita tutup, maka selama aplikasi kita berjalan, koneksi ke datatabase akan selalu terbuka
3. Jika Connection yang terbuka ke database terlalu banyak, ditakutkan nanti kita tidak bisa membuka koneksi lagi ke database dikarenakan sudah menyentuh nilai maksimam koneksi yang bisa di tangani oleh database nya
4. Contohnya, maksimum Connection di MySQL adalah 151
5. https://dev.mysql.com/doc/refman/8.0/en/server-system-variables.html#sysvar_max_connections 

# DATA SOURCE

¥ MASALAH DENGAN CONNECTION
1. Connection adalah sebuah resource yang sangat mahal
2. Jika aplikasi kita sangat ketergantungan dengan database, maka membuka tutup koneksi setiap ada request sangatlah mahal harganya
3. Connection itu sangat lambat ketika pertama kali dibuat, dan sangat mahal memakan memory
4. Oleh karena itu, melakukan manajemen Connection secara manual sangatlah tidak direkomendasikan saat nanti kita membuat aplikasi

¥ CONNECTION POOL
1. Connection Pool adalah konsep dimana dibanding kita membuat koneksi baru setiap ada request ke yang membutuhkan database
2. Lebih baik diawal kita buatkan banyak Connection terlebih dahulu, sehingga hanya lambat diawal ketika aplikasi berjalan
3. Selanjutnya ketika ada request yang butuh koneksi, kita hanya cukup menggunakan salah satu Connection, dan setelah selesai, kita kembalikan lagi Connection nya
4. Jika semua Connection sedang terpakai semua, ketika ada request yang butuh koneksi lagi, request tersebut diminta untuk menunggu terlebih dahulu, dengan demikian penggunaan memory untuk Connection tidak akan terlalu bengkak
4. Connection Pool di JDBC direpresentasikan dengan interface javax.sql.DataSource

¥ HIKARICP
Membuat connection pool secara manual bukanlah hal bijak, lebih baik kita menggunakan library connection pool yang sudah terbukti bekerja dengan baik.
HikariCP adalah salah satu connection pool library yang paling populer saat ini di Java
Kita bisa menggunakan HikariCP ini untuk melakukan connection pool terhadap koneksi database di aplikasi kita
https://github.com/brettwooldridge/HikariCP 