package Daos;


import Entidades.Paciente;
import java.sql.Connection;
import java.sql.Date;
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
     public Paciente findByCpf(String cpf) {
        String sql = "SELECT * FROM paciente WHERE cpf = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Paciente paciente = null;

        try {
            conn = ConnectDatabase.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cpf); // Define o CPF para a busca
            rs = pstmt.executeQuery();

            // Se encontrou um resultado
            if (rs.next()) {
                paciente = new Paciente();
                paciente.setCpf(rs.getString("cpf"));
                paciente.setNome(rs.getString("nome"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                // Preencha outros campos que você tenha (sexo, endereco, etc.)
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar paciente: " + e.getMessage());
        } finally {
            // Fechar conexões
        }
        return paciente;
    }
     
    public void delete(String cpf) {
        String sql = "DELETE FROM paciente WHERE cpf = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectDatabase.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cpf);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paciente excluído com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir paciente: " + e.getMessage());
        } finally {
            // Fechar conexões
        }
    }
    public void update(Paciente paciente) {
        // O CPF não muda, ele é usado na cláusula WHERE para saber qual registo atualizar
        String sql = "UPDATE paciente SET nome = ?, telefone = ?, data_nascimento = ? WHERE cpf = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectDatabase.getConnection();
            pstmt = conn.prepareStatement(sql);

            // Define os novos valores
            pstmt.setString(1, paciente.getNome());
            pstmt.setString(2, paciente.getTelefone());
            pstmt.setDate(3, java.sql.Date.valueOf(paciente.getDataNascimento()));
            
            // Define o CPF para a cláusula WHERE
            pstmt.setString(4, paciente.getCpf());

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paciente atualizado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar paciente: " + e.getMessage());
        } finally {
            // Fechar conexões
        }
    }
    public void save(Paciente paciente) {
        // Ajuste o nome da tabela e das colunas conforme seu banco de dados
        String sql = "INSERT INTO paciente (nome, cpf, telefone, endereco, sexo, data_nascimento, estado_civil, convenio, informacoes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectDatabase.getConnection(); // Use sua classe de conexão
            pstmt = conn.prepareStatement(sql);

            // Define os valores para cada '?' no comando SQL
            pstmt.setString(1, paciente.getNome());
            pstmt.setString(2, paciente.getCpf());
            pstmt.setString(3, paciente.getTelefone());
            pstmt.setString(4, paciente.getEndereco());
            pstmt.setString(5, paciente.getSexo());
            pstmt.setDate(6, Date.valueOf(paciente.getDataNascimento())); // Converte LocalDate para java.sql.Date
            pstmt.setString(7, paciente.getEstadoCivil());
            pstmt.setString(8, paciente.getConvenio());
            pstmt.setString(9, paciente.getInformacoes());

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar paciente: " + e.getMessage());
            e.printStackTrace(); // Ajuda a depurar mostrando o erro detalhado na consola
        } finally {
            // Bloco para fechar as conexões
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
    
    
    // Se você tiver um método para salvar pacientes, ele ficaria aqui.
}