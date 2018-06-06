/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp04;

import java.util.Random;

/**
 * Klasse zum platzieren der Objekte und Figuren
 * @author Bambi
 */
public class Platziere 
{
		private final String[] orte = {"Erdgeschoss","Keller","Heizungsraum","Garage","Buero","Saal","Weinkeller",
						 "Wc","1.Etage","Schlafzimmer","Kinderzimmer","Balkon","Garten"};
		private String bombenOrt,notizOrt,tSpawn,pSpawn,hSpawn;

	/**
	 * Methode zum platzieren der Bombe
	 */
	public void platziereBombe()
		{
			bombenOrt = (orte[new Random().nextInt(orte.length)]);
		}
	
	/**
	 * Methode zum platzieren der Notiz
	 */
	public void platziereNotiz()
	{
		notizOrt = (orte[new Random().nextInt(orte.length)]);
	}
	
	/**
	 * Methode zum platzieren des Terroristen
	 */
	public void platziereTerrorist()
	{
		tSpawn = (orte[new Random().nextInt(orte.length)]);
	}	
	/**
	 * Methode zum platzieren des Papagein
	 */
	public void platzierePapagei()
	{
		pSpawn = (orte[new Random().nextInt(orte.length)]);
	}
	/**
	 * Methode zum platzieren des Hundes
	 */
	public void platziereHund()
	{
		hSpawn = (orte[new Random().nextInt(orte.length)]);
	}

	/**
	 * Methode zur Rückgabe der Bombe
	 * @return zurückgeben des Bomben Ortes
	 */
	public String getBombenOrt()
	{
		return bombenOrt;
	}
	
	/**
	 * Methode zur Rückgabe der Notiz
	 * @return zurückgeben des Notizen Ortes
	 */
	public String getNotizOrt()
	{
		return notizOrt;
	}
	
	/**
	 * Methode zur Rückgabe des Terroristen
	 * @return zurückgeben des Standortes des Terroristen
	 */
	public String getTSpawn()
	{
		return tSpawn;
	}
	/**
	 * Methode zur Rückgabe des Papagein
	 * @return zurückgeben des Standortes des Papagein
	 */
	public String getPapagei()
	{
		return pSpawn;
	}
	/**
	 * Methode zur Rückgabe des Hundes
	 * @return zurückgeben des Standortes des Hundes
	 */
	public String getHund()
	{
		return hSpawn;
	}
}
