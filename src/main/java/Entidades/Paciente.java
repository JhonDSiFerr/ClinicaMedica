package Entidades;


import java.time.LocalDate;

/**
 * Classe de Modelo (Model) que representa a entidade Paciente.
 * Ela serve como um "molde" para armazenar os dados de cada paciente.
 */
public class Paciente {
    
    // Atributos que correspondem às colunas da sua tabela no banco de dados
    private String cpf;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
    // Se tiver mais colunas (ex: sexo, endereco), adicione os atributos aqui

    // Métodos Getters e Setters para aceder e modificar os atributos de forma segura
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}