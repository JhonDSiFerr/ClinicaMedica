package br.com.sistemaclinica.dao;


import br.com.sistemaclinica.model.Paciente;
import br.com.sistemaclinica.model.Prontuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProntuarioDAO {

    /**
     * Busca o prontuário de um paciente específico.
     * @param pacienteId O ID do paciente.
     * @return O objeto Prontuario, ou null se não for encontrado.
     */
    public Prontuario buscarPorPacienteId(int pacienteId) {
        String sql = "SELECT * FROM prontuario WHERE paciente_id = ?";
        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, pacienteId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Prontuario p = new Prontuario();
                    p.setId(rs.getInt("id"));
                    p.setReceituario(rs.getString("receituario"));
                    p.setExames(rs.getString("exames"));
                    p.setObservacoes(rs.getString("observacoes"));
                    return p;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar prontuário", e);
        }
        return null; // Retorna nulo se nenhum prontuário foi encontrado para este paciente.
    }

    /**
     * Salva as informações do prontuário.
     * Se o prontuário não tiver ID, ele cria um novo registro (INSERT).
     * Se já tiver ID, ele atualiza o registro existente (UPDATE).
     * @param prontuario O objeto Prontuario a ser salvo.
     */
    public void salvar(Prontuario prontuario) {
        String sql;
        // Lógica "Upsert": se não tem ID, é INSERT. Se tem, é UPDATE.
        if (prontuario.getId() == 0) {
            sql = "INSERT INTO prontuario (receituario, exames, observacoes, paciente_id) VALUES (?, ?, ?, ?)";
        } else {
            sql = "UPDATE prontuario SET receituario = ?, exames = ?, observacoes = ? WHERE id = ?";
        }

        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, prontuario.getReceituario());
            ps.setString(2, prontuario.getExames());
            ps.setString(3, prontuario.getObservacoes());

            if (prontuario.getId() == 0) { // Parâmetros do INSERT
                ps.setInt(4, prontuario.getPaciente().getId());
            } else { // Parâmetro do UPDATE
                ps.setInt(4, prontuario.getId());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar prontuário", e);
        }
    }
}