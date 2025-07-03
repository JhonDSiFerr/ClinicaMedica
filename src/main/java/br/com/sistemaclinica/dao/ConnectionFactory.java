package br.com.sistemaclinica.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por estabelecer e fornecer conexões com o banco de dados MySQL.
 */
public class ConnectionFactory {

    // Método público e estático para obter uma conexão.
    // "static" significa que podemos chamar este método sem precisar criar um objeto da classe.
    public static Connection getConnection() {
        try {
            // O caminho (URL) para o nosso banco de dados.
            // jdbc:mysql:// -> Protocolo de conexão
            // localhost:3306 -> Endereço do servidor do banco (sua máquina) e a porta padrão
            // clinica_medica_db -> O nome do banco de dados que criamos no Passo 1
            String url = "jdbc:mysql://localhost:3306/clinica_medica_db";
            
            // Seu usuário do MySQL. O padrão geralmente é "root".
            String usuario = "root";
            
            // Sua senha do MySQL. Altere se você tiver uma senha configurada.
            String senha = "an23al20"; // <-- ATENÇÃO: MUDE AQUI SE NECESSÁRIO

            // Tenta estabelecer a conexão e a retorna.
            return DriverManager.getConnection(url, usuario, senha);

        } catch (SQLException e) {
            // Se algo der errado, uma exceção é lançada.
            // Aqui nós a capturamos e imprimimos o erro no console para diagnóstico.
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            // Lançamos uma RuntimeException para interromper a execução se a conexão falhar.
            throw new RuntimeException("Não foi possível conectar ao banco de dados.", e);
        }
    }
}