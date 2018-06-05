/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp04;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Bambi
 */
public class Kampf
{
	Mensch terrorist;
	Mensch spieler;
	public Kampf(Mensch terrorist, Mensch spieler)
	{
		this.spieler = spieler;
		this.terrorist = terrorist;
	}
	public void kampfSzenario()
	{
		spieler.getStatus();
		terrorist.getZustand();	
		while((spieler.getLeben() > 0)&&(terrorist.getLeben() > 0))
		{
			System.out.printf("Waehlen sie eine Aktion!\n1.Pistole: %d/24\t2.Gewehr: %d/30\n3.Faust:    -/-\t\t4.Flucht\n"
							  ,spieler.getMunP(),spieler.getMunG());
			Scanner sc = new Scanner(System.in);
			String eingabe = sc.next();
			switch(eingabe)
			{
				case "1":
				{
					spieler.setMunP(1);
					if(spieler.getMunP() > 0 && terrorist.getLeben() > 0)
					{
						terrorist.setLeben(new Random().nextInt(10));
						System.out.printf("%d\n",terrorist.getLeben());
					}
					else if(spieler.getMunP() == 0 && terrorist.getLeben() >= 0)
					{
						System.out.printf("Keine Munition mehr!");
					}
					else
						System.out.printf("Der Terrorist ist tod!");
					break;
				}
				case "2":
				{
					spieler.setMunG(1);
					break;
				}
				case "3":
				{
					break;
				}
				case "4":
				{
					break;
				}
			}
			
		}
	}
}
