package br.com.sistemaclinica.model;

public class Perfil {

    private int id;
    private String nome;

    // --- Permissões ---

    // Módulo Funcionário
    private boolean cadastrarFuncionario;
    private boolean lerFuncionario;
    private boolean atualizarFuncionario;
    private boolean deletarFuncionario;
    private boolean listarFuncionario;

    // Módulo Paciente
    private boolean cadastrarPaciente;
    private boolean lerPaciente;
    private boolean atualizarPaciente;
    private boolean deletarPaciente;
    private boolean listarPaciente;

    // Módulo Consulta
    private boolean cadastrarConsulta;
    private boolean lerConsulta;
    private boolean atualizarConsulta;
    private boolean deletarConsulta;
    private boolean listarConsulta;

    // Construtor Padrão
    public Perfil() {
    }

    // --- Getters e Setters ---

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

    public boolean isCadastrarFuncionario() {
        return cadastrarFuncionario;
    }

    public void setCadastrarFuncionario(boolean cadastrarFuncionario) {
        this.cadastrarFuncionario = cadastrarFuncionario;
    }

    public boolean isLerFuncionario() {
        return lerFuncionario;
    }

    public void setLerFuncionario(boolean lerFuncionario) {
        this.lerFuncionario = lerFuncionario;
    }

    public boolean isAtualizarFuncionario() {
        return atualizarFuncionario;
    }

    public void setAtualizarFuncionario(boolean atualizarFuncionario) {
        this.atualizarFuncionario = atualizarFuncionario;
    }

    public boolean isDeletarFuncionario() {
        return deletarFuncionario;
    }

    public void setDeletarFuncionario(boolean deletarFuncionario) {
        this.deletarFuncionario = deletarFuncionario;
    }

    public boolean isListarFuncionario() {
        return listarFuncionario;
    }

    public void setListarFuncionario(boolean listarFuncionario) {
        this.listarFuncionario = listarFuncionario;
    }

    public boolean isCadastrarPaciente() {
        return cadastrarPaciente;
    }

    public void setCadastrarPaciente(boolean cadastrarPaciente) {
        this.cadastrarPaciente = cadastrarPaciente;
    }

    public boolean isLerPaciente() {
        return lerPaciente;
    }

    public void setLerPaciente(boolean lerPaciente) {
        this.lerPaciente = lerPaciente;
    }

    public boolean isAtualizarPaciente() {
        return atualizarPaciente;
    }

    public void setAtualizarPaciente(boolean atualizarPaciente) {
        this.atualizarPaciente = atualizarPaciente;
    }

    public boolean isDeletarPaciente() {
        return deletarPaciente;
    }

    public void setDeletarPaciente(boolean deletarPaciente) {
        this.deletarPaciente = deletarPaciente;
    }

    public boolean isListarPaciente() {
        return listarPaciente;
    }

    public void setListarPaciente(boolean listarPaciente) {
        this.listarPaciente = listarPaciente;
    }

    public boolean isCadastrarConsulta() {
        return cadastrarConsulta;
    }

    public void setCadastrarConsulta(boolean cadastrarConsulta) {
        this.cadastrarConsulta = cadastrarConsulta;
    }

    public boolean isLerConsulta() {
        return lerConsulta;
    }

    public void setLerConsulta(boolean lerConsulta) {
        this.lerConsulta = lerConsulta;
    }

    public boolean isAtualizarConsulta() {
        return atualizarConsulta;
    }

    public void setAtualizarConsulta(boolean atualizarConsulta) {
        this.atualizarConsulta = atualizarConsulta;
    }

    public boolean isDeletarConsulta() {
        return deletarConsulta;
    }

    public void setDeletarConsulta(boolean deletarConsulta) {
        this.deletarConsulta = deletarConsulta;
    }

    public boolean isListarConsulta() {
        return listarConsulta;
    }

    public void setListarConsulta(boolean listarConsulta) {
        this.listarConsulta = listarConsulta;
    }
}