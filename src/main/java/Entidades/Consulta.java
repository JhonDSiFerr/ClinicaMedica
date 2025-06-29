// src/Entidades/Consulta.java
package Entidades;

// Importe as classes de modelo que a consulta vai referenciar
import Entidades.Medico;
import Entidades.Paciente;
import Entidades.Especialidade;

public class Consulta {
    private int idConsulta;
    private String dataConsulta;
    private String horaConsulta;
    private Medico medico; // Objeto Medico que realizou a consulta
    private Paciente paciente; // Objeto Paciente da consulta
    private Especialidade especialidade; // Objeto Especialidade da consulta
    private String convenioNome; // Nome do convênio (vindo da seleção da tela)
    private String observacoes;
    private String eRetorno; // Atributo para indicar se é retorno ('S' ou 'N')

    // Construtor para criar uma nova consulta (sem ID, que será auto-gerado)
    // AGORA ACEITA O PARÂMETRO eRetorno
    public Consulta(String dataConsulta, String horaConsulta, Medico medico,
                    Paciente paciente, Especialidade especialidade,
                    String convenioNome, String observacoes, String eRetorno) { // <-- Adicione 'String eRetorno' aqui
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
        this.medico = medico;
        this.paciente = paciente;
        this.especialidade = especialidade;
        this.convenioNome = convenioNome;
        this.observacoes = observacoes;
        this.eRetorno = eRetorno; // <-- E inicialize o atributo aqui
    }

    // Construtor completo (para quando buscar do banco de dados, que já terá ID)
    // AGORA CHAMA O CONSTRUTOR ACIMA, PASSANDO 'eRetorno' TAMBÉM
    public Consulta(int idConsulta, String dataConsulta, String horaConsulta, Medico medico,
                    Paciente paciente, Especialidade especialidade,
                    String convenioNome, String observacoes, String eRetorno) { // <-- Adicione 'String eRetorno' aqui
        // Chama o outro construtor, passando todos os parâmetros
        this(dataConsulta, horaConsulta, medico, paciente, especialidade, convenioNome, observacoes, eRetorno); // <-- Passando 'eRetorno'
        this.idConsulta = idConsulta;
    }

    // --- Getters e Setters ---
    public int getIdConsulta() { return idConsulta; }
    public void setIdConsulta(int idConsulta) { this.idConsulta = idConsulta; }

    public String getDataConsulta() { return dataConsulta; }
    public void setDataConsulta(String dataConsulta) { this.dataConsulta = dataConsulta; }

    public String getHoraConsulta() { return horaConsulta; }
    public void setHoraConsulta(String horaConsulta) { this.horaConsulta = horaConsulta; }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Especialidade getEspecialidade() { return especialidade; }
    public void setEspecialidade(Especialidade especialidade) { this.especialidade = especialidade; }

    public String getConvenioNome() { return convenioNome; }
    public void setConvenioNome(String convenioNome) { this.convenioNome = convenioNome; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    // NOVO GETTER E SETTER PARA eRetorno
    public String getERetorno() { return eRetorno; }
    public void setERetorno(String eRetorno) { this.eRetorno = eRetorno; }
}