package jp02;
import java.util.Calendar;
/**
 * Klasse zur Verwaltung des Kontoauszugs
 * @author Bambi
 */
public class Kontoauszug
{
	private Kalender kalender;
	private Kontobewegung kontobewegung;
	private double betrag;
	Kontoauszug(Kalender kalender, Kontobewegung kontobewegung, double betrag)
	{
		this.kalender = kalender;
		this.kontobewegung = kontobewegung;
		this.betrag = betrag;
	}
	public String getDatum()
	{
		return this.kalender.getDatum().get(Calendar.DATE)+"."+this.kalender.getDatum().get(Calendar.MONTH)+"."+this.kalender.getDatum().get(Calendar.YEAR);
	}
	public long getTimeInMillis()
	{
		return kalender.getDatum().getTimeInMillis();
	}
	public Kontobewegung getArt()
	{
		return this.kontobewegung;
	}
	public double getBetrag()
	{
		return this.betrag;
	}
}