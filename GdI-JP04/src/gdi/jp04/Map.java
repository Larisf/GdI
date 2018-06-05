/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp04;

/**
 * Klasse zum verarbeiten und erstellen der Karte
 * @author Bambi
 */
public class Map {
	private int posi[] = {0,0,0,0,0,0,0,0,0,0,0,0,0};
	private final String[] orte = {"Wc","Garten","Saal","Büro","Erdgeschoss","Garage","Heizungsraum",
							 "Keller","Weinkeller","Schlafzimmer","Balkon","1.Etage","Kinderzimmer"};
	private int kellerID,eingangID,stockID;
	private Raum aktuellerRaum;
	/**
	 * Konstruktor zum erstellen der Karte
	 * @param aktuellerRaum übergabe des aktuellenRaumes um Position zu setzen
	 */
	public void createMap(Raum aktuellerRaum)
	{
		this.aktuellerRaum = aktuellerRaum;
		setPosition();
		drawMap();
	}
	/**
	 * Position auf der Karte setzen
	 */
	private void setPosition()
	{
		posi = new int[13];
		switch(aktuellerRaum.getName())
		{
			case "Wc" :				for(int i =0;i<=posi.length-1;i++)posi[i] = 0;posi[0] = 1;break;
			case "Garten":			for(int i =0;i<=posi.length-1;i++)posi[i] = 0;posi[1] = 1;break;
			case "Saal":			for(int i =0;i<=posi.length-1;i++)posi[i] = 0;posi[2] = 1;kellerID = 0;eingangID = 1;stockID = 0;break;
			case "Büro":			for(int i =0;i<=posi.length-1;i++)posi[i] = 0;posi[3] = 1;break;
			case "Erdgeschoss":		for(int i =0;i<=posi.length-1;i++)posi[i] = 0;posi[4] = 1;kellerID = 0;eingangID = 1;stockID = 0;break;
			case "Garage":			for(int i =0;i<=posi.length-1;i++)posi[i] = 0;posi[5] = 1;break;
			case "Heizungsraum":	for(int i =0;i<=posi.length-1;i++)posi[i] = 0;posi[6] = 1;break;
			case "Keller":			for(int i =0;i<=posi.length-1;i++)posi[i] = 0;posi[7] = 1;kellerID = 1;eingangID = 0;stockID = 0;break;
			case "Weinkeller":		for(int i =0;i<=posi.length-1;i++)posi[i] = 0;posi[8] = 1;break;
			case "Schlafzimmer":	for(int i =0;i<=posi.length-1;i++)posi[i] = 0;posi[9] = 1;break;
			case "Balkon" :			for(int i =0;i<=posi.length-1;i++)posi[i] = 0;posi[10] = 1;break;
			case "1.Etage":			for(int i =0;i<=posi.length-1;i++)posi[i] = 0;posi[11] = 1;kellerID = 0;eingangID = 0;stockID = 1;break;
			case "Kinderzimmer":	for(int i =0;i<=posi.length-1;i++)posi[i] = 0;posi[12] = 1;break;		
		}
	}
	/**
	 * Karte zeichnen
	 */
	private void drawMap()
	{
		if(eingangID == 1)
			System.out.printf("Erdgeschoss:\n=================::%d::%s::=============::%d::%s::\n::%d::%s::===::%d::%s::===========::%d::%s::==\n::%d::%s::====::%d::%s::====::%d::%s::\n", 
							  posi[0],orte[0],posi[1],orte[1],posi[11],orte[11],posi[2],orte[2],posi[3],orte[3],posi[7],orte[7],posi[4],orte[4],posi[5],orte[5]);
		else if(kellerID == 1)
			System.out.printf("Kellergeschoss:\n====::%d::%s::====::%d::%s::====::%d::%s::\n============================================::%d::%s::=\n",
							  posi[6],orte[6],posi[7],orte[7],posi[4],orte[4],posi[8],orte[8]);
		else if(stockID == 1)
			System.out.printf("1.Stock\n=============================::%d::%s::=========\n=========::%d::%s::=======::%d::%s::===::%d::%s::\n=============================::%d::%s::=========\n",
						      posi[9],orte[9], posi[10],orte[10], posi[11],orte[11],posi[2],orte[2],posi[12],orte[12]);
	}	
}