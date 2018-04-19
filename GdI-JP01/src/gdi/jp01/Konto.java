
/**
 * Klasse zum erstellen eines Kontos.
 *
 * @author Lars Isferding
 * @version 13.4.2018
 */
package gdi.jp01;
public class Konto {
    private int kontostand;
    private int kontoNr;
    private Inhaber inhaber;
    private int dispo;
    
    /**
     * Konstruktor zum eröffnen eines neuen Kontos
     * @param kontoNr Kontonummer angeben 
     * @param vorname Vornamen angeben 
     * @param nachname Nachnamen angeben 
     * @param adresse Adresse angeben
     */
    Konto(int kontoNr, String vorname, String nachname, String adresse) //Konto eröffnen
    {
        this.kontoNr = kontoNr;
        inhaber = new Inhaber(vorname,nachname,adresse);
    }
        
    
    /**
     * Konstruktor zum eröffnen eines neuen Kontos
     * @param kontoNr Kontonummer angeben 
     * @param vorname Vornamen angeben 
     * @param nachname Nachnamen angeben 
     * @param adresse Adresse angeben
     * @param einzahlung betrag zum einzahlen angeben
     */
    Konto(int kontoNr, String vorname, String nachname, String adresse, int einzahlung) //Konto eröffnen mit Einzahlung
    {
        this.kontoNr = kontoNr;
        this.kontostand = einzahlung;
        inhaber = new Inhaber(vorname,nachname,adresse);
    }
    
    
    /**
     * Konstruktor zum eröffnen eines neuen Kontos
     * @param kontoNr Kontonummer angeben 
     * @param vorname Vornamen angeben 
     * @param nachname Nachnamen angeben 
     * @param adresse Adresse angeben
     * @param empfaenger Empfaenger Konto angeben
     */
    Konto(int kontoNr, String vorname, String nachname, String adresse, Konto empfaenger) //Konto eroeffnen mit Freundschaftsbonus von 60€ fuer ein anderes Konto
    {
        this.kontoNr = kontoNr;
        empfaenger.einzahlen(60);
        inhaber = new Inhaber(vorname,nachname,adresse);
    }

    public static void main(String[] args) 
    {
    }

    /**
     * Funktion zum ausgeben des Kontostands
     * @return int kontostand Gibt den Kontostand aus
     */
    public int getKontostand() //Kontostand rausgeben
    {
        return kontostand;
    }

    /**
     * Funktion zum einzahlen eines Betrages.
     * @param int kontostand Kontostand um gewissen Betrag erhöhen
     */
    public void einzahlen(int kontostand) //Geld einzahlen
    {
        this.kontostand += kontostand;
    }

    /**
     * Funktion zum abheben eines Betrages unter Berücksichtigung des Dispos.
     * 
     * @param int abheben Betrag, welcher abgehoben werden soll.
     */
    public void abheben(int abheben) //Geld abheben
    {
        if((kontostand+dispo) >= (abheben))
            this.kontostand -= abheben;
        else
            System.out.println("Dispo nicht ausreichend");
    }
    
    /**
     * Funktion zum überweisen von A nach B
     * @param betrag Betrag welcher überwiesen werden soll
     * @param empfaenger Empfaenger Konto auswählen
     */
    public void ueberweisen(int betrag, Konto empfaenger) //Geld von A nach B ueberweisen
    {
        abheben(betrag);
        empfaenger.einzahlen(betrag);
    }
    
    /**
     * Funktion zum ausgeben der Kontonummer
     * @return int kontoNr Gibt die Kontonummer aus
     */
    public int getKontoNr() //KontoNr ausgeben
    {
        return kontoNr;
    }

    /**
     * Funktion zum setzen einer Kontonummer.
     * @param int kontoNr setzen der Kontonummer
     */
    public void setKontoNr(int kontoNr) //KontoNr Setzen
    {
        this.kontoNr = kontoNr;
    }

    /**
     * Funktion zum ausgeben des Inhabers.
     * @return inhaber Inhaber ausgeben
     */
    public Inhaber getInhaber() //Inhaber ausgeben (Konsole)
    {
        //System.out.println(inhaber.getVorname()+"\n"+inhaber.getNachname()+"\n"+inhaber.getAdresse());
        return inhaber;
    }
    
    /**
     *  Funktion zum Inanspruch nehmen eines Dispos
     *  @param int dispo setzen einer Dispogrenze
     */
    public void setDispo(int dispo)
    {
        this.dispo = dispo;
    }
   
    /**
     * Funktion zum ausgeben des Dispos
     * @return int dispo ausgeben der Dispogrenze
     */
   public int getDispo()
   {
        return dispo;    
   }
}