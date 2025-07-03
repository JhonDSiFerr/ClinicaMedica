package br.com.sistemaclinica.dao;

import br.com.sistemaclinica.model.Perfil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para a entidade Perfil. Responsável pela comunicação com a tabela 'perfil'.
 */
public class PerfilDAO {

    /**
     * Cadastra um novo perfil no banco de dados.
     * @param perfil O objeto Perfil com todos os dados preenchidos.
     */
    public void cadastrar(Perfil perfil) {
        String sql = "INSERT INTO perfil (nome, cadastrarFuncionario, lerFuncionario, atualizarFuncionario, " +
                     "deletarFuncionario, listarFuncionario, cadastrarPaciente, lerPaciente, " +
                     "atualizarPaciente, deletarPaciente, listarPaciente, cadastrarConsulta, " +
                     "lerConsulta, atualizarConsulta, deletarConsulta, listarConsulta) " +
                     "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, perfil.getNome());
            // Permissões de Funcionário
            ps.setBoolean(2, perfil.isCadastrarFuncionario());
            ps.setBoolean(3, perfil.isLerFuncionario());
            ps.setBoolean(4, perfil.isAtualizarFuncionario());
            ps.setBoolean(5, perfil.isDeletarFuncionario());
            ps.setBoolean(6, perfil.isListarFuncionario());
            // Permissões de Paciente
            ps.setBoolean(7, perfil.isCadastrarPaciente());
            ps.setBoolean(8, perfil.isLerPaciente());
            ps.setBoolean(9, perfil.isAtualizarPaciente());
            ps.setBoolean(10, perfil.isDeletarPaciente());
            ps.setBoolean(11, perfil.isListarPaciente());
            // Permissões de Consulta
            ps.setBoolean(12, perfil.isCadastrarConsulta());
            ps.setBoolean(13, perfil.isLerConsulta());
            ps.setBoolean(14, perfil.isAtualizarConsulta());
            ps.setBoolean(15, perfil.isDeletarConsulta());
            ps.setBoolean(16, perfil.isListarConsulta());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar perfil: " + e.getMessage(), e);
        }
    }

    /**
     * Retorna uma lista com todos os perfis cadastrados no banco de dados.
     * @return Uma lista de objetos Perfil.
     */
    public List<Perfil> listarTodos() {
        String sql = "SELECT * FROM perfil ORDER BY nome";
        List<Perfil> perfis = new ArrayList<>();
        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Perfil p = new Perfil();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                // Permissões de Funcionário
                p.setCadastrarFuncionario(rs.getBoolean("cadastrarFuncionario"));
                p.setLerFuncionario(rs.getBoolean("lerFuncionario"));
                p.setAtualizarFuncionario(rs.getBoolean("atualizarFuncionario"));
                p.setDeletarFuncionario(rs.getBoolean("deletarFuncionario"));
                p.setListarFuncionario(rs.getBoolean("listarFuncionario"));
                // Permissões de Paciente
                p.setCadastrarPaciente(rs.getBoolean("cadastrarPaciente"));
                p.setLerPaciente(rs.getBoolean("lerPaciente"));
                p.setAtualizarPaciente(rs.getBoolean("atualizarPaciente"));
                p.setDeletarPaciente(rs.getBoolean("deletarPaciente"));
                p.setListarPaciente(rs.getBoolean("listarPaciente"));
                // Permissões de Consulta
                p.setCadastrarConsulta(rs.getBoolean("cadastrarConsulta"));
                p.setLerConsulta(rs.getBoolean("lerConsulta"));
                p.setAtualizarConsulta(rs.getBoolean("atualizarConsulta"));
                p.setDeletarConsulta(rs.getBoolean("deletarConsulta"));
                p.setListarConsulta(rs.getBoolean("listarConsulta"));

                perfis.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar perfis: " + e.getMessage(), e);
        }
        return perfis;
    }

    /**
     * Atualiza os dados de um perfil existente no banco de dados.
     * @param perfil O objeto Perfil com os dados atualizados.
     */
    public void atualizar(Perfil perfil) {
        String sql = "UPDATE perfil SET nome=?, cadastrarFuncionario=?, lerFuncionario=?, " +
                     "atualizarFuncionario=?, deletarFuncionario=?, listarFuncionario=?, " +
                     "cadastrarPaciente=?, lerPaciente=?, atualizarPaciente=?, deletarPaciente=?, " +
                     "listarPaciente=?, cadastrarConsulta=?, lerConsulta=?, atualizarConsulta=?, " +
                     "deletarConsulta=?, listarConsulta=? WHERE id=?";

        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, perfil.getNome());
            // Permissões de Funcionário
            ps.setBoolean(2, perfil.isCadastrarFuncionario());
            ps.setBoolean(3, perfil.isLerFuncionario());
            ps.setBoolean(4, perfil.isAtualizarFuncionario());
            ps.setBoolean(5, perfil.isDeletarFuncionario());
            ps.setBoolean(6, perfil.isListarFuncionario());
            // Permissões de Paciente
            ps.setBoolean(7, perfil.isCadastrarPaciente());
            ps.setBoolean(8, perfil.isLerPaciente());
            ps.setBoolean(9, perfil.isAtualizarPaciente());
            ps.setBoolean(10, perfil.isDeletarPaciente());
            ps.setBoolean(11, perfil.isListarPaciente());
            // Permissões de Consulta
            ps.setBoolean(12, perfil.isCadastrarConsulta());
            ps.setBoolean(13, perfil.isLerConsulta());
            ps.setBoolean(14, perfil.isAtualizarConsulta());
            ps.setBoolean(15, perfil.isDeletarConsulta());
            ps.setBoolean(16, perfil.isListarConsulta());
            // ID para a cláusula WHERE
            ps.setInt(17, perfil.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar perfil: " + e.getMessage(), e);
        }
    }

    /**
     * Deleta um perfil do banco de dados com base no seu ID.
     * @param id O ID do perfil a ser deletado.
     */
    public void deletar(int id) {
        String sql = "DELETE FROM perfil WHERE id = ?";
        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            // Cuidado: pode falhar se algum funcionário estiver usando este perfil (erro de chave estrangeira)
            throw new RuntimeException("Erro ao deletar perfil: " + e.getMessage(), e);
        }
    }
}