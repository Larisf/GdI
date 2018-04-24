/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp02;
import java.util.*;

/**
 *
 * @author Bambi
 */
public class Kalender
{
	private int tag;
	private int monat;
	private int jahr;
	Kalender(int tag, int monat, int jahr)
	{
		this.tag = tag;
		this.monat = monat;
		this.jahr = jahr;
	}
	public int getTag() 
    {
		return tag;
    }
	public int getMonat() 
    {
	    return monat;
    }
    public int getJahr() 
    {
		 return jahr;
    }
}
