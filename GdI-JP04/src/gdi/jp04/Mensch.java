/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp04;


/**
 * Abstrakte Klasse Mensch für Spieler und Computer-Gegner
 * @author Bambi
 */
public abstract class Mensch 
{
	protected static int leben = 200;
	public void getStatus(){}
	public boolean pruefeTerrorist(){return false;}
	public void setAktuellerRaum(Raum aktuellerRaum){}
	public void getZustand(){}
	public void setLeben(int leben){}
	public void setMunP(int munP){}
	public void setMunG(int munG){}
	public int getLeben(){return 0;}
	public int getMunP(){return 0;}
	public int getMunG(){return 0;}
}