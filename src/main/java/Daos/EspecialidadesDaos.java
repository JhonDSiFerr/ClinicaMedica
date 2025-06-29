// src/dao/EspecialidadeDAO.java (CÓDIGO INALTERADO DO ÚLTIMO EXEMPLO)
package Daos;

import Entidades.Especialidade;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class EspecialidadesDaos {

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
}