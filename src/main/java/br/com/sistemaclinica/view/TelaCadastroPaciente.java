/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.sistemaclinica.view;


import br.com.sistemaclinica.dao.PacienteDAO;

import br.com.sistemaclinica.model.Paciente;

import javax.swing.table.DefaultTableModel;
import java.util.List;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Alisson Dias
 */
public class TelaCadastroPaciente extends javax.swing.JFrame {
    
     
    
    private List<Paciente> listaDePacientes;
    
    private int calcularIdade(LocalDate dataNascimento) {
    if (dataNascimento == null) {
        return 0; // Retorna 0 se não houver data
    }
    // Usa a classe Period para calcular a diferença entre a data de nascimento e a data de hoje.
    return Period.between(dataNascimento, LocalDate.now()).getYears();
}
       
    private int idPacienteSelecionado = 0; // <<-- ADICIONE ESTA LINHA
    
   private void tblPacientesValueChanged(javax.swing.event.ListSelectionEvent evt) {                                          
    if (!evt.getValueIsAdjusting() && tblPacientes.getSelectedRow() != -1) {
        
        int linhaSelecionada = tblPacientes.getSelectedRow();
        
        // Pega o objeto PACIENTE inteiro da nossa lista
        Paciente pacienteSelecionado = this.listaDePacientes.get(linhaSelecionada);
        
        // Guarda o ID para as ações de atualizar/deletar
        idPacienteSelecionado = pacienteSelecionado.getId();

        // --- PREENCHENDO TODOS OS CAMPOS ---
        
        // Dados Pessoais
        txtNomePaciente.setText(pacienteSelecionado.getNome());
        txtCpfPaciente.setText(pacienteSelecionado.getCpf());
        txtContato.setText(pacienteSelecionado.getContato());
        txtEmail.setText(pacienteSelecionado.getEmail());
        
        // Para a data de nascimento, precisamos converter o objeto LocalDate para Texto
        LocalDate dataNascimento = pacienteSelecionado.getDataNascimento();
        if (dataNascimento != null) {
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            txtDataNascimentoPaciente.setText(dataNascimento.format(formatador));
        } else {
            txtDataNascimentoPaciente.setText(""); // Limpa o campo se não houver data
        }
        
        // Campo de idade calculado automaticamente
        int idade = calcularIdade(dataNascimento);
        txtIdade.setText(String.valueOf(idade));
        
        // Dados de Endereço (assumindo que você tem os campos de texto para eles)
        txtRua.setText(pacienteSelecionado.getRua());
        txtNumero.setText(pacienteSelecionado.getNumero());
        txtBairro.setText(pacienteSelecionado.getBairro());
        txtComplemento.setText(pacienteSelecionado.getComplemento());
        txtCidade.setText(pacienteSelecionado.getCidade());
        txtEstado.setText(pacienteSelecionado.getEstado());
        
        // Habilita/desabilita os botões e campos
       
        txtCpfPaciente.setEnabled(false); // Mantemos o CPF bloqueado para edição
       
       String sexoDoPaciente = pacienteSelecionado.getSexo();
       cmbSexo.setSelectedItem(sexoDoPaciente);
       
        
    }
}

    
   private void carregarTabela() {
    PacienteDAO dao = new PacienteDAO();
    // Guarda a lista na variável da classe
    this.listaDePacientes = dao.listarTodos(); 

    DefaultTableModel modelo = (DefaultTableModel) tblPacientes.getModel();
    modelo.setRowCount(0);
    // Ajuste as colunas para incluir as que você precisa
    modelo.setColumnIdentifiers(new Object[]{"ID", "Nome", "CPF", "Email", "Contato"}); 

    for (Paciente p : this.listaDePacientes) { // Usa a lista da classe
        modelo.addRow(new Object[]{
            p.getId(), 
            p.getNome(), 
            p.getCpf(), 
            p.getEmail(), 
            p.getContato()
        });
    }
     }
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaCadastroPaciente.class.getName());

    /**
     * Creates new form TelaCadastroFuncionario
     */
    public TelaCadastroPaciente() {
        initComponents();
        
         cmbSexo.addItem("Masculino");
         cmbSexo.addItem("Feminino");
         cmbSexo.addItem("Outro");
        
        
        tblPacientes.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      
        
            
        @Override
        public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
            // Este método é chamado toda vez que a seleção muda.
            // Dentro dele, nós simplesmente chamamos o nosso outro método que faz o trabalho pesado.
            tblPacientesValueChanged(evt);
        }

            
    });
        
    
       carregarTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNomePaciente = new javax.swing.JTextField();
        txtCpfPaciente = new javax.swing.JTextField();
        txtDataNascimentoPaciente = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPacientes = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        txtEstado = new javax.swing.JTextField();
        txtComplemento = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtContato = new javax.swing.JTextField();
        txtRua = new javax.swing.JTextField();
        txtIdade = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        cmbSexo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Numero = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Contato = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Cpf:");

        jLabel2.setText("Nome:");

        jLabel4.setText("DatadeNascimento:");

        txtDataNascimentoPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataNascimentoPacienteActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        tblPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblPacientes);

        btnNovo.setText("Limpar Campos");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnDeletar.setBackground(new java.awt.Color(255, 51, 51));
        btnDeletar.setText("Apagar");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        txtComplemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComplementoActionPerformed(evt);
            }
        });

        txtContato.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        txtContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContatoActionPerformed(evt);
            }
        });

        txtIdade.setEditable(false);

        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        jLabel3.setText("Sexo:");

        jLabel5.setText("Idade:");

        jLabel6.setText("Rua:");

        Numero.setText("Numero:");

        jLabel7.setText("Complemento:");

        jLabel8.setText("Bairro:");

        jLabel9.setText("Cidade:");

        jLabel10.setText("Estado:");

        Contato.setText("Contato:");

        jLabel11.setText("Email:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel12.setText("Endereço:");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCpfPaciente))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Contato, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtContato))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail)))
                        .addGap(45, 45, 45))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDataNascimentoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtEstado))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(6, 6, 6)
                                                    .addComponent(txtBairro))
                                                .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtRua)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnSalvar))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addComponent(btnNovo)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                                .addComponent(btnDeletar)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(btnCancelar)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Contato)
                            .addComponent(txtContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCpfPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtDataNascimentoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addComponent(jLabel12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Numero)
                                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnSalvar)
                                    .addComponent(btnNovo)
                                    .addComponent(btnDeletar)
                                    .addComponent(btnCancelar)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel7)
                                .addGap(9, 9, 9)
                                .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDataNascimentoPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataNascimentoPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataNascimentoPacienteActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
 // Coleta todos os dados da tela
 
 
 
 
    Paciente paciente = new Paciente();
    paciente.setNome(txtNomePaciente.getText());
    paciente.setCpf(txtCpfPaciente.getText());
    paciente.setEmail(txtEmail.getText());
    paciente.setContato(txtContato.getText());
    paciente.setEstado(txtEstado.getText());
    paciente.setCidade(txtCidade.getText());
    paciente.setBairro(txtBairro.getText());
    paciente.setRua(txtRua.getText());
    paciente.setNumero(txtNumero.getText());
    paciente.setComplemento(txtComplemento.getText());
    
     // --- LÓGICA SIMPLIFICADA PARA PEGAR O SEXO DO COMBOBOX ---
    // Pega a String "Masculino", "Feminino" ou "Outro" diretamente
    String sexoSelecionado = (String) cmbSexo.getSelectedItem();
    
    // Define essa String diretamente no objeto paciente que será salvo
    paciente.setSexo(sexoSelecionado); 
    
    // --- FIM DA LÓGICA DO SEXO ---

    try {
        String dataTexto = txtDataNascimentoPaciente.getText();
        java.time.format.DateTimeFormatter formatador = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        paciente.setDataNascimento(java.time.LocalDate.parse(dataTexto, formatador));
    } catch (java.time.format.DateTimeParseException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Formato de data inválido! Use dd/MM/yyyy.", "Erro de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }

 

    // --- AQUI ESTÁ A LÓGICA ---
    try {
        PacienteDAO dao = new PacienteDAO();

        // Se idFuncionarioSelecionado é 0, então é um NOVO cadastro
        if (idPacienteSelecionado == 0) {
            dao.cadastrar(paciente);
            javax.swing.JOptionPane.showMessageDialog(this, "Paciente cadastrado com sucesso!", "Sucesso", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } 
        // Senão, é uma ATUALIZAÇÃO
        else {
            // Define o ID no objeto para que o DAO saiba quem atualizar
            paciente.setId(idPacienteSelecionado);
            dao.atualizar(paciente);
            javax.swing.JOptionPane.showMessageDialog(this, "Paciente atualizado com sucesso!", "Sucesso", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

        // Após salvar (seja criando ou atualizando), limpa o formulário e recarrega a tabela
        btnNovoActionPerformed(null); // Chama a ação do botão Novo para limpar tudo
        carregarTabela();

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao salvar:\n" + e.getMessage(), "Erro no Banco de Dados", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    
    // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
  // Limpa todos os campos de texto
    txtNomePaciente.setText("");
    txtNomePaciente.setText("");
    txtCpfPaciente.setText("");
    txtEmail.setText("");
    txtContato.setText("");
    txtEstado.setText("");
    txtCidade.setText("");
    txtBairro.setText("");
    txtRua.setText("");
    txtNumero.setText("");
    txtComplemento.setText("");
    txtCpfPaciente.setText("");
    txtDataNascimentoPaciente.setText("");

    // Habilita os campos que foram desabilitados
   
    txtCpfPaciente.setEnabled(true);

    // Remove a seleção da tabela
    tblPacientes.clearSelection();

    // Zera nossa variável de controle. Este é o passo mais importante!
    idPacienteSelecionado = 0;        // TODO add your handling code here:
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
   // 1. Verifica se algum funcionário foi selecionado na tabela.
        if (idPacienteSelecionado == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecione um Paciente na tabela para excluir.", "Nenhum funcionário selecionado", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Pede confirmação ao usuário. Este é um passo MUITO importante para evitar exclusões acidentais.
        int resposta = javax.swing.JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este Paciente?\nEsta ação não pode ser desfeita.", "Confirmação de Exclusão", javax.swing.JOptionPane.YES_NO_OPTION);

        // 3. Se o usuário clicou em "Sim"...
        if (resposta == javax.swing.JOptionPane.YES_OPTION) {
            try {
                // Cria o DAO e chama o método deletar, passando o ID que já guardamos.
                PacienteDAO dao = new PacienteDAO();
                dao.deletar(idPacienteSelecionado);

                // Mostra a mensagem de sucesso.
                javax.swing.JOptionPane.showMessageDialog(this, "Paciente excluído com sucesso!", "Sucesso", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                // Limpa o formulário e recarrega a tabela para refletir a exclusão.
                btnNovoActionPerformed(null);
                carregarTabela();

            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Erro ao excluir Paciente:\n" + e.getMessage(), "Erro no Banco de Dados", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void txtComplementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComplementoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComplementoActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void txtContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContatoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
  // Este comando fecha a janela ATUAL e libera seus recursos da memória,
    // revelando a tela que estava por baixo (a TelaPrincipal).
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaCadastroPaciente().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Contato;
    private javax.swing.JLabel Numero;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cmbSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPacientes;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtContato;
    private javax.swing.JTextField txtCpfPaciente;
    private javax.swing.JTextField txtDataNascimentoPaciente;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtIdade;
    private javax.swing.JTextField txtNomePaciente;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRua;
    // End of variables declaration//GEN-END:variables
}
