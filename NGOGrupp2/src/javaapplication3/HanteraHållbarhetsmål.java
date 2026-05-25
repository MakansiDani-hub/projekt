package javaapplication3;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class HanteraHållbarhetsmål extends javax.swing.JFrame {
    
    private InfDB idb; 
    private DefaultTableModel bordsModell;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HanteraHållbarhetsmål.class.getName());

    public HanteraHållbarhetsmål(InfDB idb) {
        this.idb = idb;
        initComponents();
        
        bordsModell = (DefaultTableModel) JTableHallberhetsmal.getModel();
        bordsModell.setColumnIdentifiers(new Object[]{"hid", "Namn", "Målnummer", "Beskrivning", "Prioritet"});
        
        laddaHållbarhetsmål();
        
        JTableHallberhetsmal.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int valdRad = JTableHallberhetsmal.getSelectedRow();
                if (valdRad >= 0) {
                    JTxtFieldHID.setText(bordsModell.getValueAt(valdRad, 0).toString());
                    JTxtFieldNamn.setText(bordsModell.getValueAt(valdRad, 1).toString());
                    JTxtFieldMalNr.setText(bordsModell.getValueAt(valdRad, 2).toString());
                    JTxtFieldBeskrivning.setText(bordsModell.getValueAt(valdRad, 3).toString());
                    JTxtFieldPrioritet.setText(bordsModell.getValueAt(valdRad, 4).toString());
                }
            }
        });
    }

    private void laddaHållbarhetsmål() {
        bordsModell.setRowCount(0);
        String fråga = "SELECT hid, namn, malnummer, beskrivning, prioritet FROM hallbarhetsmal";
        try {
            ArrayList<HashMap<String, String>> rader = idb.fetchRows(fråga);
            if (rader != null) {
                for (HashMap<String, String> rad : rader) {
                    bordsModell.addRow(new Object[]{
                        rad.get("hid"), rad.get("namn"), rad.get("malnummer"), rad.get("beskrivning"), rad.get("prioritet")
                    });
                }
            }
        } catch (InfException e) {
            JOptionPane.showMessageDialog(this, "Kunde inte ladda data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        JLblRubrik = new javax.swing.JLabel();
        JPanelHållbarhetsmål = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableHallberhetsmal = new javax.swing.JTable();
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
        JBtnTillbakaTillMeny = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JLblRubrik.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        JLblRubrik.setText("Hantera Hållbarhetsmål");

        JPanelHållbarhetsmål.setBorder(javax.swing.BorderFactory.createTitledBorder("Hållbarhetsmål"));

        JTableHallberhetsmal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {}, new String [] { "Title 1", "Title 2", "Title 3", "Title 4" }
        ));
        jScrollPane1.setViewportView(JTableHallberhetsmal);

        JLblLID.setText("HållberhetsmålID");
        JLblNamn.setText("Namn");
        JLblMalNr.setText("Målnummer");
        JLblBeskrivning.setText("Beskrivning");
        JLblPrioritet.setText("Prioritet");

        JBtnLaggTillHallbarhetsmal.setText("Lägg till");
        JBtnLaggTillHallbarhetsmal.addActionListener(evt -> JBtnLaggTillHallbarhetsmalActionPerformed(evt));

        JBtnAndraHallbarhetsmal.setText("Ändra");
        JBtnAndraHallbarhetsmal.addActionListener(evt -> JBtnAndraHallbarhetsmalActionPerformed(evt));

        JBtnTaBortHallbarhetsmal.setText("Ta bort");
        JBtnTaBortHallbarhetsmal.addActionListener(evt -> JBtnTaBortHallbarhetsmalActionPerformed(evt));

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
                    .addComponent(JLblNamn)
                    .addComponent(JLblMalNr)
                    .addComponent(JLblBeskrivning)
                    .addComponent(JLblPrioritet))
                .addGap(18, 18, 18)
                .addGroup(JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JTxtFieldHID)
                    .addComponent(JTxtFieldNamn)
                    .addComponent(JTxtFieldMalNr)
                    .addComponent(JTxtFieldBeskrivning)
                    .addComponent(JTxtFieldPrioritet)
                    .addComponent(JBtnLaggTillHallbarhetsmal, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(JBtnAndraHallbarhetsmal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBtnTaBortHallbarhetsmal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        JPanelHållbarhetsmålLayout.setVerticalGroup(
            JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelHållbarhetsmålLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPanelHållbarhetsmålLayout.createSequentialGroup()
                        .addGroup(JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblLID).addComponent(JTxtFieldHID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblNamn).addComponent(JTxtFieldNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblMalNr).addComponent(JTxtFieldMalNr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblBeskrivning).addComponent(JTxtFieldBeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPanelHållbarhetsmålLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLblPrioritet).addComponent(JTxtFieldPrioritet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(JBtnLaggTillHallbarhetsmal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBtnAndraHallbarhetsmal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBtnTaBortHallbarhetsmal)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JBtnTillbakaTillMeny.setText("Tillbaka till Meny");
        JBtnTillbakaTillMeny.addActionListener(evt -> JBtnTillbakaTillMenyActionPerformed(evt));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JPanelHållbarhetsmål, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JBtnTillbakaTillMeny)
                        .addGap(120, 120, 120)
                        .addComponent(JLblRubrik)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLblRubrik)
                    .addComponent(JBtnTillbakaTillMeny))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JPanelHållbarhetsmål, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void JBtnTillbakaTillMenyActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void JBtnLaggTillHallbarhetsmalActionPerformed(java.awt.event.ActionEvent evt) {                                                           
        String id = JTxtFieldHID.getText().trim();
        String namn = JTxtFieldNamn.getText().trim();
        String malnr = JTxtFieldMalNr.getText().trim();
        String beskrivning = JTxtFieldBeskrivning.getText().trim();
        String prioritet = JTxtFieldPrioritet.getText().trim();

        if (id.isEmpty() || namn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vänligen fyll i alla fält.");
            return;
        }
        try {
            String sql = "INSERT INTO hallbarhetsmal VALUES('" + id + "', '" + namn + "', '" + malnr + "', '" + beskrivning + "', '" + prioritet + "')";
            idb.insert(sql);
            JOptionPane.showMessageDialog(this, "Tillagd!");
            laddaHållbarhetsmål();
        } catch (InfException e) {
            JOptionPane.showMessageDialog(this, "Fel: " + e.getMessage());
        }
    }                                                          

    private void JBtnAndraHallbarhetsmalActionPerformed(java.awt.event.ActionEvent evt) {                                                        
        String id = JTxtFieldHID.getText().trim();
        try {
            String sql = "UPDATE hallbarhetsmal SET namn='" + JTxtFieldNamn.getText() + "' WHERE hid='" + id + "'";
            idb.update(sql);
            laddaHållbarhetsmål();
        } catch (InfException e) {
            JOptionPane.showMessageDialog(this, "Fel: " + e.getMessage());
        }
    }                                                       

    private void JBtnTaBortHallbarhetsmalActionPerformed(java.awt.event.ActionEvent evt) {                                                         
        String id = JTxtFieldHID.getText().trim();
        int svar = JOptionPane.showConfirmDialog(this, "Ta bort " + id + "?", "Bekräfta", JOptionPane.YES_NO_OPTION);
        if (svar == JOptionPane.YES_OPTION) {
            try {
                idb.delete("DELETE FROM hallbarhetsmal WHERE hid='" + id + "'");
                laddaHållbarhetsmål();
            } catch (InfException e) {
                JOptionPane.showMessageDialog(this, "Fel: " + e.getMessage());
            }
        }
    }                                                        

    public static void main(String args[]) {
        try {
            InfDB testDb = new InfDB("sdgsweden", "3306", "root", "masterkey"); 
            java.awt.EventQueue.invokeLater(() -> new HanteraHållbarhetsmål(testDb).setVisible(true));
        } catch (InfException e) {
            System.out.println("Fel: " + e.getMessage());
        }
    }

    private javax.swing.JButton JBtnAndraHallbarhetsmal;
    private javax.swing.JButton JBtnLaggTillHallbarhetsmal;
    private javax.swing.JButton JBtnTaBortHallbarhetsmal;
    private javax.swing.JButton JBtnTillbakaTillMeny;
    private javax.swing.JLabel JLblBeskrivning;
    private javax.swing.JLabel JLblLID;
    private javax.swing.JLabel JLblMalNr;
    private javax.swing.JLabel JLblNamn;
    private javax.swing.JLabel JLblPrioritet;
    private javax.swing.JLabel JLblRubrik;
    private javax.swing.JPanel JPanelHållbarhetsmål;
    private javax.swing.JPanel JPanelProjektMotMal;
    private javax.swing.JTable JTableHallberhetsmal;
    private javax.swing.JTextField JTxtFieldBeskrivning;
    private javax.swing.JTextField JTxtFieldHID;
    private javax.swing.JTextField JTxtFieldMalNr;
    private javax.swing.JTextField JTxtFieldNamn;
    private javax.swing.JTextField JTxtFieldPrioritet;
    private javax.swing.JScrollPane jScrollPane1;
}