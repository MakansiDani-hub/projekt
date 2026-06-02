/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import oru.inf.InfException;

/**
 *
 * @author WDM
 */
public class ProjektSokHandlaggare extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProjektSokHandlaggare.class.getName());
    private Anvandare anvandare;
    private String valStatus;
    private Date valStartdatum;
    private Date valSlutdatum;
    private String namn;

    /**
     * Creates new form ProjektSokHandlaggare
     */
    public ProjektSokHandlaggare(Anvandare anvandare) {
        initComponents();
        this.anvandare = anvandare;
        this.namn = anvandare.getDbNamn();
        laddaAllaProjektPåAvdelningen();

        lblAnvändaresNamn.setText(namn);
    }

    private void setStartdatum(Date datum) {
        valStartdatum = datum;
        //uppdatera GUI för vald-datum
        System.out.println(dateStartdatum);
        dateStartdatum.setDate(datum);
    }

    private void setSlutdatum(Date datum) {
        valSlutdatum = datum;
        //uppdatera UI för vald-datum
        dateSlutdatum.setDate(datum);
    }

    private void laddaAllaProjektPåAvdelningen() {
        try {
            String sqlfraga = "SELECT p.pid, p.projektnamn, p.beskrivning, p.startdatum, p.slutdatum, "
                    + "p.kostnad, p.status, p.prioritet, "
                    + "(SELECT CONCAT(a.fornamn, ' ', a.efternamn) "
                    + "FROM anstalld a WHERE a.aid = p.projektchef) AS projektchef_namn, "
                    + "(SELECT l.namn FROM land l WHERE l.lid = p.land) AS land_namn "
                    + "FROM projekt p "
                    + "WHERE p.status = 'Pågående' "
                    + "AND p.pid IN ( "
                    + "SELECT ap.pid FROM ans_proj ap "
                    + "WHERE ap.aid IN ( "
                    + "SELECT a.aid FROM anstalld a "
                    + "WHERE a.avdelning = ( "
                    + "SELECT avdelning FROM anstalld "
                    + "WHERE aid = " + anvandare.getAid()
                    + "))) "
                    + "ORDER BY p.pid";

            ArrayList<HashMap<String, String>> projektLista = anvandare.getIdb().fetchRows(sqlfraga);

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("PID");
            model.addColumn("Projektnamn");
            model.addColumn("Beskrivning");
            model.addColumn("Startdatum");
            model.addColumn("Slutdatum");
            model.addColumn("Kostnad");
            model.addColumn("Status");
            model.addColumn("Prioritet");
            model.addColumn("Projektchef");
            model.addColumn("Land");

            for (HashMap<String, String> projekt : projektLista) {
                //System.out.println(projekt);
                model.addRow(new Object[]{
                    projekt.get("pid"),
                    projekt.get("projektnamn"),
                    projekt.get("beskrivning"),
                    projekt.get("startdatum"),
                    projekt.get("slutdatum"),
                    projekt.get("kostnad"),
                    projekt.get("status"),
                    projekt.get("prioritet"),
                    projekt.get("projektchef_namn"),
                    projekt.get("land_namn")
                });
            }

            tblProjektlista.setModel(model);

            tblProjektlista.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

            tblProjektlista.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblProjektlista.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblProjektlista.getColumnModel().getColumn(2).setPreferredWidth(220);
            tblProjektlista.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblProjektlista.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblProjektlista.getColumnModel().getColumn(5).setPreferredWidth(90);
            tblProjektlista.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblProjektlista.getColumnModel().getColumn(7).setPreferredWidth(80);
            tblProjektlista.getColumnModel().getColumn(8).setPreferredWidth(180);
            tblProjektlista.getColumnModel().getColumn(9).setPreferredWidth(120);

        } catch (InfException ex) {
            JOptionPane.showMessageDialog(this, "Kunde inte ladda projektlistan: " + ex.getMessage());
        }
    }

    private void filtreraProjektPaDatum() {
        Date startdatum = dateStartdatum.getDate();
        Date slutdatum = dateSlutdatum.getDate();

        if (startdatum == null || slutdatum == null) {
            JOptionPane.showMessageDialog(this, "Välj både startdatum och slutdatum.");
            return;
        }

        if (startdatum.after(slutdatum)) {
            JOptionPane.showMessageDialog(this, "Startdatum kan inte vara efter slutdatum.");
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String start = sdf.format(startdatum);
            String slut = sdf.format(slutdatum);

            String sqlfraga = "SELECT p.pid, p.projektnamn, p.beskrivning, p.startdatum, p.slutdatum, "
                    + "p.kostnad, p.status, p.prioritet, "
                    + "(SELECT CONCAT(a.fornamn, ' ', a.efternamn) FROM anstalld a WHERE a.aid = p.projektchef) AS projektchef_namn, "
                    + "(SELECT l.namn FROM land l WHERE l.lid = p.land) AS land_namn "
                    + "FROM projekt p "
                    + "WHERE p.status = 'Pågående' "
                    + "AND p.startdatum >= '" + start + "' "
                    + "AND p.slutdatum <= '" + slut + "' "
                    + "AND p.pid IN ( "
                    + "SELECT ap.pid FROM ans_proj ap WHERE ap.aid IN ( "
                    + "SELECT a.aid FROM anstalld a WHERE a.avdelning = ( "
                    + "SELECT avdelning FROM anstalld WHERE aid = " + anvandare.getAid()
                    + "))) "
                    + "ORDER BY p.pid";

            ArrayList<HashMap<String, String>> projektLista = anvandare.getIdb().fetchRows(sqlfraga);

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("PID");
            model.addColumn("Projektnamn");
            model.addColumn("Beskrivning");
            model.addColumn("Startdatum");
            model.addColumn("Slutdatum");
            model.addColumn("Kostnad");
            model.addColumn("Status");
            model.addColumn("Prioritet");
            model.addColumn("Projektchef");
            model.addColumn("Land");

            for (HashMap<String, String> projekt : projektLista) {
                model.addRow(new Object[]{
                    projekt.get("pid"),
                    projekt.get("projektnamn"),
                    projekt.get("beskrivning"),
                    projekt.get("startdatum"),
                    projekt.get("slutdatum"),
                    projekt.get("kostnad"),
                    projekt.get("status"),
                    projekt.get("prioritet"),
                    projekt.get("projektchef_namn"),
                    projekt.get("land_namn")
                });
            }

            tblProjektlista.setModel(model);
            tblProjektlista.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        } catch (InfException ex) {
            JOptionPane.showMessageDialog(this, "Kunde inte filtrera projekt: " + ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateSlutdatum = new com.toedter.calendar.JDateChooser();
        dateStartdatum = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTillbakaTillMeny = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        ScrollPane = new javax.swing.JScrollPane();
        tblProjektlista = new javax.swing.JTable();
        lblAnvändaresNamn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Startdatum");

        jLabel4.setText("Slutdatum");

        lblTillbakaTillMeny.setText("Tillbaka till Meny");
        lblTillbakaTillMeny.addActionListener(this::lblTillbakaTillMenyActionPerformed);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Sök efter projekt");

        jLabel1.setText("HandLäggare");

        jButton1.setText("Sök 🔍");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        tblProjektlista.setModel(new javax.swing.table.DefaultTableModel(
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
        ScrollPane.setViewportView(tblProjektlista);

        lblAnvändaresNamn.setText("användares namn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTillbakaTillMeny)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(dateStartdatum, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateSlutdatum, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblAnvändaresNamn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTillbakaTillMeny)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAnvändaresNamn)
                .addGap(11, 11, 11)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateStartdatum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dateSlutdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(jButton1))
                .addGap(30, 30, 30)
                .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblTillbakaTillMenyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblTillbakaTillMenyActionPerformed
        Navigering.tillbakaTillMeny(anvandare);
        this.dispose();
    }//GEN-LAST:event_lblTillbakaTillMenyActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        filtreraProjektPaDatum();
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
        // java.awt.EventQueue.invokeLater(() -> new ProjektSokHandlaggare().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPane;
    private com.toedter.calendar.JDateChooser dateSlutdatum;
    private com.toedter.calendar.JDateChooser dateStartdatum;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblAnvändaresNamn;
    private javax.swing.JButton lblTillbakaTillMeny;
    private javax.swing.JTable tblProjektlista;
    // End of variables declaration//GEN-END:variables
}
