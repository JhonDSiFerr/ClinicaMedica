// src/model/Especialidade.java
package Entidades;

public class Especialidade {
    private String cbo; // Atributo para o CBO
    private String nomeEspecialidade;

    // Construtor
    public Especialidade(String cbo, String nomeEspecialidade) {
        this.cbo = cbo;
        this.nomeEspecialidade = nomeEspecialidade;
    }

    // --- GETTER E SETTER PARA CBO (VERIFIQUE ESTES NOMES) ---
    public String getCbo() { // Este método é crucial!
        return cbo;
    }

    public void setCbo(String cbo) {
        this.cbo = cbo;
    }
    // --------------------------------------------------------

    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    public void setNomeEspecialidade(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade;
    }

    @Override
    public String toString() {
        return nomeEspecialidade + " (" + cbo + ")";
    }
}