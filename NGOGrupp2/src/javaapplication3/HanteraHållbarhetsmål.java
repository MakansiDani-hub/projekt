/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication3;

/**
 *
 * @author Kristoffer Kolkowski
 */
import projListeners.MalListener;
import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class HanteraHållbarhetsmål extends javax.swing.JFrame {

    private MalListener malListener;
    private InfDB idb;
    private DefaultTableModel bordsModell; //Modell för Hållbarhetsmållistan
    private DefaultTableModel projektBordsModell; // Modell för projektlistan
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HanteraHållbarhetsmål.class.getName());
    private Anvandare anvandare;

    /**
     * Creates new form HanteraHållbarhetsmål
     */
    public HanteraHållbarhetsmål(Anvandare anvandare) {

        initComponents();
        this.idb = anvandare.getIdb();
        this.anvandare = anvandare;

        JBtnAndraHallbarhetsmal.addActionListener(this::JBtnAndraHallbarhetsmalActionPerformed);
        JBtnTaBortHallbarhetsmal.addActionListener(this::JBtnTaBortHallbarhetsmalActionPerformed);

        // Kopplar JTable till en hanterbar modell och sätter kolumnnamn
        bordsModell = (DefaultTableModel) JTableHallbarhetsmal.getModel();
        bordsModell.setColumnIdentifiers(new Object[]{"hid", "Namn", "Målnummer", "Beskrivning", "Prioritet"});

        // Initiera projekttabellen (Projekt kopplade till mål)
        projektBordsModell = (DefaultTableModel) JTableProjekt.getModel();
        projektBordsModell.setColumnIdentifiers(new Object[]{"Kopplade Projekt"});

        // Hämtar data automatiskt vid uppstart
        laddaHållbarhetsmål();

        JTableHallbarhetsmal.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Ta reda på vilken rad användaren klickade på
                int valdRad = JTableHallbarhetsmal.getSelectedRow();

                // kollar om en rad är vald
                if (valdRad >= 0) {
                    visaRadInfo(valdRad);
                }
            }
        });

        if (!anvandare.getRoll().equals("admin")) {
            //Användaren är ej admin och ska ej få tillgång till knapparna lägg till, ändra och ta bort
            JBtnLaggTillHallbarhetsmal.setVisible(false);
            JBtnAndraHallbarhetsmal.setVisible(false);
            JBtnTaBortHallbarhetsmal.setVisible(false);
        }
    }

    public void addMalListener(MalListener malListener) {
        this.malListener = malListener;
    }

    private void laddaHållbarhetsmål() {
        bordsModell.setRowCount(0); // Tömmer testrader

        // sqlfråga
        String fråga = "SELECT hid, namn, malnummer, beskrivning, prioritet FROM hallbarhetsmal";

        try {
            ArrayList<HashMap<String, String>> rader = idb.fetchRows(fråga);

            if (rader != null) {
                for (HashMap<String, String> rad : rader) {
                    bordsModell.addRow(new Object[]{
                        rad.get("hid"),
                        rad.get("namn"),
                        rad.get("malnummer"),
                        rad.get("beskrivning"),
                        rad.get("prioritet")
                    });
                }
            }
        } catch (InfException e) {
            JOptionPane.showMessageDialog(this, "Kunde inte ladda data: " + e.getMessage());
        }
    }

    //Hämtar projekt från databasen som är kopplade till det valda målet från kopplingstabellen(proj_Hallbarhet)
    private void laddaProjektForMal(String hid) {
        projektBordsModell.setRowCount(0);
        try {
            // SQLfråga för att hämta datan från tabellen
            String sql = "SELECT p.projektnamn FROM projekt p "
                    + "JOIN proj_hallbarhet ph ON p.pid = ph.pid "
                    + "WHERE ph.hid = " + hid;

            ArrayList<HashMap<String, String>> rader = idb.fetchRows(sql);
            if (rader != null) {
                for (HashMap<String, String> rad : rader) {
                    projektBordsModell.addRow(new Object[]{rad.get("projektnamn")});
                }
            }
        } catch (InfException e) {
            System.out.println("Kunde inte hämta projekt: " + e.getMessage());
        }
    }
    
    //Letar upp och markerar en specifik rad i tabellen baserat på ett ID.
    //Synkroniserar även gränssnittet så att textfälten fylls med 
    // informationen från den valda raden via visaRadInfo.
    public void valjRad(String malId) {
        int valdRad = SwingUtils.valjRadIJTableMedId(JTableHallbarhetsmal, malId, 0);
        System.out.println("rad: " + valdRad);
        visaRadInfo(valdRad);
    }

    private void visaRadInfo(int valdRad) {
        // Hämtar datan från kolumnerna på den valda raden
        String id = bordsModell.getValueAt(valdRad, 0).toString();
        String namn = bordsModell.getValueAt(valdRad, 1).toString();
        String malnr = bordsModell.getValueAt(valdRad, 2).toString();
        String beskrivning = bordsModell.getValueAt(valdRad, 3).toString();
        String prioritet = bordsModell.getValueAt(valdRad, 4).toString();

        // Sätter in datan i JTextFields
        JTxtFieldHID.setText(id);
        JTxtFieldNamn.setText(namn);
        JTxtFieldMalNr.setText(malnr);
        JTxtFieldBeskrivning.setText(beskrivning);
        JTxtFieldPrioritet.setText(prioritet);

        // Låser ID-rutan så man inte ändrar den av misstag och rör till det i databasen.
        JTxtFieldHID.setEditable(false);

        //anropar projektlistan
        laddaProjektForMal(id);

        //Kallar på mål-listenerns metod valLand så andra fönster kan registrera ett val vid behov
        if (malListener != null) {
            malListener.valMal(id, namn, malnr);
        }
    }
    //metod för att tömma TxtFieldsen
    private void rensaFalt() {
        JTxtFieldHID.setText("");
        JTxtFieldNamn.setText("");
        JTxtFieldMalNr.setText("");
        JTxtFieldBeskrivning.setText("");
        JTxtFieldPrioritet.setText("");
        JTxtFieldHID.setEditable(true); // Låser upp HID för att kunna skriva in ett nytt
    }

    private void laggTillMal() {
        //Validering via ValideringInput istället för i detta fönster
        //Kollar så det finns ett hid och malNr och att detta är ett heltal
        //Kollar även att det finns ett namn
        if (ValideringInput.harVarde(JTxtFieldHID, "HID")
                && ValideringInput.arHeltal(JTxtFieldHID, "HID")
                && ValideringInput.harVarde(JTxtFieldNamn, "Namn")
                && ValideringInput.arHeltal(JTxtFieldMalNr, "Målnummer")) {

            try {
                //Hämtar datan ur TxtFieldsen och sätter den i variabler
                String id = JTxtFieldHID.getText().trim();
                String namn = JTxtFieldNamn.getText().trim().replace("'", "''");
                String malnr = JTxtFieldMalNr.getText().trim();
                String besk = JTxtFieldBeskrivning.getText().trim().replace("'", "''");
                String prio = JTxtFieldPrioritet.getText().trim().replace("'", "''");

                //För in datan i databasen
                String sql = "INSERT INTO hallbarhetsmal VALUES (" + id + ", '" + namn + "', " + malnr + ", '" + besk + "', '" + prio + "')";
                idb.insert(sql);

                laddaHållbarhetsmål();
                rensaFalt();//rensar TxtFieldsen så man kan jobba vidare med andra mål
                //meddelande ifall det fungerat
                JOptionPane.showMessageDialog(this, "Målet har lagts till!");
            } catch (InfException e) {
                //meddelande ifall det inte fungerat
                JOptionPane.showMessageDialog(this, "Kunde inte spara: " + e.getMessage());
            }
        }
    }

    private void andraMal() {
        // Kontrollerar att ett mål är valt genom att se om HID-rutan har ett värde
        if (JTxtFieldHID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Välj ett mål i tabellen först.");
            return;
        }
        //Validering via ValideringInput istället för i detta fönster
        //Kontrollerar att det finns ett namn och att målnummer är ett heltal
        if (ValideringInput.harVarde(JTxtFieldNamn, "Namn")
                && ValideringInput.arHeltal(JTxtFieldMalNr, "Målnummer")) {

            try {
                //Hämtar datan ur TxtFieldsen och sätter den i variabler
                String id = JTxtFieldHID.getText();
                String namn = JTxtFieldNamn.getText().trim().replace("'", "''");
                String malnr = JTxtFieldMalNr.getText().trim();
                String besk = JTxtFieldBeskrivning.getText().trim().replace("'", "''");
                String prio = JTxtFieldPrioritet.getText().trim().replace("'", "''");

                //uppdaterar datan i databasen med SQLsats
                String sql = "UPDATE hallbarhetsmal SET namn='" + namn + "', malnummer=" + malnr
                        + ", beskrivning='" + besk + "', prioritet='" + prio + "' WHERE hid=" + id;

                idb.update(sql);
                laddaHållbarhetsmål();
                //meddelande ifall det fungerat
                JOptionPane.showMessageDialog(this, "Ändringar sparade!");
            } catch (InfException e) {
                //meddelande ifall det inte fungerat
                JOptionPane.showMessageDialog(this, "Fel vid uppdatering: " + e.getMessage());
            }
        }
    }

    private void taBortMal() {
        //Kollar att HID har ett värde och är ett heltal
        if (ValideringInput.harVarde(JTxtFieldHID, "HID") && ValideringInput.arHeltal(JTxtFieldHID, "HID")) {

            String id = JTxtFieldHID.getText().trim();

            //Extra kontroll så användare inte tar bort ett mål av misstag
            int svar = JOptionPane.showConfirmDialog(this, "Vill du verkligen ta bort mål " + id + "?", "Bekräfta", JOptionPane.YES_NO_OPTION);

            if (svar == JOptionPane.YES_OPTION) {
                try {
                    idb.delete("DELETE FROM hallbarhetsmal WHERE hid=" + id);
                    laddaHållbarhetsmål();
                    rensaFalt(); //Rensar rutorna så användare kan jobba vidare med andra mål
                    //meddelande ifall det har tagits bort
                    JOptionPane.showMessageDialog(this, "Målet har raderats.");
                } catch (InfException e) {
                    //meddelande ifall det inte har tagits bort
                    JOptionPane.showMessageDialog(this, "Kunde inte ta bort: " + e.getMessage());
                }
            }
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

        JLblRubrik = new javax.swing.JLabel();
        JPanelHållbarhetsmål = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableHallbarhetsmal = new javax.swing.JTable();
        JLblLID = new javax.swing.JLabel();
        JLblNamn = new javax.swing.JLabel();
        JLblMalNr = new javax.swing.JLabel();
        JLblBeskrivning = new javax.swing.JLabel();
        JLblPrioritet = new javax.swing.JLabel();
        JTxtFieldHID = new javax.swing.JTextField();
        JTxtFieldNamn = new javax.swing.JTextField();
        JTxtFieldMalNr = new javax.swing.JTextField();
        JTxtFieldBeskrivning = new javax.swing.JTextField();
        JTxtFieldPrioritet = new javax.swing.JTextField();
        JBtnLaggTillHallbarhetsmal = new javax.swing.JButton();
        JBtnAndraHallbarhetsmal = new javax.swing.JButton();
        JBtnTaBortHallbarhetsmal = new javax.swing.JButton();
        JPanelProjektMotMal = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTableProjekt = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JLblRubrik.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        JLblRubrik.setText("Hantera Hållbarhetsmål");

        JPanelHållbarhetsmål.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hållbarhetsmål", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        JTableHallbarhetsmal.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(JTableHallbarhetsmal);

        JLblLID.setText("HållbarhetsmålID");

        JLblNamn.setText("Namn");

        JLblMalNr.setText("Målnummer");

        JLblBeskrivning.setText("Beskrivning");

        JLblPrioritet.setText("Prioritet");

        JTxtFieldHID.setText("[HID]");

        JTxtFieldNamn.setText("[Namn]");

        JTxtFieldMalNr.setText("[Malnummer]");

        JTxtFieldBeskrivning.setText("[Beskrivning]");

        JTxtFieldPrioritet.setText("[Prioritet]");

        JBtnLaggTillHallbarhetsmal.setText("Lägg till");
        JBtnLaggTillHallbarhetsmal.addActionListener(this::JBtnLaggTillHallbarhetsmalActionPerformed);

        JBtnAndraHallbarhetsmal.setText("Ändra");

        JBtnTaBortHallbarhetsmal.setText("Ta bort");

        javax.swing.GroupLayout JPanelHållbarhetsmålLayout = new javax.swing.GroupLayout(JPanelHållbarhetsmål);
        JPanelHållbarhetsmål.setLayout(JPanelHållbarhetsmålLayout);
        JPanelHållbarhetsmålLayout.setHorizontalGroup(
            JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelHållbarhetsmålLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JLblLID, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(JLblNamn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblMalNr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblBeskrivning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblPrioritet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBtnLaggTillHallbarhetsmal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JTxtFieldHID, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTxtFieldNamn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTxtFieldMalNr, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTxtFieldBeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTxtFieldPrioritet, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPanelHållbarhetsmålLayout.createSequentialGroup()
                        .addComponent(JBtnAndraHallbarhetsmal, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JBtnTaBortHallbarhetsmal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(186, Short.MAX_VALUE))
        );
        JPanelHållbarhetsmålLayout.setVerticalGroup(
            JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelHållbarhetsmålLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPanelHållbarhetsmålLayout.createSequentialGroup()
                        .addGroup(JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblLID)
                            .addComponent(JTxtFieldHID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblNamn)
                            .addComponent(JTxtFieldNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblMalNr)
                            .addComponent(JTxtFieldMalNr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblBeskrivning)
                            .addComponent(JTxtFieldBeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblPrioritet)
                            .addComponent(JTxtFieldPrioritet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JBtnAndraHallbarhetsmal)
                            .addComponent(JBtnLaggTillHallbarhetsmal)
                            .addComponent(JBtnTaBortHallbarhetsmal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );

        JPanelProjektMotMal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Projekt kopplade till valt hållbarhetsmål", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        JTableProjekt.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(JTableProjekt);

        javax.swing.GroupLayout JPanelProjektMotMalLayout = new javax.swing.GroupLayout(JPanelProjektMotMal);
        JPanelProjektMotMal.setLayout(JPanelProjektMotMalLayout);
        JPanelProjektMotMalLayout.setHorizontalGroup(
            JPanelProjektMotMalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelProjektMotMalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        JPanelProjektMotMalLayout.setVerticalGroup(
            JPanelProjektMotMalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
        );

        jButton1.setText("Tillbaka till meny");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(188, 188, 188)
                                .addComponent(JLblRubrik))
                            .addComponent(JPanelHållbarhetsmål, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(JPanelProjektMotMal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLblRubrik)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPanelHållbarhetsmål, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JPanelProjektMotMal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBtnLaggTillHallbarhetsmalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnLaggTillHallbarhetsmalActionPerformed
        //anropar laggTillMal
        laggTillMal();
    }//GEN-LAST:event_JBtnLaggTillHallbarhetsmalActionPerformed

    private void JBtnAndraHallbarhetsmalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnAndraHallbarhetsmalActionPerformed
        //anropar andraMal
        andraMal();
    }//GEN-LAST:event_JBtnAndraHallbarhetsmalActionPerformed

    private void JBtnTaBortHallbarhetsmalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnTaBortHallbarhetsmalActionPerformed
        //anropar taBortMal
        taBortMal();
    }//GEN-LAST:event_JBtnTaBortHallbarhetsmalActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Navigering.tillbakaTillMeny(anvandare);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBtnAndraHallbarhetsmal;
    private javax.swing.JButton JBtnLaggTillHallbarhetsmal;
    private javax.swing.JButton JBtnTaBortHallbarhetsmal;
    private javax.swing.JLabel JLblBeskrivning;
    private javax.swing.JLabel JLblLID;
    private javax.swing.JLabel JLblMalNr;
    private javax.swing.JLabel JLblNamn;
    private javax.swing.JLabel JLblPrioritet;
    private javax.swing.JLabel JLblRubrik;
    private javax.swing.JPanel JPanelHållbarhetsmål;
    private javax.swing.JPanel JPanelProjektMotMal;
    private javax.swing.JTable JTableHallbarhetsmal;
    private javax.swing.JTable JTableProjekt;
    private javax.swing.JTextField JTxtFieldBeskrivning;
    private javax.swing.JTextField JTxtFieldHID;
    private javax.swing.JTextField JTxtFieldMalNr;
    private javax.swing.JTextField JTxtFieldNamn;
    private javax.swing.JTextField JTxtFieldPrioritet;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    //Tillfällig main metod för att kunna provköra klassen
//    public static void main(String args[]) {
//        try {
//            // upprättar anslutning mot mot sql-servern
//            InfDB testDb = new InfDB("sdgsweden", "3306", "root", "masterkey"); 
//            
//            java.awt.EventQueue.invokeLater(new Runnable() {
//                public void run() {
//                    new HanteraHållbarhetsmål(testDb).setVisible(true);
//                }
//            });
//        } catch (InfException e) {
//            System.out.println("Kunde inte ansluta till MySQL-servern: " + e.getMessage());
//        }
//    }
}
