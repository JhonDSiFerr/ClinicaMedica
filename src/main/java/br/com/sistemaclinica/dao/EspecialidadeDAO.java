package br.com.sistemaclinica.dao;

import br.com.sistemaclinica.model.Especialidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para a entidade Especialidade. Responsável pela comunicação com a tabela 'especialidade'.
 */
public class EspecialidadeDAO {

    /**
     * Cadastra uma nova especialidade no banco de dados.
     * @param especialidade O objeto Especialidade com os dados preenchidos.
     */
    public void cadastrar(Especialidade especialidade) {
        // O SQL correto insere apenas os campos que a tabela especialidade possui.
        String sql = "INSERT INTO especialidade (descricao, cbo) VALUES (?, ?)";
        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, especialidade.getDescricao());
            ps.setString(2, especialidade.getCbo()); // Assumindo que 'cbo' é uma String
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar especialidade", e);
        }
    }

    /**
     * Retorna uma lista com todas as especialidades cadastradas.
     * @return Uma lista de objetos Especialidade.
     */
    public List<Especialidade> listarTodos() {
        String sql = "SELECT * FROM especialidade ORDER BY descricao";
        List<Especialidade> especialidades = new ArrayList<>();
        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Especialidade especialidade = new Especialidade();
                especialidade.setId(rs.getInt("id"));
                especialidade.setDescricao(rs.getString("descricao"));
                especialidade.setCbo(rs.getString("cbo"));
                especialidades.add(especialidade);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar especialidades", e);
        }
        return especialidades;
    }

    /**
     * Atualiza os dados de uma especialidade existente.
     * @param especialidade O objeto Especialidade com os dados atualizados.
     */
    public void atualizar(Especialidade especialidade) {
        String sql = "UPDATE especialidade SET descricao = ?, cbo = ? WHERE id = ?";
        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            // Parâmetros na ordem correta do SQL
            ps.setString(1, especialidade.getDescricao());
            ps.setString(2, especialidade.getCbo());
            ps.setInt(3, especialidade.getId()); // O ID é o 3º '?' para a cláusula WHERE
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar especialidade", e);
        }
    }

    /**
     * Deleta uma especialidade do banco de dados com base no seu ID.
     * @param id O ID da especialidade a ser deletada.
     */
    public void deletar(int id) {
        String sql = "DELETE FROM especialidade WHERE id = ?";
        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar especialidade", e);
        }
    }
}