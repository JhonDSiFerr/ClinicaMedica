package Daos;



import Entidades.Medico;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe de Acesso a Dados para a entidade Médico.
 */
public class MedicoDAO {

    /**
     * Salva um novo médico no banco de dados.
     * @param medico O objeto Medico contendo os dados a serem salvos.
     */
    public void save(Medico medico) {
        // Comando SQL para inserção
        String sql = "INSERT INTO medico (cpf, nome, telefone, endereco, sexo, estado_civil, atende_convenio, data_nascimento, especialidade, observacoes, crm) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Cria uma conexão com o banco
            conn = ConnectDatabase.getConnection();
            
            // Cria um PreparedStatement, o que previne SQL Injection
            pstmt = conn.prepareStatement(sql);

            // Adiciona os valores do objeto 'medico' no comando SQL
            pstmt.setString(1, medico.getCpf());
            pstmt.setString(2, medico.getNome());
            pstmt.setString(3, medico.getTelefone());
            pstmt.setString(4, medico.getEndereco());
            pstmt.setString(5, medico.getSexo());
            pstmt.setString(6, medico.getEstadoCivil());
            pstmt.setBoolean(7, medico.isAtendeConvenio());
            pstmt.setDate(8, Date.valueOf(medico.getDataNascimento())); // Converte LocalDate para java.sql.Date
            pstmt.setString(9, medico.getEspecialidade());
            pstmt.setString(10, medico.getObservacoes());
            pstmt.setString(11, medico.getCrm());
            // Executa o comando SQL
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Médico salvo com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar médico: " + e.getMessage());
        } finally {
            // Fecha as conexões
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
    // Adicione este método dentro da sua classe DAO.MedicoDAO

    /**
     * Busca e retorna o nome de todos os médicos do banco de dados.
     * @return Uma lista de Strings com o nome de cada médico.
     */
    public List<String> findAllNames() {
        String sql = "SELECT nome FROM medico ORDER BY nome ASC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<String> medicos = new ArrayList<>();

        try {
            conn = ConnectDatabase.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                medicos.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar médicos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return medicos;
    }
    public List<Medico> findAll() {
        // Altere os nomes das colunas se forem diferentes no seu banco
        String sql = "SELECT nome, cpf, telefone, data_nascimento, crm, especialidade, atende_convenio FROM medico ORDER BY nome ASC";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Medico> medicos = new ArrayList<>();

        try {
            conn = ConnectDatabase.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // Cria um objeto Medico para cada linha do resultado
                Medico medico = new Medico();

                // Preenche o objeto com os dados do banco
                medico.setNome(rs.getString("nome"));
                medico.setCpf(rs.getString("cpf"));
                medico.setTelefone(rs.getString("telefone"));
                medico.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                medico.setCrm(rs.getString("crm"));
                medico.setEspecialidade(rs.getString("especialidade"));
                medico.setAtendeConvenio(rs.getBoolean("atende_convenio"));

                // Adiciona o médico preenchido à lista
                medicos.add(medico);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar médicos: " + e.getMessage());
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
        return medicos;
    }
}