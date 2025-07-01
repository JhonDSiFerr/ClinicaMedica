package Utils;

import javax.swing.JComboBox;

/**
 * Classe utilitária para gerenciar e popular JComboBoxes.
 */
public class ComboBoxManager {

    /**
     * Preenche um JComboBox com as opções de sexo.
     *
     * @param comboBox O JComboBox a ser populado.
     */
    public static void preencherComboBoxSexo(JComboBox<String> comboBox) {
        comboBox.removeAllItems(); // Limpa itens existentes
        comboBox.addItem("Selecione..."); // Adiciona um item padrão
        comboBox.addItem("Masculino");
        comboBox.addItem("Feminino");
        comboBox.addItem("Outro");
    }

    /**
     * Preenche um JComboBox com as opções de estado civil.
     *
     * @param comboBox O JComboBox a ser populado.
     */
    public static void preencherComboBoxEstadoCivil(JComboBox<String> comboBox) {
        comboBox.removeAllItems(); // Limpa itens existentes
        comboBox.addItem("Selecione..."); // Adiciona um item padrão
        comboBox.addItem("Solteiro");
        comboBox.addItem("Casado");
        comboBox.addItem("Divorciado");
    }
    
    /**
     * Preenche um JComboBox com uma lista de convênios de saúde.
     *
     * @param comboBox O JComboBox a ser populado.
     */
    public static void preencherComboBoxConvenio(JComboBox<String> comboBox) {
        comboBox.removeAllItems(); // Limpa itens existentes
        comboBox.addItem("Selecione..."); // Adiciona um item padrão
        comboBox.addItem("Amil");
        comboBox.addItem("Allianz Saúde");
        comboBox.addItem("Bradesco Saúde");
        comboBox.addItem("Golden Cross");
        comboBox.addItem("Hapvida");
        comboBox.addItem("NotreDame Intermédica");
        comboBox.addItem("Porto Seguro Saúde");
        comboBox.addItem("SulAmérica Saúde");
        comboBox.addItem("Unimed");
        comboBox.addItem("Particular"); // Adicionando a opção para consulta particular
    }
}