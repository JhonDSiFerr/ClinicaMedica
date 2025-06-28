// src/dao/ConvenioDAO.java
package Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; // Importe ArrayList
import java.util.List;      // Importe List
import javax.swing.JOptionPane; // Para exibir mensagens ao usuário

public class EspecialidadesDaos {


    public List<String> BuscarTodasEspecialidades() {
        List<String> listaEspecialidades = new ArrayList<>();
        String sql = "SELECT Nome FROM especialidades ORDER BY Nome ASC";

        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectDatabase.getConnection(); // Obtém a conexão com o banco de dados
            stmt = conexao.prepareStatement(sql); // Prepara a instrução SQL
            rs = stmt.executeQuery(); // Executa a consulta e armazena os resultados

            while (rs.next()) { // Itera sobre cada linha do resultado
                listaEspecialidades.add(rs.getString("nome_especialidade")); // Adiciona o nome do convênio à lista
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar especialidades: " + e.getMessage(), "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
            System.err.println("Erro SQL ao buscar especialidade na EspecialidadesDAO: " + e.getMessage());
            e.printStackTrace(); // Para depuração
        } finally {
            // Garante que os recursos (ResultSet, PreparedStatement, Connection) sejam fechados
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                ConnectDatabase.closeConnection(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos na EspecialidadesDAO: " + e.getMessage());
            }
        }
        return listaEspecialidades;
    }
}