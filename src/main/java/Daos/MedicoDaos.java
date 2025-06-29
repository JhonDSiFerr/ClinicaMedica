package Daos; // Seu pacote de DAOs

import Entidades.Medico;
import Entidades.Especialidade; // Para usar o objeto Especialidade no Medico
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MedicoDaos { // O nome da sua classe DAO (MedicoDaos com 's')

    /**
     * Adiciona um novo médico na tabela 'medico'.
     *
     * @param medico O objeto Medico a ser adicionado.
     */
    public void adicionar(Medico medico) {
        // SQL para inserir o médico com a coluna especialidade_cbo
        String sqlMedico = "INSERT INTO medico (CRM, nome, cpf, data_nascimento, estado_civil, "
                + "sexo, convenio, endereco, observacoes, especialidade_cbo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // 10 placeholders

        Connection conexao = null;
        PreparedStatement stmtMedico = null;

        try {
            conexao = ConnectDatabase.getConnection(); // Sua classe de conexão
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
                stmtMedico.setString(10, medico.getEspecialidade().getCbo());
            } else {
                stmtMedico.setNull(10, java.sql.Types.VARCHAR); // Insere NULL se a especialidade for nula
            }

            stmtMedico.executeUpdate(); // Executa a inserção do médico

            JOptionPane.showMessageDialog(null, "Médico " + medico.getNome() + " cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            // Tratamento específico para erros de chave única (CRM ou CPF duplicado)
            if (e.getSQLState().startsWith("23")) { // SQLState para violação de integridade
                JOptionPane.showMessageDialog(null, "Erro: CRM ou CPF já existem no sistema. Verifique os dados.", "Erro de Duplicidade", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar médico: " + e.getMessage(), "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
            }
            System.err.println("Erro SQL ao cadastrar médico: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fecha os recursos do banco de dados
            try {
                if (stmtMedico != null) {
                    stmtMedico.close();
                }
                ConnectDatabase.closeConnection(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    /**
     * Busca todos os médicos do banco de dados para popular componentes como JComboBox.
     * Inclui a especialidade vinculada a cada médico.
     *
     * @return Uma lista de objetos Medico.
     */
    public List<Medico> buscarTodosMedicos() {
        List<Medico> listaMedicos = new ArrayList<>();
        // Seleciona todas as colunas do médico, incluindo especialidade_cbo
        String sql = "SELECT CRM, nome, cpf, data_nascimento, estado_civil, sexo, convenio, endereco, observacoes, especialidade_cbo FROM medico ORDER BY nome ASC";

        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectDatabase.getConnection(); // Sua classe de conexão
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Especialidade especialidadeMedico = null;
                String cboMedico = rs.getString("especialidade_cbo"); // Pega o CBO da especialidade vinculada

                // Se houver um CBO, cria um objeto Especialidade para o médico
                if (cboMedico != null && !cboMedico.isEmpty()) {
                    // Crie um objeto Especialidade. Usamos "Especialidade " + cboMedico como nome
                    // porque buscar o nome real aqui envolveria outra consulta ao banco,
                    // o que pode ser ineficiente para muitos médicos.
                    // O toString() da Especialidade (Nome + CBO) já deve exibir bem no ComboBox.
                    especialidadeMedico = new Especialidade(cboMedico, "Especialidade " + cboMedico);
                }

                // Cria o objeto Medico com os dados do ResultSet e o objeto Especialidade
                Medico medico = new Medico(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("CRM"),
                    rs.getString("data_nascimento"),
                    rs.getString("estado_civil"),
                    rs.getString("sexo"),
                    rs.getString("convenio"),
                    rs.getString("endereco"),
                    rs.getString("observacoes"),
                    especialidadeMedico // Passa o objeto Especialidade (ou null, se não houver)
                );
                listaMedicos.add(medico);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar médicos: " + e.getMessage(), "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
            System.err.println("Erro SQL ao buscar médicos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fecha os recursos do banco de dados
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                ConnectDatabase.closeConnection(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return listaMedicos;
    }
}