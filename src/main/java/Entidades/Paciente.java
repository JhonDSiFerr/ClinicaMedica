// src/model/Paciente.java
package Entidades;

public class Paciente {
    private String nome;
    private String cpf;
    private String telefone;
    private String dataNascimento; 
    private String endereco;
    private String sexo;
    private String estadoCivil;
    private String convenio; // 'S' ou 'N'
    private String informacoes;
    private String conveniosList;
    private String prontuario; 

  
    public Paciente(String nome, String cpf, String telefone, String dataNascimento, String endereco,
                    String sexo, String estadoCivil, String convenio, String informacoes,
                    String conveniosList) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.convenio = convenio;
        this.informacoes = informacoes;
        this.conveniosList = conveniosList;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

    public String getConveniosList() {
        return conveniosList;
    }

    public void setConveniosList(String conveniosList) {
        this.conveniosList = conveniosList;
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }
}