package br.com.sistemaclinica.model;


public class Prontuario {

    private int id;
    private String receituario;
    private String exames;
    private String observacoes;

    // Relacionamento com o Paciente
    private Paciente paciente;

    public Prontuario() {
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getReceituario() { return receituario; }
    public void setReceituario(String receituario) { this.receituario = receituario; }
    public String getExames() { return exames; }
    public void setExames(String exames) { this.exames = exames; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
}