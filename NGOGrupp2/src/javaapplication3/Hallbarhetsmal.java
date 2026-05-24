package javaapplication3;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class Hallbarhetsmal extends javax.swing.JFrame {
    
    private InfDB idb; 
    private DefaultTableModel bordsModell;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Hallbarhetsmal.class.getName());
    
    public Hallbarhetsmal(InfDB idb) {
        this.idb = idb;
        initComponents();
        
        // Kopplar JTable till modell och sätter rubriker
        bordsModell = (DefaultTableModel) JTableVisaMal.getModel();
        bordsModell.setColumnIdentifiers(new Object[]{"hid", "Namn", "Målnummer", "Beskrivning", "Prioritet"});
        
        // Hämtar data
        visaAllaHallbarhetsmal();
    }

    private void visaAllaHallbarhetsmal() {
        bordsModell.setRowCount(0);
        String SQLFraga = "SELECT hid, namn, malnummer, beskrivning, prioritet FROM hallbarhetsmal";
        
        try {
            ArrayList<HashMap<String, String>> rader = idb.fetchRows(SQLFraga);
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
            JOptionPane.showMessageDialog(this, "Kunde inte hämta hållbarhetsmål: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        JBtnTillbakaTillMeny = new javax.swing.JButton();
        JScrollPaneHallbarhetsmal = new javax.swing.JScrollPane();
        JTableVisaMal = new javax.swing.JTable();
        JLblRubrik = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JBtnTillbakaTillMeny.setText("Tillbaka till Meny");
        JBtnTillbakaTillMeny.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBtnTillbakaTillMenyActionPerformed(evt);
            }
        });

        JTableVisaMal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "Title 1", "Title 2", "Title 3", "Title 4" }
        ));
        JScrollPaneHallbarhetsmal.setViewportView(JTableVisaMal);

        JLblRubrik.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        JLblRubrik.setText("Hållbarhetsmål");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JScrollPaneHallbarhetsmal, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JBtnTillbakaTillMeny)
                        .addGap(122, 122, 122)
                        .addComponent(JLblRubrik)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBtnTillbakaTillMeny)
                    .addComponent(JLblRubrik))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JScrollPaneHallbarhetsmal, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void JBtnTillbakaTillMenyActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        this.dispose();
    }                                                    

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            try {
                InfDB testDb = new InfDB("sdgsweden", "3306", "root", "masterkey");
                new Hallbarhetsmal(testDb).setVisible(true);
            } catch (InfException e) {
                System.out.println("Kunde inte starta: " + e.getMessage());
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton JBtnTillbakaTillMeny;
    private javax.swing.JLabel JLblRubrik;
    private javax.swing.JScrollPane JScrollPaneHallbarhetsmal;
    private javax.swing.JTable JTableVisaMal;
    // End of variables declaration                   
}