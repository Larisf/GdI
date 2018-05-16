package jp02;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 * Klasse zum erstellen eines Kontos.
 * @author Lars Isferding
 * @version 27.4.2018
 */
public class Konto 
{
    private static int anzKonten = 1;
    protected double kontostand;
	private double abbuchung, transGebühr;
    private int kontoNr, dispo;
	protected KontoTyp kontoTyp;
    private Inhaber inhaber;
	private Calendar calendar = Calendar.getInstance();
	private ArrayList<Kontoauszug> kontoauszugListe = new ArrayList();
	
	/**
     * Konstruktor zum eröffnen eines neuen Kontos
     * @param vorname Vornamen angeben 
     * @param nachname Nachnamen angeben 
     * @param adresse Adresse angeben
     */
    Konto(String vorname, String nachname, String adresse) //Konto eröffnen
    {
        kontoNr = anzKonten++;
		this.kontoTyp = KontoTyp.GIROKONTO;
		inhaber = new Inhaber(vorname,nachname,adresse);
	}
    
	/**
     * Konstruktor zum eröffnen eines neuen Kontos
     * @param vorname Vornamen angeben 
     * @param nachname Nachnamen angeben 
     * @param adresse Adresse angeben
     * @param einzahlung betrag zum einzahlen angeben
     */
    Konto(String vorname, String nachname, String adresse, int einzahlung,int jahr, int monat, int tag) //Konto eröffnen mit Einzahlung
    {
        kontoNr = anzKonten++;
		this.kontostand = einzahlung;
		this.kontoTyp = KontoTyp.GIROKONTO;
        inhaber = new Inhaber(vorname,nachname,adresse);
    }   
    
	/**
     * Konstruktor zum eröffnen eines neuen Kontos
     * @param vorname Vornamen angeben 
     * @param nachname Nachnamen angeben 
     * @param adresse Adresse angeben
     * @param empfaenger Empfaenger Konto angeben
     */
    Konto(String vorname, String nachname, String adresse, Konto empfaenger,int jahr, int monat, int tag) //Konto eroeffnen mit Freundschaftsbonus von 60€ fuer ein anderes Konto
    {
		kontoNr = anzKonten++;
		empfaenger.einzahlen(60,jahr,monat,tag);
		this.kontoTyp = KontoTyp.GIROKONTO;
        inhaber = new Inhaber(vorname,nachname,adresse);
    }
	   public static void main(String args[]) 
    {
		Konto k = new Konto("lars", "isferding", "vissingkamp", 100,2018,4,10);
		Sparkonto k1 = new Sparkonto("Gustav", "Gans", "Entenhausen",100,0,2018,4,15);
		k.einzahlen(10, 2018, 4, 20);
		k.abheben(9, 2018, 4, 4);
		k.ueberweisen(8, k1, 2017, 12, 24);
		k.einzahlen(7, 2018, 4, 28);
		k1.setZinssatz(6);
		k1.zinszahlung();
		k1.einzahlen(5, 2018, 1, 30);
		k1.ueberweisen(4, k, 2018, 2, 28);
		
		k1.getKontoauszug(2018, 5, 3);
		k.getKontoauszug(2018, 5, 3);
		
    }	
	/**
     * Funktion zum einzahlen eines Betrages.
     * @param einzahlung Kontostand um gewissen Betrag erhöhen
	 * @param jahr Jahreszahl der Einzahlung
	 * @param monat Monat der Einzahlung
	 * @param tag Tag der Einzahlung
     */
    public void einzahlen(double einzahlung,int jahr, int monat, int tag) //Geld einzahlen
    {
		this.kontostand += (einzahlung+transGebühr);
		kontoauszugListe.add(new Kontoauszug(new Kalender(tag,monat,jahr),Kontobewegung.EINZAHLUNG,einzahlung));
	}
	
	/**
     * Funktion zum abbuchung eines Betrages unter Berücksichtigung des Dispos.
     * Sparkonten werden berücksichtigt
     * @param abbuchung Betrag, welcher abgehoben werden soll.
	 * @param jahr Jahreszahl der Abbuchung
	 * @param monat der Abbuchung
	 * @param tag  der Abbuchung
     */
    public void abheben(int abbuchung,int jahr, int monat, int tag) //Geld abbuchung
    {
        if((kontostand+dispo) >= (abbuchung))
		{
			this.abbuchung = abbuchung;
            this.kontostand -= (abbuchung+transGebühr);
			kontoauszugListe.add(new Kontoauszug(new Kalender(tag,monat,jahr),Kontobewegung.ABBUCHUNG,(-1*abbuchung)));
		}
		else if(kontoTyp == KontoTyp.GIROKONTO)
            System.out.printf("Dispo nicht ausreichend\n");
		else
			System.out.printf("Kein negatives Guthaben erlaubt bei Sparkonten!\n");
    }
    
