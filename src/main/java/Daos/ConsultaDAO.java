package Daos; // Seu pacote de DAOs

import Entidades.Consulta;
import Entidades.Medico;
import Entidades.Paciente;
import Entidades.Especialidade;
// Importe sua classe de conexão, garantindo que o caminho está correto
import Daos.ConnectDatabase; // Exemplo: se ConnectDatabase está no pacote Daos

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConsultaDAO {
    public Consulta buscarUltimaConsultaDoPaciente(String pacienteCpf) {
        Consulta ultimaConsulta = null;
        // Seleciona a consulta mais recente para o paciente
        String sql = "SELECT id_consulta, data_consulta, hora_consulta, medico_crm, " +
                     "paciente_cpf, especialidade_cbo, convenio_nome, observacoes, e_retorno " +
                     "FROM consulta " +
                     "WHERE paciente_cpf = ? " +
                     "ORDER BY STR_TO_DATE(data_consulta, '%d/%m/%Y') DESC, hora_consulta DESC " + // Converte a string para data para ordenar
                     "LIMIT 1"; // Pega apenas a mais recente

        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectDatabase.getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, pacienteCpf);
            rs = stmt.executeQuery();

            if (rs.next()) { // Se encontrou uma consulta
                // Aqui você precisaria buscar os objetos completos de Medico, Paciente e Especialidade
                // apenas com os IDs (CRM, CPF, CBO) retornados.
                // Para simplificar, vou criar objetos Medico, Paciente e Especialidade apenas com seus IDs
                // e nomes temporários. Em um sistema real, você chamaria os DAOs correspondentes
                // (MedicoDaos, PacienteDaos, EspecialidadeDaoCad) para buscar os objetos completos.

                // Apenas criando objetos com os IDs necessários para o Medico e Especialidade.
                // Paciente já temos o objeto completo para o parâmetro.
                Medico medicoDaConsulta = new Medico(
                    null, null, rs.getString("medico_crm"), // CRM é a chave
                    null, null, null, null, null, null, null
                );
                // O toString() do Medico deve exibir o CRM (e nome, se buscar)

                Especialidade especialidadeDaConsulta = new Especialidade(
                    rs.getString("especialidade_cbo"), // CBO é a chave
                    null // Nome temporário, toString() de Especialidade já usa o CBO
                );

                ultimaConsulta = new Consulta(
                    rs.getInt("id_consulta"),
                    rs.getString("data_consulta"),
                    rs.getString("hora_consulta"),
                    medicoDaConsulta,
                    // Passando null para o objeto Paciente, pois já o temos como parâmetro pacienteCpf
                    // ou você pode buscar o paciente completo aqui se precisar
                    null, // Passando null, ou você pode passar o paciente completo (buscarTodosPacientes().stream().filter().findFirst().orElse(null))
                    especialidadeDaConsulta,
                    rs.getString("convenio_nome"),
                    rs.getString("observacoes"),
                    rs.getString("e_retorno")
                );
            }

        } catch (SQLException e) {
            System.err.println("Erro SQL ao buscar última consulta do paciente: " + e.getMessage());
            e.printStackTrace();
            // Nao mostre JOptionPane aqui, pois o metodo que chama lidara com isso.
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                ConnectDatabase.closeConnection(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos (buscarUltimaConsultaDoPaciente): " + e.getMessage());
            }
        }
        return ultimaConsulta;
    }

    /**
     * Agenda uma nova consulta no banco de dados.
     *
     * @param consulta O objeto Consulta contendo todos os dados da consulta.
     */
    public void agendar(Consulta consulta) {
        // SQL para inserir a consulta, incluindo a nova coluna 'e_retorno'
        String sql = "INSERT INTO consulta (data_consulta, hora_consulta, medico_crm, " +
                     "paciente_cpf, especialidade_cbo, convenio_nome, observacoes, e_retorno) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; // 8 placeholders para 8 colunas

        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConnectDatabase.getConnection(); // Obtém a conexão com o banco de dados
            stmt = conexao.prepareStatement(sql);     // Prepara a instrução SQL

            // Define os valores para os placeholders na ordem correta
            stmt.setString(1, consulta.getDataConsulta());
            stmt.setString(2, consulta.getHoraConsulta());
            // Pega o CRM do objeto Medico (que vem do ComboBox)
            stmt.setString(3, consulta.getMedico().getCrm());
            // Pega o CPF do objeto Paciente (que é String em Entidades.Paciente.java)
            stmt.setString(4, consulta.getPaciente().getCpf());
            // Pega o CBO do objeto Especialidade (que vem do ComboBox)
            stmt.setString(5, consulta.getEspecialidade().getCbo());
            stmt.setString(6, consulta.getConvenioNome());
            // Coleta observações (pode ser vazia se não houver campo)
            stmt.setString(7, consulta.getObservacoes());
            // Coleta o estado do checkbox de retorno ('S' ou 'N')
            stmt.setString(8, consulta.getERetorno());

            stmt.executeUpdate(); // Executa a inserção no banco de dados

            // Mensagem de sucesso para o usuário
            JOptionPane.showMessageDialog(null,
                    "Consulta agendada com sucesso para " + consulta.getPaciente().getNome() +
                    " com " + consulta.getMedico().getNome() + "!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            // Mensagem de erro em caso de falha na inserção
            JOptionPane.showMessageDialog(null, "Erro ao agendar consulta: " + e.getMessage(), "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
            System.err.println("Erro SQL ao agendar consulta: " + e.getMessage());
            e.printStackTrace(); // Imprime o stack trace completo para depuração
        } finally {
            // Garante que os recursos do banco de dados sejam fechados, mesmo em caso de erro
            try {
                if (stmt != null) {
                    stmt.close(); // Fecha o PreparedStatement
                }
                ConnectDatabase.closeConnection(conexao); // Fecha a conexão
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos do banco de dados: " + e.getMessage());
            }
        }
    }
}