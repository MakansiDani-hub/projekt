/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication3;

/**
 *
 * @author Kristoffer Kolkowski
 */
import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class HanteraAvdelning extends javax.swing.JFrame {

    private InfDB idb;
    private DefaultTableModel bordsModell;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HanteraAvdelning.class.getName());

    /**
     * Creates new form HanteraAvdelning
     */
    public HanteraAvdelning(InfDB idb) {
        this.idb = idb;
        initComponents();

        // Initierar tabellen
        bordsModell = (DefaultTableModel) JTableAvdelningar.getModel();
        bordsModell.setColumnIdentifiers(new Object[]{"avdid", "namn", "beskrivning", "adress", "epost", "telefon", "stad", "chef"});

        fyllTabell();

        // Lägger till MouseListener för att kunna klicka i tabellen
        JTableAvdelningar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int rad = JTableAvdelningar.getSelectedRow();
                if (rad >= 0) {
                    JTxtFieldAvdID.setText(bordsModell.getValueAt(rad, 0).toString());
                    JTxtFieldNamn.setText(bordsModell.getValueAt(rad, 1).toString());
                    JTxtFieldBeskrivning.setText(bordsModell.getValueAt(rad, 2).toString());
                    JTxtFieldAdress.setText(bordsModell.getValueAt(rad, 3).toString());
                    JTxtFieldEpost.setText(bordsModell.getValueAt(rad, 4).toString());
                    JTxtFieldTelefon.setText(bordsModell.getValueAt(rad, 5).toString());
                    JTxtFieldStad.setText(bordsModell.getValueAt(rad, 6).toString());
                    JTxtFieldChef.setText(bordsModell.getValueAt(rad, 7).toString());
                }
            }
        });
    }

    private void fyllTabell() {
        bordsModell.setRowCount(0);
        try {
            String fraga = "SELECT * FROM avdelning ORDER BY avdid";
            ArrayList<HashMap<String, String>> rader = idb.fetchRows(fraga);

            if (rader != null) {
                for (HashMap<String, String> rad : rader) {
                    bordsModell.addRow(new Object[]{
                        rad.get("avdid"), rad.get("namn"), rad.get("beskrivning"),
                        rad.get("adress"), rad.get("epost"), rad.get("telefon"),
                        rad.get("stad"), rad.get("chef")
                    });
                }
            }
        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Fel vid hämtning: " + e.getMessage());
        }
    }

    private void laggTillAvdelning() {
        try {
            String id = JTxtFieldAvdID.getText();
            String namn = JTxtFieldNamn.getText();
            String besk = JTxtFieldBeskrivning.getText();
            String adress = JTxtFieldAdress.getText();
            String epost = JTxtFieldEpost.getText();
            String tel = JTxtFieldTelefon.getText();
            String stad = JTxtFieldStad.getText();
            String chef = JTxtFieldChef.getText();

            if (id.isEmpty() || namn.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vänligen fyll i avdid och namn!");
                return;
            }

            String fraga = "INSERT INTO avdelning VALUES (" + id + ", '" + namn + "', '" + besk + "', '"
                    + adress + "', '" + epost + "', '" + tel + "', " + stad + ", " + chef + ")";

            idb.insert(fraga);
            fyllTabell();
            JOptionPane.showMessageDialog(this, "Avdelning tillagd!");
        } catch (InfException e) {
            JOptionPane.showMessageDialog(this, "Kunde inte lägga till avdelning!: " + e.getMessage());
        }
    }

    private void andraAvdelning() {
        try {
            String id = JTxtFieldAvdID.getText();
            //kontroll så användaren har valt en avdelning att ändra
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Välj en avdelning i tabellen att ändra!");
                return;
            }

            // SQL för att uppdatera baserat på avdid
            String fraga = "UPDATE avdelning SET namn='" + JTxtFieldNamn.getText()
                    + "', beskrivning='" + JTxtFieldBeskrivning.getText()
                    + "', adress='" + JTxtFieldAdress.getText()
                    + "', epost='" + JTxtFieldEpost.getText()
                    + "', telefon='" + JTxtFieldTelefon.getText()
                    + "', stad=" + JTxtFieldStad.getText()
                    + ", chef=" + JTxtFieldChef.getText()
                    + " WHERE avdid=" + id;

            idb.update(fraga);
            fyllTabell();
            JOptionPane.showMessageDialog(this, "Ändringarna har sparats!");
        } catch (InfException e) {
            JOptionPane.showMessageDialog(this, "Kunde inte ändra: " + e.getMessage());
        }
    }

    private void taBortAvdelning() {
        try {
            String id = JTxtFieldAvdID.getText();
            //kontroll för att användaren har valt en avdelning att ta bort.
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Välj en avdelning att ta bort!");
                return;
            }

            //Extra säkerhet så man inte råkar ta bort en avdelning av misstag.
            int svar = JOptionPane.showConfirmDialog(this, "Vill du verkligen ta bort avdelning " + id + "?", "Bekräfta", JOptionPane.YES_NO_OPTION);
            if (svar == JOptionPane.YES_OPTION) {
                String fraga = "DELETE FROM avdelning WHERE avdid=" + id;
                idb.delete(fraga);
                fyllTabell();
                JOptionPane.showMessageDialog(this, "Avdelningen borttagen!");
            }
        } catch (InfException e) {
            JOptionPane.showMessageDialog(this, "Kunde inte ta bort: " + e.getMessage());
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

        JPanelAvdelningar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableAvdelningar = new javax.swing.JTable();
        JLblAvdID = new javax.swing.JLabel();
        JLblNamn = new javax.swing.JLabel();
        JLblBeskrivning = new javax.swing.JLabel();
        JLblAdress = new javax.swing.JLabel();
        JLblEpost = new javax.swing.JLabel();
        JTxtFieldAvdID = new javax.swing.JTextField();
        JLblTelefon = new javax.swing.JLabel();
        JLblStad = new javax.swing.JLabel();
        JTxtFieldNamn = new javax.swing.JTextField();
        JTxtFieldBeskrivning = new javax.swing.JTextField();
        JTxtFieldAdress = new javax.swing.JTextField();
        JTxtFieldEpost = new javax.swing.JTextField();
        JTxtFieldTelefon = new javax.swing.JTextField();
        JTxtFieldStad = new javax.swing.JTextField();
        JBtnLaggTillAvdelning = new javax.swing.JButton();
        JbtnAndraAvdelning = new javax.swing.JButton();
        JBtnTaBortAvdelning = new javax.swing.JButton();
        JLblChef = new javax.swing.JLabel();
        JTxtFieldChef = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JPanelAvdelningar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hantera avdelningar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        JTableAvdelningar.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(JTableAvdelningar);

        JLblAvdID.setText("AvdelningID");

        JLblNamn.setText("Namn");

        JLblBeskrivning.setText("Beskrivning");

        JLblAdress.setText("Adress");

        JLblEpost.setText("Epost");

        JLblTelefon.setText("Telefon");

        JLblStad.setText("Stad");

        JBtnLaggTillAvdelning.setText("Lägg till");
        JBtnLaggTillAvdelning.addActionListener(this::JBtnLaggTillAvdelningActionPerformed);

        JbtnAndraAvdelning.setText("Ändra");
        JbtnAndraAvdelning.addActionListener(this::JbtnAndraAvdelningActionPerformed);

        JBtnTaBortAvdelning.setText("Ta bort");
        JBtnTaBortAvdelning.addActionListener(this::JBtnTaBortAvdelningActionPerformed);

        JLblChef.setText("Chef");

        javax.swing.GroupLayout JPanelAvdelningarLayout = new javax.swing.GroupLayout(JPanelAvdelningar);
        JPanelAvdelningar.setLayout(JPanelAvdelningarLayout);
        JPanelAvdelningarLayout.setHorizontalGroup(
            JPanelAvdelningarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelAvdelningarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPanelAvdelningarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(JLblAdress, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblBeskrivning, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblNamn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblAvdID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblEpost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblTelefon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblStad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBtnLaggTillAvdelning, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(JBtnTaBortAvdelning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblChef, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPanelAvdelningarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JTxtFieldAvdID)
                    .addComponent(JTxtFieldNamn)
                    .addComponent(JTxtFieldBeskrivning)
                    .addComponent(JTxtFieldAdress)
                    .addComponent(JTxtFieldEpost)
                    .addComponent(JTxtFieldTelefon)
                    .addComponent(JTxtFieldStad)
                    .addComponent(JbtnAndraAvdelning, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(JTxtFieldChef))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        JPanelAvdelningarLayout.setVerticalGroup(
            JPanelAvdelningarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelAvdelningarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanelAvdelningarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JPanelAvdelningarLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(JPanelAvdelningarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblAvdID)
                            .addComponent(JTxtFieldAvdID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelAvdelningarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblNamn)
                            .addComponent(JTxtFieldNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelAvdelningarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblBeskrivning)
                            .addComponent(JTxtFieldBeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelAvdelningarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblAdress)
                            .addComponent(JTxtFieldAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelAvdelningarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblEpost)
                            .addComponent(JTxtFieldEpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelAvdelningarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblTelefon)
                            .addComponent(JTxtFieldTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelAvdelningarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblStad)
                            .addComponent(JTxtFieldStad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelAvdelningarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblChef)
                            .addComponent(JTxtFieldChef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(JPanelAvdelningarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JBtnLaggTillAvdelning, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JbtnAndraAvdelning, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(JBtnTaBortAvdelning, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPanelAvdelningar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPanelAvdelningar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBtnLaggTillAvdelningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnLaggTillAvdelningActionPerformed
        laggTillAvdelning();
    }//GEN-LAST:event_JBtnLaggTillAvdelningActionPerformed

    private void JbtnAndraAvdelningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnAndraAvdelningActionPerformed
        andraAvdelning();
    }//GEN-LAST:event_JbtnAndraAvdelningActionPerformed

    private void JBtnTaBortAvdelningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnTaBortAvdelningActionPerformed
        taBortAvdelning();
    }//GEN-LAST:event_JBtnTaBortAvdelningActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new HanteraAvdelning().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBtnLaggTillAvdelning;
    private javax.swing.JButton JBtnTaBortAvdelning;
    private javax.swing.JLabel JLblAdress;
    private javax.swing.JLabel JLblAvdID;
    private javax.swing.JLabel JLblBeskrivning;
    private javax.swing.JLabel JLblChef;
    private javax.swing.JLabel JLblEpost;
    private javax.swing.JLabel JLblNamn;
    private javax.swing.JLabel JLblStad;
    private javax.swing.JLabel JLblTelefon;
    private javax.swing.JPanel JPanelAvdelningar;
    private javax.swing.JTable JTableAvdelningar;
    private javax.swing.JTextField JTxtFieldAdress;
    private javax.swing.JTextField JTxtFieldAvdID;
    private javax.swing.JTextField JTxtFieldBeskrivning;
    private javax.swing.JTextField JTxtFieldChef;
    private javax.swing.JTextField JTxtFieldEpost;
    private javax.swing.JTextField JTxtFieldNamn;
    private javax.swing.JTextField JTxtFieldStad;
    private javax.swing.JTextField JTxtFieldTelefon;
    private javax.swing.JButton JbtnAndraAvdelning;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
