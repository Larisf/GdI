package jp02;
import java.util.Comparator;

/**
 * Klasse zum vergleichen von Objekten
 * @author Bambi
 */
public class Compare implements Comparator<Kontoauszug>
{
	/**
	 * Objekte vergleichen und zurückgeben
	 * @param kB1 Objekt 1 Übergeben
	 * @param kB2 Objekt 2 Übergeben
	 * @return Vergleichswert
	 */
	@Override
	public int compare(Kontoauszug kB1, Kontoauszug kB2) 
	{
		return Double.compare(kB1.getTimeInMillis(), kB2.getTimeInMillis());
	}	
}