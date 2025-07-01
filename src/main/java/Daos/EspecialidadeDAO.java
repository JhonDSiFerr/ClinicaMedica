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
        String sql = "INSERT INTO especialidade (nome, atende_convenio, data_adicao, cbo) VALUES (?, ?, ?, ?)";

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
            pstmt.setString(4, especialidade.getCbo());
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
    public List<Especialidade> findAll() {
        // SQL para selecionar todas as colunas necessárias
        String sql = "SELECT cbo, nome, atende_convenio, data_adicao FROM especialidade ORDER BY nome ASC";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Especialidade> especialidades = new ArrayList<>();

        try {
            conn = ConnectDatabase.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // Cria um objeto Especialidade para cada linha do resultado
                Especialidade especialidade = new Especialidade();

                // Preenche o objeto com TODOS os dados do banco
                especialidade.setCbo(rs.getString("cbo"));
                especialidade.setNome(rs.getString("nome"));
                especialidade.setAtendeConvenio(rs.getBoolean("atende_convenio"));
                especialidade.setDataAdicao(rs.getDate("data_adicao").toLocalDate());

                // Adiciona o objeto completo à lista
                especialidades.add(especialidade);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar especialidades: " + e.getMessage());
        } finally {
            // Bloco para fechar as conexões
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        // O método agora retorna corretamente uma lista de objetos Especialidade
        return especialidades;
    }
 public List<String> findAllNames() {
        String sql = "SELECT nome FROM especialidade ORDER BY nome ASC";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> nomesEspecialidades = new ArrayList<>();

        try {
            conn = ConnectDatabase.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // Adiciona apenas o nome (String) à lista
                nomesEspecialidades.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar nomes de especialidades: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return nomesEspecialidades;
    }
}
    
