/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

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
     * för roll.equals "admin" gjorts.
     */
    public static boolean arAdmin(String roll){
        return "admin".equals(roll);
    }
}
