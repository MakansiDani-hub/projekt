/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication3;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.HashMap;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author alexander.willen
 */
public class ProjektHandlaggare extends javax.swing.JFrame {

    private final JFrame dennaFrame = this;

    //---Instanstyper som alternativ för skapandet av knappar---
    public enum Instanstyp {
        HALLBARHETSMAL,
        ADMIN,
        HANDLAGGARE,
        PARTNER
    }

    //---Session---
    private Anvandare anv; //databas + anvandare
    private int pid; //det valda projektet vars uppgifter visas

    public ProjektHandlaggare(Anvandare anv, Integer pid) {
         if(anv == null) {
            throw new NullPointerException("Användaren var null. Kan inte öppna projektfönstret");
        }
        if(pid == null) {
            System.out.println("Argument pid var null: inget projekt valt, projekt öppnas ej.");
            return;
        }
        
        this.anv = anv;
        this.pid = pid;

        initComponents();
        laddaInfo();
        startLageGUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spnlTop = new javax.swing.JScrollPane();
        pnlTop = new javax.swing.JPanel();
        lblPid = new javax.swing.JLabel();
        lblStartar = new javax.swing.JLabel();
        lblAvslutad = new javax.swing.JLabel();
        pnlVanster = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btnMalDropdown = new javax.swing.JButton();
        spnlMalDropdown = new javax.swing.JScrollPane();
        pnlMal = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        btnBeskrivningDropdown = new javax.swing.JButton();
        spnlBeskrivningDropdown = new javax.swing.JScrollPane();
        pnlBeskrivning = new javax.swing.JPanel();
        txarBeskrivning = new javax.swing.JTextArea();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnLand = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtfStatus = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtfPrioritet = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtfKostnad = new javax.swing.JTextField();
        pnlHoger = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        btnDeltagareDropdown = new javax.swing.JButton();
        spnlDeltagareDropdown = new javax.swing.JScrollPane();
        pnlDeltagare = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        pnlAdmin = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        pnlHandlaggare = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        pnlProjektchef = new javax.swing.JPanel();
        btnProjektchef = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        btnPartnersDropdown = new javax.swing.JButton();
        spnlPartnersDropdown = new javax.swing.JScrollPane();
        pnlPartners = new javax.swing.JPanel();
        pnlStartDatum = new javax.swing.JPanel();
        txtfStartDatum = new javax.swing.JTextField();
        pnlSlutDatum = new javax.swing.JPanel();
        txtfSlutDatum = new javax.swing.JTextField();
        pnlProjektnamn = new javax.swing.JPanel();
        txtfProjektnamn = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        spnlTop.setBorder(null);
        spnlTop.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spnlTop.setPreferredSize(new java.awt.Dimension(730, 577));

        pnlTop.setMaximumSize(new java.awt.Dimension(700, 2000));

        lblPid.setText("Projektid: [pid]");
        lblPid.setMaximumSize(new java.awt.Dimension(150, 16));

        lblStartar.setText("Startad");

        lblAvslutad.setText("Avslutad");

        pnlVanster.setMaximumSize(new java.awt.Dimension(385, 30000));
        pnlVanster.setMinimumSize(new java.awt.Dimension(200, 260));
        pnlVanster.setLayout(new javax.swing.BoxLayout(pnlVanster, javax.swing.BoxLayout.Y_AXIS));

        jPanel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel9.setMaximumSize(new java.awt.Dimension(310, 35));
        jPanel9.setPreferredSize(new java.awt.Dimension(250, 35));

        btnMalDropdown.setText("▶ Hållbarhetsmål");
        btnMalDropdown.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMalDropdown.setMaximumSize(new java.awt.Dimension(150, 23));
        btnMalDropdown.setMinimumSize(new java.awt.Dimension(86, 23));
        btnMalDropdown.setPreferredSize(new java.awt.Dimension(86, 23));
        btnMalDropdown.addActionListener(this::btnMalDropdownActionPerformed);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(btnMalDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 173, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMalDropdown, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        pnlVanster.add(jPanel9);

        spnlMalDropdown.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        spnlMalDropdown.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spnlMalDropdown.setMaximumSize(new java.awt.Dimension(310, 120));
        spnlMalDropdown.setMinimumSize(new java.awt.Dimension(310, 100));
        spnlMalDropdown.setPreferredSize(new java.awt.Dimension(310, 100));

        pnlMal.setMaximumSize(null);
        pnlMal.setMinimumSize(new java.awt.Dimension(200, 90));
        pnlMal.setOpaque(false);
        pnlMal.setPreferredSize(new java.awt.Dimension(200, 90));
        pnlMal.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        spnlMalDropdown.setViewportView(pnlMal);

        pnlVanster.add(spnlMalDropdown);

        jPanel13.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel13.setMaximumSize(new java.awt.Dimension(310, 38));
        jPanel13.setMinimumSize(new java.awt.Dimension(139, 38));
        jPanel13.setName(""); // NOI18N
        jPanel13.setPreferredSize(new java.awt.Dimension(250, 38));

        btnBeskrivningDropdown.setText("▶ Beskrivning");
        btnBeskrivningDropdown.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBeskrivningDropdown.setMargin(new java.awt.Insets(0, 0, 0, 14));
        btnBeskrivningDropdown.setMaximumSize(new java.awt.Dimension(160, 30));
        btnBeskrivningDropdown.setMinimumSize(new java.awt.Dimension(123, 30));
        btnBeskrivningDropdown.setPreferredSize(new java.awt.Dimension(125, 30));
        btnBeskrivningDropdown.setVerifyInputWhenFocusTarget(false);
        btnBeskrivningDropdown.addActionListener(this::btnBeskrivningDropdownActionPerformed);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(btnBeskrivningDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 185, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(btnBeskrivningDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlVanster.add(jPanel13);

        spnlBeskrivningDropdown.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spnlBeskrivningDropdown.setToolTipText("");
        spnlBeskrivningDropdown.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spnlBeskrivningDropdown.setMaximumSize(new java.awt.Dimension(32767, 60));
        spnlBeskrivningDropdown.setMinimumSize(new java.awt.Dimension(225, 60));
        spnlBeskrivningDropdown.setPreferredSize(new java.awt.Dimension(230, 60));

        pnlBeskrivning.setMinimumSize(new java.awt.Dimension(220, 100));
        pnlBeskrivning.setName(""); // NOI18N
        pnlBeskrivning.setLayout(new java.awt.BorderLayout());

        txarBeskrivning.setEditable(false);
        txarBeskrivning.setColumns(5);
        txarBeskrivning.setLineWrap(true);
        txarBeskrivning.setRows(5);
        txarBeskrivning.setWrapStyleWord(true);
        txarBeskrivning.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txarBeskrivning.setFocusable(false);
        txarBeskrivning.setMaximumSize(new java.awt.Dimension(200, 84));
        txarBeskrivning.setMinimumSize(new java.awt.Dimension(200, 20));
        pnlBeskrivning.add(txarBeskrivning, java.awt.BorderLayout.PAGE_START);

        spnlBeskrivningDropdown.setViewportView(pnlBeskrivning);

        pnlVanster.add(spnlBeskrivningDropdown);

        jPanel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 4, 0));
        jPanel10.setMaximumSize(new java.awt.Dimension(310, 32));
        jPanel10.setPreferredSize(new java.awt.Dimension(200, 36));

        jLabel12.setText("Land");

        btnLand.setText(" [Land]");
        btnLand.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        btnLand.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLand.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnLand.setMaximumSize(new java.awt.Dimension(180, 30));
        btnLand.setMinimumSize(new java.awt.Dimension(43, 30));
        btnLand.setPreferredSize(new java.awt.Dimension(50, 30));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(43, 43, 43)
                .addComponent(btnLand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap())
        );

        pnlVanster.add(jPanel10);

        jPanel12.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 4, 0));
        jPanel12.setMaximumSize(new java.awt.Dimension(310, 32));
        jPanel12.setPreferredSize(new java.awt.Dimension(200, 36));

        jLabel13.setText("Status");

        txtfStatus.setEditable(false);
        txtfStatus.setBackground(new java.awt.Color(238, 238, 238));
        txtfStatus.setText("[Status]");
        txtfStatus.setFocusable(false);
        txtfStatus.setMaximumSize(new java.awt.Dimension(160, 30));
        txtfStatus.setMinimumSize(new java.awt.Dimension(45, 30));
        txtfStatus.setPreferredSize(new java.awt.Dimension(60, 30));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addGap(36, 36, 36)
                .addComponent(txtfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlVanster.add(jPanel12);

        jPanel14.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 4, 0));
        jPanel14.setMaximumSize(new java.awt.Dimension(310, 32));
        jPanel14.setPreferredSize(new java.awt.Dimension(200, 36));

        jLabel15.setText("Prioritet");

        txtfPrioritet.setEditable(false);
        txtfPrioritet.setBackground(new java.awt.Color(238, 238, 238));
        txtfPrioritet.setText("[Prioritet]");
        txtfPrioritet.setFocusable(false);
        txtfPrioritet.setMaximumSize(new java.awt.Dimension(185, 30));
        txtfPrioritet.setMinimumSize(new java.awt.Dimension(45, 30));
        txtfPrioritet.setPreferredSize(new java.awt.Dimension(70, 30));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addGap(29, 29, 29)
                .addComponent(txtfPrioritet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtfPrioritet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlVanster.add(jPanel14);

        jPanel15.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 4, 0));
        jPanel15.setMaximumSize(new java.awt.Dimension(310, 32));
        jPanel15.setPreferredSize(new java.awt.Dimension(200, 36));

        jLabel17.setText("Kostnad");

        txtfKostnad.setEditable(false);
        txtfKostnad.setBackground(new java.awt.Color(238, 238, 238));
        txtfKostnad.setText("[Kostnad]");
        txtfKostnad.setFocusable(false);
        txtfKostnad.setMaximumSize(new java.awt.Dimension(200, 30));
        txtfKostnad.setMinimumSize(new java.awt.Dimension(64, 30));
        txtfKostnad.setPreferredSize(new java.awt.Dimension(70, 30));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(26, 26, 26)
                .addComponent(txtfKostnad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtfKostnad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlVanster.add(jPanel15);

        pnlHoger.setPreferredSize(new java.awt.Dimension(300, 475));
        pnlHoger.setLayout(new javax.swing.BoxLayout(pnlHoger, javax.swing.BoxLayout.Y_AXIS));

        jPanel8.setMaximumSize(new java.awt.Dimension(32767, 35));
        jPanel8.setPreferredSize(new java.awt.Dimension(456, 35));

        btnDeltagareDropdown.setText("▶ Deltagare");
        btnDeltagareDropdown.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDeltagareDropdown.setMaximumSize(new java.awt.Dimension(150, 27));
        btnDeltagareDropdown.setMinimumSize(new java.awt.Dimension(93, 27));
        btnDeltagareDropdown.setPreferredSize(new java.awt.Dimension(93, 30));
        btnDeltagareDropdown.addActionListener(this::btnDeltagareDropdownActionPerformed);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(btnDeltagareDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 283, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(btnDeltagareDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlHoger.add(jPanel8);

        spnlDeltagareDropdown.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        spnlDeltagareDropdown.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spnlDeltagareDropdown.setMaximumSize(new java.awt.Dimension(32767, 300));
        spnlDeltagareDropdown.setPreferredSize(new java.awt.Dimension(395, 300));

        pnlDeltagare.setMaximumSize(new java.awt.Dimension(350, 30000));
        pnlDeltagare.setMinimumSize(new java.awt.Dimension(350, 200));
        pnlDeltagare.setPreferredSize(new java.awt.Dimension(350, 200));

        jLabel7.setText("Projektchef");

        jLabel8.setText("Administratörer");

        pnlAdmin.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        pnlHandlaggare.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel9.setText("Handläggare");

        pnlProjektchef.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        btnProjektchef.setText("[Projektchef]");
        btnProjektchef.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnProjektchef.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnProjektchef.setMaximumSize(new java.awt.Dimension(180, 30));
        btnProjektchef.setMinimumSize(new java.awt.Dimension(43, 30));
        btnProjektchef.setPreferredSize(new java.awt.Dimension(80, 30));
        pnlProjektchef.add(btnProjektchef);

        javax.swing.GroupLayout pnlDeltagareLayout = new javax.swing.GroupLayout(pnlDeltagare);
        pnlDeltagare.setLayout(pnlDeltagareLayout);
        pnlDeltagareLayout.setHorizontalGroup(
            pnlDeltagareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDeltagareLayout.createSequentialGroup()
                .addComponent(pnlHandlaggare, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDeltagareLayout.createSequentialGroup()
                .addComponent(pnlAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDeltagareLayout.createSequentialGroup()
                .addGroup(pnlDeltagareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDeltagareLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlDeltagareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(14, 14, 14))
            .addGroup(pnlDeltagareLayout.createSequentialGroup()
                .addComponent(pnlProjektchef, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDeltagareLayout.setVerticalGroup(
            pnlDeltagareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDeltagareLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlProjektchef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlHandlaggare, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        spnlDeltagareDropdown.setViewportView(pnlDeltagare);

        pnlHoger.add(spnlDeltagareDropdown);

        jPanel11.setMaximumSize(new java.awt.Dimension(32767, 40));
        jPanel11.setPreferredSize(new java.awt.Dimension(456, 40));

        btnPartnersDropdown.setText("▶ Partners");
        btnPartnersDropdown.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPartnersDropdown.setMaximumSize(new java.awt.Dimension(150, 23));
        btnPartnersDropdown.setVerifyInputWhenFocusTarget(false);
        btnPartnersDropdown.addActionListener(this::btnPartnersDropdownActionPerformed);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(btnPartnersDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 290, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(btnPartnersDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlHoger.add(jPanel11);

        spnlPartnersDropdown.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        spnlPartnersDropdown.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spnlPartnersDropdown.setMinimumSize(new java.awt.Dimension(16, 100));
        spnlPartnersDropdown.setPreferredSize(new java.awt.Dimension(375, 100));

        pnlPartners.setMaximumSize(null);
        pnlPartners.setMinimumSize(new java.awt.Dimension(350, 60));
        pnlPartners.setPreferredSize(new java.awt.Dimension(350, 400));
        pnlPartners.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        spnlPartnersDropdown.setViewportView(pnlPartners);

        pnlHoger.add(spnlPartnersDropdown);

        pnlStartDatum.setMinimumSize(new java.awt.Dimension(20, 20));
        pnlStartDatum.setPreferredSize(new java.awt.Dimension(20, 22));
        pnlStartDatum.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtfStartDatum.setEditable(false);
        txtfStartDatum.setBackground(new java.awt.Color(238, 238, 238));
        txtfStartDatum.setText("ÅÅÅÅ-MM-DD");
        txtfStartDatum.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 3, 0, 0));
        txtfStartDatum.setFocusable(false);
        txtfStartDatum.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtfStartDatum.setPreferredSize(new java.awt.Dimension(86, 16));
        pnlStartDatum.add(txtfStartDatum, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 30));

        pnlSlutDatum.setMinimumSize(new java.awt.Dimension(20, 20));
        pnlSlutDatum.setPreferredSize(new java.awt.Dimension(20, 22));
        pnlSlutDatum.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtfSlutDatum.setEditable(false);
        txtfSlutDatum.setBackground(new java.awt.Color(238, 238, 238));
        txtfSlutDatum.setText("ÅÅÅÅ-MM-DD");
        txtfSlutDatum.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 3, 0, 0));
        txtfSlutDatum.setFocusable(false);
        txtfSlutDatum.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtfSlutDatum.setPreferredSize(new java.awt.Dimension(86, 16));
        pnlSlutDatum.add(txtfSlutDatum, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 30));

        txtfProjektnamn.setEditable(false);
        txtfProjektnamn.setBackground(new java.awt.Color(238, 238, 238));
        txtfProjektnamn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtfProjektnamn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfProjektnamn.setText("[Projektnamn]");
        txtfProjektnamn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtfProjektnamn.setFocusable(false);
        txtfProjektnamn.setMaximumSize(new java.awt.Dimension(500, 25));
        txtfProjektnamn.setMinimumSize(new java.awt.Dimension(80, 25));
        txtfProjektnamn.setOpaque(true);
        txtfProjektnamn.setPreferredSize(new java.awt.Dimension(150, 25));
        pnlProjektnamn.add(txtfProjektnamn);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addComponent(pnlVanster, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(pnlHoger, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblPid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTopLayout.createSequentialGroup()
                                .addGap(313, 313, 313)
                                .addComponent(lblStartar)
                                .addGap(17, 17, 17))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblAvslutad)
                                .addGap(18, 18, 18)))
                        .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlSlutDatum, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                            .addComponent(pnlStartDatum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(pnlProjektnamn, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(pnlProjektnamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(pnlStartDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlSlutDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblStartar)
                        .addGap(16, 16, 16)
                        .addComponent(lblAvslutad)
                        .addGap(18, 18, 18)))
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHoger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlVanster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        spnlTop.setViewportView(pnlTop);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(spnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(spnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startLageGUI() {
        //Gömmer pop-up paneler
        spnlMalDropdown.setVisible(false);
        spnlBeskrivningDropdown.setVisible(false);
        spnlDeltagareDropdown.setVisible(false);
        spnlPartnersDropdown.setVisible(false);
    }

    private void laddaInfo() {
        InfDB idb = anv.getIdb();

        try {
            //---Hämtar projektinfo---
            HashMap<String, String> projektinfoEnskilda = idb.fetchRow(
                    "SELECT projektnamn, startdatum, slutdatum, Projekt.beskrivning, "
                    + "status, Projekt.prioritet, kostnad, Land.namn, lid, "
                    + "CONCAT(fornamn, ' ', efternamn) as chefnamn, projektchef "
                    + "FROM Projekt "
                    + "LEFT JOIN Land ON land = lid "
                    + "LEFT JOIN Anstalld ON projektchef = aid "
                    + "WHERE pid = " + pid);

            ArrayList<HashMap<String, String>> projektinfoMal = idb.fetchRows(
                    "SELECT malnummer, namn, h.hid as id "
                    + "FROM Hallbarhetsmal h "
                    + "JOIN Proj_Hallbarhet ph on h.hid = ph.hid "
                    + "WHERE ph.pid = " + pid);

            ArrayList<HashMap<String, String>> projektinfoAdmin = idb.fetchRows(
                    "SELECT CONCAT(fornamn, ' ', efternamn) as namn, a.aid as id "
                    + "FROM Anstalld a "
                    + "JOIN Admin ad on a.aid = ad.aid "
                    + "JOIN Ans_Proj ap on a.aid = ap.aid "
                    + "WHERE ap.pid = " + pid);

            ArrayList<HashMap<String, String>> projektinfoHandlaggare = idb.fetchRows(
                    "SELECT CONCAT(fornamn, ' ', efternamn) as namn, a.aid as id "
                    + "FROM Anstalld a "
                    + "JOIN Handlaggare h on a.aid = h.aid "
                    + "JOIN Ans_Proj ap on a.aid = ap.aid "
                    + "WHERE ap.pid = " + pid);

            ArrayList<HashMap<String, String>> projektinfoPartners = idb.fetchRows(
                    "SELECT namn, p.pid as id "
                    + "FROM Partner p "
                    + "JOIN Projekt_Partner pp on p.pid = pp.partner_pid "
                    + "WHERE pp.pid = " + pid);

            //---Visar hämtad projektInfo---
            //...ändrar text
            lblPid.setText("Projektid: " + pid);
            txtfProjektnamn.setText(projektinfoEnskilda.get("projektnamn"));
            txtfStartDatum.setText(projektinfoEnskilda.get("startdatum"));
            txtfSlutDatum.setText(projektinfoEnskilda.get("slutdatum"));
            txarBeskrivning.setText(projektinfoEnskilda.get("beskrivning"));
            txtfStatus.setText(projektinfoEnskilda.get("status"));
            txtfPrioritet.setText(projektinfoEnskilda.get("prioritet"));
            txtfKostnad.setText(projektinfoEnskilda.get("kostnad"));
            btnProjektchef.setText(projektinfoEnskilda.get("chefnamn"));
            btnLand.setText(projektinfoEnskilda.get("namn"));

            //---Skapar "instansknappar"---
            //...Hållbarhetsmålen
            skapaInstansknappar(projektinfoMal, Instanstyp.HALLBARHETSMAL);
            //...Admin
            skapaInstansknappar(projektinfoAdmin, Instanstyp.ADMIN);
            //...Handläggare
            skapaInstansknappar(projektinfoHandlaggare, Instanstyp.HANDLAGGARE);
            //...Partners
            skapaInstansknappar(projektinfoPartners, Instanstyp.PARTNER);

            //Skapar actions för projektchef och land knapparna
            skapaActionBtnLand(projektinfoEnskilda.get("lid")); //skickar in land-id
            skapaActionBtnProjektchef(projektinfoEnskilda.get("projektchef")); //skickar in aid för projektchefen

        } catch (InfException e) {
            System.out.println("Info kunde ej laddas. " + e.getMessage());
        }

        pnlTop.revalidate();
    }

    /**
     * "Instansknappar" är knappar användaren trycker på för att ta sig vidare
     * till ett nytt fönster som visar information om saken knappen
     * representerade. Knappar kan vara hållbarhetsmål, deltagare och partners.
     * Denna metod skapar en viss typ av instansknapp, som definieras via
     * parametern instanstyp. En knapp är en "instans" av typ
     * HashMap<String, String>. Genom argumentet för instans kommer information
     * (namn, id) om instansen in. Alla instansers HashMap behöver ha keys "id"
     * och "namn" med mappade värden. Just hållbarhetsmål är ett specialfall. Om
     * dess HashMap även innehåller key "målnummer" så tas även detta med i
     * knappens text. Vid tryckning av en knapp tas vi till information om dess
     * instans i ett nytt fönster. Tex i ett fönster om hållbarhetsmål med id
     * "5".
     */
    private void skapaInstansknapp(HashMap<String, String> instans, Instanstyp instanstyp) {
        //Gör om nyckelidentifieraren i HashMappen till "id" eftersom alias ("as...") inte fungerade i SQL-frågan...
        for (String key : instans.keySet()) {
            if (key.matches("^[aph]id$")) {
                String varde = instans.remove(key);
                instans.put("id", varde);
                break;
            }
        }
        //---Endast dessa attributer sparas per instans---
        //...namm används som text på knappen
        String namn = instans.get("namn");
        //...id skickas vidare vid knapptryckning till nya fönstret
        String id = instans.get("id");

        JButton btnInstans = new JButton(namn);
        btnInstans.setMinimumSize(new Dimension(70, 23));
        btnInstans.setMaximumSize(new Dimension(200, 23));

        switch (instanstyp) {
            case HALLBARHETSMAL -> {
                //Lägger till malnummer i knappens text om det finns
                if (instans.containsKey("malnummer")) {
                    btnInstans.setText(btnInstans.getText() + " [" + instans.get("malnummer") + "]");
                }
                //Lägger till metod vid knapp-tryck (genom klassen ActionListener)
                btnInstans.addActionListener(new ActionListener() { //<--implementerar ActionListener interface i ny instans av anonymklass
                    @Override //override av abstrakt metod
                    public void actionPerformed(ActionEvent e) {
                        oppnaPopupVisaMalMedId(id);
                    }
                });
                pnlMal.add(btnInstans); //lägger knappen sist i panelen
            }
            case ADMIN, HANDLAGGARE -> {
                btnInstans.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        oppnaPopupVisaAnstalldMedId(id);
                    }
                });
                if (instanstyp == Instanstyp.ADMIN) {
                    pnlAdmin.add(btnInstans);
                }
                if (instanstyp == Instanstyp.HANDLAGGARE) {
                    pnlHandlaggare.add(btnInstans);
                }
            }
            case PARTNER -> {
                btnInstans.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        oppnaPopupVisaPartnerMedId(id);
                    }
                });
                pnlPartners.add(btnInstans);
            }
            default ->
                throw new IllegalStateException();
        }
    }

    private void skapaInstansknappar(ArrayList<HashMap<String, String>> instanser, Instanstyp instanstyp) {
        for (HashMap<String, String> instans : instanser) {
            skapaInstansknapp(instans, instanstyp);
        }
    }

    private void skapaActionBtnLand(String landId) {
        btnLand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oppnaPopupVisaLandMedId(landId);
            }
        });
    }

    private void skapaActionBtnProjektchef(String projektchefId) {
        btnProjektchef.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oppnaPopupVisaAnstalldMedId(projektchefId);
            }
        });
    }

    private void vaxlaDropdownLage(JScrollPane dropdownRuta, JButton dropdownKnapp) {
        Boolean rutaNySynlighet = !dropdownRuta.isVisible();

        String pil = rutaNySynlighet ? "▼" : "▶"; //Blir rutan synlig --> pil = ▼. Blir rutan osynlig --> pil = ▶
        dropdownKnapp.setText(pil + dropdownKnapp.getText().substring(1)); //sätter texten som pil + nuvarande texten minus pilen som redan fanns
        dropdownRuta.setVisible(rutaNySynlighet);
        //Uppdaterar layout
        dropdownRuta.getParent().revalidate();
    }

    private void oppnaPopupVisaMalMedId(String id) {
        //Öppnar ett fönster som visar en lista på mål med detta mål i fokus
        HanteraHållbarhetsmål malFonster = new HanteraHållbarhetsmål(anv);
        malFonster.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        malFonster.setVisible(true);
        malFonster.valjRad(id);
    }

    private void oppnaPopupVisaAnstalldMedId(String id) {
        //Öppnar ett fönster som visar en lista på anstallda med denna projektchef i fokus
        HanteraAnstalld anstalldFonster = new HanteraAnstalld(anv);
        anstalldFonster.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        anstalldFonster.setVisible(true);
        anstalldFonster.valjRad(id);
    }

    private void oppnaPopupVisaLandMedId(String id) {
        HanteraLand landFonster = new HanteraLand(anv);
        landFonster.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        landFonster.setVisible(true);
        landFonster.valjRad(id);
    }

    private void oppnaPopupVisaPartnerMedId(String id) {
        PartnerAdmin partnerFonster = new PartnerAdmin(anv);
        partnerFonster.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        partnerFonster.setVisible(true);
        partnerFonster.valjRad(id);
    }

    private void btnDeltagareDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeltagareDropdownActionPerformed
        vaxlaDropdownLage(spnlDeltagareDropdown, btnDeltagareDropdown);
    }//GEN-LAST:event_btnDeltagareDropdownActionPerformed

    private void btnMalDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMalDropdownActionPerformed
        vaxlaDropdownLage(spnlMalDropdown, btnMalDropdown);
    }//GEN-LAST:event_btnMalDropdownActionPerformed

    private void btnPartnersDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPartnersDropdownActionPerformed
        vaxlaDropdownLage(spnlPartnersDropdown, btnPartnersDropdown);
    }//GEN-LAST:event_btnPartnersDropdownActionPerformed

    private void btnBeskrivningDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBeskrivningDropdownActionPerformed
        vaxlaDropdownLage(spnlBeskrivningDropdown, btnBeskrivningDropdown);
    }//GEN-LAST:event_btnBeskrivningDropdownActionPerformed

