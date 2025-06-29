// src/Daos/EspecialidadeDaoCad.java
package Daos; // Seu pacote de DAOs

import Entidades.Especialidade;
import Entidades.Medico; // Para o método de buscar médicos
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

// Importante: Certifique-se que ConnectDatabase.java existe e está no pacote Daos, ou ajuste o import e as chamadas.
// Ex: import util.ConnectDatabase; se estiver em 'util'
// Ou, se ConnectDatabase estiver em Daos:
// import Daos.ConnectDatabase; // Adicione esta linha se não estiver

public class EspecialidadeDaoCad {

    /**
     * Adiciona uma nova especialidade na tabela 'especialidades'.
     * @param especialidade O objeto Especialidade a ser adicionado.
     */
    public void adicionar(Especialidade especialidade) {
        String sql = "INSERT INTO especialidades (Dataadd, CBO, Convenio, Nome) VALUES (?, ?, ?, ?)";

        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConnectDatabase.getConnection(); // Usando sua classe de conexão
            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, especialidade.getDataAdd());
            stmt.setString(2, especialidade.getCbo());
            stmt.setString(3, especialidade.getConvenio());
            stmt.setString(4, especialidade.getNomeEspecialidade());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Especialidade '" + especialidade.getNomeEspecialidade() + "' adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            // Tratamento mais específico para chaves duplicadas (CBO ou Nome UNIQUE)
            if (e.getSQLState().startsWith("23")) { // SQLState para violação de integridade (duplicidade)
                 JOptionPane.showMessageDialog(null, "Erro: CBO ou Nome da Especialidade já existem. Verifique os dados.", "Erro de Duplicidade", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar especialidade: " + e.getMessage(), "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
            }
            System.err.println("Erro SQL ao adicionar especialidade: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                ConnectDatabase.closeConnection(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    /**
     * Busca todas as especialidades do banco de dados (para popular ComboBox/JList).
     * @return Uma lista de objetos Especialidade (apenas CBO e Nome preenchidos).
     */
    public List<Especialidade> buscarTodasEspecialidades() {
        List<Especialidade> listaEspecialidades = new ArrayList<>();
        String sql = "SELECT CBO, Nome FROM especialidades ORDER BY Nome ASC";

        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectDatabase.getConnection();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String cbo = rs.getString("CBO");
                String nome = rs.getString("Nome");
                listaEspecialidades.add(new Especialidade(cbo, nome));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar especialidades: " + e.getMessage(), "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
            System.err.println("Erro SQL ao buscar especialidades: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                ConnectDatabase.closeConnection(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return listaEspecialidades;
    }

    /**
     * Busca todos os médicos que atendem a uma especialidade específica.
     * @param cboEspecialidade O CBO da especialidade a ser buscada.
     * @return Uma lista de objetos Medico que atendem a essa especialidade.
     */
    public List<Medico> buscarMedicosPorEspecialidade(String cboEspecialidade) {
        List<Medico> medicosDaEspecialidade = new ArrayList<>();
        String sql = "SELECT m.CRM, m.Nome, m.CPF, m.datanascimento, m.`Estadocivil`, " +
                     "m.Sexo, m.Convenio, m.Endereco, m.Observacoes " +
                     "FROM medico m " +
                     "JOIN medico_especialidade me ON m.CRM = me.medico_crm " +
                     "WHERE me.especialidade_cbo = ?";

        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectDatabase.getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cboEspecialidade);
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Ao buscar o médico aqui, assumimos que ele tem UMA especialidade (se estiver usando JComboBox)
                // ou que a lista de especialidades será preenchida em outro momento (se for JList)
                // Neste contexto, para evitar recursão infinita, a lista de especialidades do médico é null.
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
                    null // Importante: Especialidade do médico é nula nesta busca para evitar loop infinito
                );
                medicosDaEspecialidade.add(medico);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar médicos por especialidade: " + e.getMessage(), "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
            System.err.println("Erro SQL ao buscar médicos por especialidade: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                ConnectDatabase.closeConnection(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return medicosDaEspecialidade;
    }
}