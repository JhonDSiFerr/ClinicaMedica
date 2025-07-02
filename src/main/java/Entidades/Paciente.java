package Entidades;

import java.time.LocalDate;

public class Paciente {

    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private String sexo;
    private LocalDate dataNascimento;
    private String estadoCivil;
    private String convenio;
    private String informacoes;

    // Getters e Setters para todos os atributos
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public String getConvenio() { return convenio; }
    public void setConvenio(String convenio) { this.convenio = convenio; }

    public String getInformacoes() { return informacoes; }
    public void setInformacoes(String informacoes) { this.informacoes = informacoes; }
}