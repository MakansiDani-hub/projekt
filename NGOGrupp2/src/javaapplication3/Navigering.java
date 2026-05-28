/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

/**
 *
 * @author WDM
 */
public class Navigering {

    public static void tillbakaTillMeny(Anvandare anvandare) {

        String roll = anvandare.getRoll();

        if (roll.equalsIgnoreCase("admin")) {

            new AdministratörMeny(anvandare).setVisible(true);

        } 
        else if (roll.equalsIgnoreCase("handlaggare_projektchef")) {

            new MenyHandlaggareProjektchef(anvandare).setVisible(true);

        } 
        else if (roll.equalsIgnoreCase("handlaggare")) {

            new MenyHandlaggare(anvandare).setVisible(true);

        }
    }
}