	/**
     * Funktion zum überweisen von A nach B
     * @param ueberweisung Betrag welcher überwiesen werden soll
     * @param empfaenger Empfaenger Konto auswählen
	 * @param jahr Jahreszahl der Ueberweisung
	 * @param monat Monat der Ueberweisung
	 * @param tag Tag der Ueberweisung
     */
    public void ueberweisen(double ueberweisung, Konto empfaenger,int jahr, int monat, int tag) //Geld von A nach B ueberweisen
    {
        if(kontostand >= (ueberweisung+dispo) && (kontostand+dispo) >= (abbuchung))
		{
			empfaenger.einzahlen(ueberweisung, jahr, monat, tag);
			kontoauszugListe.add(new Kontoauszug(new Kalender(tag,monat,jahr),Kontobewegung.UEBERWEISUNG,(-1*ueberweisung)));
		}
    }
   
	/**
     * Funktion zum ausgeben des Kontostands
     * @return int kontostand Gibt den Kontostand aus
     */
    public double getKontostand() //Kontostand rausgeben
    {
        return kontostand;
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
     * @param kontoNr übergebene Kontonummer.
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
		return inhaber;
    }   
    
	/**
     *  Funktion zum Inanspruch nehmen eines Dispos
	 *  Sparkonten erhalten keinen Dispo
     *  @param dispo setzen einer Dispogrenze
     */
    public void setDispo(int dispo)
    {
		if(kontoTyp == KontoTyp.GIROKONTO)
			this.dispo = dispo;
		else
			System.out.printf("Kein Dispo bei Sparkonten erlaubt!\n");
    }
    
	/**
     * Funktion zum ausgeben des Dispos
     * @return int dispo ausgeben der Dispogrenze
     */
   public int getDispo()
   {
        return dispo;    
   }
    /**
     * Set-Methode für die Transaktionsgebühr
     * @param transGebühr wert übergeben
     */
    public void setTrans(double transGebühr)
    {
        this.transGebühr = transGebühr;
    }

	/**
     * Get-Methode der Transaktionsgebühr
     * @return rückgabe transGebühr
     */
    public double getTrans()
    {
		return transGebühr;
	}
	public KontoTyp getTyp()
	{
		if(kontoTyp == KontoTyp.GIROKONTO)
			return KontoTyp.GIROKONTO;
		else
			return KontoTyp.SPARKONTO;
	}

	 /**
	* Erstellt einen Kontoauszug
	* @param jahr Jahreszahl übergeben
	* @param monat Monat übergeben
	* @param tag  Tag übergeben
	*/
	public void getKontoauszug(int jahr, int monat, int tag)
	{
		calendar.add(Calendar.DATE, -30);
		Collections.sort(kontoauszugListe,new Compare());
		System.out.printf("\nVORNAME:...%S |NACHNAME:...%S |ADRESSE:...%S |KONTONUMMER:...%d |GUTHABEN:...%.2f EURO |TYP:...%S\nART\t\tMENGE\tDATUM\n",inhaber.getVorname(),inhaber.getNachname(),inhaber.getAdresse(),getKontoNr(),getKontostand(),getTyp());
		if(jahr == 0 || monat == 0 || tag == 0)
		{
			for(Kontoauszug kontoauszug : kontoauszugListe) 
				if(calendar.getTimeInMillis() <= kontoauszug.getTimeInMillis())
					System.out.printf("%s\t%.2f\t%s\n",kontoauszug.getArt(),kontoauszug.getBetrag(),kontoauszug.printDatum());
		}
		else
		{
		    calendar.set(jahr, monat-1, tag+1);
			for(Kontoauszug kontoauszug: kontoauszugListe)
				if(kontoauszug.getTimeInMillis() <= calendar.getTimeInMillis())
					System.out.printf("%s\t%.2f\t%s\n",kontoauszug.getArt(),kontoauszug.getBetrag(),kontoauszug.printDatum());
		}
	}
}
