package Entidades;


import java.time.LocalDate;

/**
 * Classe que representa a entidade Especialidade.
 */
public class Especialidade {

    private String nome;
    private boolean atendeConvenio;
    private LocalDate dataAdicao;
    private String Cbo;
    // Getters e Setters
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtendeConvenio() {
        return atendeConvenio;
    }

    public void setAtendeConvenio(boolean atendeConvenio) {
        this.atendeConvenio = atendeConvenio;
    }

    public LocalDate getDataAdicao() {
        return dataAdicao;
    }

    public void setDataAdicao(LocalDate dataAdicao) {
        this.dataAdicao = dataAdicao;
    }
     public String getCbo() {
        return Cbo;
    }

    public void setCbo(String Cbo) {
        this.Cbo = Cbo;
    }
}