/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp02;

/**
 *
 * @author Bambi
 */
public class Sparkonto extends Konto 
{
	private double zinssatz;
/**
 * 
 * @param vorname Vornamen übergeben
 * @param nachname Nachnamen übergeben
 * @param adresse Adresse übergeben
 * @param einzahlung einzahlgun übergeben
 * @param zinssatz zinssatz übergeben
 * @param jahr jahr übergeben
 * @param monat monat übergeben
 * @param tag tag übergeben
 */	
	public Sparkonto(String vorname, String nachname, String adresse, int einzahlung, double zinssatz, int jahr, int monat, int tag) 
	{
		super(vorname, nachname, adresse, einzahlung, jahr, monat, tag);
		this.zinssatz = zinssatz;
		super.kontoTyp = KontoTyp.SPARKONTO;	
	}
			  
   /**
	* Funktion zur Zinszahlung bei Sparkonten
	* @return Rückgabe des neuen Kontostandes
	*/
   public double zinszahlung()
   {
	   super.kontostand = kontostand + (kontostand * (zinssatz/10000));
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
}
