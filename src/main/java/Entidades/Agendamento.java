package Entidades;


import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Classe que representa a entidade Agendamento de Consulta.
 */
public class Agendamento {

    private LocalDate data;
    private LocalTime hora;
    private String paciente;
    private String convenio;
    private String medico;
    private String especialidade;

    // Getters e Setters para todos os atributos
    
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}