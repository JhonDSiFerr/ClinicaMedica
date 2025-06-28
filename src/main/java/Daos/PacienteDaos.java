// src/dao/PacienteDAO.java
package Daos;

import Entidades.Paciente; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane; // Para exibir mensagens ao usuário

public class PacienteDaos {

    public void adicionar(Paciente paciente) {
        
        String sql = "INSERT INTO pacientes (Nome, Cpf, Telefone, `Data nasc.`, Endereco, Sexo, " +
                     "`Estado Civil`, Convenio, Informacoes, `Convenios list`, Prontuario) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, null)";

        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConnectDatabase.getConnection(); 
            stmt = conexao.prepareStatement(sql); 
         
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getTelefone());
            stmt.setString(4, paciente.getDataNascimento());
            stmt.setString(5, paciente.getEndereco());
            stmt.setString(6, paciente.getSexo());
            stmt.setString(7, paciente.getEstadoCivil());
            stmt.setString(8, paciente.getConvenio());
            stmt.setString(9, paciente.getInformacoes());
            stmt.setString(10, paciente.getConveniosList());
           

            stmt.executeUpdate(); 

           
            JOptionPane.showMessageDialog(null, "Paciente " + paciente.getNome() + " adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
        
            JOptionPane.showMessageDialog(null, "Erro ao adicionar paciente: " + e.getMessage(), "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
            System.err.println("Erro SQL ao adicionar paciente: " + e.getMessage());
            e.printStackTrace(); 
        } finally {
       
            try {
                if (stmt != null) {
                    stmt.close();
                }
                ConnectDatabase.closeConnection(conexao); 
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}