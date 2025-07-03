package br.com.sistemaclinica.dao;

import br.com.sistemaclinica.model.Funcionario;
import br.com.sistemaclinica.model.Perfil;
import br.com.sistemaclinica.model.TipoFuncionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 * Classe DAO para a entidade Funcionario. Responsável pela comunicação com a tabela 'funcionario'.
 */
public class FuncionarioDAO {

    /**
     * Cadastra um novo funcionário no banco de dados.
     * @param funcionario O objeto Funcionario com todos os dados preenchidos.
     */
    public void cadastrar(Funcionario funcionario) {
        // SQL atualizado para incluir a coluna especialidade_id
        String sql = "INSERT INTO funcionario (nome, usuario, senha, cpf, dataNascimento, tipoFuncionario, especialidade_id, perfil_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getUsuario());
            stmt.setString(3, funcionario.getSenha());
            stmt.setString(4, funcionario.getCpf());
            stmt.setDate(5, Date.valueOf(funcionario.getDataNascimento()));
            stmt.setString(6, funcionario.getTipoFuncionario().name());

            // Lógica para salvar a especialidade
            if (funcionario.getEspecialidade() != null) {
                stmt.setInt(7, funcionario.getEspecialidade().getId());
            } else {
                stmt.setNull(7, java.sql.Types.INTEGER);
            }
            
            // Lógica para o perfil_id (pode ser ajustada para pegar de um ComboBox na tela)
            if (funcionario.getPerfil() != null) {
                stmt.setInt(8, funcionario.getPerfil().getId());
            } else {
                stmt.setNull(8, java.sql.Types.INTEGER);
            }

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar funcionário: " + e.getMessage(), e);
        }
    }

    /**
     * Atualiza os dados de um funcionário existente.
     * @param funcionario O objeto Funcionario com os dados atualizados.
     */
    public void atualizar(Funcionario funcionario) {
        // SQL atualizado para incluir a coluna especialidade_id
        String sql = "UPDATE funcionario SET nome = ?, usuario = ?, senha = ?, cpf = ?, dataNascimento = ?, tipoFuncionario = ?, especialidade_id = ?, perfil_id = ? WHERE id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getUsuario());
            stmt.setString(3, funcionario.getSenha());
            stmt.setString(4, funcionario.getCpf());
            stmt.setDate(5, Date.valueOf(funcionario.getDataNascimento()));
            stmt.setString(6, funcionario.getTipoFuncionario().name());

            if (funcionario.getEspecialidade() != null) {
                stmt.setInt(7, funcionario.getEspecialidade().getId());
            } else {
                stmt.setNull(7, java.sql.Types.INTEGER);
            }
            
            if (funcionario.getPerfil() != null) {
                stmt.setInt(8, funcionario.getPerfil().getId());
            } else {
                stmt.setNull(8, java.sql.Types.INTEGER);
            }

            // O ID para a cláusula WHERE
            stmt.setInt(9, funcionario.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar funcionário: " + e.getMessage(), e);
        }
    }

    /**
     * Retorna uma lista com todos os funcionários cadastrados.
     * @return Uma lista de objetos Funcionario.
     */
    public List<Funcionario> listarTodos() {
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> funcionarios = new ArrayList<>();
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setUsuario(rs.getString("usuario"));
                funcionario.setCpf(rs.getString("cpf"));
                if (rs.getDate("dataNascimento") != null) {
                    funcionario.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                }
                funcionario.setTipoFuncionario(TipoFuncionario.valueOf(rs.getString("tipoFuncionario")));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar funcionários: " + e.getMessage(), e);
        }
        return funcionarios;
    }

    /**
     * Retorna uma lista de médicos que pertencem a uma especialidade específica.
     * @param especialidadeId O ID da especialidade.
     * @return Uma lista de objetos Funcionario (médicos).
     */
     public List<Funcionario> listarMedicosPorEspecialidade(int especialidadeId) {
        String sql = "SELECT * FROM funcionario WHERE tipoFuncionario = 'MEDICO' AND especialidade_id = ?";
        List<Funcionario> medicos = new ArrayList<>();
        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, especialidadeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Funcionario f = new Funcionario();
                    f.setId(rs.getInt("id"));
                    f.setNome(rs.getString("nome"));
                    medicos.add(f);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar médicos por especialidade.", e);
        }
        return medicos;
    }
    
    /**
     * Autentica um funcionário com base no usuário e senha.
     * @param usuario O nome de usuário para autenticar.
     * @param senha A senha para autenticar.
     * @return Um objeto Funcionario se a autenticação for bem-sucedida, ou null caso contrário.
     */
    public Funcionario autenticar(String usuario, String senha) {
        String sql = "SELECT f.*, p.nome as nome_perfil, p.cadastrarFuncionario, p.lerFuncionario, " +
                     "p.atualizarFuncionario, p.deletarFuncionario, p.listarFuncionario, " +
                     "p.cadastrarPaciente, p.lerPaciente, p.atualizarPaciente, p.deletarPaciente, " +
                     "p.listarPaciente, p.cadastrarConsulta, p.lerConsulta, p.atualizarConsulta, " +
                     "p.deletarConsulta, p.listarConsulta " +
                     "FROM funcionario f " +
                     "JOIN perfil p ON f.perfil_id = p.id " +
                     "WHERE f.usuario = ? AND f.senha = ?";

        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, senha);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Funcionario func = new Funcionario();
                    Perfil perfil = new Perfil();

                    func.setId(rs.getInt("id"));
                    func.setNome(rs.getString("nome"));
                    func.setUsuario(rs.getString("usuario"));

                    perfil.setId(rs.getInt("perfil_id"));
                    perfil.setNome(rs.getString("nome_perfil"));
                    perfil.setCadastrarFuncionario(rs.getBoolean("cadastrarFuncionario"));
                    perfil.setLerFuncionario(rs.getBoolean("lerFuncionario"));
                    perfil.setAtualizarFuncionario(rs.getBoolean("atualizarFuncionario"));
                    perfil.setDeletarFuncionario(rs.getBoolean("deletarFuncionario"));
                    perfil.setListarFuncionario(rs.getBoolean("listarFuncionario"));
                    perfil.setCadastrarPaciente(rs.getBoolean("cadastrarPaciente"));
                    perfil.setLerPaciente(rs.getBoolean("lerPaciente"));
                    perfil.setAtualizarPaciente(rs.getBoolean("atualizarPaciente"));
                    perfil.setDeletarPaciente(rs.getBoolean("deletarPaciente"));
                    perfil.setListarPaciente(rs.getBoolean("listarPaciente"));
                    perfil.setCadastrarConsulta(rs.getBoolean("cadastrarConsulta"));
                    perfil.setLerConsulta(rs.getBoolean("lerConsulta"));
                    perfil.setAtualizarConsulta(rs.getBoolean("atualizarConsulta"));
                    perfil.setDeletarConsulta(rs.getBoolean("deletarConsulta"));
                    perfil.setListarConsulta(rs.getBoolean("listarConsulta"));
                    
                    func.setPerfil(perfil);
                    return func;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao autenticar usuário: " + e.getMessage(), e);
        }
        return null;
    }

    /**
     * Deleta um funcionário do banco de dados com base no seu ID.
     * @param id O ID do funcionário a ser deletado.
     */
    public void deletar(int id) {
        String sql = "DELETE FROM funcionario WHERE id = ?";
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar funcionário no banco.", e);
        }
    }
}