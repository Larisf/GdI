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
		this.calendar.set(jahr, monat-1, tag);
	}

	Kalender() {}
	/**
	 * Datum zurückgeben
	 * @return calendar
	 */
	public Calendar getDatum()
	{
		return calendar;
	}
	public int getTag()
	{
		return calendar.get(Calendar.DATE);
	}
	public int getMonat()
	{
		return calendar.get(Calendar.MONTH)+1;
	}
	public int getJahr()
	{
		return calendar.get(Calendar.YEAR);
	}
	public String printDatum()
	{
		if(getTag() <= 9 && getMonat() > 9)
			return "0"+getTag()+"."+getMonat()+"."+getJahr();
		else if(getMonat() <=9 && getTag() > 9)
			return  getTag()+".0"+getMonat()+"."+getJahr();
		else if(getMonat() <= 9 && getTag() <= 9)
			return "0"+getTag()+".0"+getMonat()+"."+getJahr();
		else
			return getTag()+"."+getMonat()+"."+getJahr();
	}
}
