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
public class Spieler extends Mensch
{	
	private static int lebenS;
	private static int munP;
	private static int munG;
	public Spieler()
	{
		Spieler.lebenS = leben;
		Spieler.munP = munitionPistole;
		Spieler.munG = munitionGewehr;
	}
	@Override
	public  void getStatus()
	{
		System.out.printf("Sie haben noch:\nLeben: %d\nMunition Pistole: %d\nMunition Gewehr: %d.\n\n"
								 ,lebenS,munP,munG);
	}
	@Override
	public void setLeben(int leben)
	{
		lebenS -= leben;
	}
	@Override
	public void setMunP(int munP)
	{
		Spieler.munP -= munP;
	}
	@Override
	public void setMunG(int munG)
	{
		Spieler.munG -= munG;
	}

	@Override
	public int getLeben()
	{
		return lebenS;
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
