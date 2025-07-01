package Daos;


import Entidades.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe de Acesso a Dados para a entidade Paciente.
 */
public class PacienteDAO {

    /**
     * Busca e retorna o nome de todos os pacientes cadastrados.
     * @return Uma lista de Strings com o nome de cada paciente.
     */
    public List<String> findAllNames() {
        // Altere 'paciente' para o nome da sua tabela, se for diferente
        String sql = "SELECT nome FROM paciente ORDER BY nome ASC"; 
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<String> pacientes = new ArrayList<>();

        try {
            conn = ConnectDatabase.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                pacientes.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar pacientes: " + e.getMessage());
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
        return pacientes;
    }
    public List<Paciente> findAll() {
        // SQL para selecionar os campos necessários. Altere os nomes se forem diferentes no seu banco.
        String sql = "SELECT cpf, nome, telefone, data_nascimento FROM paciente ORDER BY nome ASC";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Paciente> pacientes = new ArrayList<>();

        try {
            conn = ConnectDatabase.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // Itera sobre cada linha do resultado da busca
            while (rs.next()) {
                // Cria um novo objeto Paciente para cada linha
                Paciente paciente = new Paciente();

                // Define os atributos do objeto com os dados da linha atual
                paciente.setCpf(rs.getString("cpf"));
                paciente.setNome(rs.getString("nome"));
                paciente.setTelefone(rs.getString("telefone"));
                
                // Converte o java.sql.Date do banco para LocalDate
                paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());

                // Adiciona o objeto preenchido à lista
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar pacientes: " + e.getMessage());
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
        return pacientes; // Retorna a lista completa de pacientes
    }
    
    // Se você tiver um método para salvar pacientes, ele ficaria aqui.
}