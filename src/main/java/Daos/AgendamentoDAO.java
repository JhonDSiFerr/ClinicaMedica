package Daos;



import Entidades.Agendamento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import javax.swing.JOptionPane;

/**
 * Classe de Acesso a Dados para a entidade Agendamento.
 */
public class AgendamentoDAO {

    /**
     * Salva um novo agendamento no banco de dados.
     * @param agendamento O objeto Agendamento contendo os dados.
     */
    public void save(Agendamento agendamento) {
        // Ajuste o nome da tabela e das colunas conforme seu banco de dados
        String sql = "INSERT INTO agendamento (data_consulta, hora_consulta, paciente, convenio, medico, especialidade) VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectDatabase.getConnection();
            pstmt = conn.prepareStatement(sql);

            // Define os valores para o comando SQL
            pstmt.setDate(1, Date.valueOf(agendamento.getData()));
            pstmt.setTime(2, Time.valueOf(agendamento.getHora()));
            pstmt.setString(3, agendamento.getPaciente());
            pstmt.setString(4, agendamento.getConvenio());
            pstmt.setString(5, agendamento.getMedico());
            pstmt.setString(6, agendamento.getEspecialidade());

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Consulta agendada com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao agendar consulta: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}