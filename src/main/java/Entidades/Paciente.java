package Entidades;

public class Paciente {
    private String Nome;
    private String Cpf;
    private String Endereco;
    private String Telefone;
    private String Sexo;
    private String Datadenascimento;
    private String EstadoCivil;
    private String informacao;
    private char convenio;
    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        this.Cpf = cpf;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        this.Endereco = endereco;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        this.Telefone = telefone;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        this.Sexo = sexo;
    }

    public String getDatadenascimento() {
        return Datadenascimento;
    }

    public void setDatadenascimento(String datadenascimento) {
        this.Datadenascimento = datadenascimento;
    }

    public String getEstadoCivil() {
        return EstadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.EstadoCivil = estadoCivil;
    }

    public String getInformacao() {
        return informacao;
    }

    public void setInformacao(String informacao) {
        this.informacao = informacao;
    }

    public char getConvenio() {
        return convenio;
    }

    public void setConvenio(char convenio) {
        this.convenio = convenio;
    }

}
