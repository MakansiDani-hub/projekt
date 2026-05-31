/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication3;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import oru.inf.InfDB;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import oru.inf.InfException;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class ProjektSokAdmin extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProjektSokAdmin.class.getName());

    private Anvandare anvandare;

    //Användarens söknings-inställningar
    private String valStatus;
    private Date valStartdatum;
    private Date valSlutdatum;

    public ProjektSokAdmin(Anvandare anvandare) {
        initComponents();
        this.anvandare = anvandare;
        laddaAllaProjekt();
        låsFält();

        tblProjektlista.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                fyllRutorFranValdRad();
            }
        });
    }

    private void setStatus(String status) {
        valStatus = status;
        //uppdatera UI för vald-status
        if (SwingUtils.finnsIComboBox(cbStatus, status)) {
            cbStatus.setSelectedItem(status);
        }
    }

    private void setStartdatum(Date datum) {
        valStartdatum = datum;
        //uppdatera UI för vald-status
        System.out.println(dateStartdatum);
        dateStartdatum.setDate(datum);
    }

    private void setSlutdatum(Date datum) {
        valSlutdatum = datum;
        //uppdatera UI för vald-status
        dateSlutdatum.setDate(datum);
    }

    private void laddaAllaProjekt() {
        try {
            String sql = "SELECT pid, projektnamn, beskrivning, startdatum, slutdatum, "
                    + "kostnad, status, prioritet, projektchef, land "
                    + "FROM projekt "
                    + "ORDER BY pid";

            ArrayList<HashMap<String, String>> projektLista = anvandare.getIdb().fetchRows(sql);

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
                    projekt.get("projektchef"),
                    projekt.get("land")
                });
            }

            tblProjektlista.setModel(model);

        } catch (InfException ex) {
            JOptionPane.showMessageDialog(this, "Kunde inte ladda projektlistan: " + ex.getMessage());
        }
    }

    private void fyllRutorFranValdRad() {
        int rad = tblProjektlista.getSelectedRow();

        if (rad == -1) {
            return;
        }

        jTextField2.setText(tblProjektlista.getValueAt(rad, 0).toString()); // PID
        jTextField3.setText(tblProjektlista.getValueAt(rad, 1).toString()); // Projektnamn
        jTextField4.setText(tblProjektlista.getValueAt(rad, 2).toString()); // Beskrivning
        jTextField5.setText(tblProjektlista.getValueAt(rad, 3).toString()); // Startdatum
        jTextField6.setText(tblProjektlista.getValueAt(rad, 4).toString()); // Slutdatum
        jTextField7.setText(tblProjektlista.getValueAt(rad, 5).toString()); // Kostnad
        cbProjektStatus.setSelectedItem(tblProjektlista.getValueAt(rad, 6).toString()); // Status
        cbProjektPrioritet.setSelectedItem(tblProjektlista.getValueAt(rad, 7).toString()); // Prioritet
        jTextField10.setText(tblProjektlista.getValueAt(rad, 8).toString()); // Projektchef
        jTextField11.setText(tblProjektlista.getValueAt(rad, 9).toString()); // Land
    }

    private void låsFält() {

        jTextField2.setEditable(false);
        jTextField3.setEditable(false);
        jTextField4.setEditable(false);
        jTextField5.setEditable(false);
        jTextField6.setEditable(false);
        jTextField7.setEditable(false);
        cbProjektStatus.setEnabled(false);
        cbProjektPrioritet.setEnabled(false);
        jTextField10.setEditable(false);
        jTextField11.setEditable(false);

        jTextField2.setBackground(java.awt.Color.LIGHT_GRAY);
        jTextField3.setBackground(java.awt.Color.LIGHT_GRAY);
        jTextField4.setBackground(java.awt.Color.LIGHT_GRAY);
        jTextField5.setBackground(java.awt.Color.LIGHT_GRAY);
        jTextField6.setBackground(java.awt.Color.LIGHT_GRAY);
        jTextField7.setBackground(java.awt.Color.LIGHT_GRAY);
        cbProjektStatus.setBackground(java.awt.Color.LIGHT_GRAY);
        cbProjektPrioritet.setBackground(java.awt.Color.LIGHT_GRAY);
        jTextField10.setBackground(java.awt.Color.LIGHT_GRAY);
        jTextField11.setBackground(java.awt.Color.LIGHT_GRAY);
    }

    private void låsUppFält() {

        jTextField2.setEditable(true);
        jTextField3.setEditable(true);
        jTextField4.setEditable(true);
        jTextField5.setEditable(true);
        jTextField6.setEditable(true);
        jTextField7.setEditable(true);
        cbProjektStatus.setEnabled(true);
        cbProjektPrioritet.setEnabled(true);
        jTextField10.setEditable(true);
        jTextField11.setEditable(true);

        jTextField2.setBackground(java.awt.Color.WHITE);
        jTextField3.setBackground(java.awt.Color.WHITE);
        jTextField4.setBackground(java.awt.Color.WHITE);
        jTextField5.setBackground(java.awt.Color.WHITE);
        jTextField6.setBackground(java.awt.Color.WHITE);
        jTextField7.setBackground(java.awt.Color.WHITE);
        cbProjektStatus.setBackground(java.awt.Color.WHITE);
        cbProjektPrioritet.setBackground(java.awt.Color.WHITE);
        jTextField10.setBackground(java.awt.Color.WHITE);
        jTextField11.setBackground(java.awt.Color.WHITE);
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

            String sql = "SELECT pid, projektnamn, beskrivning, startdatum, slutdatum, "
                    + "kostnad, status, prioritet, projektchef, land "
                    + "FROM projekt "
                    + "WHERE startdatum >= '" + start + "' "
                    + "AND slutdatum <= '" + slut + "' "
                    + "ORDER BY pid";

            ArrayList<HashMap<String, String>> projektLista = anvandare.getIdb().fetchRows(sql);

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
                    projekt.get("projektchef"),
                    projekt.get("land")
                });
            }

            tblProjektlista.setModel(model);

        } catch (InfException ex) {
            JOptionPane.showMessageDialog(this, "Kunde inte filtrera projekt: " + ex.getMessage());
        }
    }
    
    private void filtreraTabellPaStatus() {
        String valdStatus = cbStatus.getSelectedItem().toString();

        DefaultTableModel model = (DefaultTableModel) tblProjektlista.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);

        tblProjektlista.setRowSorter(sorter);

        if (valdStatus.equals("Ingen")) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("^" + valdStatus + "$", 6));
        }
    }
    
    private void rensaFalt() {
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        cbProjektStatus.setSelectedItem("Ingen");
        cbProjektPrioritet.setSelectedItem("Ingen");
        jTextField10.setText("");
        jTextField11.setText("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cbStatus = new javax.swing.JComboBox<>();
        dateSlutdatum = new com.toedter.calendar.JDateChooser();
        dateStartdatum = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProjektlista = new javax.swing.JTable();
        lblTillbakaTillMeny = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnTaBort = new javax.swing.JButton();
        btnLäggTill = new javax.swing.JButton();
        btnÄndra = new javax.swing.JButton();
        lblPID = new javax.swing.JLabel();
        lblProjektsnamn = new javax.swing.JLabel();
        lblBeskrivning = new javax.swing.JLabel();
        lblStartdatum = new javax.swing.JLabel();
        lblSlutdatum = new javax.swing.JLabel();
        lblKostnad = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        lblPrioritet = new javax.swing.JLabel();
        lblProjektchef = new javax.swing.JLabel();
        lblLand = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        cbProjektStatus = new javax.swing.JComboBox<>();
        cbProjektPrioritet = new javax.swing.JComboBox<>();
        btnRensa = new javax.swing.JButton();

        jDialog1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog1.setTitle("Hitta personal");
        jDialog1.setModal(true);
        jDialog1.setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "[namn]", "[namn]" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList2);

        jTextField1.setText("[Sökning]");
        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jLabel6.setForeground(new java.awt.Color(204, 0, 51));
        jLabel6.setText("[felmeddelande]");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 119, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(9, 9, 9)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Planerat", "Pågående", "Avslutat", "Ingen" }));
        cbStatus.addActionListener(this::cbStatusActionPerformed);

        jLabel3.setText("Startdatum");

        jLabel4.setText("Slutdatum");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Sök efter projekt");

        jButton1.setText("Sök 🔍");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        tblProjektlista.setBackground(new java.awt.Color(242, 242, 242));
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
        tblProjektlista.setFillsViewportHeight(true);
        jScrollPane2.setViewportView(tblProjektlista);

        lblTillbakaTillMeny.setText("Tillbaka till Meny");
        lblTillbakaTillMeny.addActionListener(this::lblTillbakaTillMenyActionPerformed);

        jLabel1.setText("Admin");

        btnTaBort.setText("Ta Bort");

        btnLäggTill.setText("Lägg Till");

        btnÄndra.setText("Ändra");
        btnÄndra.addActionListener(this::btnÄndraActionPerformed);

        lblPID.setText("PID");

        lblProjektsnamn.setText("Projektsnamn");

        lblBeskrivning.setText("Beskrivning");

        lblStartdatum.setText("Startdatum");

        lblSlutdatum.setText("Slutdatum");

        lblKostnad.setText("Kostnad");

        jTextField7.addActionListener(this::jTextField7ActionPerformed);

        jButton2.setText("Spara");

        lblStatus.setText("Status");

        lblPrioritet.setText("Prioritet");

        lblProjektchef.setText("Projektchef");

        lblLand.setText("Land");

        cbProjektStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Planerat", "Pågående", "Avslutat","Ingen"}));

        cbProjektPrioritet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hög", "Medel", "Låg","Ingen"}));

        btnRensa.setText("Rensa");
        btnRensa.addActionListener(this::btnRensaActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTillbakaTillMeny)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateStartdatum, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateSlutdatum, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLäggTill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTaBort, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnÄndra, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnRensa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblKostnad)
                            .addComponent(lblPID)
                            .addComponent(lblProjektsnamn)
                            .addComponent(lblBeskrivning)
                            .addComponent(lblStartdatum)
                            .addComponent(lblStatus)
                            .addComponent(lblPrioritet)
                            .addComponent(lblProjektchef)
                            .addComponent(lblLand)
                            .addComponent(lblSlutdatum))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbProjektPrioritet, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField10)
                            .addComponent(cbProjektStatus, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField7)
                            .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTillbakaTillMeny)
                            .addComponent(jLabel1))
                        .addGap(26, 26, 26)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateStartdatum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateSlutdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPID)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProjektsnamn)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBeskrivning)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStartdatum)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSlutdatum)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKostnad)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStatus)
                            .addComponent(cbProjektStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPrioritet)
                            .addComponent(cbProjektPrioritet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProjektchef)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLand)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnTaBort)
                                    .addComponent(jButton2)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnLäggTill)
                                .addComponent(btnÄndra)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRensa))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnTaBort.getAccessibleContext().setAccessibleName("");
        btnLäggTill.getAccessibleContext().setAccessibleName("");
        btnÄndra.getAccessibleContext().setAccessibleName("");
        jButton2.getAccessibleContext().setAccessibleName("");
        cbProjektStatus.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusActionPerformed
        filtreraTabellPaStatus();
    }//GEN-LAST:event_cbStatusActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void lblTillbakaTillMenyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblTillbakaTillMenyActionPerformed
        Navigering.tillbakaTillMeny(anvandare);
        this.dispose();
    }//GEN-LAST:event_lblTillbakaTillMenyActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        filtreraProjektPaDatum();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnÄndraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnÄndraActionPerformed
        låsUppFält();
    }//GEN-LAST:event_btnÄndraActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void btnRensaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRensaActionPerformed
        rensaFalt();
    }//GEN-LAST:event_btnRensaActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
//            logger.log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLäggTill;
    private javax.swing.JButton btnRensa;
    private javax.swing.JButton btnTaBort;
    private javax.swing.JButton btnÄndra;
    private javax.swing.JComboBox<String> cbProjektPrioritet;
    private javax.swing.JComboBox<String> cbProjektStatus;
    private javax.swing.JComboBox<String> cbStatus;
    private com.toedter.calendar.JDateChooser dateSlutdatum;
    private com.toedter.calendar.JDateChooser dateStartdatum;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel lblBeskrivning;
    private javax.swing.JLabel lblKostnad;
    private javax.swing.JLabel lblLand;
    private javax.swing.JLabel lblPID;
    private javax.swing.JLabel lblPrioritet;
    private javax.swing.JLabel lblProjektchef;
    private javax.swing.JLabel lblProjektsnamn;
    private javax.swing.JLabel lblSlutdatum;
    private javax.swing.JLabel lblStartdatum;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JButton lblTillbakaTillMeny;
    private javax.swing.JTable tblProjektlista;
    // End of variables declaration//GEN-END:variables
}
