package br.com.sistemaclinica.dao;


import br.com.sistemaclinica.model.Convenio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConvenioDAO {

    public void cadastrar(Convenio convenio) {
        String sql = "INSERT INTO convenio (nome, descricao) VALUES (?, ?)";
        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, convenio.getNome());
            ps.setString(2, convenio.getDescricao());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar convênio", e);
        }
    }

    public List<Convenio> listarTodos() {
        String sql = "SELECT * FROM convenio";
        List<Convenio> convenios = new ArrayList<>();
        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Convenio convenio = new Convenio();
                convenio.setId(rs.getInt("id"));
                convenio.setNome(rs.getString("nome"));
                convenio.setDescricao(rs.getString("descricao"));
                convenios.add(convenio);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar convênios", e);
        }
        return convenios;
    }

    public void atualizar(Convenio convenio) {
        String sql = "UPDATE convenio SET nome = ?, descricao = ? WHERE id = ?";
        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, convenio.getNome());
            ps.setString(2, convenio.getDescricao());
            ps.setInt(3, convenio.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar convênio", e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM convenio WHERE id = ?";
        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar convênio", e);
        }
    }
}