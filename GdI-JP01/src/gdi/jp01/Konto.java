/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp01;
/**
 * Write a description of class Konto here.
 *
 * @author Lars Isferding
 * @version 13.4.2018
 */
public class Konto {

    /**
     * @param args the command line arguments
     */
    private int kontostand;
    private int kontoNr;
    private Inhaber inhaber;
    Konto(int kontoNr, String vorname, String nachname, String adresse) //Konto eröffnen
    {
        this.kontoNr = kontoNr;
        inhaber = new Inhaber(vorname,nachname,adresse);
    }
        
    Konto(int kontoNr, String vorname, String nachname, String adresse, int einzahlung) //Konto eröffnen mit Einzahlung
    {
        this.kontoNr = kontoNr;
        this.kontostand = einzahlung;
        inhaber = new Inhaber(vorname,nachname,adresse);
    }
        Konto(int kontoNr, String vorname, String nachname, String adresse, Konto empfaenger) //Konto eroeffnen mit Freundschaftsbonus von 60€ fuer ein anderes Konto
    {
        this.kontoNr = kontoNr;
        empfaenger.einzahlen(60);
        inhaber = new Inhaber(vorname,nachname,adresse);
    }

    public static void main(String[] args) 
    {
    }

    public int getKontostand() //Kontostand rausgeben
    {
        return kontostand;
    }

    public void einzahlen(int kontostand) //Geld einzahlen
    {
        this.kontostand += kontostand;
    }

    public void abheben(int kontostand) //Geld abheben
    {
        this.kontostand -= kontostand;
    }

    public void ueberweisen(int betrag, Konto empfaenger) //Geld von A nach B ueberweisen
    {
        abheben(betrag);
        empfaenger.einzahlen(betrag);
    }
    public int getKontoNr() //KontoNr ausgeben
    {
        return kontoNr;
    }

    public void setKontoNr(int kontoNr) //KontoNr Setzen
    {
        this.kontoNr = kontoNr;
    }

    public void getInhaber() //Inhaber ausgeben (Konsole)
    {
        System.out.println(inhaber.getVorname()+"\n"+inhaber.getNachname()+"\n"+inhaber.getAdresse());
    }
}

