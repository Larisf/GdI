/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp04;

import java.util.Random;
import java.util.Scanner;

/**
 * Methode um ein Kampfszenario zu bearbeiten
 * @author Bambi
 */
public class Kampf extends Mensch implements Waffen
{
	private final Mensch terrorist;
	private final Mensch spieler;
	private final int pPuffer = 10;
	private final int gPuffer = 20;
	private final int maxLeben;
	private final int maxMunP;
	private final int maxMunG;
	private boolean alive = true;
	private boolean zugSpieler = true;
	private boolean zugComputer = false;
	/**
	 * Konstruktor
	 * @param terrorist Terroristen übergeben
	 * @param spieler Spieler übergeben
	 */
	public Kampf(Mensch terrorist, Mensch spieler)
	{
		this.spieler = spieler;
		this.terrorist = terrorist;
		this.maxLeben = leben;
		this.maxMunG = MUNITION_GEWEHR;
		this.maxMunP = MUNITION_PISTOLE;
	}
	/**
	 * Methode zum einleiten eines Kampfes
	 */
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
	/**
	 * Methode um den Zug des Spielers zu bearbeiten
	 */
	private void spielerZug()
	{
			System.out.printf("Waehlen sie eine Aktion!\n1.Pistole: %d/%d (10-20 Schaden)\t2.Gewehr: %d/%d (30-50 Schaden)\n3.Faust:    -/-  (0-5 Schaden)\t\t4.Flucht\t(Kann Fehlschlagen)\n\n"
							  ,spieler.getMunP(),maxMunP,spieler.getMunG(),maxMunG);
			Scanner sc = new Scanner(System.in);
			String eingabe = sc.next();
			switch(eingabe)
			{
				case "1":
				{
					spieler.setMunP(1);
					if(spieler.getMunP() > 0 && terrorist.getLeben() > 0)
					{
						terrorist.setLeben(new Random().nextInt(DMG_P)+pPuffer);
						System.out.printf("Der Terrorist hat noch %d/%d Leben.\n\n",terrorist.getLeben(),maxLeben);
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
						terrorist.setLeben(new Random().nextInt(DMG_G)+gPuffer);
						System.out.printf("Der Terrorist hat noch %d/%d Leben.\n\n",terrorist.getLeben(),maxLeben);
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
						terrorist.setLeben(new Random().nextInt(DMG_F));
						System.out.printf("Der Terrorist hat noch %d/%d Leben.\n\n",terrorist.getLeben(),maxLeben);
						zugSpieler = false;
						zugComputer = true;
					}
					break;
				}
				case "4":
				{
					boolean rnd = new Random().nextBoolean();
					if(rnd)
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
					System.out.printf("Gueltige Eingabe taetigen!");
			}
		if(terrorist.getLeben() <= 0)
		{
			System.out.printf("Der Terrorist ist tod! Er trug einen Zettel bei sich, auf diesem steht der Code sowie der Aufenthaltsort der Bombe.\n"
							+ "Rufe die Karte auf 'showMap'fuer Informationen!\nVerlasse diesen Raum und kehre nicht wieder, hier wimmelt es gleich von seinen Kollegen.\n\n");
			alive  = false;
			zugSpieler = false;
		}
	}
	/**
	 * Methode um den Zug des Computers als Terrorist zu bearbeiten
	 */
	private void terrorZug()
	{
		int rnd = new Random().nextInt(3);
			switch(rnd)
			{
				case 0:
				{
					terrorist.setMunP(1);
					if(terrorist.getMunP() > 0 && spieler.getLeben() > 0)
					{
						spieler.setLeben(new Random().nextInt(DMG_P)+pPuffer);
						System.out.printf("Sie haben noch %d/%d Leben.\n\n",spieler.getLeben(),maxLeben);
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
						spieler.setLeben(new Random().nextInt(DMG_G)+gPuffer);
						System.out.printf("Sie haben noch %d/%d Leben.\n\n",spieler.getLeben(),maxLeben);
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
						spieler.setLeben(new Random().nextInt(DMG_F));
						System.out.printf("Sie haben noch %d/%d Leben.\n\n",spieler.getLeben(),maxLeben);					
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