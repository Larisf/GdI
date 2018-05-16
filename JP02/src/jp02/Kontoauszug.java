package jp02;
/**
 * Klasse zur Verwaltung des Kontoauszugs
 * @author Bambi
 */
public class Kontoauszug
{
	private Kalender kalender;
	private Kontobewegung kontotyp;
	private double betrag;
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
		if(this.kalender.getTag() <= 9 && this.kalender.getMonat() > 9)
			return "0"+this.kalender.getTag()+"."+this.kalender.getMonat()+"."+this.kalender.getJahr();
		else if(this.kalender.getMonat() <=9 && this.kalender.getTag() > 9)
			return  this.kalender.getTag()+".0"+this.kalender.getMonat()+"."+this.kalender.getJahr();
		else if(this.kalender.getMonat() <= 9 && this.kalender.getTag() <= 9)
			return "0"+this.kalender.getTag()+".0"+this.kalender.getMonat()+"."+this.kalender.getJahr();
		else
			return this.kalender.getTag()+"."+this.kalender.getMonat()+"."+this.kalender.getJahr();
	}
	public long getTimeInMillis()
	{
		return kalender.getDatum().getTimeInMillis();
	}
}