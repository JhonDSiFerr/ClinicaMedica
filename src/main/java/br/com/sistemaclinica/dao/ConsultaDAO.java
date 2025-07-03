package br.com.sistemaclinica.dao;


import br.com.sistemaclinica.model.Consulta;
import br.com.sistemaclinica.model.Funcionario;
import br.com.sistemaclinica.model.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

   // No ConsultaDAO.java
public void cadastrar(Consulta consulta) {
    // Adicionamos a coluna 'especialidade_id'
    String sql = "INSERT INTO consulta (dataHorario, sintomas, eRetorno, estaAtiva, paciente_id, medico_id, atendente_id, especialidade_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
        ps.setTimestamp(1, java.sql.Timestamp.valueOf(consulta.getDataHorario()));
        ps.setString(2, consulta.getSintomas());
        ps.setBoolean(3, consulta.iseRetorno());
        ps.setBoolean(4, true); 
        ps.setInt(5, consulta.getPaciente().getId());
        ps.setInt(6, consulta.getMedico().getId());
        ps.setInt(7, 1); // ID do atendente (provisório)

        // Adiciona o ID da especialidade
        if (consulta.getEspecialidade() != null) {
            ps.setInt(8, consulta.getEspecialidade().getId());
        } else {
            ps.setNull(8, java.sql.Types.INTEGER);
        }
        ps.execute();
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao agendar consulta", e);
    }
}

    public List<Consulta> listarTodas() {
        // Este SQL é mais complexo. Ele "junta" a tabela de consulta com a de paciente e a de funcionário.
        // "JOIN paciente p ON c.paciente_id = p.id" -> Para cada consulta 'c', encontre o paciente 'p' cujo id seja igual ao paciente_id da consulta.
        // "as nome_paciente" -> Cria um "apelido" para a coluna de nome, para não confundir com o nome do médico.
        String sql = "SELECT c.id, c.dataHorario, c.sintomas, p.nome as nome_paciente, f.nome as nome_medico " +
                     "FROM consulta c " +
                     "JOIN paciente p ON c.paciente_id = p.id " +
                     "JOIN funcionario f ON c.medico_id = f.id " +
                     "WHERE c.estaAtiva = true " +
                     "ORDER BY c.dataHorario DESC";
        
        List<Consulta> consultas = new ArrayList<>();
        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Consulta consulta = new Consulta();
                Paciente paciente = new Paciente();
                Funcionario medico = new Funcionario();

                // Preenche os dados da consulta
                consulta.setId(rs.getInt("id"));
                consulta.setDataHorario(rs.getTimestamp("dataHorario").toLocalDateTime());
                consulta.setSintomas(rs.getString("sintomas"));
                
                // Preenche os objetos com os dados que vieram do JOIN
                paciente.setNome(rs.getString("nome_paciente"));
                medico.setNome(rs.getString("nome_medico"));
                
                // Associa os objetos à consulta
                consulta.setPaciente(paciente);
                consulta.setMedico(medico);
                
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar consultas", e);
        }
        return consultas;
    }
    public void deletar(int id) {
        String sql = "DELETE FROM consulta WHERE id = ?";
        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            // Em vez de deletar, uma alternativa em sistemas reais seria marcar como "CANCELADA"
            // UPDATE consulta SET estaAtiva = false WHERE id = ?
            throw new RuntimeException("Erro ao cancelar a consulta", e);
        }
    }
    
    // Aqui no futuro podemos ter os métodos de atualizar (remarcar) e deletar (cancelar).

    
}