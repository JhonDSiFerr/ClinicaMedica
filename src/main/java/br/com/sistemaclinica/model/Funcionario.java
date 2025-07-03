package br.com.sistemaclinica.model;


import java.time.LocalDate; // Importa a classe para trabalhar com datas

/**
 * Representa um funcionário da clínica. Contém dados pessoais, de endereço,
 * contato e informações específicas do sistema como usuário e senha.
 */
public class Funcionario {
    
    // Atributos baseados no seu diagrama [cite: 8]
    private int id; // [cite: 9]
    private String nome; // [cite: 12]
    private String usuario; // [cite: 10]
    private String senha; // [cite: 11]
    private int idade; // [cite: 13]
    private char sexo; // [cite: 14]
    private String cpf; // [cite: 15]
    private String rua; // [cite: 16]
    private String numero; // [cite: 17]
    private String complemento; // [cite: 18]
    private String bairro; // [cite: 19]
    private String cidade; // [cite: 20]
    private String estado; // [cite: 21]
    private String contato; // [cite: 22]
    private String email; // [cite: 23]
    private LocalDate dataNascimento; // [cite: 24]
    
    // Atributos de relacionamento
    private TipoFuncionario tipoFuncionario; // [cite: 7]
    private Perfil perfil;
    private Especialidade especialidade;

    public Funcionario() {
    }

    // Getters e Setters para todos os atributos (gerados no NetBeans)
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    // ... continue para todos os outros atributos ...

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    // (etc.)
    
    public TipoFuncionario getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDate getDataNascimento() {
       return dataNascimento;
    }

    public void setDataNascimento( LocalDate dataNascimento ) {
       this.dataNascimento = dataNascimento;
   
    }
     public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
         public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
public String toString() {
    return this.getNome(); // Retorna o nome do funcionário
}
}