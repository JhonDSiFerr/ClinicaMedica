package Entidades;

public class Especialidade {
  private String Dataadd;
  private String Nome;
  private char covennio;
  private Medico medico;
  private String CBO;

    public String getDataadd() {
        return Dataadd;
    }

    public void setDataadd(String dataadd) {
        this.Dataadd = dataadd;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public char getCovennio() {
        return covennio;
    }

    public void setCovennio(char covennio) {
        this.covennio = covennio;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getCBO() {
        return CBO;
    }

    public void setCBO(String CBO) {
        this.CBO = CBO;
    }
}
