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
public class Konto 
{
    /**
     * @param args the command line arguments
     */
    private int kontostand;
    private int kontoNr;
    private Inhaber inhaber;
    /**
     * Konstruktor zum eröffnen eines neuen Kontos
     * @param int kontoNr String vorname String nachname String adresse
     */
    Konto(int kontoNr, String vorname, String nachname, String adresse) //Konto eröffnen
    {
        this.kontoNr = kontoNr;
        inhaber = new Inhaber(vorname,nachname,adresse);
    }
        
    /**
     * Konstruktor zum eröffnen eines Kontos und direkter Einzahlung eines Betrages
     * @param int KontoNr String vorname String nachname String adresse int einzahlung
     */
    Konto(int kontoNr, String vorname, String nachname, String adresse, int einzahlung) //Konto eröffnen mit Einzahlung
    {
        this.kontoNr = kontoNr;
        this.kontostand = einzahlung;
        inhaber = new Inhaber(vorname,nachname,adresse);
    }
    /**
     * Konstruktor zum eröffnen eines Kontos und Freundschaftspauschale für ein anderes Konto
     * @param int kontoNr String vorname String nachname String adresse Konto empfaenger
     */
    Konto(int kontoNr, String vorname, String nachname, String adresse, Konto empfaenger) //Konto eroeffnen mit Freundschaftsbonus von 60€ fuer ein anderes Konto
    {
        this.kontoNr = kontoNr;
        empfaenger.einzahlen(60);
        inhaber = new Inhaber(vorname,nachname,adresse);
    }
    public static void main(String[] args) {}
    /**
     * @return    Gibt den Kontostand aus
     */
    public int getKontostand() //Kontostand rausgeben
    {
        return kontostand;
    }
    /**
     * Funktion zum einzahlen eines Betrages.
     * @param int kontostand
     */
    public void einzahlen(int kontostand) //Geld einzahlen
    {
        this.kontostand += kontostand;
    }
    /**
     * Funktion zum abheben eines Betrages.
     * @param int kontostand
     */
    public void abheben(int kontostand) //Geld abheben
    {
        this.kontostand -= kontostand;
    }
    /**
     * Funktion zum überweisen von A nach B
     * @param int betrag, Konto empfaenger
     */
    public void ueberweisen(int betrag, Konto empfaenger) //Geld von A nach B ueberweisen
    {
        abheben(betrag);
        empfaenger.einzahlen(betrag);
    }
    /**
     * @return Gibt die Kontonummer aus
     */
    public int getKontoNr() //KontoNr ausgeben
    {
        return kontoNr;
    }
    /**
     * Funktion zum setzen einer Kontonummer.
     * @param int kontoNr
     */
    public void setKontoNr(int kontoNr) //KontoNr Setzen
    {
        this.kontoNr = kontoNr;
    }
    /**
     * Funktion zum ausgeben des Inhabers.
     * @return inhaber
     */
    public Inhaber getInhaber() //Inhaber ausgeben (Konsole)
    {
        //System.out.println(inhaber.getVorname()+"\n"+inhaber.getNachname()+"\n"+inhaber.getAdresse());
        return inhaber;
    }
}