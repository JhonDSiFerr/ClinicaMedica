package Daos; // Mude para o nome do seu pacote

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    // Constantes para os tipos de usuário
    public static final int ATENDENTE = 1;
    public static final int MEDICO = 2;
    public static final int NENHUM = 0;

    /**
     * Verifica as credenciais do usuário nas tabelas atendentelogin e medicologin.
     *
     * @param usuario O nome de usuário a ser verificado.
     * @param senha A senha a ser verificada.
     * @return ATENDENTE se o login for de um atendente, MEDICO se for de um médico,
     * ou NENHUM se as credenciais forem inválidas.
     */
    public int verificarLogin(String usuario, String senha) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectDatabase.getConnection(); // Obtém a conexão

            // Primeiro, tenta na tabela atendentelogin
            String sqlAtendente = "SELECT * FROM loginatendente WHERE Usuario = ? AND Senha = ?";
            stmt = con.prepareStatement(sqlAtendente);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return ATENDENTE; // Encontrou como atendente
            }

            // Se não encontrou como atendente, tenta na tabela medicologin
            String sqlMedico = "SELECT * FROM loginmedico WHERE Usuario = ? AND Senha = ?";
            stmt.close(); // Fecha o PreparedStatement anterior
            rs.close();   // Fecha o ResultSet anterior

            stmt = con.prepareStatement(sqlMedico);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return MEDICO; // Encontrou como médico
            }

        } catch (SQLException e) {
            System.err.println("Erro ao verificar login: " + e.getMessage());
            // Opcional: JOptionPane.showMessageDialog para o usuário final
        } finally {
            // Garante que os recursos são fechados
            ConnectDatabase.closeConnection(con);
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return NENHUM; // Credenciais inválidas em ambas as tabelas
    }
}