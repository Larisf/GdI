/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp03;

import java.util.Scanner;


/**
 * Klasse zum erstellen der Bombe
 * @author Bambi
 */
class Bombe {

	private String bombe;
	private Raum aktuellerRaum;
	private boolean beendet;
	/**
	 * Konstruktor zum erstellen der Bombe
	 * @param bombe übergabe des Raumes in dem die Bombe liegt
	 * @param aktuellerRaum  übergabe des Aktuellen Raumes
	 */
	public Bombe(String bombe, Raum aktuellerRaum) 
	{
		this.bombe = bombe;
		this.aktuellerRaum = aktuellerRaum;
	}
	/**
	 * Methode zum überprüfen ob in dem Raum die Bombe liegt
	 */
	public void getBombe()
	{
		if(bombe.equals(aktuellerRaum.getName()))
		{
			System.out.printf("Sie haben die Bombe gefunden, entschärfen Sie sie indem sie den richtigen Code eingeben!\n");
			entschaerfen();
		}
		else
			System.out.printf("Nichts außer Staub.\n");
	}
	/**
	 * methode zum entschaerfen der Bombe beim Fund
	 */
	public void entschaerfen()
	{
		Scanner scan = new Scanner(System.in);
		String eingabe = scan.next();
		if(eingabe.equals("1234"))
		{
			System.out.printf("Sie haben die Bombe entschärft und somit gewonnen!\n");
			System.exit(0);
		}
		else
		{
			System.out.printf("Sie haben den Code falsch eingegeben, die Bombe ist explodiert! Sie haben verloren.\n");
			System.exit(0);
		}
	}
}
