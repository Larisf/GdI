/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp04;

/**
 * Klasse für den Hund
 * @author Bambi
 */
public class Hund extends Tier{
	private final Raum hund;
	private Raum aktuellerRaum;
	Hund(Raum hund) 
	{		
		this.hund = hund;
	}
	/**
	 * Raum setzen für das Tier
	 * @param aktuellerRaum Raum übergeben
	 */
	@Override
	public void setAktuellerRaum(Raum aktuellerRaum) 
	{
		this.aktuellerRaum = aktuellerRaum;
	}


	/**
	 * Raum zurückgeben in dem das Tier sitzt
	 * @return Wahr oder Falsch
	 */
	@Override
	public boolean pruefeTier() 
	{
		return hund == aktuellerRaum;
	}
	
}
