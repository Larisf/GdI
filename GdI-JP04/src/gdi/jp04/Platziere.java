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
	private Raum[] orte;
	private Raum bombenOrt,notizOrt,tSpawn,pSpawn,hSpawn;

	Platziere(Raum eingang, Raum keller, Raum garten, Raum schlafzimmer, Raum kinderzimmer, Raum balkon, Raum saal, Raum wc, Raum ersteEtage, Raum buero, Raum heizungsraum, Raum weinkeller, Raum garage) {
		orte = new Raum[]{eingang, keller, heizungsraum, garage, buero, saal, weinkeller, wc, 
						  ersteEtage, schlafzimmer, kinderzimmer, balkon, garten};
	 
	}

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
	public Raum getBombenOrt()
	{
		return bombenOrt;
	}
	
	/**
	 * Methode zur Rückgabe der Notiz
	 * @return zurückgeben des Notizen Ortes
	 */
	public Raum getNotizOrt()
	{
		return notizOrt;
	}
	
	/**
	 * Methode zur Rückgabe des Terroristen
	 * @return zurückgeben des Standortes des Terroristen
	 */
	public Raum getTSpawn()
	{
		return tSpawn;
	}
	/**
	 * Methode zur Rückgabe des Papagein
	 * @return zurückgeben des Standortes des Papagein
	 */
	public Raum getPapagei()
	{
		return pSpawn;
	}
	/**
	 * Methode zur Rückgabe des Hundes
	 * @return zurückgeben des Standortes des Hundes
	 */
	public Raum getHund()
	{
		return hSpawn;
	}

	void setHund(Raum hSpawn) {
		this.hSpawn = hSpawn;
	}
}
