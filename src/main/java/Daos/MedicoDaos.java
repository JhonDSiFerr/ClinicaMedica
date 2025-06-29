// src/dao/MedicoDAO.java
package Daos;

import Entidades.Medico;
import Entidades.Especialidade; // Mantenha este import, pois o Medico.java ainda usa Especialidade


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MedicoDaos {

    public void adicionar(Medico medico) {
        // SQL para inserir o médico, AGORA COM A COLUNA especialidade_cbo diretamente
        // Certifique-se que sua tabela `medico` no MySQL tem a coluna `especialidade_cbo`
        String sqlMedico = "INSERT INTO medico (CRM, nome, cpf, data_nascimento, estado_civil, " +
                           "sexo, convenio, endereco, observacoes, especialidade_cbo) " + // Coluna especialidade_cbo
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // Um placeholder a mais

        Connection conexao = null;
        PreparedStatement stmtMedico = null;

        try {
            conexao = ConnectDatabase.getConnection();
            // Para seleção única, não precisamos de transação complexa com rollback,
            // pois é uma única operação INSERT no médico.
            // conexao.setAutoCommit(false); // Não é mais necessário para este cenário simples

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
            
            // AQUI ESTÁ A MUDANÇA PRINCIPAL: Lidar com a ÚNICA especialidade
            if (medico.getEspecialidade() != null) {
                // Se uma especialidade foi selecionada, pegue o CBO dela
                stmtMedico.setString(10, medico.getEspecialidade().getCbo());
            } else {
                // Se nenhuma especialidade foi selecionada (ou item "Selecione"), insere NULL
                stmtMedico.setNull(10, java.sql.Types.VARCHAR);
            }

            stmtMedico.executeUpdate(); // Executa a inserção do médico

            // conexao.commit(); // Não é mais necessário para este cenário
            JOptionPane.showMessageDialog(null, "Médico " + medico.getNome() + " cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            // Se houver erro, apenas exibe a mensagem, sem rollback de transação aqui
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar médico: " + e.getMessage(), "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
            System.err.println("Erro SQL ao cadastrar médico: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fecha os recursos
            try {
                if (stmtMedico != null) stmtMedico.close();
                // if (conexao != null) conexao.setAutoCommit(true); // Não é mais necessário
                ConnectDatabase.closeConnection(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}