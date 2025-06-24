package Entidades;

public class consulta {
    private String Data;
    private Paciente paciente;
    private Especialidade especialidade;
    private Medico medico;
    private char convenio;

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        this.Data = data;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public char getConvenio() {
        return convenio;
    }

    public void setConvenio(char convenio) {
        this.convenio = convenio;
    }
}
