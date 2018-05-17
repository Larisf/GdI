/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp03;

/**
 * Klasse mit den gültigen Befehlen
 * @author Bambi
 */
class BefehlsSatz {
	private static final String befehlsListe[] = {"go", "quit", "help", "showMap", "sucheBombe", "liesNotiz","zeit"};	
	/**
	 * Methode zum verarbeiten der eingabe
	 * @param eingabe Eingabe übergeben
	 * @return true or false, je nachdem ob die Eingabe existiert oder nicht
	 */
	public boolean getBefehlsWort(String eingabe)
    {
        for(int i = 0; i < befehlsListe.length; i++)
            if(befehlsListe[i].equals(eingabe))
                return true;
        return false;
    }
    /**
	 * Methode zum ausgeben zulässiger Befehle
	 */
	public void befehleAusgeben() 
    {
        for(int i = 0; i < befehlsListe.length; i++)
            System.out.print(befehlsListe[i] + "  ");
        System.out.println();
    }
	
}
