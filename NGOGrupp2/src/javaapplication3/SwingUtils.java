/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

import javax.swing.JComboBox;

public final class SwingUtils {
    private SwingUtils(){}
    
    /**
     * Kollar om en item finns i en JComboBox
     *
     * @param <T> typen av elementen i JComboBoxen
     * @param cb JComboBoxen som håller 1-N items av typ T
     * @param item item som vi kollar om finns i cb.
     * @return true om item finns i denna cb, false om inte. Om item är null
     * returneras alltid false.
     *
     * Kan exempelvis kontrollera en item av typ String.
     * @author alexander.willen
     */
    public static <T> boolean arIComboBox(JComboBox<T> cb, T item) { //generisk typ T ser till att båda parametrar har samma typ T, tex String
        //Validerar argument
        if (cb == null) {
            System.out.println("Argumentet för JComboBox<O> cb saknade referens - cb var null");
            return false;
        }
        for (int i = 0; i < cb.getItemCount(); i++) {
            T cbItem = cb.getItemAt(i);
            if (cbItem != null && cbItem.equals(item)) {
                return true;
            }
        }
        return false;
    }
}
