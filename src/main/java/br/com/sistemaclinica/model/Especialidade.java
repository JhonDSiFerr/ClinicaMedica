package br.com.sistemaclinica.model;

public class Especialidade {
    
    private int id;
    private String descricao;
    private String cbo;
    private String nome;
    public Especialidade() {}

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getCbo() { return cbo; }
    public void setCbo(String cbo) { this.cbo = cbo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    // Este método é crucial para o JComboBox funcionar corretamente
    @Override
    public String toString() {
        return this.getDescricao();
    }
}