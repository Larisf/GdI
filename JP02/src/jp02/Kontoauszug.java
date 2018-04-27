package jp02;
/**
 * Klasse zur Verwaltung des Kontoauszugs
 * @author Bambi
 */
public class Kontoauszug
{
	private final Kalender kalender;
	private final Kontobewegung kontotyp;
	private final double betrag;
	Kontoauszug(Kalender kalender, Kontobewegung kontotyp, double betrag)
	{
		this.kalender = kalender;
		this.kontotyp = kontotyp;
		this.betrag = betrag;
	}
	public Kontobewegung getArt()
	{
		return this.kontotyp;
	}
	public double getBetrag()
	{
		return this.betrag;
	}
		public String printDatum()
	{
		return this.kalender.getTag()+"."+this.kalender.getMonat()+"."+this.kalender.getJahr();
	}
	public long getTimeInMillis()
	{
		return kalender.getDatum().getTimeInMillis();
	}
}