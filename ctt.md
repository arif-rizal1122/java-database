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

# STATMENT

¥ MENGIRIM PERINTAH SQL
1. Saat kita terkoneksi ke database via Connection, sudah pasti kita ingin mengirim perintah SQL ke database
2. Connection adalah object yang bertugas sebagai jembatan koneksi dari aplikasi kita ke database, untuk mengirim perintah SQL, kita bisa menggunakan beberapa object lain, salah satunya yang akan kita bahas sekarang, yaitu Statement


¥ STATMENT
1. java.sql.Statement adalah interface yang bisa kita gunakan untuk mengirim SQL ke database, sekaligus menerima response data dari database
2. Ada banyak method yang bisa kita gunakan di Statement untuk mengirim perintah SQL, kita akan bahas satu persatu
3. https://docs.oracle.com/en/java/javase/15/docs/api/java.sql/java/sql/Statement.html
4. Untuk membuat statement kita bisa menggunakan method createStatement() milik Connection

¥ Statement.executeUpdate(sql)
Method pertama yang akan kita bahas adalah executeUpdate(sql)
Method ini digunakan untuk mengirim perintah SQL INSERT, UPDATE, DELETE atau apapun yang tidak membutuhkan result
Bahkan bisa perintah SQL DDL (create table, create index, dan lain-lain), walaupun best practice nya, perintah DDL lebih baik dilakukan langsung di database, atau menggunakan migration script, tidak dari aplikasi
executeUpdate(sql) mengembalikan return int, dimana ini biasanya mengembalikan berapa banyak record di database yang terkena impact perintah SQL kita, misal ketika mengirim perintah UPDATE, berapa banyak record yang ter-update misalnya


¥ Statement.executeQuery(sql)
1. Jika kita ingin mengirim perintah SQL yang mengembalikan data, maka kita bisa menggunakan method executeQuery(sql)
2. Method ini akan mengembalikan object java.sql.ResultSet, yaitu berisikan data-data hasil query SQL yang kita kirimkan
3. Pembahasan interface ResultSet akan kita bahas lebih detail lagi nanti di chapter tersendiri

# RESUL SET

1. Seperti yang sudah kita bahas sebelumnya di Statement, ketika kita melakukan query, maka akan menghasilkan java.sql.ResultSet
2. ResultSet adalah representasi data hasil query dari database
3. ResultSet itu mirip seperti iterator, jadi kita bisa melakukan perulangan di ResultSet untuk mendapatkan data tiap record nya
4. https://docs.oracle.com/en/java/javase/15/docs/api/java.sql/java/sql/ResultSet.html 

¥ MENGAMBIL DATA KOLOM DI RESULT
1. Cara kerja ResultSet adalah seperti cursor, dimana untuk maju kita menggunakan method next() dan untuk mundur, kita bisa menggunakan method previous()
2. Untuk mendapatkan data tiap kolom pada saat sekarang kita berada di lokasi cursor nya, kita bisa menggunakan banyak sekali method getXxx(column) di ResultSet
3. Kita bisa sesuaikan dengan tipe data kolom tersebut, misal getString(column), getInt(column), dan lain-lain

# SQL INJECTION

¥ SQL DENGAN PARAMETER
1. Saat membuat aplikasi, kita tidak mungkin akan melakukan hardcode perintah SQL di kode Java kita
2. Biasanya kita akan menerima input data dari user, lalu membuat perintah SQL dari input user, dan mengirimnya menggunakan perintah SQL

¥ SQL INJECTION
1. SQL Injection adalah sebuah teknik yang menyalahgunakan sebuah celah keamanan yang terjadi dalam lapisan basis data sebuah aplikasi.
2. Biasa, SQL Injection dilakukan dengan mengirim input dari user dengan perintah yang salah, sehingga menyebabkan hasil SQL yang kita buat menjadi tidak valid
3. SQL Injection sangat berbahaya, jika sampai kita salah membuat SQL, bisa jadi data kita tidak aman

