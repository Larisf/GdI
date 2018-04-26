/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp02;
import java.util.Calendar;

/**
 *
 * @author Bambi
 */
public class Kalender {
	private Calendar calendar = Calendar.getInstance();
	/**
	 * Konstruktor für den Kalender
	 * @param tag Tag übergeben
	 * @param monat Monat übergeben
	 * @param jahr Jahr übergeben
	 */
	Kalender(int tag, int monat, int jahr)
	{
		this.calendar.set(jahr, monat, tag);
	}
	/**
	 * Datum zurückgeben
	 * @return calendar
	 */
	public Calendar getDatum()
	{
		return calendar;
	}	
}
