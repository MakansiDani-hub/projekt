/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;


import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 *
 * @author alexander.willen
 */
public final class ValideringInput {
    private ValideringInput(){}
    
    /**
     * Kontrollerar om rollen är handläggare eller ej
     * Fördelen med metoden är att den samlar en "single source of truth"
     * för vad värdet av rollen ska vara för att det ska vara en 
     * handläggare. I detta fall antingen "handlaggare" eller
     * "handlaggare_projektchef"
     */
    public static boolean arHandlaggare(String roll){
        return "handlaggare".equals(roll) || "handlaggare_projektchef".equals(roll);
    }
    /**
     * Kontrollerar om rollen är admin eller ej.
     * Implementera gärna denna metod där egen check
     * för roll.equals"admin" gjorts.
     */
    public static boolean arAdmin(String roll){
        return "admin".equals(roll);
    }
    
    
    // kontroll om ett fält är tomt
    public static boolean harVarde(JTextField fält, String fältNamn) {
        if (fält.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, fältNamn + " måste fyllas i!");
            fält.requestFocus(); // Sätter markören i det tomma fältet
            return false;
        }
        return true;
    }

    // Kontroll så fältet innehåller ett heltal
    public static boolean arHeltal(JTextField fält, String fältNamn) {
        try {
            Integer.parseInt(fält.getText().trim());
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, fältNamn + " måste vara ett heltal!");
            fält.requestFocus();
            return false;
        }
    }

    //e-postvalidering för epost fönster.
    public static boolean isGiltigEpost(JTextField fält) {
        String epost = fält.getText().trim();
        if (!epost.contains("@") || !epost.contains(".")) {
            JOptionPane.showMessageDialog(null, "Ange en giltig e-postadress!");
            fält.requestFocus();
            return false;
        }
        return true;
    }
}
