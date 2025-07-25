/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AtendenteTelas;

/**
 *
 * @author Alisson Dias
 */
public class PacientesCadastradosAtendente extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PacientesCadastradosAtendente.class.getName());

    /**
     * Creates new form Menumain
     */
    public PacientesCadastradosAtendente() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        PacientesMenu = new javax.swing.JMenu();
        PacientesCadastrados = new javax.swing.JMenu();
        CadastrarPaciente = new javax.swing.JMenu();
        AgendarConsultaMenus = new javax.swing.JMenu();
        AgendarRetorno = new javax.swing.JMenu();
        MedicosMenu = new javax.swing.JMenu();
        CadastrarMedico = new javax.swing.JMenu();
        MedicoCadastrados = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        Agenta = new javax.swing.JMenu();
        Sair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "Cpf", "Telefone", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButton1.setText("Editar Pacientes Cadastrados");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        PacientesMenu.setText("Pacientes");

        PacientesCadastrados.setText("Pacientes Cadastrados");
        PacientesMenu.add(PacientesCadastrados);

        CadastrarPaciente.setText("Cadastrar Paciente");
        PacientesMenu.add(CadastrarPaciente);

        jMenuBar1.add(PacientesMenu);

        AgendarConsultaMenus.setText("Agendar Consultas");
        jMenuBar1.add(AgendarConsultaMenus);

        AgendarRetorno.setText("Agendar retorno");
        jMenuBar1.add(AgendarRetorno);

        MedicosMenu.setText("Médicos");

        CadastrarMedico.setText("Cadastrar Médico");
        MedicosMenu.add(CadastrarMedico);

        MedicoCadastrados.setText("Médicos Cadastrados");
        MedicosMenu.add(MedicoCadastrados);

        jMenuBar1.add(MedicosMenu);

        jMenu10.setText("Especialidades");

        jMenu11.setText("Cadastrar Especialidades");
        jMenu10.add(jMenu11);

        jMenu12.setText("Listar Especialidades");
        jMenu10.add(jMenu12);

        jMenuBar1.add(jMenu10);

        Agenta.setText("Agenda");
        jMenuBar1.add(Agenta);

        Sair.setText("Sair");
        jMenuBar1.add(Sair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new PacientesCadastradosAtendente().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AgendarConsultaMenus;
    private javax.swing.JMenu AgendarRetorno;
    private javax.swing.JMenu Agenta;
    private javax.swing.JMenu CadastrarMedico;
    private javax.swing.JMenu CadastrarPaciente;
    private javax.swing.JMenu MedicoCadastrados;
    private javax.swing.JMenu MedicosMenu;
    private javax.swing.JMenu PacientesCadastrados;
    private javax.swing.JMenu PacientesMenu;
    private javax.swing.JMenu Sair;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