//    public static void main(String args[]) { //TA BORT MAIN METODEN TILLSLUT. NI SKA ENDAST ANVÄNDA MAIN METODEN I Startklassen
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
//        }
//        //</editor-fold>
//
//        try {
//            InfDB idb = new InfDB("sdgsweden", "3306", "root", "masterkey");
//            new ProjektHandlaggare(new Anvandare(idb, null, null, null, 3, null, null, null, "handlaggare"), 3).setVisible(true);
//            System.out.println("Databaskoppling skapad");
//        } catch (InfException e) {
//            System.out.println(e.getMessage());
//        }
//    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBeskrivningDropdown;
    private javax.swing.JButton btnDeltagareDropdown;
    private javax.swing.JButton btnLand;
    private javax.swing.JButton btnMalDropdown;
    private javax.swing.JButton btnPartnersDropdown;
    private javax.swing.JButton btnProjektchef;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAvslutad;
    private javax.swing.JLabel lblPid;
    private javax.swing.JLabel lblStartar;
    private javax.swing.JPanel pnlAdmin;
    private javax.swing.JPanel pnlBeskrivning;
    private javax.swing.JPanel pnlDeltagare;
    private javax.swing.JPanel pnlHandlaggare;
    private javax.swing.JPanel pnlHoger;
    private javax.swing.JPanel pnlMal;
    private javax.swing.JPanel pnlPartners;
    private javax.swing.JPanel pnlProjektchef;
    private javax.swing.JPanel pnlProjektnamn;
    private javax.swing.JPanel pnlSlutDatum;
    private javax.swing.JPanel pnlStartDatum;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPanel pnlVanster;
    private javax.swing.JScrollPane spnlBeskrivningDropdown;
    private javax.swing.JScrollPane spnlDeltagareDropdown;
    private javax.swing.JScrollPane spnlMalDropdown;
    private javax.swing.JScrollPane spnlPartnersDropdown;
    private javax.swing.JScrollPane spnlTop;
    private javax.swing.JTextArea txarBeskrivning;
    private javax.swing.JTextField txtfKostnad;
    private javax.swing.JTextField txtfPrioritet;
    private javax.swing.JTextField txtfProjektnamn;
    private javax.swing.JTextField txtfSlutDatum;
    private javax.swing.JTextField txtfStartDatum;
    private javax.swing.JTextField txtfStatus;
    // End of variables declaration//GEN-END:variables
}
