/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp02;

import java.util.ArrayList;

/**
 *
 * @author Bambi
 */
public class Liste {
	private static ArrayList<Kontoauszug> kontoauszugListe = new ArrayList();

	public void setListe(Kontoauszug kontoauszug)
	{
		kontoauszugListe.add(kontoauszug);
	}
	public ArrayList<Kontoauszug> getListe()
	{
		return kontoauszugListe;
	}
	public int getListeS()
	{
		return kontoauszugListe.size();
	}
	
}
