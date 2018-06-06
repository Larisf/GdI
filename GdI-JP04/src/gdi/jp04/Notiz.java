/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp04;

/**
 * Klasse zum erstellen der Notiz
 * @author Bambi
 */
public class Notiz {
	private final String notiz;
	private final Raum aktuellerRaum;
	/**
	 * Konstruktor zum erzeugen der Notiz
	 * @param notiz übergabe des Raumes in dem die Notiz liegt
	 * @param aktuellerRaum Übergabe des aktuellen Raumes
	 */
	Notiz(String notiz, Raum aktuellerRaum) 
	{
		this.notiz = notiz;
		this.aktuellerRaum = aktuellerRaum;
	}
	/**
	 * Methode zur Rückgabe ob in dem Raum die Notiz liegt oder nicht.
	 */
	public void getNotiz()
	{
		if(notiz.equals(aktuellerRaum.getName()))
			System.out.printf("Sie haben eine Notiz gefunden, auf ihr steht geschrieben:\"Um die Bombe zu entschärfen, müssen sie das Jahr der Heiligsprechung des Dominikus wissen!\"\n");
		else
			System.out.printf("Nichts außer Staub.\n");
	}
}
