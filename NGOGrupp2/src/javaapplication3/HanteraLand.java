/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication3;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Krist
 */

    public class HanteraLand extends javax.swing.JFrame {
    
        private InfDB idb;
        private DefaultTableModel bordsModell;
        private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HanteraLand.class.getName());

    /**
     * Creates new form HanteraLand
     */
    public HanteraLand(InfDB idb) {
        this.idb = idb;// Sparar databasanslutningen
        initComponents();
        
        //initierar tabellmodellen
        bordsModell = (DefaultTableModel) JTableListaLand.getModel();
        //sätter rubriker som matchar databasen
        bordsModell.setColumnIdentifiers(new Object[]{"lid","namn","sprak","valuta","tidszon","politisk_struktur","ekonomi"});
        
        fyllTabell();
        
        //Gör så att man kan klicka i tabellen för att fylla textfälten.
        JTableListaLand.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int rad = JTableListaLand.getSelectedRow();
            if (rad >= 0) {
                JTxtLID.setText(bordsModell.getValueAt(rad, 0).toString());
                JTxtFieldNamn.setText(bordsModell.getValueAt(rad, 1).toString());
                JTxtFieldSprak.setText(bordsModell.getValueAt(rad, 2).toString());
                JTxtFieldValuta.setText(bordsModell.getValueAt(rad, 3).toString());
                JTxtFieldTidZon.setText(bordsModell.getValueAt(rad, 4).toString());
                JTxtFieldPolitiskStruktur.setText(bordsModell.getValueAt(rad, 5).toString());
                JTxtFieldEkonomi.setText(bordsModell.getValueAt(rad, 6).toString());
            }
        }
    });
    }
    //metod för att ändra uppgifter om ett land
    private void andraLand(){
        
        try {
        String id = JTxtLID.getText();
        String namn = JTxtFieldNamn.getText();
        String sprak = JTxtFieldSprak.getText();
        String valuta = JTxtFieldValuta.getText();
        String tidszon = JTxtFieldTidZon.getText();
        String politik = JTxtFieldPolitiskStruktur.getText();
        String ekonomi = JTxtFieldEkonomi.getText();

        if (id.isEmpty()) {
            //om inget land är valt så får användaren detta meddelande.
            JOptionPane.showMessageDialog(this, "Välj ett land i tabellen att ändra!");
            return;
        }
        // SQl, Uppdaterar data kopplat på det lid man skrivit in.
        String fraga = "UPDATE land SET namn='" + namn + "', sprak='" + sprak + 
                       "', valuta='" + valuta + "', tidszon='" + tidszon + 
                       "', politisk_struktur='" + politik + "', ekonomi='" + ekonomi + 
                       "' WHERE lid=" + id;

        idb.update(fraga);
        fyllTabell();
        //meddelande ifall det har fungerat
        JOptionPane.showMessageDialog(this, "Ändringarna sparades!");
        
    } catch (InfException e) {
        //meddelande ifall det inte fungerat
        JOptionPane.showMessageDialog(this, "Kunde inte ändra: " + e.getMessage());
        }
        
    }
    
    private void taBortLand() {
    try {
        String id = JTxtLID.getText();
        if (id.isEmpty()) {
            //meddelande ber användaren välja ett land att ta bort.
            JOptionPane.showMessageDialog(this, "Välj ett land i tabellen att ta bort!");
            return;
        }
        //ber användaren att bekräfta innan man tar bort ett land för att undvika att man tar bort data av misstag.
        int svar = JOptionPane.showConfirmDialog(this, "Är du säker på att du vill ta bort landet?", "Bekräfta", JOptionPane.YES_NO_OPTION);
        if (svar == JOptionPane.YES_OPTION) {
            //sql sats var man vill ta bort datan.
            String fraga = "DELETE FROM land WHERE lid=" + id;
            idb.delete(fraga);
            fyllTabell();
            // bekräftelsemeddelande att det är borttaget.
            JOptionPane.showMessageDialog(this, "Landet borttaget!");
        }
    } catch (InfException e) {
        JOptionPane.showMessageDialog(this, "Kunde inte ta bort (det kan finnas städer kopplade till landet): " + e.getMessage());
        }
    }
    // Metod som hämtar all data om länderna och lägger de i tabellen
    private void fyllTabell() {
        bordsModell.setRowCount(0);// Tömmer tabellen först
        try {
            String fraga = "Select * From land ORDER BY lid";
            ArrayList<HashMap<String, String>> rader = idb.fetchRows(fraga);
            
            if (rader != null) {
                for(HashMap<String, String> rad : rader) {
                    bordsModell.addRow(new Object[]{
                        rad.get("lid"),
                        rad.get("namn"),
                        rad.get("sprak"),
                        rad.get("valuta"),
                        rad.get("tidszon"),
                        rad.get("politisk_struktur"),
                        rad.get("ekonomi")
                });
                }
            }
            
        } catch (InfException e){
            JOptionPane.showMessageDialog(null,"fel vid hämtning av data från databasen" + e.getMessage());
        }
        
    }
    
    private void LaggTillLand(){
        try{
            String id = JTxtLID.getText();
            String namn = JTxtFieldNamn.getText();
            String sprak = JTxtFieldSprak.getText();
            String valuta = JTxtFieldValuta.getText();
            String tidszon = JTxtFieldTidZon.getText();
            String politik = JTxtFieldPolitiskStruktur.getText();
            String ekonomi = JTxtFieldEkonomi.getText();
            
            if(id.isEmpty() || namn.isEmpty()){
                JOptionPane.showMessageDialog(null, "Land ID och Namn måste fyllas i!");
                return;
            }
            
            String fraga = "INSERT INTO land(lid, namn, sprak, valuta, tidszon, politisk_struktur, ekonomi)" +
                           "VALUES (" + id + ", '" + namn + "', '" + sprak + "', '" + valuta + "', '" + tidszon + "', '" + politik + "', '" + ekonomi + "')";
            
            idb.insert(fraga);
            fyllTabell();// Uppdaterar listan
            JOptionPane.showMessageDialog(null, "Landet har lags till!");
            
        } catch(InfException e) {
            JOptionPane.showMessageDialog(null, "Kunde inte lägga till: " + e.getMessage());
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableListaLand = new javax.swing.JTable();
        JLblLID = new javax.swing.JLabel();
        JLblNamn = new javax.swing.JLabel();
        JLblSprak = new javax.swing.JLabel();
        JLblValuta = new javax.swing.JLabel();
        JLblTidZon = new javax.swing.JLabel();
        JlblPolitiskStruktur = new javax.swing.JLabel();
        JLblEkonomi = new javax.swing.JLabel();
        JTxtLID = new javax.swing.JTextField();
        JTxtFieldNamn = new javax.swing.JTextField();
        JTxtFieldSprak = new javax.swing.JTextField();
        JTxtFieldValuta = new javax.swing.JTextField();
        JTxtFieldTidZon = new javax.swing.JTextField();
        JTxtFieldPolitiskStruktur = new javax.swing.JTextField();
        JTxtFieldEkonomi = new javax.swing.JTextField();
        JBtnLaggTillLand = new javax.swing.JButton();
        JBtnÄndraLand = new javax.swing.JButton();
        JBtnTaBortLand = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JLblRubrik.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        JLblRubrik.setText("Hantera Land");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Länder", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        JTableListaLand.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(JTableListaLand);

        JLblLID.setText("LandID");

        JLblNamn.setText("Namn");

        JLblSprak.setText("Språk");

        JLblValuta.setText("Valuta");

        JLblTidZon.setText("Tidzon");

        JlblPolitiskStruktur.setText("Politisk Struktur");

        JLblEkonomi.setText("Ekonomi");

        JBtnLaggTillLand.setText("Lägg Till");
        JBtnLaggTillLand.addActionListener(this::JBtnLaggTillLandActionPerformed);

        JBtnÄndraLand.setText("Ändra");
        JBtnÄndraLand.addActionListener(this::JBtnÄndraLandActionPerformed);

        JBtnTaBortLand.setText("Ta bort");
        JBtnTaBortLand.addActionListener(this::JBtnTaBortLandActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(JlblPolitiskStruktur, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(JLblTidZon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblValuta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblSprak, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblNamn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblLID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLblEkonomi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBtnLaggTillLand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JTxtLID)
                    .addComponent(JTxtFieldNamn)
                    .addComponent(JTxtFieldSprak)
                    .addComponent(JTxtFieldValuta)
                    .addComponent(JTxtFieldTidZon)
                    .addComponent(JTxtFieldPolitiskStruktur, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(JTxtFieldEkonomi)
                    .addComponent(JBtnÄndraLand, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(JBtnTaBortLand, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblLID)
                            .addComponent(JTxtLID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblNamn)
                            .addComponent(JTxtFieldNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblSprak)
                            .addComponent(JTxtFieldSprak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblValuta)
                            .addComponent(JTxtFieldValuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblTidZon)
                            .addComponent(JTxtFieldTidZon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JlblPolitiskStruktur)
                            .addComponent(JTxtFieldPolitiskStruktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblEkonomi)
                            .addComponent(JTxtFieldEkonomi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JBtnLaggTillLand, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(JBtnÄndraLand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JBtnTaBortLand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(JLblRubrik)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLblRubrik)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBtnLaggTillLandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnLaggTillLandActionPerformed
        //Kallar på LaggTillLand metoden
        LaggTillLand();
        
    }//GEN-LAST:event_JBtnLaggTillLandActionPerformed

    private void JBtnÄndraLandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnÄndraLandActionPerformed
        // Kallar på andraLand metoden
        andraLand();
    }//GEN-LAST:event_JBtnÄndraLandActionPerformed

    private void JBtnTaBortLandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnTaBortLandActionPerformed
        // Kallar på TaBortLand Metoden
        taBortLand();
    }//GEN-LAST:event_JBtnTaBortLandActionPerformed


//tillfällig main metod för att testa fönstret.
public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(() -> {
        try {
            InfDB db = new InfDB("sdgsweden", "3306", "root", "masterkey");
            new HanteraLand(db).setVisible(true);
        } catch (InfException ex) {
            System.out.println("Kunde inte starta databasen: " + ex.getMessage());
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBtnLaggTillLand;
    private javax.swing.JButton JBtnTaBortLand;
    private javax.swing.JButton JBtnÄndraLand;
    private javax.swing.JLabel JLblEkonomi;
    private javax.swing.JLabel JLblLID;
    private javax.swing.JLabel JLblNamn;
    private javax.swing.JLabel JLblRubrik;
    private javax.swing.JLabel JLblSprak;
    private javax.swing.JLabel JLblTidZon;
    private javax.swing.JLabel JLblValuta;
    private javax.swing.JTable JTableListaLand;
    private javax.swing.JTextField JTxtFieldEkonomi;
    private javax.swing.JTextField JTxtFieldNamn;
    private javax.swing.JTextField JTxtFieldPolitiskStruktur;
    private javax.swing.JTextField JTxtFieldSprak;
    private javax.swing.JTextField JTxtFieldTidZon;
    private javax.swing.JTextField JTxtFieldValuta;
    private javax.swing.JTextField JTxtLID;
    private javax.swing.JLabel JlblPolitiskStruktur;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
