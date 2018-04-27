package jp02;
import java.util.Calendar;
/**
 * Klasse zur Verwaltung des Kontoauszugs
 * @author Bambi
 */
public class Kontoauszug
{
	private Kalender kalender;
	private KontoTyp kontotyp;
	private double betrag;
	Kontoauszug(Kalender kalender, KontoTyp kontotyp, double betrag)
	{
		this.kalender = kalender;
		this.kontotyp = kontotyp;
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
	public KontoTyp getArt()
	{
		return this.kontotyp;
	}
	public double getBetrag()
	{
		return this.betrag;
	}
}