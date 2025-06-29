// src/model/Especialidade.java
package Entidades;

public class Especialidade {
    private String dataAdd;      // Se você está usando esta coluna
    private String cbo;          // ESTE ATRIBUTO É CRÍTICO
    private String convenio;     // Se você está usando esta coluna
    private String nomeEspecialidade;

    // Construtor completo (se você for usar todos os campos na Especialidade)
    public Especialidade(String dataAdd, String cbo, String convenio, String nomeEspecialidade) {
        this.dataAdd = dataAdd;
        this.cbo = cbo;
        this.convenio = convenio;
        this.nomeEspecialidade = nomeEspecialidade;
    }

    // Construtor mais comum para carregar JComboBox/JList (apenas CBO e Nome)
    public Especialidade(String cbo, String nomeEspecialidade) {
        // Chama o construtor completo com nulo para os outros campos, ou remove eles se não precisar
        this(null, cbo, null, nomeEspecialidade);
    }

    // --- GETTERS E SETTERS ---
    // VERIFIQUE SE O NOME DO MÉTODO É EXATAMENTE 'getCbo()' (minúsculo 'c')
    public String getCbo() {
        return cbo;
    }

    public void setCbo(String cbo) {
        this.cbo = cbo;
    }

    // ... Outros getters e setters (getDataAdd, getConvenio, getNomeEspecialidade) ...
    public String getDataAdd() { return dataAdd; }
    public void setDataAdd(String dataAdd) { this.dataAdd = dataAdd; }

    public String getConvenio() { return convenio; }
    public void setConvenio(String convenio) { this.convenio = convenio; }

    public String getNomeEspecialidade() { return nomeEspecialidade; }
    public void setNomeEspecialidade(String nomeEspecialidade) { this.nomeEspecialidade = nomeEspecialidade; }


    @Override
    public String toString() {
        // Isso é importante para o JComboBox exibir o texto correto
        return nomeEspecialidade + " (" + cbo + ")";
    }
}