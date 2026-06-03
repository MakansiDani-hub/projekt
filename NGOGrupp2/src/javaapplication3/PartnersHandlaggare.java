package javaapplication3;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 * Detaljvy för partners - Handläggarversion.
 * @author alexander.willen
 */
public class PartnersHandlaggare extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PartnersHandlaggare.class.getName());
    
    // Vi sparar enbart ditt Anvandare-objekt i klassen nu!
    private Anvandare anvandare; 

    public PartnersHandlaggare() {
        initComponents();
    }

    /**
     * Riktig konstruktor som tar emot ett Anvandare-objekt.
     */
    public PartnersHandlaggare(Anvandare anvandare) {
        initComponents();
        this.anvandare = anvandare;
        
        // Fyll rullgardinsmenyn med partners direkt när fönstret laddas
        fyllPartnerComboBox();
    }
    
    /**
     * Hämtar alla partnernamn från databasen och lägger till i ComboBoxen.
     */
    private void fyllPartnerComboBox() {
        try {
            // SÄKERHETSSPÄRR 1: Kolla så att hela användarobjektet finns
            if (anvandare == null) return;
            
            // Kort lokal variabel för just denna metod!
            InfDB idb = anvandare.getIdb();
            
            // SÄKERHETSSPÄRR 2: Avbryt tyst om databasen saknas vid fristående testkörning
            if (idb == null) return;
            
            String fraga = "SELECT namn FROM partner";
            ArrayList<String> partners = idb.fetchColumn(fraga);
            
            if (partners != null) {
                for (String namn : partners) {
                    jComboBox1.addItem(namn);
                }
            }
        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Fel vid laddning av partners: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Tillbaka till ");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("[partnernamn]");

        jLabel2.setText("partnerID: [pid]");

        jLabel3.setText("Kontaktperson");

        jButton2.setText("🔍 Alla projekt partnern deltar i");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("   🔍 Mina projekt partnern deltar i");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jLabel4.setText("Kontaktepost");

        jLabel5.setText("Telefon");

        jLabel6.setText("Adress");

        jLabel7.setText("Branch");

        jLabel8.setText("Land");

        jLabel9.setText("Stad");

        jTextField1.setEditable(false);

        jTextField2.setEditable(false);

        jTextField3.setEditable(false);
        jTextField3.addActionListener(this::jTextField3ActionPerformed);

        jTextField4.setEditable(false);
        jTextField4.addActionListener(this::jTextField4ActionPerformed);

        jTextField5.setEditable(false);
        jTextField5.addActionListener(this::jTextField5ActionPerformed);

        jTextField6.setEditable(false);
        jTextField6.addActionListener(this::jTextField6ActionPerformed);

        jTextField7.setEditable(false);
        jTextField7.addActionListener(this::jTextField7ActionPerformed);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj partner" }));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        jLabel10.setText("Sök partner:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButton2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            if (anvandare == null) return;
            InfDB idb = anvandare.getIdb();
            if (idb == null) return;

            String pidText = jLabel2.getText().replace("partnerID: ", "").trim();
            if (pidText.equals("[pid]") || pidText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Välj en partner i listan först!");
                return;
            }
            
            String fraga = "SELECT projekt.projektnamn FROM projekt " +
                           "JOIN projekt_partner ON projekt.pid = projekt_partner.pid " +
                           "WHERE projekt_partner.partner_pid = " + pidText;
            
            ArrayList<String> projekt = idb.fetchColumn(fraga);
            if (projekt != null && !projekt.isEmpty()) {
                String resultat = "Partnern deltar i följande projekt:\n" + String.join("\n", projekt);
                JOptionPane.showMessageDialog(null, resultat);
            } else {
                JOptionPane.showMessageDialog(null, "Denna partner deltar inte i några projekt.");
            }
        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Fel vid hämtning av projekt: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            if (anvandare == null) return;
            InfDB idb = anvandare.getIdb();
            if (idb == null) return;
            int aid = anvandare.getAid(); 

            String pidText = jLabel2.getText().replace("partnerID: ", "").trim();
            if (pidText.equals("[pid]") || pidText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Välj en partner i listan först!");
                return;
            }
            
            String fraga = "SELECT projekt.projektnamn FROM projekt " +
                           "JOIN projekt_partner ON projekt.pid = projekt_partner.pid " +
                           "WHERE projekt_partner.partner_pid = " + pidText + " AND projekt.projektchef = " + aid;
            
            ArrayList<String> projekt = idb.fetchColumn(fraga);
            if (projekt != null && !projekt.isEmpty()) {
                String resultat = "Partnern deltar i dina projekt:\n" + String.join("\n", projekt);
                JOptionPane.showMessageDialog(null, resultat);
            } else {
                JOptionPane.showMessageDialog(null, "Denna partner deltar inte i några av dina projekt.");
            }
        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Fel vid hämtning av dina projekt: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       // Öppnar menynigen och skickar med användaren automatiskt
       new MenyHandlaggareProjektchef(anvandare).setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String valtNamn = (String) jComboBox1.getSelectedItem();
        if (valtNamn != null && !valtNamn.equals("Välj partner") && anvandare != null) {
            try {
                InfDB idb = anvandare.getIdb();
                if (idb == null) return;
                
                String fraga = "SELECT pid, kontaktperson, kontaktepost, telefon, adress, branch, stad FROM partner WHERE namn = '" + valtNamn + "'";
                HashMap<String, String> partnerData = idb.fetchRow(fraga);
                
                if (partnerData != null) {
                    jLabel1.setText(valtNamn);
                    jLabel2.setText("partnerID: " + partnerData.get("pid"));
                    jTextField1.setText(partnerData.get("kontaktperson"));
                    jTextField2.setText(partnerData.get("kontaktepost"));
                    jTextField3.setText(partnerData.get("telefon")); 
                    jTextField5.setText(partnerData.get("adress"));
                    jTextField4.setText(partnerData.get("branch"));
                    jTextField7.setText(partnerData.get("stad"));
                    
                    String stadId = partnerData.get("stad");
                    if (stadId != null) {
                        String landsFraga = "SELECT land.namn FROM land JOIN stad ON land.lid = stad.land WHERE stad.sid = " + stadId;
                        String landNamn = idb.fetchSingle(landsFraga);
                        jTextField6.setText(landNamn != null ? landNamn : "Okänt");
                    }
                }
            } catch (InfException e) {
                JOptionPane.showMessageDialog(null, "Fel vid hämtning av partnerdata: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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

        java.awt.EventQueue.invokeLater(() -> {
            try {
                oru.inf.InfDB testIdb = new oru.inf.InfDB("sdgsweden", "3306", "root", "masterkey");
                Anvandare testAnv = new Anvandare(testIdb, "Test", "Testsson", "2026-01-01", 1, "Gata 1", "123", "masterkey", "Handläggare");
                new PartnersHandlaggare(testAnv).setVisible(true);
            } catch (Exception e) {
                Anvandare fejkAnv = new Anvandare(null, "", "", "", 0, "", "", "", "");
                new PartnersHandlaggare(fejkAnv).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}