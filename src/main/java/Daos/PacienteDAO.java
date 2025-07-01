package Daos;


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
    
    // Se você tiver um método para salvar pacientes, ele ficaria aqui.
}