/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp03;

/**
 * Klasse zum verarbeiten der Befehle
 * @author Bambi
 */
class Befehl {
	private String erstesWort;
	private String zweitesWort;
	/**
	 * Konstruktor für den Befehl
	 * @param erstesWort erste Zeichenkette wird übergeben
	 * @param zweitesWort zweite Zeichenkette wird übergeben
	 */
	public Befehl(String erstesWort, String zweitesWort)
	{
		this.erstesWort = erstesWort;
		this.zweitesWort = zweitesWort;
	}
	/**
	 * Methode zur Rückgabe der ersten Zeichenkette
	 * @return rückgabe der ersten Zeichenkette
	 */
	public String getErstesWort()
	{
		return erstesWort;
	}
	/**
	 * Methode zur Rückgabe der zweiten Zeichenkette
	 * @return rückgabe der zweiten Zeichenkette
	 */
	public String getZweitesWort()
	{
		return zweitesWort;
	}
	/**
	 * Methode zur Überprüfung ob die Eingabe unbekannt ist
	 * @return true or false, je nachdem ob bekannt oder unbekannt
	 */
	public boolean istUnbekannt()
	{
		return (erstesWort == null);
	}
	/**
	 * Methode zur Überprüfung der zweiten Zeichenkette, falls vorhanden
	 * @return true or false, je nachdem ob vorhanden oder nicht.
	 */
	public boolean hatZweitesWort()
	{
		return (zweitesWort != null);
	}
	
}
