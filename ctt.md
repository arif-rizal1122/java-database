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


