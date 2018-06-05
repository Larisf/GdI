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
	 * @return boolean ob der Terrorist dort ist oder nicht
	 */
	@Override
	public boolean pruefeTerrorist()
	{
		return terrorist.equals(aktuellerRaum.getName());
	}

	/**
	 * Methode zum zurückgeben des allgemeinen Zustands
	 */
	@Override
	public void getZustand()
	{
		System.out.printf("Der Terrorist:\nLeben: %d\nMunition Pistole: %d\nMunition Gewehr: %d\n\n",lebenT,munP,munG);
	}

	/**
	 * Methode zum abziehen von Lebenspunkten
	 * @param leben leben abziehen
	 */
	@Override
	public void setLeben(int leben)
	{
		lebenT -= leben;
	}

	/**
	 * Methode zum abziehen von Pistolenmunition
	 * @param munP munition abziehen
	 */
	@Override
	public void setMunP(int munP)
	{
		Terrorist.munP -= munP;
	}

	/**
	 * Methode zum abziehen von Gewehrmunition
	 * @param munG munition abziehen
	 */
	@Override
	public void setMunG(int munG)
	{
		Terrorist.munG -= munG;
	}

	/**
	 * Methode zum abrufen der Lebenspunkte
	 * @return leben zurückgeben
	 */
	@Override
	public int getLeben()
	{
		return lebenT;
	}

	/**
	 * Methode zum abrufen der Pistolenmunition
	 * @return munition zurückgeben
	 */
	@Override
	public int getMunP()
	{
		return munP;
	}

	/**
	 * Methode zum abrufen der Gewehrmunition
	 * @return munition zurückgeben
	 */
	@Override
	public int getMunG()
	{
		return munG;
	}
}
