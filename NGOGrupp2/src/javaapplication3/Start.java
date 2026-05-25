/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;

/**
 *
 * @author alexander.willen
 */
public class Start {

    private static InfDB db;

    public static void main(String[] args) {
        try {
            db = new InfDB("SDGSweden", "3306", "dbAdmin2024", "dbAdmin2024PW");
            System.out.println("InfDB instansierades korrekt");
            
            //Testar SQL-fråga, ta bort när klart
            ArrayList<String> kostnader = db.fetchColumn("SELECT kostnad FROM projekt WHERE prioritet = 'Hög'");
            for(int i = 0; i < kostnader.size(); i++){
                System.out.println("kostnad " + i + ": " + kostnader.get(i));
            }
            
        } catch (InfException e) {
            System.out.println("Fel vid instansiering av InfDB\n"
                               +e.getMessage());
        }
    }
}
