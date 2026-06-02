package javaapplication3;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 * Hantering av partners (Lägga till, ändra, ta bort).
 *
 * @author Krist
 */
public class VisaPartnerAdmin extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VisaPartnerAdmin.class.getName());

    private Anvandare anvandare;

    /**
     * Tom konstruktor.
     */
    public VisaPartnerAdmin() {
        initComponents();
    }

    /**
     * Riktig konstruktor som tar emot ett Anvandare-objekt.
     */
    public VisaPartnerAdmin(Anvandare anvandare) {
        initComponents();
        this.anvandare = anvandare;

        // Fyll tabellen med partners när fönstret öppnas
        laddaAllaPartners();

        // Gör så att textfälten fylls i automatiskt när man klickar på en rad i översta tabellen
        JTablePartners.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && JTablePartners.getSelectedRow() != -1) {
                fyllTextfaltFranTabell();
                laddaProjektForValdPartner();
            }
        });
    }

    /**
     * Hämtar alla partners och fyller i översta tabellen (JTablePartners).
     */
    private void laddaAllaPartners() {
        try {
            // SÄKERHETSSPÄRR 1: Kolla så att hela användarobjektet inte är tomt
            if (anvandare == null) {
                return;
            }

            InfDB idb = anvandare.getIdb();

            // SÄKERHETSSPÄRR 2: Om databasen saknas (t.ex. vid fristående Run File),
            // avbryter metoden här innan den hinner krascha
            if (idb == null) {
                return;
            }

            String fraga = "SELECT pid, namn, kontaktperson, kontaktepost, telefon, adress, branch, stad FROM partner";
            ArrayList<HashMap<String, String>> rader = idb.fetchRows(fraga);

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"PID", "Namn", "Kontaktperson", "E-post", "Telefon", "Adress", "Bransch", "Stad"}, 0
            );

            if (rader != null) {
                for (HashMap<String, String> rad : rader) {
                    model.addRow(new Object[]{
                        rad.get("pid"), rad.get("namn"), rad.get("kontaktperson"),
                        rad.get("kontaktepost"), rad.get("telefon"), rad.get("adress"),
                        rad.get("branch"), rad.get("stad")
                    });
                }
            }
            JTablePartners.setModel(model);
        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Fel vid hämtning av partners: " + e.getMessage());
        }
    }

    /**
     * Fyller textfälten med information från den valda partnern i tabellen.
     */
    private void fyllTextfaltFranTabell() {
        int valdRad = JTablePartners.getSelectedRow();
        if (valdRad != -1) {
            JTxtFieldPID.setText(JTablePartners.getValueAt(valdRad, 0).toString());
            JTxtFieldNamn.setText(JTablePartners.getValueAt(valdRad, 1).toString());
            JTxtFieldKontaktPerson.setText(JTablePartners.getValueAt(valdRad, 2).toString());
            JTxtFieldKontaktEpost.setText(JTablePartners.getValueAt(valdRad, 3).toString());
            JTxtFieldTelefon.setText(JTablePartners.getValueAt(valdRad, 4).toString());
            JTxtFieldAdress.setText(JTablePartners.getValueAt(valdRad, 5).toString());
            JTxtFieldBranch.setText(JTablePartners.getValueAt(valdRad, 6).toString());
            JTxtStad.setText(JTablePartners.getValueAt(valdRad, 7) != null ? JTablePartners.getValueAt(valdRad, 7).toString() : "");
        }
    }

    /**
     * Fyller den undre tabellen med projekt som den valda partnern är kopplad
     * till.
     */
    private void laddaProjektForValdPartner() {
        int valdRad = JTablePartners.getSelectedRow();
        if (valdRad != -1) {
            // Lokal genväg till databasen här med!
            InfDB idb = anvandare.getIdb();
            if (idb == null) {
                return;
            }

            String pid = JTablePartners.getValueAt(valdRad, 0).toString();
            try {
                String fraga = "SELECT projekt.pid, projekt.projektnamn, projekt.status FROM projekt "
                        + "JOIN projekt_partner ON projekt.pid = projekt_partner.pid "
                        + "WHERE projekt_partner.partner_pid = " + pid;

                ArrayList<HashMap<String, String>> projektRader = idb.fetchRows(fraga);

                DefaultTableModel model = new DefaultTableModel(
                        new String[]{"Projekt-ID", "Projektnamn", "Status"}, 0
                );

                if (projektRader != null) {
                    for (HashMap<String, String> rad : projektRader) {
                        model.addRow(new Object[]{
                            rad.get("pid"), rad.get("projektnamn"), rad.get("status")
                        });
                    }
                }
                JTableAktivaProjektMedPartner.setModel(model);
            } catch (InfException e) {
                JOptionPane.showMessageDialog(null, "Fel vid hämtning av projekt: " + e.getMessage());
            }
        } else {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPanelPartners = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTablePartners = new javax.swing.JTable();
        JLblPID = new javax.swing.JLabel();
        JLblNamn = new javax.swing.JLabel();
        JLblKontaktPerson = new javax.swing.JLabel();
        JLblKontaktEpost = new javax.swing.JLabel();
        JLblTelefon = new javax.swing.JLabel();
        JLblAdress = new javax.swing.JLabel();
        JLblBranch = new javax.swing.JLabel();
        JLblStad = new javax.swing.JLabel();
        JTxtFieldPID = new javax.swing.JTextField();
        JTxtFieldNamn = new javax.swing.JTextField();
        JTxtFieldKontaktPerson = new javax.swing.JTextField();
        JTxtFieldKontaktEpost = new javax.swing.JTextField();
        JTxtFieldTelefon = new javax.swing.JTextField();
        JTxtFieldAdress = new javax.swing.JTextField();
        JTxtFieldBranch = new javax.swing.JTextField();
        JTxtStad = new javax.swing.JTextField();
        JBtnLaggTillPartner = new javax.swing.JButton();
        JBtnÄndraPartner = new javax.swing.JButton();
        JBtnTaBortPartner = new javax.swing.JButton();
        JLblRubrik = new javax.swing.JLabel();
        JPanelAktivaSammarbeten = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTableAktivaProjektMedPartner = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JPanelPartners.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Partner", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        JTablePartners.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(JTablePartners);

        JLblPID.setText("PartnerID");

        JLblNamn.setText("Namn");

        JLblKontaktPerson.setText("Kontaktperson");

        JLblKontaktEpost.setText("Kontaktepost");

        JLblTelefon.setText("Telefon");

        JLblAdress.setText("Adress");

        JLblBranch.setText("Branch");

        JLblStad.setText("Stad");

        JTxtFieldAdress.addActionListener(this::JTxtFieldAdressActionPerformed);

        JBtnLaggTillPartner.setText("Lägg till");
        JBtnLaggTillPartner.addActionListener(this::JBtnLaggTillPartnerActionPerformed);

        JBtnÄndraPartner.setText("Ändra");
        JBtnÄndraPartner.addActionListener(this::JBtnÄndraPartnerActionPerformed);

        JBtnTaBortPartner.setText("Ta Bort");
        JBtnTaBortPartner.addActionListener(this::JBtnTaBortPartnerActionPerformed);

        javax.swing.GroupLayout JPanelPartnersLayout = new javax.swing.GroupLayout(JPanelPartners);
        JPanelPartners.setLayout(JPanelPartnersLayout);
        JPanelPartnersLayout.setHorizontalGroup(
            JPanelPartnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelPartnersLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(JPanelPartnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JBtnLaggTillPartner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblPID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblNamn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblKontaktPerson, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(JLblKontaktEpost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblTelefon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblAdress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblBranch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblStad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(JPanelPartnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JBtnÄndraPartner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JTxtFieldKontaktEpost)
                    .addComponent(JTxtFieldTelefon)
                    .addComponent(JTxtFieldAdress)
                    .addComponent(JTxtFieldBranch)
                    .addComponent(JTxtStad)
                    .addComponent(JTxtFieldPID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTxtFieldNamn)
                    .addComponent(JTxtFieldKontaktPerson))
                .addGap(18, 18, 18)
                .addComponent(JBtnTaBortPartner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JPanelPartnersLayout.setVerticalGroup(
            JPanelPartnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelPartnersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanelPartnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelPartnersLayout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addGroup(JPanelPartnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblPID)
                            .addComponent(JTxtFieldPID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelPartnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblNamn)
                            .addComponent(JTxtFieldNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelPartnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblKontaktPerson)
                            .addComponent(JTxtFieldKontaktPerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelPartnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblKontaktEpost)
                            .addComponent(JTxtFieldKontaktEpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelPartnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblTelefon)
                            .addComponent(JTxtFieldTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelPartnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblAdress)
                            .addComponent(JTxtFieldAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelPartnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblBranch)
                            .addComponent(JTxtFieldBranch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelPartnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblStad)
                            .addComponent(JTxtStad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(JPanelPartnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JBtnLaggTillPartner, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JBtnÄndraPartner, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JBtnTaBortPartner, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        JLblRubrik.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        JLblRubrik.setText("Hantera partner");

        JPanelAktivaSammarbeten.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aktiva projekt med denna partner", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        JTableAktivaProjektMedPartner.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(JTableAktivaProjektMedPartner);

        javax.swing.GroupLayout JPanelAktivaSammarbetenLayout = new javax.swing.GroupLayout(JPanelAktivaSammarbeten);
        JPanelAktivaSammarbeten.setLayout(JPanelAktivaSammarbetenLayout);
        JPanelAktivaSammarbetenLayout.setHorizontalGroup(
            JPanelAktivaSammarbetenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelAktivaSammarbetenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        JPanelAktivaSammarbetenLayout.setVerticalGroup(
            JPanelAktivaSammarbetenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelAktivaSammarbetenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Tillbaka till meny");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JPanelPartners, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(229, 229, 229)
                                .addComponent(JLblRubrik)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(JPanelAktivaSammarbeten, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLblRubrik)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPanelPartners, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(JPanelAktivaSammarbeten, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBtnLaggTillPartnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnLaggTillPartnerActionPerformed
        // TODO add your handling code here                                                 
        try {
            if (anvandare == null) return;
            InfDB idb = anvandare.getIdb();
            if (idb == null) return;

            String pid = JTxtFieldPID.getText().trim();
            String namn = JTxtFieldNamn.getText().trim();
            String kontakt = JTxtFieldKontaktPerson.getText().trim();
            String epost = JTxtFieldKontaktEpost.getText().trim();
            String tel = JTxtFieldTelefon.getText().trim();
            String adress = JTxtFieldAdress.getText().trim();
            String branch = JTxtFieldBranch.getText().trim();
            String stad = JTxtStad.getText().trim();
            
            if(pid.isEmpty() || namn.isEmpty()) {
                JOptionPane.showMessageDialog(null, "PartnerID och Namn måste fyllas i!");
                return;
            }
            
            String fraga = "INSERT INTO partner (pid, namn, kontaktperson, kontaktepost, telephone, adress, branch, stad) " +
                           "VALUES (" + pid + ", '" + namn + "', '" + kontakt + "', '" + epost + "', '" + tel + "', '" + adress + "', '" + branch + "', '" + stad + "')";
            
            idb.insert(fraga);
            JOptionPane.showMessageDialog(null, "Partner tillagd!");
            laddaAllaPartners();
        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Kunde inte lägga till: " + e.getMessage());
        }
    }//GEN-LAST:event_JBtnLaggTillPartnerActionPerformed

    private void JBtnÄndraPartnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnÄndraPartnerActionPerformed
        // TODO add your handling code here:
      try {
            if (anvandare == null) return;
            InfDB idb = anvandare.getIdb();
            if (idb == null) return;

            String pid = JTxtFieldPID.getText().trim();
            String namn = JTxtFieldNamn.getText().trim();
            String kontakt = JTxtFieldKontaktPerson.getText().trim();
            String epost = JTxtFieldKontaktEpost.getText().trim();
            String tel = JTxtFieldTelefon.getText().trim();
            String adress = JTxtFieldAdress.getText().trim();
            String branch = JTxtFieldBranch.getText().trim();
            String stad = JTxtStad.getText().trim();
            
            if(pid.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Välj en partner i listan först!");
                return;
            }
            
            String fraga = "UPDATE partner SET namn='" + namn + "', kontaktperson='" + kontakt + 
                           "', kontaktepost='" + epost + "', telephone='" + tel + "', adress='" + adress + 
                           "', branch='" + branch + "', stad='" + stad + "' WHERE pid=" + pid;
            
            idb.update(fraga);
            JOptionPane.showMessageDialog(null, "Partner uppdaterad!");
            laddaAllaPartners();
        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Kunde inte ändra: " + e.getMessage());
        }
    }//GEN-LAST:event_JBtnÄndraPartnerActionPerformed

    private void JBtnTaBortPartnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnTaBortPartnerActionPerformed
        // TODO add your handling code here:
        try {
            if (anvandare == null) return;
            InfDB idb = anvandare.getIdb();
            if (idb == null) return;

            String pid = JTxtFieldPID.getText().trim();
            if(pid.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Välj en partner i listan först!");
                return;
            }
            
            int svar = JOptionPane.showConfirmDialog(null, "Är du säker på att du vill ta bort denna partner?", "Varning", JOptionPane.YES_NO_OPTION);
            if(svar == JOptionPane.YES_OPTION) {
                idb.delete("DELETE FROM projekt_partner WHERE partner_pid = " + pid);
                idb.delete("DELETE FROM partner WHERE pid = " + pid);
                
                JOptionPane.showMessageDialog(null, "Partnern har tagits bort!");
                laddaAllaPartners();
                
                DefaultTableModel model = (DefaultTableModel) JTableAktivaProjektMedPartner.getModel();
                model.setRowCount(0);
            }
        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Kunde inte ta bort partner: " + e.getMessage());
        }
    }//GEN-LAST:event_JBtnTaBortPartnerActionPerformed

    private void JTxtFieldAdressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTxtFieldAdressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTxtFieldAdressActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Navigering.tillbakaTillMeny(anvandare);
        this.dispose();
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
                    // Försöker starta med en riktig test-anslutning
                    oru.inf.InfDB testIdb = new oru.inf.InfDB("sdgsweden", "3306", "root", "masterkey");
                    Anvandare testAnv = new Anvandare(testIdb, "Test", "Testsson", "2026-01-01", 1, "Gata 1", "123", "pw", "Projektchef");
                    new VisaPartnerAdmin(testAnv).setVisible(true);
                } catch (Exception e) {
                    // FIX: Om databasen inte är igång skapar vi ändå en tom fejk-användare 
                    // så att fönstret öppnas tomt istället för att krascha med röd text!
                    Anvandare fejkAnv = new Anvandare(null, "", "", "", 0, "", "", "", "");
                    new VisaPartnerAdmin(fejkAnv).setVisible(true);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBtnLaggTillPartner;
    private javax.swing.JButton JBtnTaBortPartner;
    private javax.swing.JButton JBtnÄndraPartner;
    private javax.swing.JLabel JLblAdress;
    private javax.swing.JLabel JLblBranch;
    private javax.swing.JLabel JLblKontaktEpost;
    private javax.swing.JLabel JLblKontaktPerson;
    private javax.swing.JLabel JLblNamn;
    private javax.swing.JLabel JLblPID;
    private javax.swing.JLabel JLblRubrik;
    private javax.swing.JLabel JLblStad;
    private javax.swing.JLabel JLblTelefon;
    private javax.swing.JPanel JPanelAktivaSammarbeten;
    private javax.swing.JPanel JPanelPartners;
    private javax.swing.JTable JTableAktivaProjektMedPartner;
    private javax.swing.JTable JTablePartners;
    private javax.swing.JTextField JTxtFieldAdress;
    private javax.swing.JTextField JTxtFieldBranch;
    private javax.swing.JTextField JTxtFieldKontaktEpost;
    private javax.swing.JTextField JTxtFieldKontaktPerson;
    private javax.swing.JTextField JTxtFieldNamn;
    private javax.swing.JTextField JTxtFieldPID;
    private javax.swing.JTextField JTxtFieldTelefon;
    private javax.swing.JTextField JTxtStad;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
