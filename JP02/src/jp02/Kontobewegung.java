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
public class Kontobewegung
{
	private Kalender kalender;
	private KontoTyp kontotyp;
	private int betrag;
	Kontobewegung(Kalender k, KontoTyp kT, int betrag)
	{
		this.kalender = k;
		this.kontotyp = kT;
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
	public int getBetrag()
	{
		return this.betrag;
	}
}
