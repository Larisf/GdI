package jp02;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
/**
 * Klasse zum erstellen eines Kontos.
 *
 * @author Lars Isferding
 * @version 13.4.2018
 */
public class Konto 
{
    private static int anzKonten = 100000000;
    private double kontostand;
    private int kontoNr;
	private int kontoTyp = 0;
    private final Inhaber inhaber;
	private double zinssatz;
    private int dispo;
	private int abzug,einz,ueber;
	private ArrayList<Kontobewegung> obj = new ArrayList();

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
        //obj.add(new Kontobewegung(new Kalender(tag,monat,jahr),KontoTyp.ERÖFFNUNG,abzug));
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
        //obj.add(new Kontobewegung(new Kalender(tag,monat,jahr),KontoTyp.FREUNDSCHAFTSBONUS,abzug));
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
    Konto(String vorname, String nachname, String adresse, int einzahlung,double zinssatz, int jahr, int monat, int tag) //Konto eröffnen mit Einzahlung
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
		k.einzahlen(200,2018,2,1);
		k.abheben(20,2018,3,2);
		k.einzahlen(130,2018,4,5);
		k.ueberweisen(20,k1,2018,4,4);
		k.einzahlen(120,2018,4,2);
		k.einzahlen(140,2018,4,8);
		k.abheben(30,2018,4,1);
		k.abheben(20,2018,4,2);
		k.abheben(40,2018,4,7);
		k.ueberweisen(13,k1,2018,4,12);
		k.ueberweisen(12,k1,2018,4,15);
		k.ueberweisen(14,k1,2018,4,11);
		k.getKontoauszug(0, 0, 0);
		k1.setZinssatz(25);
		k1.zinszahlung();
		k1.getKontoauszug(0, 0, 0);
    }
    
	/**
     * Funktion zum einzahlen eines Betrages.
     * @param kontostand Kontostand um gewissen Betrag erhöhen
	 * @param jahr Jahreszahl der Einzahlung
	 * @param monat Monat der Einzahlung
	 * @param tag Tag der Einzahlung
     */
    public void einzahlen(int kontostand,int jahr, int monat, int tag) //Geld einzahlen
    {
		this.einz = kontostand;
        this.kontostand += kontostand;
		obj.add(new Kontobewegung(new Kalender(tag,monat,jahr),KontoTyp.EINZAHLUNG,einz));
    }
   
	/**
     * Funktion zum abheben eines Betrages unter Berücksichtigung des Dispos.
     * 
     * @param abheben Betrag, welcher abgehoben werden soll.
	 * @param jahr Jahreszahl der Abbuchung
	 * @param monat der Abbuchung
	 * @param tag  der Abbuchung
     */
    public void abheben(int abheben,int jahr, int monat, int tag) //Geld abheben
    {
        if((kontostand+dispo) >= (abheben))
		{
			this.abzug = abheben;
            this.kontostand -= abheben;
			obj.add(new Kontobewegung(new Kalender(tag,monat,jahr),KontoTyp.ABBUCHUNG,abzug));
		}
		else if(kontoTyp == 0)
            System.out.println("Dispo nicht ausreichend");
		else
			System.out.println("Kein negatives Guthaben erlaubt bei Sparkonten!");
    }
    
	/**
     * Funktion zum überweisen von A nach B
     * @param betrag Betrag welcher überwiesen werden soll
     * @param empfaenger Empfaenger Konto auswählen
	 * @param jahr Jahreszahl der Ueberweisung
	 * @param monat Monat der Ueberweisung
	 * @param tag Tag der Ueberweisung
     */
    public void ueberweisen(int betrag, Konto empfaenger,int jahr, int monat, int tag) //Geld von A nach B ueberweisen
    {
        if(kontostand >= (betrag+dispo) && (kontostand+dispo) >= (abzug))
		{
			this.ueber = betrag;
			empfaenger.einzahlen(betrag, jahr, monat, tag);
			obj.add(new Kontobewegung(new Kalender(tag,monat,jahr),KontoTyp.UEBERWEISUNG,ueber));
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
		Calendar cal = Calendar.getInstance();
		Collections.sort(obj,new Compare());
		System.out.printf("Vorname: %S |Nachname: %S |Adresse: %S |Kontonummer: %d |Guthaben: %.2f Euro\nART\t\tMENGE\tDATUM\n",getInhaber().getVorname(),getInhaber().getNachname(),getInhaber().getAdresse(),getKontoNr(),getKontostand());
		if(jahr == 0 || monat == 0 || tag == 0)
		{
			for (Kontobewegung konto : obj) {
				if(cal.getTimeInMillis() - konto.getTimeInMillis() <= 30)
					System.out.printf("%s\t%d\t%s\n",konto.getArt(),konto.getBetrag(),konto.getDatum());
			}
		}
		else
		{
		    cal.set(jahr, monat, tag);
			for(Kontobewegung konto: obj)
				if(konto.getTimeInMillis() >= cal.getTimeInMillis())
					System.out.printf("%s\t%d\t%s\n",konto.getArt(),konto.getBetrag(),konto.getDatum());
		}
	}
}
