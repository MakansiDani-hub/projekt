/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

import oru.inf.InfDB;

public class Anvandare {

    private InfDB idb;
    private String dbNamn;
    private String dbEfternamn;
    private String dbAnstallningsdatum;
    private int aid;
    private String dbAnvandaresAdress;
    private String dbAnvandaresTelefon;
    private String dbAnvandareslosenord;
    private String roll;

    // Constructor
    public Anvandare(
            InfDB idb,
            String dbNamn,
            String dbEfternamn,
            String dbAnstallningsdatum,
            int aid,
            String dbAnvandaresAdress,
            String dbAnvandaresTelefon,
            String dbAnvandareslosenord) {

        this.idb = idb;
        this.dbNamn = dbNamn;
        this.dbEfternamn = dbEfternamn;
        this.dbAnstallningsdatum = dbAnstallningsdatum;
        this.aid = aid;
        this.dbAnvandaresAdress = dbAnvandaresAdress;
        this.dbAnvandaresTelefon = dbAnvandaresTelefon;
        this.dbAnvandareslosenord = dbAnvandareslosenord;
    }

    // Get metoder

    public InfDB getIdb() {
        return idb;
    }

    public String getDbNamn() {
        return dbNamn;
    }

    public String getDbEfternamn() {
        return dbEfternamn;
    }

    public String getDbAnstallningsdatum() {
        return dbAnstallningsdatum;
    }

    public int getAid() {
        return aid;
    }

    public String getDbAnvandaresAdress() {
        return dbAnvandaresAdress;
    }

    public String getDbAnvandaresTelefon() {
        return dbAnvandaresTelefon;
    }

    public String getDbAnvandareslosenord() {
        return dbAnvandareslosenord;
    }

    // SET METHODS

    public void setDbAnvandaresAdress(String adress) {
        this.dbAnvandaresAdress = adress;
    }

    public void setDbAnvandaresTelefon(String telefon) {
        this.dbAnvandaresTelefon = telefon;
    }

    public void setDbAnvandareslosenord(String losenord) {
        this.dbAnvandareslosenord = losenord;
    }
}