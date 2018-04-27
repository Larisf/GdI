package jp02;
import java.util.Calendar;

/**
 * Klasse zur Datumsverwaltung
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
