/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp04;

/**
 *
 * @author Bambi
 */
public class Terrorist extends Mensch 
{
	private final String terrorist;
	private final Raum aktuellerRaum;
	private static int munP;
	private static int munG;
	private static int lebenT;
	public Terrorist(String terrorist, Raum aktuellerRaum)
	{
		Terrorist.munP = munitionPistole;
		Terrorist.munG = munitionGewehr;
		Terrorist.lebenT = leben;
		this.terrorist = terrorist;
		this.aktuellerRaum = aktuellerRaum;
	}
	
	/**
	 * Methode zum überprüfen ob in dem Raum der Terrorist liegt
	 * @return 
	 */
	@Override
	public boolean pruefeTerrorist()
	{
		return terrorist.equals(aktuellerRaum.getName());
	}
	@Override
	public void getZustand()
	{
		System.out.printf("Der Terrorist:\nLeben: %d\nMunition Pistole: %d\nMunition Gewehr: %d\n\n",lebenT,munP,munG);
	}
	@Override
	public void setLeben(int leben)
	{
		lebenT -= leben;
	}
	@Override
	public void setMunP(int munP)
	{
		Terrorist.munP -= munP;
	}
	@Override
	public void setMunG(int munG)
	{
		Terrorist.munG -= munG;
	}
	@Override
	public int getLeben()
	{
		return lebenT;
	}
	@Override
	public int getMunP()
	{
		return munP;
	}
	@Override
	public int getMunG()
	{
		return munG;
	}
}
