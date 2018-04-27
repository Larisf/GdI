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
    private double kontostand, zinssatz, abbuchung;
    private int kontoNr, dispo;
	private int kontoTyp = 0;
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
        kontoNr = kontoNr+anzKonten;
        anzKonten++;
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
        kontoNr = kontoNr+anzKonten;
        anzKonten++;
		this.kontostand = einzahlung;
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
        kontoNr = kontoNr+anzKonten;
        anzKonten++;
        empfaenger.einzahlen(60,jahr,monat,tag);
        inhaber = new Inhaber(vorname,nachname,adresse);
    }
	
	/**
     * Konstruktor zum eröffnen eines neuen Kontos
     * @param vorname Vornamen angeben 
     * @param nachname Nachnamen angeben 
     * @param adresse Adresse angeben
     * @param einzahlung betrag zum einzahlen angeben
	 * @param zinssatz Zinssatz setzen
     */
    Konto(String vorname, String nachname, String adresse, int einzahlung,double zinssatz, int jahr, int monat, int tag) //Sparkonto eröffnen mit Einzahlung
    {
		kontoTyp = 1;
        kontoNr = kontoNr+anzKonten;
        anzKonten++;
        this.kontostand = einzahlung;
        inhaber = new Inhaber(vorname,nachname,adresse);
		this.zinssatz = zinssatz;
    }   
	
	/**
	 * Zu Testzwecken in Netbeans - Compilerus muchos besserus.
	 * @param args[] uebergebene Argumente abfragen
	 */
    public static void main(String args[]) 
    {
		Konto k = new Konto("lars", "isferding", "vissingkamp", 0,2018,4,10);
		Konto k1 = new Konto("Gustav", "Gans", "Entenhausen",100,0,2018,4,15);
		k.einzahlen(100, 2018, 4, 20);
		k.abheben(20, 2018, 4, 22);
		k.ueberweisen(10, k1, 2018, 4, 24);
		k.einzahlen(10, 2018, 4, 28);
		k1.setZinssatz(25);
		k1.zinszahlung();
		k1.einzahlen(200, 2018, 3, 30);
		k1.ueberweisen(10, k, 2018, 2, 28);
		
		
		k.getKontoauszug(2018, 4, 27);
		k1.getKontoauszug(0, 0, 0);
		
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
		this.kontostand += einzahlung;
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
            this.kontostand -= abbuchung;
			kontoauszugListe.add(new Kontoauszug(new Kalender(tag,monat,jahr),Kontobewegung.ABBUCHUNG,abbuchung));
		}
		else if(kontoTyp == 0)
            System.out.println("Dispo nicht ausreichend");
		else
			System.out.println("Kein negatives Guthaben erlaubt bei Sparkonten!");
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
			kontoauszugListe.add(new Kontoauszug(new Kalender(tag,monat,jahr),Kontobewegung.UEBERWEISUNG,ueberweisung));
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
		if(kontoTyp == 0)
			this.dispo = dispo;
		else
			System.out.println("Kein Dispo bei Sparkonten erlaubt!");
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
	* Funktion zur Zinszahlung bei Sparkonten
	* @return Rückgabe des neuen Kontostandes
	*/
   public double zinszahlung()
   {
	   this.kontostand = this.kontostand + (this.kontostand * (zinssatz/10000));
	   return kontostand;
   }
	
   /**
	* Funktion zum setzen des Zinssatzes
	* @param zinssatz  zinssatz übergeben
	*/
   public void setZinssatz(double zinssatz)
   {
	   this.zinssatz = zinssatz;
   }
   
   /**
	* Funktion zur Rückgabe des Zinssatzes
	* @return 
	*/
   public double getZinssatz()
   {
	   return zinssatz/10000;
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
		System.out.printf("\nVORNAME:...%S |NACHNAME:...%S |ADRESSE:...%S |KONTONUMMER:...%d |GUTHABEN:...%.2f EURO\nART\t\tMENGE\tDATUM\n",getInhaber().getVorname(),getInhaber().getNachname(),getInhaber().getAdresse(),getKontoNr(),getKontostand());
		if(jahr == 0 || monat == 0 || tag == 0)
		{
			for(Kontoauszug kontoauszug : kontoauszugListe) 
				if(calendar.getTimeInMillis() <= kontoauszug.getTimeInMillis())
					System.out.printf("%s\t%.2f\t%s\n",kontoauszug.getArt(),kontoauszug.getBetrag(),kontoauszug.printDatum());
		}
		else
		{
		    calendar.set(jahr, monat-1, tag);
			for(Kontoauszug kontoauszug: kontoauszugListe)
				if(kontoauszug.getTimeInMillis() <= calendar.getTimeInMillis())
					System.out.printf("%s\t%.2f\t%s\n",kontoauszug.getArt(),kontoauszug.getBetrag(),kontoauszug.printDatum());
		}
	}
}
