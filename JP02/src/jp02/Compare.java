/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp02;

import java.util.Comparator;

/**
 *
 * @author Bambi
 */
public class Compare implements Comparator<Kontobewegung> {

	/**
	 * Objekte vergleichen und zurückgeben
	 * @param kB1 Objekt 1 Übergeben
	 * @param kB2 Objekt 2 Übergeben
	 * @return Vergleichswert
	 */
	@Override
	public int compare(Kontobewegung kB1, Kontobewegung kB2) {
		return Double.compare(kB1.getTimeInMillis(), kB2.getTimeInMillis());
	}
	
}
