// src/model/Medico.java
package Entidades; // Pacote correto: Entidades

public class Medico {
    private String nome;
    private String cpf;
    private String crm;
    private String dataNascimento;
    private String estadoCivil;
    private String sexo;
    private String convenio; // 'S' ou 'N'
    private String endereco;
    private String observacoes;

    private Especialidade especialidade; // Atributo: um objeto Especialidade único

    // Construtor principal
    public Medico(String nome, String cpf, String crm, String dataNascimento,
                  String estadoCivil, String sexo, String convenio,
                  String endereco, String observacoes, Especialidade especialidade) {
        this.nome = nome;
        this.cpf = cpf;
        this.crm = crm;
        this.dataNascimento = dataNascimento;
        this.estadoCivil = estadoCivil;
        this.sexo = sexo;
        this.convenio = convenio;
        this.endereco = endereco;
        this.observacoes = observacoes;
        this.especialidade = especialidade;
    }

    // --- Getters e Setters ---
    // O CRM será o identificador principal, mas também tem seus getters/setters
    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }

    // ***** MÉTODO GETTER DA ESPECIALIDADE - CORRIGIDO AQUI! *****
    public Especialidade getEspecialidade() { // AGORA RETORNA Especialidade
        return especialidade;
    }
    public void setEspecialidade(Especialidade especialidade) { // Tipo do parâmetro correto
        this.especialidade = especialidade;
    }
    // *********************************************************

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getConvenio() { return convenio; }
    public void setConvenio(String convenio) { this.convenio = convenio; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}