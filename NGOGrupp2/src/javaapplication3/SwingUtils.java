/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

import javax.swing.JComboBox;
import javax.swing.JTable;

public final class SwingUtils {
    //Gör SwingUtils ej-instansierbar
    private SwingUtils(){}
    
    /**
     * Kollar om en item finns i en JComboBox
     *
     * @param <T> typen av elementen i JComboBoxen
     * @param comboBox JComboBoxen som håller 1-N items av typ T
     * @param itemAttProva item som vi kollar om finns i comboBox.
     * @return true om item finns i denna comboBox, false om inte. Om item är null
     * returneras alltid false.
     *
     * Kan exempelvis kontrollera en item av typ String.
     * @author alexander.willen
     */
    public static <T> boolean finnsIComboBox(JComboBox<T> comboBox, T itemAttProva) { //generisk typ T ser till att båda parametrar har samma typ T, tex String
        //Validerar argument
        if (comboBox == null) {
            System.out.println("Argumentet för JComboBox<O> cb saknade referens - cb var null");
            return false;
        }
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            T cbItem = comboBox.getItemAt(i);
            if (cbItem != null && cbItem.equals(itemAttProva)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Gör en rad selected i en JTable med en viss id i en angiven kolumn.
     * Returnerar raden som valdes utifrån given id.
     */
    public static int valjRadIJTableMedId(JTable lista, String id, int idKolumn) {

        if (id == null) {
            throw new NullPointerException("parameter id var null");
        }
        for (int rad = 0; rad < lista.getRowCount(); rad++) {
            String radId = (String) lista.getValueAt(rad, idKolumn);
            if (id.equals(radId)) {
                //Sätter raden som selected
                lista.setRowSelectionInterval(rad, rad);
                //Scrollar till där raden är
                lista.scrollRectToVisible(lista.getCellRect(rad, 0, true));
                return rad;
            }
        }
        //Då alla rader letats igenom utan att hitta matchande id
        throw new IllegalStateException("Id för rad hittades inte i JTable-listan när det bör finnas");
    }
}
