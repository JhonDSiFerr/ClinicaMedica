package br.com.sistemaclinica.model;


import java.time.LocalDateTime;

public class Consulta {

    private int id;
    private LocalDateTime dataHorario;
    private String sintomas;
    private boolean eRetorno;
    private boolean estaAtiva;
    private Especialidade especialidade;
    // Relacionamentos com outras entidades
    private Paciente paciente;
    private Funcionario medico;
    private String descricao;

    public Consulta() {
    }

    // Getters e Setters para todos os atributos
    public Especialidade getEspecialidade() {
    return especialidade;
}

public void setEspecialidade(Especialidade especialidade) {
    this.especialidade = especialidade;
}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDateTime getDataHorario() { return dataHorario; }
    public void setDataHorario(LocalDateTime dataHorario) { this.dataHorario = dataHorario; }
    public String getSintomas() { return sintomas; }
    public void setSintomas(String sintomas) { this.sintomas = sintomas; }
    public boolean iseRetorno() { return eRetorno; }
    public void seteRetorno(boolean eRetorno) { this.eRetorno = eRetorno; }
    public boolean isEstaAtiva() { return estaAtiva; }
    public void setEstaAtiva(boolean estaAtiva) { this.estaAtiva = estaAtiva; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public Funcionario getMedico() { return medico; }
    public void setMedico(Funcionario medico) { this.medico = medico; }
   public String getDescricao() {
    return descricao;
}

public void setDescricao(String descricao) {
    this.descricao = descricao;
}

@Override
public String toString() {
    // É mais útil que o toString da consulta retorne algo identificável
    return "Consulta #" + id + " - " + paciente.getNome();
}
}