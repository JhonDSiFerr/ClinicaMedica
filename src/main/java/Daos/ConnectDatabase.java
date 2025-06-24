package Daos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



    public class ConnectDatabase {

        // Altere estas configurações para as do seu banco de dados
        private static final String URL = "jdbc:mysql://localhost:3306/clinica"; // Exemplo para MySQL
        // private static final String URL = "jdbc:postgresql://localhost:5432/minha_loja"; // Exemplo para PostgreSQL
        private static final String USER = "root";
        private static final String PASSWORD = "an23al20";

        public static Connection getConnection() throws SQLException {
            try {
                // Carregar o driver JDBC (necessário apenas para JDBC mais antigos, mas não faz mal incluir)
                // Class.forName("com.mysql.cj.jdbc.Driver"); // Para MySQL
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
                throw e; // Lançar a exceção para que quem chamou possa tratá-la
            }
            // Para JDBC moderno (Java 6+), o driver é carregado automaticamente com DriverManager.getConnection
        }
    }






