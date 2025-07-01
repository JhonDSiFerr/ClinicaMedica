package Daos;



import Entidades.Especialidade;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe de Acesso a Dados para a entidade Especialidade.
 */
public class EspecialidadeDAO {

    /**
     * Salva uma nova especialidade no banco de dados.
     * @param especialidade O objeto Especialidade contendo os dados a serem salvos.
     */
    public void save(Especialidade especialidade) {
        // Comando SQL para a inserção
        String sql = "INSERT INTO especialidade (nome, atende_convenio, data_adicao) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Obtém a conexão com o banco
            conn = ConnectDatabase.getConnection();
            
            // Prepara o comando SQL para execução
            pstmt = conn.prepareStatement(sql);

            // Define os valores para cada '?' no comando SQL
            pstmt.setString(1, especialidade.getNome());
            pstmt.setBoolean(2, especialidade.isAtendeConvenio());
            pstmt.setDate(3, Date.valueOf(especialidade.getDataAdicao())); // Converte LocalDate para java.sql.Date

            // Executa a inserção no banco
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Especialidade salva com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar especialidade: " + e.getMessage());
        } finally {
            // Bloco para garantir que a conexão seja fechada
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
      /**
     * Busca e retorna o nome de todas as especialidades do banco de dados.
     * @return Uma lista de Strings com o nome de cada especialidade.
     */
    public List<String> findAll() {
        // Comando SQL para selecionar apenas a coluna 'nome'
        String sql = "SELECT nome FROM especialidade ORDER BY nome ASC";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; // Objeto para guardar o resultado da consulta

        // Lista que irá armazenar os nomes das especialidades
        List<String> especialidades = new ArrayList<>();

        try {
            conn = ConnectDatabase.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            // O executeQuery() é usado para comandos SELECT
            rs = pstmt.executeQuery();

            // Itera sobre o resultado da consulta
            while (rs.next()) {
                // Adiciona o nome da especialidade (da coluna 'nome') à lista
                especialidades.add(rs.getString("nome"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar especialidades: " + e.getMessage());
        } finally {
            // Fecha todas as conexões (ResultSet, PreparedStatement e Connection)
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return especialidades; // Retorna a lista preenchida
    }
}