
package jp02;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Klasse zum erstellen eines Kontos.
 *
 * @author Lars Isferding
 * @version 13.4.2018
 */
public class Konto {
    private static int anzKonten = 100000000;
    private int kontostand;
    private int kontoNr;
    private final Inhaber inhaber;
    private double dispo;
	private int abzug,einz,ueber;
	private Calendar calA;
	private final ArrayList<Calendar> listA = new ArrayList<>(); //Kalender-Liste für Einzahlungen
	private final ArrayList listA1 = new ArrayList(); //Liste Für Einzahlungen
	private final ArrayList listA2 = new ArrayList(); //Liste Für Einzahlungen
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
	 * Zu Testzwecken in Netbeans - Compilerus muchos besserus.
	 * @param args[] uebergebene Argumente abfragen
	 */
    public static void main(String args[]) 
    {
		Konto k = new Konto("lars", "isferding", "vissingkamp", 0,1991,8,30);
		Konto k1 = new Konto("lars", "isferding", "vissingkamp");
		k.einzahlen(200,2018,4,24);
		k.abheben(20,2018,4,24);
		k.einzahlen(130,2018,4,1);
		k.ueberweisen(20,k1,2018,4,24);
		k.einzahlen(120,2018,3,30);
		k.einzahlen(140,2018,3,24);
		k.abheben(30,2018,4,1);
		k.abheben(20,2018,3,30);
		k.abheben(40,2018,3,24);
		k.ueberweisen(13,k1,2018,4,1);
		k.ueberweisen(12,k1,2018,3,30);
		k.ueberweisen(14,k1,2018,3,24);
		k.getKontoauszug(0, 0, 0);	
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
     * @param kontostand Kontostand um gewissen Betrag erhöhen
	 * @param jahr Jahreszahl der Einzahlung
	 * @param monat Monat der Einzahlung
	 * @param tag Tag der Einzahlung
     */
    public void einzahlen(int kontostand,int jahr, int monat, int tag) //Geld einzahlen
    {
		this.einz = kontostand;
        this.kontostand += kontostand;
		listA.add(calA = new GregorianCalendar(jahr,monat,tag));
		listA1.add(einz);
		listA2.add("Einzahlung");
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
			listA.add(calA = new GregorianCalendar(jahr,monat,tag));
			listA1.add(abzug);
			listA2.add("Abbuchung");
		}
        else
            System.out.println("Dispo nicht ausreichend");
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
			listA.add(calA = new GregorianCalendar(jahr,monat,tag));
			listA1.add(ueber);
			listA2.add("Überweisung");
		}
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
        //System.out.println(inhaber.getVorname()+"\n"+inhaber.getNachname()+"\n"+inhaber.getAdresse());
		return inhaber;
       // return ("Vorname: " + inhaber.getVorname() +" Nachname: "+ inhaber.getNachname() + " Adresse: " + inhaber.getAdresse());
    }   
    /**
     *  Funktion zum Inanspruch nehmen eines Dispos
     *  @param dispo setzen einer Dispogrenze
     */
    public void setDispo(int dispo)
    {
        this.dispo = dispo;
    }
    /**
     * Funktion zum ausgeben des Dispos
     * @return int dispo ausgeben der Dispogrenze
     */
   public double getDispo()
   {
        return dispo;    
   }
   /**
	* Erstellt einen Kontoauszug
	* @param jahr Jahreszahl übergeben
	* @param monat Monat übergeben
	* @param tag  Tag übergeben
	*/
   public void getKontoauszug(int jahr, int monat, int tag)
   {
	   int i=0;
	   System.out.printf("Vorname: %S |Nachname: %S |Adresse: %S |Kontonummer: %d |Guthaben: %d Euro\n", 
			   getInhaber().getVorname(),
			   getInhaber().getNachname(),
			   getInhaber().getAdresse(),
			   getKontoNr(),
			   getKontostand());
	   if(jahr == 0 || monat == 0 || tag == 0)
	   {
			for(Calendar cal: listA)
			{
				if((calA.getTimeInMillis() - cal.getTimeInMillis())  <= 30)
				{
					System.out.printf("%S von: %d Euro am: %d.%d.%d\n", listA2.get(i),listA1.get(i),cal.get(Calendar.DATE),cal.get(Calendar.MONTH),cal.get(Calendar.YEAR));
					i++;				
				}
			}
	   }
	   else
	   {
		   Calendar calB = new GregorianCalendar(jahr,monat,tag);
			for(Calendar cal: listA)
			{
				if((cal.getTimeInMillis())  >= calB.getTimeInMillis())
				{
					System.out.printf("%S von: %d Euro am: %d.%d.%d\n", listA2.get(i),listA1.get(i),cal.get(Calendar.DATE),cal.get(Calendar.MONTH),cal.get(Calendar.YEAR));
					i++;
				}
			}

		}
	}
}
