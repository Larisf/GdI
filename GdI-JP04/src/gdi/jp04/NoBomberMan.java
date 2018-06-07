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
 * NoBomberMan ein keines textbasiertes Spiel bei dem man eine Bombe suchen und entschärfen muss.
 */
public class NoBomberMan 
{
	private Raum aktuellerRaum, eingang, keller, garten, schlafzimmer, kinderzimmer, balkon, saal, wc, ersteEtage, buero, heizungsraum, weinkeller, garage;
	private final Parser parser;
	private final Map map;
	private Notiz notify;
	private Bombe bomb;
	private final Tier papagei, hund;
	private Mensch terrorist;
	private final Platziere platziere;
	private final Mensch spieler;
	private final Timer timer;
	private boolean leicht;
	private boolean beendet;
	private static final long SEKUNDEN = 300;
	private static long START;
	private boolean alive;
	private static boolean firstMove = true;
	/**
	 * Konstruktor zum erstellen der Karte und des Parsers für die Eingabe
	 */
	public NoBomberMan()
	{
		map = new Map();
		alive = true;
		beendet = false;
		timer = new Timer();
		karteErzeugen();
		platziere = new Platziere(eingang, keller, garten, schlafzimmer, kinderzimmer, balkon, saal, wc, ersteEtage, buero, heizungsraum, weinkeller, garage);
		platziere.platzierePapagei();
		platziere.platziereHund();
		platziere.platziereBombe();
		platziere.platziereNotiz();
		platziere.platziereTerrorist();
		parser = new Parser();
		spieler = new Spieler();
		terrorist = new Terrorist(platziere.getTSpawn());
		papagei = new Papagei(platziere.getPapagei());
		hund = new Hund(platziere.getHund());
	}
	public static void main(String args[])
	{
		NoBomberMan b = new NoBomberMan();
		b.spielen();
	}
	/**
	 * Methode zum erstellen der Karte
	 */
	private void karteErzeugen()
	{	
		eingang = new Raum("Erdgeschoss");
		keller = new Raum("Keller");
		heizungsraum = new Raum("Heizungsraum");
		garage = new Raum("Garage");
		buero = new Raum("Buero");
		saal = new Raum("Saal");
		weinkeller = new Raum("Weinkeller");
		wc = new Raum("Wc");
		ersteEtage = new Raum("1.Etage");
		schlafzimmer = new Raum("Schlafzimmer");
		kinderzimmer = new Raum("Kinderzimmer");
		balkon = new Raum("Balkon");
		garten = new Raum("Garten");
		
		
		eingang.setAusgang("north", saal);
		eingang.setAusgang("east", garage);
		eingang.setAusgang("west", keller);
		
		saal.setAusgang("south", eingang);
		saal.setAusgang("east", buero);
		saal.setAusgang("north", wc);
		saal.setAusgang("west", ersteEtage);
		
		garage.setAusgang("west", eingang);
		
		keller.setAusgang("south", weinkeller);
		keller.setAusgang("west", heizungsraum);
		keller.setAusgang("east", eingang);
		
		weinkeller.setAusgang("north", keller);
		heizungsraum.setAusgang("east", keller);
		
		buero.setAusgang("west", saal);
		buero.setAusgang("north", garten);
		
		garten.setAusgang("south", buero);
		garten.setAusgang("west", wc);
		
		wc.setAusgang("south", saal);
		
		ersteEtage.setAusgang("east", saal);
		ersteEtage.setAusgang("west", balkon);
		ersteEtage.setAusgang("north", schlafzimmer);
		ersteEtage.setAusgang("south", kinderzimmer);
		
		balkon.setAusgang("east", ersteEtage);
		
		schlafzimmer.setAusgang("south", ersteEtage);
		
		kinderzimmer.setAusgang("north", ersteEtage);
		
		aktuellerRaum = eingang;
	}
	/**
	 * Hauptmethode über die das spiel gestartet wird.
	 */
	public void spielen()
	{
		willkommensTextAusgeben();
		schwierigKeitAussuchen();
        while(!beendet) 
		{
			Befehl befehl = parser.getBefehl();
			beendet = checkBefehl(befehl);
        }
        System.out.println("Danke fuer dieses Spiel. Auf Wiedersehen.\n");	
		timer.stopTimer();
	}
	/**
	 * Methode für die Willkommensnachricht
	 */
	private void willkommensTextAusgeben(){
		System.out.printf("Willkommen bei DefinitelyNotBomberMan!\n"
				+ "Sie gehoeren zu einem Bombenentschaerfungsteam und muessen die Bombe finden.\n"
				+ "Sie haben dafuer %d Sekunden zeit andererseits werdet ihr alle sterben.\n"
				+ aktuellerRaum.getBeschreibung(),SEKUNDEN);
		showMap();
	}
	/**
	 * Methode zur Überprüfung der Eingaben
	 * @param befehl Befehl übergeben
	 * @return true oder false zurückgeben, je nachdem ob der Befehl existiert
	 */
	private boolean checkBefehl(Befehl befehl)
	{
		boolean exit = false;
		if(befehl.istUnbekannt())
		{
			System.out.printf("Was ihr wollen?\n");
			return false;
		}
		String erstesWort = befehl.getErstesWort();
		switch (erstesWort) {
			case "help":
				hilfeAusgeben();
				break;
			case "go":
				wechsleRaum(befehl);
				break;
			case "quit":
				exit  = exit(befehl);
				break;
			case "showMap":
				showMap();
				break;
			case "liesNotiz":
				liesNotiz();
				break;
			case "sucheBombe":
				sucheBombe();
				break;
			case "zeit":
				getTime();
				break;
			case "status":
				spieler.getStatus();
				break;
			default:
				break;
		}
		return exit;
	}
	/**
	 * Methode zur Ausgabe der Hilfe
	 */
	private void hilfeAusgeben()
	{
		System.out.printf("Sie muessen die Bombe finden und entschaerfen!\n");
		parser.befehleAusgeben();
	}
	/**
	 * Methode für den Raumwechsel
	 * @param befehl Befehl wird übergeben.
	 */
	private void wechsleRaum(Befehl befehl)
	{
		if(firstMove)
		{
			START = (System.currentTimeMillis()+SEKUNDEN*1000);
			timer.countDown(START);
			firstMove = false;
		}
		if(!befehl.hatZweitesWort())
		{
			System.out.printf("Ich kann hier nicht lang!\n");
			return;
		}
		String richtung = befehl.getZweitesWort();
		Raum nxtRaum = aktuellerRaum.getAusgang(richtung);
		if(nxtRaum == null)
			System.out.printf("Schoene Wand!\n");
		else
		{
			aktuellerRaum = nxtRaum;
			System.out.printf(aktuellerRaum.getBeschreibung());
			encounter();
		}
	}
	/**
	 * Methode zur Überprüfung des befehls zur Beendigung
	 * @param befehl befehl wird übergeben
	 * @return true oder false je nachdem, ob der befehl aus einem oder mehreren Zeichenketten besteht
	 */
	private boolean exit(Befehl befehl)
	{
		if(befehl.hatZweitesWort())
		{
			System.out.printf("Willste raus? ein Wort genuegt.\n");
			return false;
		}
		else
		{
			return true;
		}
	}
	/**
	 * Methode zum aufrufen der Karte
	 */
	private void showMap()
	{
		map.createMap(aktuellerRaum);
		if(!alive)
			System.out.printf("Bombe:\t\t%s\nCode:\t\t1234\n",platziere.getBombenOrt().getName());
		if(leicht)
			System.out.printf("Terrorist:\t%s\nPapagei:\t%s\nHund:\t\t%s\n",platziere.getTSpawn().getName(),platziere.getPapagei().getName(),platziere.getHund().getName());
	}
	/**
	 * Methode zum lesen der Notiz
	 */
	private void liesNotiz()
	{
		notify = new Notiz(platziere.getNotizOrt(), aktuellerRaum);
		notify.getNotiz();
	}
	/**
	 * Methode zum überprüfen ob die Bombe sich in dem Raum befindet.
	 */
	private void sucheBombe()
	{
		bomb = new Bombe(platziere.getBombenOrt(), aktuellerRaum);
		bomb.getBombe();
	}
	/**
	 * Methode, die die Restzeit zurückgibt.
	 */
	private void getTime()
	{
		if(timer.getStatus())
			System.out.printf("Noch: %d Sekunden verbleibend.\n",(START - System.currentTimeMillis())/1000);
		else
			System.out.printf("Timer startet nach der ersten Bewegung.\n");
	}
	/**
	 * Methode zum antreffen des Terroristen
	 */
	private void encounter()
	{
		papagei.setAktuellerRaum(aktuellerRaum);
		hund.setAktuellerRaum(aktuellerRaum);
		terrorist.setAktuellerRaum(aktuellerRaum);
		Kampf kampf = new Kampf(terrorist,spieler);
		if(terrorist.pruefeTerrorist() && terrorist.getLeben() > 0)
		{
			System.out.printf("Sie haben einen Terroristen gefunden. Kaempfen Sie um Ihr leben!\n\n");
			kampf.kampfSzenario();
		}
		if(papagei.pruefeTier())
		{
			System.out.printf("In diesem Raum befindet sich ein Papagei, er ruft immer wieder: %s. Vielleicht sollten wir einmal nachsehen was es dort zu finden gibt.\n",platziere.getNotizOrt().getName());
		}
		if(hund.pruefeTier())
		{
			boolean rnd = new Random().nextBoolean();
			System.out.printf("In diesem Raum ist ein Hund! als Sie die Tuer oeffneten rennt er an Ihnen vorbei, als wenn er irgendwo hin wollte.\nSie folgen ihm in einen anderen Raum!\n");
			if(rnd)
			{
				System.out.printf("Der Hund fuehrte Sie zu der Bombe!\nEntschaerfen Sie diese!\n");
				aktuellerRaum = platziere.getBombenOrt();
				platziere.setHund(platziere.getBombenOrt());
				sucheBombe();
			}
			else
			{
				System.out.printf("Der Hund fuehrt Sie in einen Raum in dem sich einer der Terroristen befindet!\nEr greift Ihn an. Als der Terrorist vor Schreck in die Decke schiesst, rennt der Hund weg!\n");
				terrorist.setLeben(50);
				aktuellerRaum = platziere.getTSpawn();
				platziere.setHund(platziere.getTSpawn());
				kampf.kampfSzenario();
			}
		}
		if(terrorist.getLeben() <= 0 )
		{
			alive = false;
			platziere.platziereTerrorist();
			terrorist = new Terrorist(platziere.getTSpawn());
		}
	}
	/**
	* Methode zum einstellen des Schwierigkeitsgrades
	*/
	private void schwierigKeitAussuchen() 
	{
		boolean chosen = false;
		System.out.printf("\nWaehlen sie einen Schwierigkeitsgrad aus:\n1 = leicht (Figuren werden bei 'showMap' angezeigt)\n2 = schwer (Keine Hilfen in 'showMap')\n");
		while(!chosen)
		{
			Scanner sc = new Scanner(System.in);
			String auswahl = sc.next();
			switch(auswahl)
			{
				case "1":
					System.out.printf("Schwierigkeitsgrad: LEICHT\n");
					leicht = true;
					chosen = true;
					break;
				case "2":
					System.out.printf("Schwierigkeitsgrad: SCHWER\n");
					leicht = false;
					chosen = true;
					break;
				default:
					System.out.printf("Gueltige Zahl eingeben! 1 oder 2!\n");
			}
		}
	}
}