¥ SOLUSINYA?????
1. Statement tidak didesain untuk bisa menangani SQL Injection
2. Oleh karena itu, jika SQL query yang kita gunakan dibuat berdasarkan input dari user, maka kita jangan menggunakan Statement
3. Untuk menghindari SQL Injection, terdapat interface bernama PreparedStatement, ini adalah jenis statement yang bisa menangani SQL Injection dengan baik
4. Kita akan bahas PreparedStatement di chapter tersendiri

# PREPARED STATMENT
1. PreparedStatement adalah turunan dari Statement, jadi apapun yang bisa dilakukan Statement, bisa dilakukan juga oleh PreparedStatement
2. Yang membedakan PreparedStatement dari Statement adalah, PreparedStatement memiliki kemampuan untuk mengamankan input dari user sehingga aman dari serangan SQL Injection

¥ MEMBUAT PREPARED STATMENT
1. Berbeda dengan Statement, pada PreparedStatement, ketika pembuatannya, sudah ditentukan SQL apa yang akan kita gunakan
2. Oleh karena itu, PreparedStatement biasanya digunakan untuk sekali mengirim perintah SQL, jika ingin mengirim perintah SQL lagi, kita harus membuat PreparedStatement baru
3. Untuk membuat PreparedStatement, kita bisa menggunakan method prepareStatement(sql) milik Connection

¥ MENERIMA INPUT USER
1. Sekarang pertanyaannya, bagaimana cara menerima input user menggunakan PreparedStatement?
2. Untuk menerima input dari user, SQL yang kita buat harus diubah juga
3. Input dari user, perlu kita ubah menjadi ? (tanda tanya)
4. Nanti ketika pembuatan object, kita bisa subtitusi datanya menggunakan setXxx(index, value) sesuai dengan tipe datanya, misal setString(), setInt() dan lain-lain

# BATCH PROCCESS

¥ REQUEST & RESPONSE
1. Secara default, komunikasi antara database dan aplikasi Java kita adalah request dan response
2. Artinya, setiap kali kita mengirim perintah SQL, maka aplikasi kita akan menunggu sampai database melakukan response dari perintah SQL tersebut
3. Proses tersebut kadang terlalu chatty kalo tujuan kita misal ingin mengirim data secara banyak ke database

¥ BATCH PROCCCESS
1. Batch process adalah proses mengirim perintah secara banyak sekaligus.
2. Biasanya batch process dilakukan dalam kasus tertentu saja, misal ketika kita ingin mengirim import data dari file excel ke database yang jumlahnya jutaan.
3. Biasanya dalam batch process, yang diutamakan adalah kecepatan, karena jika perintah SQL nya di execute satu satu dan menunggu response satu satu, maka sudah pasti akan sangat lambat sekali


¥ BATCH PROCCESS DI JDBC
1. JDBC mendukung proses eksekusi perintah SQL secara batch di Statement ataupun di PreparedStatement
2. Di Statement, terdapat method addBatch(sql) untuk menambahkan perintah ke proses batch
3. Sedangkan di PreparedStatement terdapat method addBatch() untuk menambahkan proses ke batch, lalu bisa gunakan method clearParameters() untuk menghapus parameter input user sebelumnya.
4. Setelah proses batch selesai, untuk mengeksekusinya, kita bisa gunakan perintah executeBatch()

¥ PERINGATAN
Proses batch akan disimpan di memory sebelum dikirim ke database
Oleh karena itu, bijaklah membuat proses batch, jangan terlalu banyak menambahkan ke batch, misal per 100 atau per 1000
Jika sudah mencapai 100 atau 1000, kita bisa mengirim batch tersebut menggunakan perintah executeBatch()


# AUTO INCREMANT

1. Kadang setelah melakukan INSERT data ke database yang memiliki primary key auto increment, kita ingin mendapatkan data id terbarunya
2. Di MySQL sebenarnya kita bisa melakukan query SELECT LAST_INSERT_ID(), namun berarti kita harus melakukan query ulang dengan Statement dan melakukan iterasi lagi dengan ResultSet
3. Untungnya di JDBC, ada kemampuan untuk mendapatkan auto generate data seperti auto increment dengan method getGenerateKeys() yang mengembalikan ResultSet
4. Selanjutnya kita bisa melakukan iterasi terhadap ResultSet tersebut


