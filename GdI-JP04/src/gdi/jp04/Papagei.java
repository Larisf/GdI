/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp04;

/**
 * Klasse für den Papagei
 * @author Bambi
 */
public class Papagei extends Tier{
	private final Raum papagei;
	private Raum aktuellerRaum;
	Papagei(Raum papagei) 
	{
		this.papagei = papagei;
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
		return papagei == aktuellerRaum;
	}
	
}
