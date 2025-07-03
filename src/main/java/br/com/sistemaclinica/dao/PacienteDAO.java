package br.com.sistemaclinica.dao;


import br.com.sistemaclinica.model.Paciente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void cadastrar(Paciente paciente) {
        String sql = "INSERT INTO paciente (nome, idade, sexo, cpf, rua, numero, complemento, bairro, cidade, estado, contato, email, dataNascimento) VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)";
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNome());
            stmt.setInt(2, paciente.getIdade());
            stmt.setString(3, paciente.getSexo());
            stmt.setString(4, paciente.getCpf());
            stmt.setString(5, paciente.getRua());
            stmt.setString(6, paciente.getNumero());
            stmt.setString(7, paciente.getComplemento());
            stmt.setString(8, paciente.getBairro());
            stmt.setString(9, paciente.getCidade());
            stmt.setString(10, paciente.getEstado());
            stmt.setString(11, paciente.getContato());
            stmt.setString(12, paciente.getEmail());
            stmt.setDate(13, Date.valueOf(paciente.getDataNascimento()));
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar paciente.", e);
        }
    }

    public List<Paciente> listarTodos() {
        String sql = "SELECT * FROM paciente";
        List<Paciente> pacientes = new ArrayList<>();
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setIdade(rs.getInt("idade"));
                paciente.setSexo(rs.getString("sexo"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setRua(rs.getString("rua"));
                paciente.setNumero(rs.getString("numero"));
                paciente.setComplemento(rs.getString("complemento"));
                paciente.setBairro(rs.getString("bairro"));
                paciente.setCidade(rs.getString("cidade"));
                paciente.setEstado(rs.getString("estado"));
                paciente.setContato(rs.getString("contato"));
                paciente.setEmail(rs.getString("email"));
                
                
                if (rs.getDate("dataNascimento") != null) {
                    paciente.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                }
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pacientes.", e);
        }
        return pacientes;
    }

    public void atualizar(Paciente paciente) {
    String sql = "UPDATE paciente SET nome = ?, idade = ?, sexo = ?, cpf = ?, rua = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ?, contato = ?, email = ?, dataNascimento = ? WHERE id = ?";
    try (Connection conexao = ConnectionFactory.getConnection();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {
        // Parâmetros na ordem correta
        stmt.setString(1, paciente.getNome());
        stmt.setInt(2, paciente.getIdade());
        stmt.setString(3, paciente.getSexo());
        stmt.setString(4, paciente.getCpf());
        stmt.setString(5, paciente.getRua());
        stmt.setString(6, paciente.getNumero());
        stmt.setString(7, paciente.getComplemento());
        stmt.setString(8, paciente.getBairro());
        stmt.setString(9, paciente.getCidade());
        stmt.setString(10, paciente.getEstado());
        stmt.setString(11, paciente.getContato());
        stmt.setString(12, paciente.getEmail());
        stmt.setDate(13, java.sql.Date.valueOf(paciente.getDataNascimento()));
        stmt.setInt(14, paciente.getId()); // O ID é o último parâmetro para o WHERE
        
        stmt.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao atualizar paciente.", e);
    }
}

    public void deletar(int id) {
        String sql = "DELETE FROM paciente WHERE id = ?";
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar paciente.", e);
        }
    }
}