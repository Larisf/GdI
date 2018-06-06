/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp04;


/**
 * Klasse für Spieler
 * @author Bambi
 */
public class Spieler extends Mensch implements Waffen
{	
	private static int lebenS;
	private static int munP;
	private static int munG;
	public Spieler()
	{
		Spieler.lebenS = leben;
		Spieler.munP = MUNITION_PISTOLE;
		Spieler.munG = MUNITION_GEWEHR;
	}
	/**
	 * Methode zum zurückgeben des allgemeinen Zustands
	 */
	@Override
	public  void getStatus()
	{
		System.out.printf("Sie haben noch:\nLeben: %d\nMunition Pistole: %d\nMunition Gewehr: %d.\n\n"
						  ,lebenS,munP,munG);
	}
	/**
	 * Methode zum abziehen von Lebenspunkten
	 * @param leben leben abziehen
	 */
	@Override
	public void setLeben(int leben)
	{
		lebenS -= leben;
	}
	/**
	 * Methode zum abziehen von Pistolenmunition
	 * @param munP munition abziehen
	 */
	@Override
	public void setMunP(int munP)
	{
		Spieler.munP -= munP;
	}
	/**
	 * Methode zum abziehen von Gewehrmunition
	 * @param munG munition abziehen
	 */
	@Override
	public void setMunG(int munG)
	{
		Spieler.munG -= munG;
	}
	/**
	 * Methode zum abrufen der Lebenspunkte
	 * @return leben zurückgenen
	 */
	@Override
	public int getLeben()
	{
		return lebenS;
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