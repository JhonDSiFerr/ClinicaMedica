// src/Entidades/Paciente.java
package Entidades;

public class Paciente {
    private String nome;
    private String cpf; // OK, é String
    private String telefone;
    private String dataNascimento;    
    private String endereco;
    private String sexo;
    private String estadoCivil;
    private String convenio; // 'S' ou 'N'
    private String informacoes;
    private String conveniosList;
    private String prontuario; // Atributo para Prontuario

    // Construtor completo para quando o Prontuario É LIDO do banco de dados (tem valor)
    public Paciente(String nome, String cpf, String telefone, String dataNascimento, String endereco,
                    String sexo, String estadoCivil, String convenio, String informacoes,
                    String conveniosList, String prontuario) { // Incluído 'prontuario'
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
        this.prontuario = prontuario; // Inicializa prontuario
    }

    // Opcional: Construtor para quando o Prontuario NÃO é passado (ex: no cadastro, ele pode ser null ou gerado automaticamente)
    // Se o prontuário for gerado automaticamente pelo banco, você pode usar este construtor
    // e o PacienteDAO de adicionar não precisará passar o prontuário.
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
        this.prontuario = null; // Garante que prontuario é null por padrão
    }


    // --- Getters e Setters (já estão corretos) ---
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; } // Retorna String
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public String getConvenio() { return convenio; }
    public void setConvenio(String convenio) { this.convenio = convenio; }

    public String getInformacoes() { return informacoes; }
    public void setInformacoes(String informacoes) { this.informacoes = informacoes; }

    public String getConveniosList() { return conveniosList; }
    public void setConveniosList(String conveniosList) { this.conveniosList = conveniosList; }

    public String getProntuario() { return prontuario; } // Getter para prontuario
    public void setProntuario(String prontuario) { this.prontuario = prontuario; } // Setter para prontuario

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")"; // Exibe o nome e CPF no ComboBox
    }
}