¥ MENDAPATKAN AUTO GENERATED KEY 
Secara default, Statement ataupun PreparedStatement tidak mengerti untuk mengambil auto generate key
Kita perlu memberi tahunya agar Statement ataupun PreparedStatement mengambil auto generate id secara otomatis
Untuk Statement, kita perlu memberi tahu ketika memanggil method executeUpdate(sql, Statement.RETURN_GENERATED_KEYS)
Sedangkan untuk PreparedStatement, kita perlu memberi tahu ketika membuat prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
Setelah itu, untuk mendapatkan auto generate key, kita bisa menggunakan method getGeneratedKeys(), method ini akan error jika kita lupa mengirim parameter generated keys


# DATE, TIME AND TIMESTAMP

1. Seperti kita ketahui, tipe tanggal dan waktu di database biasanya banyak, ada Date, Time ada juga Timestamp
2. Sedangkan di Java hanya terdapat tipe data java.util.Date
3. Agar bisa menghandle hal ini, terdapat class-class turunan java.util.Date di package java.sql yang bernama Date, Time dan Timestamp
4. Sesuai dengan namanya, class-class tersebut digunakan sebagai representasi di Java
5. Secara otomatis JDBC bisa melakukan konversi tipe data tersebut dari database menjadi object di Java

# DATABASE TRANSACTION

1. Fitur paling bermanfaat di database salah satu nya adalah database transaction
2. Fitur database transaction pun bisa kita jalankan menggunakan JDBC
3. Jika belum mengerti apa itu database transaction, silahkan tonton course saya tentang database MySQL, disana saya jelaskan secara terperinci

¥ AUTO COMMIT
1. Secara default, Connection yang kita buat menggunakan JDBC memiliki sifat auto commit
2. Auto commit artinya setiap perintah SQL yang kita kirim akan langsung di commit secara otomatis
3. Karena dalam database transaction, kita biasanya melakukan commit transaction setelah semua proses selesai, maka kita perlu mematikan auto commit di JDBC
4. Untuk mematikan fitur auto commit di JDBC, kita bisa menggunakan method di Connection bernama setAutoCommit(false)
5. Setelah selesai melakukan proses , kita bisa melakukan commit transaction dengan menggunakan method commit() milik Connection
6. Untuk membatalkan proses transaksi (rollback), kita bisa menggunakan method rollback() milik Connection

# METADATA

1. Kadang kita ingin mendapat informasi seputar database yang kita gunakan
2. Informasi tersebut bernama MetaData
3. Ada banyak jenis metadata, seperti DatabaseMetaData, ParameterMetaData, ResultSetMetaData
4. https://docs.oracle.com/en/java/javase/15/docs/api/java.sql/java/sql/DatabaseMetaData.html
5. https://docs.oracle.com/en/java/javase/15/docs/api/java.sql/java/sql/ParameterMetaData.html
6. https://docs.oracle.com/en/java/javase/15/docs/api/java.sql/java/sql/ResultSetMetaData.html 


¥ DATABASE METADATA

1. DatabaseMetaData adalah informasi seputar seluruh database yang kita gunakan, seperti misal :
 - Nama database
 - Versi database
 - Table yang ada di database
 - dan lain-lain
2. Untuk membuat DatabaseMetaData, kita bisa menggunakan method getMetaData() dari object Collection

¥ PARAMETER METADATA
1. ParameterMetadata adalah informasi seputar parameter yang terdapat di PreparedStatement
2. Dengan ParameterMetadata, kita bisa mendapat banyak informasi parameter yang bisa digunakan untuk input di PreparedStatement, seperti berapa banyak parameter, tipe data parameter, dan lain-lain
3. Namun perlu diperhatikan, beberapa Driver mungkin tidak mendukung untuk mendapatkan jenis tipe parameter di ParameterMetadata

¥ RESULT SET METADATA
1. ResultSetMetaData adalah informasi seputar hasil ResultSet
2. Dengan ResultSetMetaData, kita bisa mendapatkan informasi tentang jumlah kolom, nama kolom, tipe data tiap kolom nya, dan lain-lain













































































