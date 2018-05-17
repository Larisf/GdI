/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp03;

import java.util.Random;

/**
 *
 * @author Bambi
 * NoBomberMan ein keines textbasiertes Spiel bei dem man eine Bombe suchen und entschärfen muss.
 */
public class NoBomberMan {
	private Raum aktuellerRaum;
	private Parser parser;
	private Map map = new Map();
	private Notiz notify;
	private Bombe bomb;
	private String bombe;
	private String notiz;
	private boolean beendet = false;
	private static long start  = (System.currentTimeMillis()+(10*1000));
	/**
	 * Konstruktor zum erstellen der Karte und des Parsers für die Eingabe
	 */
	public NoBomberMan()
	{
			karteErzeugen();
			parser = new Parser(start);
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
		String[] orte = {"Erdgeschoss","Keller","Heizungsraum","Garage","Büro","Saal","Weinkeller","Wc","1.Etage","Schlafzimmer","Kinderzimmer","Balkon","Garten"};
		bombe = (orte[new Random().nextInt(orte.length)]);
		notiz = (orte[new Random().nextInt(orte.length)]);
		
		Raum eingang = new Raum("Erdgeschoss");
		Raum keller = new Raum("Keller");
		Raum heizungsraum = new Raum("Heizungsraum");
		Raum garage = new Raum("Garage");
		Raum buero = new Raum("Büro");
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
		
		beendet = false;
        while(!beendet) 
		{
			Befehl befehl = parser.getBefehl();
			beendet = checkBefehl(befehl);;
        }
        System.out.println("Danke für dieses Spiel. Auf Wiedersehen.\n");		
	}
	/**
	 * Methode für die Willkommensnachricht
	 */
	private void willkommensTextAusgeben(){
		System.out.printf("Willkommen bei DefinitelyNotBomberMan!\n"
				+ "Du gehörst zu einem Bombenentschärfungsteam und musst die Bombe finden. Ihr habt dafür 60 Sekunden zeit andererseits werdet ihr alle sterben.\n"
				+ aktuellerRaum.getBeschreibung());
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
		if(erstesWort.equals("help"))
			hilfeAusgeben();
		else if(erstesWort.equals("go"))
			wechsleRaum(befehl);
		else if(erstesWort.equals("quit"))
			exit  = exit(befehl);
		else if(erstesWort.equals("showMap"))
			showMap();
		else if(erstesWort.equals("liesNotiz"))
			liesNotiz();
		else if(erstesWort.equals("sucheBombe"))
			sucheBombe();
		else if(erstesWort.equals("zeit"))
			getTime();
		return exit;
	}
	/**
	 * Methode zur Ausgabe der Hilfe
	 */
	private void hilfeAusgeben()
	{
		System.out.printf("Sie müssen die Bombe finden und entschärfen!\n");
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
			System.out.printf("Willste raus? ein Wort genügt.\n");
			return false;
		}
		else
			return true;
	}
	/**
	 * Methode zum aufrufen der Karte
	 */
	public void showMap()
	{
		map.createMap(aktuellerRaum);
		//System.out.printf("Bombe: %s\nNotiz: %s\n", bombe,notiz);
	}
	/**
	 * Methode zum lesen der Notiz
	 */
	public void liesNotiz()
	{
		notify = new Notiz(notiz, aktuellerRaum);
		notify.getNotiz();
	}
	/**
	 * Methode zum überprüfen ob die Bombe sich in dem Raum befindet.
	 */
	public void sucheBombe()
	{
		bomb = new Bombe(bombe, aktuellerRaum);
		bomb.getBombe();
	}
	/**
	 * Methode, die die Restzeit zurückgibt.
	 */
	public void getTime()
	{
		 System.out.printf("Noch: %d Sekunden verbleibend.\n",(start - System.currentTimeMillis())/1000);
	}
}