/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp03;

import java.util.Scanner;

/**
 * Klasse zum einlesen und verarbeiten der Eingaben
 * @author Bambi
 */
class Parser {
	private Scanner reader;
	private BefehlsSatz befehle;
	private Timer timer = new Timer();
	private long start;
	/**
	 * Konstruktor zum erstellen des Parsers
	 * @param start Systemzeit + 60Sekunden
	 */
	public Parser(long start) 
	{
		befehle = new BefehlsSatz();
		reader =  new Scanner(System.in);
		this.start = start;
	}
	/**
	 * Aufnehmen der Eingabe über System.in mit Innerer Klasse um nach ablauf der Zeit das Spiel zu beenden.
	 * @return gibt ein Befehlsobjekt zurück
	 */
	public Befehl getBefehl()
	{
		String eingabe;
		String wort1 = null;
		String wort2 = null;
		
		System.out.printf("> ");
		eingabe = reader.nextLine();
		timer.countDown(start);
		Scanner tokenizer = new Scanner(eingabe);
		if(tokenizer.hasNext())
		{
			wort1 = tokenizer.next();
			if(tokenizer.hasNext())
				wort2 = tokenizer.next();
		}
		if(befehle.getBefehlsWort(wort1))
			return new Befehl(wort1, wort2);
		else
			return new Befehl(null,wort2);
		
	}
	/**
	 * Methode zur Ausgabe gültiger Befehle.
	 */
	public void befehleAusgeben()
	{
		befehle.befehleAusgeben();
	}
	
}
