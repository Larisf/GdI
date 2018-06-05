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
	private final int pPuffer = 10;
	private final int gPuffer = 20;
	private boolean alive = true;
	private boolean zugSpieler = true;
	private boolean zugComputer = false;
	public Kampf(Mensch terrorist, Mensch spieler)
	{
		this.spieler = spieler;
		this.terrorist = terrorist;
	}
	public void kampfSzenario()
	{
		boolean flee = false;
		spieler.getStatus();
		terrorist.getZustand();
		while(alive && !flee)
		{
			if(terrorist.getLeben() > 0 && zugSpieler)
				spielerZug();
			else if(spieler.getLeben() > 0 && zugComputer)
				terrorZug();
			else if(!zugSpieler && !zugComputer)
				flee = true;
		}
	}
	private void spielerZug()
	{
			System.out.printf("Waehlen sie eine Aktion!\n1.Pistole: %d/24 (10-20 Schaden)\t2.Gewehr: %d/10 (30-50 Schaden)\n3.Faust:    -/-\t\t\t\t4.Flucht\n\n"
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
						terrorist.setLeben(new Random().nextInt(10)+pPuffer);
						System.out.printf("Der Terrorist hat noch %d/100 Leben.\n\n",terrorist.getLeben());
						zugSpieler = false;
						zugComputer = true;
					}
					else if(spieler.getMunP() == 0 && terrorist.getLeben() >= 0)
					{
						System.out.printf("Keine Munition mehr!\n\n");
						zugSpieler = false;
						zugComputer = true;
					}
					break;
				}
				case "2":
				{
					spieler.setMunG(1);
					if(spieler.getMunG() > 0 && terrorist.getLeben() > 0)
					{
						terrorist.setLeben(new Random().nextInt(30)+gPuffer);
						System.out.printf("Der Terrorist hat noch %d/100 Leben.\n\n",terrorist.getLeben());
						zugSpieler = false;
						zugComputer = true;
					}
					else if(spieler.getMunG() == 0 && terrorist.getLeben() >= 0)
					{
						System.out.printf("Keine Munition mehr!\n\n");
						zugSpieler = false;
						zugComputer = true;
					}
					break;
				}
				case "3":
				{
					if(terrorist.getLeben() > 0)
					{
						terrorist.setLeben(new Random().nextInt(5));
						System.out.printf("Der Terrorist hat noch %d/100 Leben.\n\n",terrorist.getLeben());
						zugSpieler = false;
						zugComputer = true;
					}
					break;
				}
				case "4":
				{
					int rnd = new Random().nextInt(3);
					if(rnd == 0)
					{
						System.out.printf("Sie sind geflohen!\n");
						zugSpieler = false;
						zugComputer = false;
					}
					else
					{
						System.out.printf("Sie haben versucht zu fliehen, doch es hat nicht funktioniert.\n");
						zugSpieler = false;
						zugComputer = true;
					}
					break;
				}
				default :
					System.out.printf("Gültige Eingabe tätigen!");
			}
		if(terrorist.getLeben() <= 0)
		{
			System.out.printf("Der Terrorist ist tod! Er trug einen Zettel bei sich, auf diesem steht der Code sowie der Aufenthaltsort der Bombe. "
							+ "Rufe die Karte auf (showMap)für Informationen! Verlasse diesen Raum und kehre nicht wieder, hier wimmelt es gleich von seinen Kollegen.\n\n");
			alive  = false;
			zugSpieler = false;
		}
	}
	private void terrorZug()
	{
		int rnd = new Random().nextInt(2);
			switch(rnd)
			{
				case 0:
				{
					terrorist.setMunP(1);
					if(terrorist.getMunP() > 0 && spieler.getLeben() > 0)
					{
						spieler.setLeben(new Random().nextInt(10)+pPuffer);
						System.out.printf("Sie haben noch %d/100 Leben.\n\n",spieler.getLeben());
						zugSpieler = true;
						zugComputer = false;
					}
					else if(terrorist.getMunP() == 0 && spieler.getLeben() >= 0)
					{
						System.out.printf("Keine Munition mehr!\n\n");
						zugSpieler = true;
						zugComputer = false;
					}
					break;
				}
				case 1:
				{
					terrorist.setMunG(1);
					if(terrorist.getMunG() > 0 && spieler.getLeben() > 0)
					{
						spieler.setLeben(new Random().nextInt(30)+gPuffer);
						System.out.printf("Sie haben noch %d/100 Leben.\n\n",spieler.getLeben());
						zugSpieler = true;
						zugComputer = false;
					}
					else if(terrorist.getMunG() == 0 && spieler.getLeben() >= 0)
					{
						System.out.printf("Keine Munition mehr!\n\n");
						zugSpieler = true;
						zugComputer = false;
					}
					break;
				}
				case 2:
				{
					if(spieler.getLeben() > 0)
					{
						spieler.setLeben(new Random().nextInt(5));
						System.out.printf("Sie haben noch %d/100 Leben.\n\n",spieler.getLeben());					
						zugSpieler = true;
						zugComputer = false;
					}
					break;
				}
			}
		if(spieler.getLeben() <0)
		{
			System.out.printf("Sie sind gestorben!\n\n");
			System.exit(0);
		}
	}
}
