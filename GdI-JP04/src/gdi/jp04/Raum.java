/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp04;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Klasse zum erstellen der Räume
 * @author Bambi
 */
public class Raum {

	private final String beschreibung;
	private final HashMap ausgaenge;
	/**
	 * Konstruktor zum erstellen der Räume
	 * @param beschreibung Übergabe der Kurzbeschreibung der Räume
	 */
	public Raum(String beschreibung)
	{
		this.beschreibung = beschreibung;
		ausgaenge = new HashMap();
	}
	/**
	 * Methode zur Beschreibung des Ortes
	 * @return String mit beschreibung des Ortes
	 */
	public String getBeschreibung()
	{
		return "Sie befinden sich hier: "+beschreibung + "\nEs gibt folgende Möglichkeiten weiterzugehen: "+ getAusgaenge() +"\n";
	}
	/**
	 * Methode zur Rückgabe der Ausgänge
	 * @return rückgabe gültiger ausgaenge
	 */
	public String getAusgaenge()
	{
		String exit = "";
		Set aus = this.ausgaenge.keySet();
		for(Iterator it = aus.iterator(); it.hasNext();)
			exit  += " " +it.next();
		return exit;
	}
	/**
	 * Methode zum verarbeiten der eingeschlagenen Richtung für den ausgang
	 * @param richtung richtung in die wir uns bewegen
	 * @return Rückgabe der Richtung 
	 */
	public Raum getAusgang(String richtung)
	{
		return (Raum)ausgaenge.get(richtung);
	}
	/**
	 * Methode zum auffüllen der HashMap
	 * @param richtung richtung übergeben (key)
	 * @param raum  Raum übergeben (Value)
	 */
	public void setAusgang(String richtung, Raum raum)
	{
		ausgaenge.put(richtung, raum);
	}
	/**
	 * Methode zur rückgabe des Raumes als String
	 * @return String name des Ortes
	 */
	public String getName()
	{
		return beschreibung;
	}
}
