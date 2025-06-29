// src/dao/MedicoDAO.java
package Daos;
import Entidades.Especialidade;
import Entidades.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MedicoDaos {

    public void adicionar(Medico medico) {
        // SQL para inserir o médico (agora com a coluna especialidade_cbo)
        // Certifique-se de que a coluna especialidade_cbo existe na sua tabela medico
        String sqlMedico = "INSERT INTO medico (CRM, Nome, CPF, Datanascimento, `Estado Civil`, " +
                           "Sexo, Convenio, Endereco, Observacoes, Especialidade) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // Um placeholder a mais

        Connection conexao = null;
        PreparedStatement stmtMedico = null;

        try {
            conexao = ConnectDatabase.getConnection();
            // Nao precisamos mais de setAutoCommit(false) e commit()/rollback()
            // para este caso simples de uma única inserção.

            // 1. Inserir o médico
            stmtMedico = conexao.prepareStatement(sqlMedico);
            stmtMedico.setString(1, medico.getCrm());
            stmtMedico.setString(2, medico.getNome());
            stmtMedico.setString(3, medico.getCpf());
            stmtMedico.setString(4, medico.getDataNascimento());
            stmtMedico.setString(5, medico.getEstadoCivil());
            stmtMedico.setString(6, medico.getSexo());
            stmtMedico.setString(7, medico.getConvenio());
            stmtMedico.setString(8, medico.getEndereco());
            stmtMedico.setString(9, medico.getObservacoes());
            // Define o CBO da especialidade selecionada (pode ser null se nenhuma for selecionada)
            if (medico.getEspecialidade() != null) {
                stmtMedico.setString(10, medico.getEspecialidade());
            } else {
                stmtMedico.setNull(10, java.sql.Types.VARCHAR); // Se nenhuma especialidade for selecionada, insere NULL
            }


            stmtMedico.executeUpdate(); // Executa a inserção do médico

            JOptionPane.showMessageDialog(null, "Médico " + medico.getNome() + " cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar médico: " + e.getMessage(), "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
            System.err.println("Erro SQL ao cadastrar médico: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fecha os recursos
            try {
                if (stmtMedico != null) stmtMedico.close();
                ConnectDatabase.closeConnection(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}