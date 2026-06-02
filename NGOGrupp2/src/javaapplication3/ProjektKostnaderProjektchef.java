package javaapplication3;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 * Kostnadsstatistik för projekt.
 *
 * @author Big Dick J
 */
public class ProjektKostnaderProjektchef extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProjektKostnaderProjektchef.class.getName());
    
    private Anvandare anvandare;

    /**
     * Konstruktor som tar emot ett Anvandare-objekt.
     */
    public ProjektKostnaderProjektchef(Anvandare anvandare) {
        initComponents();
        this.anvandare = anvandare;

        // Fyll tabellen med all data direkt när fönstret laddas
        uppdateraStatistik("SELECT projektnamn, status, startdatum, slutdatum, kostnad FROM projekt");

        // Lägger till en lyssnare på ComboBoxen
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
    }

    /**
     * Hämtar data baserat på SQL-frågan, fyller tabellen och räknar ut statistik.
     */
    private void uppdateraStatistik(String sqlFraga) {
        try {
            // SÄKERHETSSPÄRR 1: Kolla så att hela användarobjektet finns
            if (anvandare == null) {
                return;
            }
            
            // Genväg till databasen via ditt objekt
            InfDB idb = anvandare.getIdb();
            
            // SÄKERHETSSPÄRR 2: Avbryt tyst om databasen saknas vid en fristående testkörning
            if (idb == null) {
                return;
            }
            
            ArrayList<HashMap<String, String>> projektLista = idb.fetchRows(sqlFraga);
            DefaultTableModel model = (DefaultTableModel) tblKostnadsStatistik.getModel();
            model.setRowCount(0); // Tömmer gamla rader

            double totalKostnad = 0;
            int antalProjekt = 0;

            if (projektLista != null) {
                for (HashMap<String, String> rad : projektLista) {
                    String pNamn = rad.get("projektnamn");
                    String status = rad.get("status");
                    String startDatum = rad.get("startdatum");
                    String slutDatum = rad.get("slutdatum");
                    String kostnad = rad.get("kostnad");

                    model.addRow(new Object[]{pNamn, status, startDatum, slutDatum, kostnad});

                    if (kostnad != null && !kostnad.isEmpty()) {
                        totalKostnad += Double.parseDouble(kostnad);
                        antalProjekt++;
                    }
                }
            }

            // Uppdatera textetiketterna med statistik
            lblTotalKostnad.setText(Math.round(totalKostnad) + " kr");
            if (antalProjekt > 0) {
                lblGenomsnitt.setText(Math.round(totalKostnad / antalProjekt) + " kr");
            } else {
                lblGenomsnitt.setText("0 kr");
            }

        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Databasfel: " + e.getMessage());
        }
    }

    /**
     * Hanterar filtrering baserat på vald status i rullgardinsmenyn.
     */
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        String valtVal = jComboBox1.getSelectedItem().toString();
        String fraga = "SELECT projektnamn, status, startdatum, slutdatum, kostnad FROM projekt";

        if (valtVal.equals("Endast planerade projekt")) {
            fraga += " WHERE status = 'Planerat'";
        } else if (valtVal.equals("Endast pågående projekt")) {
            fraga += " WHERE status = 'Pågående'";
        } else if (valtVal.equals("Endast pausade projekt")) {
            fraga += " WHERE status = 'Pausat'";
        } else if (valtVal.equals("Endast avslutade projekt")) {
            fraga += " WHERE status = 'Avslutat'";
        }

        uppdateraStatistik(fraga);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKostnadsStatistik = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        lblTotalKostnad = new javax.swing.JLabel();
        lblTillbakaTillMeny = new javax.swing.JButton();
        txtStartDatum = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSlutDatum = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnSökDatumActionPerformed = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lblGenomsnitt = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        jLabel4.setText("Datum:");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Kostnadsstatistik");

        jLabel2.setText("Visa projekt:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Alla projekt", "Endast planerade projekt", "Endast pågående projekt", "Endast pausade projekt", "Endast avslutade projekt", " " }));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        tblKostnadsStatistik.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Projektnamn", "Status", "Startdatum", "Slutdatum", "Kostnad"
            }
        ));
        jScrollPane1.setViewportView(tblKostnadsStatistik);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Totalsumma för projekt:");

        lblTotalKostnad.setText("0 kr");

        lblTillbakaTillMeny.setText("Tillbaka till Meny");
        lblTillbakaTillMeny.addActionListener(this::lblTillbakaTillMenyActionPerformed);

        txtStartDatum.setToolTipText("ÅÅÅÅ-MM-DD");
        txtStartDatum.addActionListener(this::txtStartDatumActionPerformed);

        jLabel5.setText("Till");

        txtSlutDatum.addActionListener(this::txtSlutDatumActionPerformed);

        jLabel6.setText("Startdatum:");

        btnSökDatumActionPerformed.setText("Sök");
        btnSökDatumActionPerformed.addActionListener(this::btnSökDatumActionPerformedActionPerformed);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Genomsnittlig kostnad:");

        lblGenomsnitt.setText("0 kr");

        jLabel8.setText("Slutdatum:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotalKostnad)
                    .addComponent(lblGenomsnitt))
                .addGap(67, 67, 67))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblTillbakaTillMeny)
                                .addGap(141, 141, 141)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel5))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtStartDatum, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                .addComponent(txtSlutDatum))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(btnSökDatumActionPerformed, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTillbakaTillMeny)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtSlutDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSökDatumActionPerformed)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtStartDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblTotalKostnad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGenomsnitt)
                    .addComponent(jLabel7))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblTillbakaTillMenyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblTillbakaTillMenyActionPerformed
        //new MenyHandlaggareProjektchef().setVisible(true);
       // new MenyHandlaggareProjektchef(anvandare).setVisible(true);
       new MenyHandlaggareProjektchef(anvandare).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblTillbakaTillMenyActionPerformed

    private void txtStartDatumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStartDatumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStartDatumActionPerformed

    private void txtSlutDatumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSlutDatumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSlutDatumActionPerformed

    private void btnSökDatumActionPerformedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSökDatumActionPerformedActionPerformed
        // TODO add your handling code here:                                                 
        String start = txtStartDatum.getText().trim();
        String slut = txtSlutDatum.getText().trim();

        if (start.isEmpty() || slut.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Du måste fylla i både start- och slutdatum (ÅÅÅÅ-MM-DD)!");
            return;
        }

        // Hämtar alla 5 värden för det valda datumsintervallet
        String fraga = "SELECT projektnamn, status, startdatum, slutdatum, kostnad FROM projekt "
                + "WHERE startdatum >= '" + start + "' AND slutdatum <= '" + slut + "'";
        uppdateraStatistik(fraga);
    }//GEN-LAST:event_btnSökDatumActionPerformedActionPerformed
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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

        /* Create and display the form */ 
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    oru.inf.InfDB testIdb = new oru.inf.InfDB("sdgsweden", "3306", "root", "password");
                    Anvandare testAnv = new Anvandare(testIdb, "Test", "Testsson", "2026-01-01", 1, "Gata 1", "123", "pw", "Projektchef");
                    new ProjektKostnaderProjektchef(testAnv).setVisible(true);
                } catch (Exception e) {
                    // Om databasen inte hittas vid fristående testkörning, startar vi ändå en tom fejk-användare
                    Anvandare fejkAnv = new Anvandare(null, "", "", "", 0, "", "", "", "");
                    new ProjektKostnaderProjektchef(fejkAnv).setVisible(true);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSökDatumActionPerformed;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblGenomsnitt;
    private javax.swing.JButton lblTillbakaTillMeny;
    private javax.swing.JLabel lblTotalKostnad;
    private javax.swing.JTable tblKostnadsStatistik;
    private javax.swing.JTextField txtSlutDatum;
    private javax.swing.JTextField txtStartDatum;
    // End of variables declaration//GEN-END:variables
}
