/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp04;

import java.util.Random;

/**
 *
 * @author Bambi
 * NoBomberMan ein keines textbasiertes Spiel bei dem man eine Bombe suchen und entschärfen muss.
 */
public class NoBomberMan {
	private Raum aktuellerRaum, eingang;
	private final Parser parser;
	private final Map map;
	private Notiz notify;
	private Bombe bomb;
	private final Tier papagei, hund;
	private Mensch terrorist;
	private final Platziere platziere = new Platziere();
	private final Mensch spieler;
	private final Timer timer;
	private boolean beendet;
	private static final long SEKUNDEN = 300;
	private static final long START  = (System.currentTimeMillis()+SEKUNDEN*1000);
	private boolean alive;
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
		platziere.platzierePapagei();
		platziere.platziereHund();
		platziere.platziereBombe();
		platziere.platziereNotiz();
		platziere.platziereTerrorist();
		eingang = new Raum("Erdgeschoss");
		Raum keller = new Raum("Keller");
		Raum heizungsraum = new Raum("Heizungsraum");
		Raum garage = new Raum("Garage");
		Raum buero = new Raum("Buero");
		Raum saal = new Raum("Saal");
		Raum weinkeller = new Raum("Weinkeller");
		Raum wc = new Raum("Wc");
		Raum ersteEtage = new Raum("1.Etage");
		Raum schlafzimmer = new Raum("Schlafzimmer");
		Raum kinderzimmer = new Raum("Kinderzimmer");
		Raum balkon = new Raum("Balkon");
		Raum garten = new Raum("Garten");
		
		
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
		
		timer.countDown(START);
		beendet = false;
        while(!beendet) 
		{
			Befehl befehl = parser.getBefehl();
			beendet = checkBefehl(befehl);
        }
        System.out.println("Danke fuer dieses Spiel. Auf Wiedersehen.\n");		
	}
	/**
	 * Methode für die Willkommensnachricht
	 */
	private void willkommensTextAusgeben(){
		System.out.printf("Willkommen bei DefinitelyNotBomberMan!\n"
				+ "Du gehoerst zu einem Bombenentschaerfungsteam und musst die Bombe finden. "
				+ "Ihr habt dafuer %d Sekunden zeit andererseits werdet ihr alle sterben."
				+ "Beeilung! Die zeit laeufts bereits!\n"
				+ aktuellerRaum.getBeschreibung(),SEKUNDEN);
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
		if(!befehl.hatZweitesWort())
		{
			System.out.printf("Ich kann hier nicht lang!\n");
			return;
		}
		String richtung = befehl.getZweitesWort();
		Raum nxtRaum = aktuellerRaum.getAusgang(richtung);
		if(nxtRaum == null)
			System.out.printf("Schöne Wand!\n");
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
			timer.stopTimer();
			return true;
		}
	}
	/**
	 * Methode zum aufrufen der Karte
	 */
	public void showMap()
	{
		map.createMap(aktuellerRaum);
		if(alive == false)
			System.out.printf("Bombe: %s\nCode: 1234\n",platziere.getBombenOrt());
		System.out.printf("T: %s P: %s H: %s\n",platziere.getTSpawn(),platziere.getPapagei(),platziere.getHund());
	}
	/**
	 * Methode zum lesen der Notiz
	 */
	public void liesNotiz()
	{
		notify = new Notiz(platziere.getNotizOrt(), aktuellerRaum);
		notify.getNotiz();
	}
	/**
	 * Methode zum überprüfen ob die Bombe sich in dem Raum befindet.
	 */
	public void sucheBombe()
	{
		bomb = new Bombe(platziere.getBombenOrt(), aktuellerRaum);
		bomb.getBombe();
	}
	/**
	 * Methode, die die Restzeit zurückgibt.
	 */
	public void getTime()
	{
		 System.out.printf("Noch: %d Sekunden verbleibend.\n",(START - System.currentTimeMillis())/1000);
	}

	/**
	 * Methode zum antreffen des Terroristen
	 */
	public void encounter()
	{
		papagei.setAktuellerRaum(aktuellerRaum);
		hund.setAktuellerRaum(aktuellerRaum);
		terrorist.setAktuellerRaum(aktuellerRaum);
		Kampf kampf = new Kampf(terrorist,spieler);
		if(terrorist.pruefeTerrorist() == true && terrorist.getLeben() > 0)
		{
			System.out.printf("Sie haben einen Terroristen gefunden. Kaempfen Sie um Ihr leben!\n\n");
			kampf.kampfSzenario();
		}
		if(papagei.pruefeTier() == true)
		{
			System.out.printf("In diesem Raum befindet sich ein Papagei, er ruft immer wieder: %s. Vielleicht sollten wir einmal nachsehen was es dort zu finden gibt.\n",platziere.getNotizOrt());
		}
		if(hund.pruefeTier() == true)
		{
			int rnd = new Random().nextInt(2);
			System.out.printf("In diesem Raum ist ein Hund! als Sie die Tür öffneten rennt er an Ihnen vorbei, als wenn er irgendwo hin wollte.\n Sie folgen ihm in einen anderen Raum!\n");
			if(rnd == 0)
			{
				System.out.printf("Der Hund führte Sie zu der Bombe!\n entschärfen Sie diese, indem Sie 'sucheBombe' eingeben!\n");
			}
			else
			{
				System.out.printf("Der Hund führt Sie in einen Raum in dem sich einer der Terroristen befindet!\n Er greift Ihn an, wird dabei aber erschossen!\n");
				terrorist.setLeben(50);
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
}