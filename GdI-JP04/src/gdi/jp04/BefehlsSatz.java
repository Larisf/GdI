/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp04;

/**
 * Klasse mit den gültigen Befehlen
 * @author Bambi
 */
class BefehlsSatz {
	private static final String befehlsListe[] = {"go","status", "quit", "help", "zeit", "showMap", "sucheBombe", "liesNotiz"};	
	private static final String beschreibungBefehle[] = {"\tgehe in Richtung: [north,east,west,south]",
														 "\tzeige Leben und Munition",
														 "\tbeende das Spiel",
														 "\tHilfe aufrufen",
														 "\tverbleibende Zeit anzeigen.",
														 "Karte anzeigen -> 1 = aktuelle Position",
														 "im aktuellen Raum nach der Bombe suchen.",
														 "im aktuellen Raum nach der Notiz suchen.",};
	 /* Methode zum verarbeiten der eingabe
	 * @param eingabe Eingabe übergeben
	 * @return true or false, je nachdem ob die Eingabe existiert oder nicht
	 */
	public boolean getBefehlsWort(String eingabe)
    {
		for (String befehl : befehlsListe) {
			if (befehl.equals(eingabe)) {
				return true;
			}
		}
        return false;
    }
    /**
	 * Methode zum ausgeben zulässiger Befehle mit kuzer Beschreibung dieser.
	 */
	public void befehleAusgeben() 
    {
        for(int i = 0; i < befehlsListe.length; i++)
            System.out.print(befehlsListe[i] + ":\t" + beschreibungBefehle[i] + "\n");
    }	
}